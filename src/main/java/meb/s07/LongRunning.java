package meb.s07;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/s07/longRunning")
public class LongRunning extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = LoggerFactory.getLogger(LongRunning.class);

    @EJB
    LongRunningBean service;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.trace("enter");

        String type = request.getParameter("type");
        if (type == null || type.isBlank()) {
            type = "SYNC";
        }
        String result = null;
        switch (type) {
        case "FF":
            // FF: Fire and Forget
            service.fireAndForget();
            result = "Fired and forgetted";
            break;
        case "FFE":
            // Fire and Forget w/ exception
            service.fireAndForgetEx();
            result = "Fired and forgetted - exception";
            break;
        case "FC":
            // FC: Fire and Check later
            try {
                Future<String> future = service.fireAndCheck();

                LOG.trace("simulating a long job FC");
                Thread.sleep(800);

                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                LOG.error("Fire and check failure", e);
                result = "Something went wrong";
            }
            break;
        case "FCE":
            // FCE: Fire and Check later w/ exception
            try {
                Future<String> future = service.fireAndCheckEx();

                LOG.trace("simulating a long job FCE");
                Thread.sleep(800);
                LOG.trace("Now get the future");
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                LOG.error("Fire and check failure: " + e.getMessage());
                result = "Something went wrong";
            }
            break;
        case "FCC":
            // FC: Fire and Check later but cancel
            try {
                Future<String> future = service.fireAndCheckCancel();

                LOG.trace("simulating a long job FCC");
                Thread.sleep(500);
                LOG.trace("Changed my mind");
                future.cancel(true);

                if (future.isCancelled()) {
                    LOG.info("Don't wait for future");
                    result = "Alternative future";
                } else {
                    LOG.info("Wait for cancelled future");
                    result = future.get();
                }
            } catch (InterruptedException | ExecutionException e) {
                LOG.error("Fire and check failure: " + e.getMessage());
                result = "Something went wrong";
            }
            break;
        default:
            // SYNC: synchronous call
            result = service.synchronous();
            break;
        }

        response.getWriter().println(result);
        LOG.trace("exit");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
