package messageJMS;

import javax.naming.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.annotation.Resource;
import javax.jms.*;

public class ConsumeMessage {
	
	@Resource (mappedName="tiwconnectionfactory") 
		TopicConnectionFactory factory;
	@Resource (mappedName="tiwtopic")
		Topic topic;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException {
		
		System.out.println("Recibiendo mensajes...");
		
		//Using JMS 1.0
		try {
			//Starting...
			Context initialcontext= new InitialContext();
			//Get connection factory object
			TopicConnectionFactory tiwconnectionfactory=(TopicConnectionFactory) initialcontext.lookup("tiwconnectionfactory");
			TopicConnectionFactory factory= (TopicConnectionFactory) tiwconnectionfactory;
			//Get destination object
			Topic myTopic = (Topic) initialcontext.lookup("tiwtopic");
			TopicConnection connection = factory.createTopicConnection();
			//Create a session
			TopicSession session = connection.createTopicSession(false,  Session.AUTO_ACKNOWLEDGE);
			//Create a receiver
			TopicSubscriber subscriber = session.createSubscriber(myTopic);
			//Start connection
			connection.start();
			//Create a list of messages
			ArrayList<MessageObj> messages = new ArrayList<>();
			MessageObj message = null;
			while (true) {
				Message myMessage=(Message) subscriber.receive(100);
				System.out.println("MENSAJE:" +myMessage);
				if (myMessage != null){
					if(myMessage instanceof TextMessage ){
						message = new MessageObj();
						TextMessage m= (TextMessage) myMessage;
						message.setText(m.getText());
						message.setSenderId(myMessage.getStringProperty("sender"));
						messages.add(message);
					}
				} else {
					break;
				}
			}
			
			//Close all
			subscriber.close();
			session.close();
			connection.close();
			
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("/messages.jsp").forward(request, response);
		} catch(javax.jms.JMSException e) {
			
		}
	}
}