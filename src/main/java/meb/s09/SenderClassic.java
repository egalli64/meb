package meb.s09;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/s09/classic")
public class SenderClassic extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(SenderClassic.class);

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(lookup = "java:/queue/my")
    private Queue queue;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String text = request.getParameter("text");
        if (text == null || text.isBlank()) {
            text = "Empty message";
        }

        boolean check = true;
        try (Connection connection = cf.createConnection(); //
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //
                MessageProducer mp = session.createProducer(queue)) {
            connection.start();
            TextMessage message = session.createTextMessage(text);
            mp.send(message);
        } catch (Exception ex) {
            check = false;
            LOG.error("Can't send message " + text, ex);
        } finally {
            response.getWriter().println(String.format("Send message %s status is %b", text, check));
        }
    }
}