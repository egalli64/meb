package meb;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import meb.bl.CounterSF;

@WebServlet("/counterSF1")
public class CounterSF1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(CounterSF1.class);

	@EJB
	CounterSF service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("counter");
		response.getWriter().println("Counter is: " + service.increase());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
