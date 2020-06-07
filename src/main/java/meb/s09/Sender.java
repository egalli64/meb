package meb.s09;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/s09/message")
public class Sender extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(SenderClassic.class);

    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext context;

    @Resource(name = "java:/queue/my")
    private Destination destination;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String text = request.getParameter("text");
        if (text == null || text.isEmpty() || text.isBlank()) {
            text = "Empty message";
        }

        LOG.trace("Sending message " + text);

        try {
            TextMessage message = context.createTextMessage(text);
            context.createProducer().send(destination, message);
        } finally {
            response.getWriter().println(String.format("Message %s sent", text));
        }
    }
}