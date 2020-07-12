package meb.s10;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(activationConfig = { //
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/my"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class QueueReceiverBean implements MessageListener {
    static final Logger LOG = LoggerFactory.getLogger(QueueReceiverBean.class);

    public void onMessage(Message message) {
        TextMessage text = (TextMessage) message;
        try {
            LOG.info("Message received: " + text.getText());
        } catch (JMSException je) {
            LOG.error("Can't consume message" + je);
        }
    }
}