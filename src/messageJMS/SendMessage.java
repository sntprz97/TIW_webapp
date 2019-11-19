package messageJMS;

import javax.naming.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.annotation.Resource;
import javax.jms.*;

public class SendMessage {
	
	@Resource (mappedName="tiwconnectionfactory") 
		QueueConnectionFactory factory;
	@Resource (mappedName="tiwqueue")
		Queue queue;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException {
		
		String receiverId = request.getParameter("receiverId");
				
		//Using JMS 1.0
		try {
			//Getting receiver email
			HttpSession sesion = request.getSession();
			String senderId = sesion.getAttribute("Usuario").toString(); 
			System.out.println(receiverId);
			//Starting...
			Context initialcontext= new InitialContext();
			//Get connection factory object
			ConnectionFactory tiwconnectionfactory=(ConnectionFactory) initialcontext.lookup("tiwconnectionfactory");
			QueueConnectionFactory factory= (QueueConnectionFactory) tiwconnectionfactory;
			//Get destination object
			Queue myQueue = (Queue) initialcontext.lookup("tiwqueue");
			QueueConnection connection = factory.createQueueConnection();
			//Start connection
			connection.start();
			//Create a session
			QueueSession session = connection.createQueueSession(false,  Session.AUTO_ACKNOWLEDGE);
			//Create a sender
			QueueSender sender= session.createSender(myQueue);
			//Create message
			TextMessage message= session.createTextMessage();
			message.setText((String) request.getParameter("message"));
			//Set message properties
			message.setStringProperty("sender", senderId);
			message.setStringProperty("receiver", receiverId);
			//Send message
			sender.send(message);
			//check if the message has reached its destination
			System.out.println(sender.getDestination());
			//Close all
			sender.close();
			session.close();
			connection.close();
			
		} catch(javax.jms.JMSException e) {
			
		}
		
		System.out.println("Mensaje Enviado");
		request.getRequestDispatcher("/messages.jsp").forward(request, response);
	}

}
