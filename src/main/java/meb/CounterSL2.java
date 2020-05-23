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

import meb.bl.CounterSL;

@WebServlet("/counterSL2")
public class CounterSL2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(CounterSL2.class);

	@EJB
	CounterSL service;

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
