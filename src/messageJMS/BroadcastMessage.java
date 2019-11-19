package messageJMS;

import javax.naming.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.annotation.Resource;
import javax.jms.*;

public class BroadcastMessage {
	
	@Resource (mappedName="tiwconnectionfactory") 
		TopicConnectionFactory factory;
	@Resource (mappedName="tiwtopic")
		Topic topic;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException {
				
		//Using JMS 1.0
		try {
			//Getting receiver email
			HttpSession sesion = request.getSession();
			String senderId = sesion.getAttribute("Usuario").toString(); 
			//Starting...
			Context initialcontext= new InitialContext();
			//Get connection factory object
			TopicConnectionFactory tiwconnectionfactory=(TopicConnectionFactory) initialcontext.lookup("tiwconnectionfactory");
			TopicConnectionFactory factory= (TopicConnectionFactory) tiwconnectionfactory;
			//Get destination object
			Topic myTopic = (Topic) initialcontext.lookup("tiwtopic");
			TopicConnection connection = (TopicConnection) factory.createConnection();
			//Start connection
			connection.start();
			//Create a session
			TopicSession session = connection.createTopicSession(false,  Session.AUTO_ACKNOWLEDGE);
			//Create a publisher
			TopicPublisher publisher= session.createPublisher(myTopic);
			//Create message
			TextMessage message= session.createTextMessage();
			message.setText((String) request.getParameter("message"));
			System.out.println(message.getText());
			//Set message properties
			message.setStringProperty("sender", senderId);
			//Send message
			publisher.publish(message);
			//check if the message has reached its destination
			System.out.println(publisher.getDestination());
			//Close all
			publisher.close();
			session.close();
			connection.close();
			
		} catch(javax.jms.JMSException e) {
			
		}
		
		System.out.println("Mensaje Publicado");
		request.getRequestDispatcher("/messages.jsp").forward(request, response);
	}

}
