package meite.example.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author gavin
 * @date 2019/1/26 11:05
 */
public class Producer001 {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("gavin");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 1; i <= 5; i++) {
            sendMsg(session, producer, "第" + i + "条消息");
        }
    }

    public static void sendMsg(Session session, MessageProducer producer, String msg) throws JMSException {
        TextMessage textMessage = session.createTextMessage("hello gavin" + msg);
        producer.send(textMessage);
    }
}
