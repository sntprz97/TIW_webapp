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

public class ReceiveMessage {
	
	@Resource (mappedName="tiwconnectionfactory") 
		QueueConnectionFactory factory;
	@Resource (mappedName="tiwqueue")
		Queue queue;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException {
		
		System.out.println("Recibiendo mensajes...");
		
		//Using JMS 1.0
		try {
			//Getting receiver email
			HttpSession sesion = request.getSession();
			String receiverId = sesion.getAttribute("Usuario").toString(); 
			System.out.println("receiver: "+receiverId);
			//Starting...
			Context initialcontext= new InitialContext();
			//Get connection factory object
			ConnectionFactory tiwconnectionfactory=(ConnectionFactory) initialcontext.lookup("tiwconnectionfactory");
			QueueConnectionFactory factory= (QueueConnectionFactory) tiwconnectionfactory;
			//Get destination object
			Queue myQueue = (Queue) initialcontext.lookup("tiwqueue");
			QueueConnection connection = factory.createQueueConnection();
			//Create a session
			QueueSession session = connection.createQueueSession(false,  Session.AUTO_ACKNOWLEDGE);
			//Create a browser to look at messages without removing them
			QueueBrowser browser= session.createBrowser(myQueue);
			//Create a receiver
			QueueReceiver receiver = session.createReceiver(myQueue);
			//Start connection
			connection.start();
			//Create a list of messages
			ArrayList<MessageObj> messages = new ArrayList<>();
			Enumeration queueMessages=browser.getEnumeration();
			MessageObj message = null;
			while (true) {
				System.out.println("MENSAJITOS :"+queueMessages.toString());
				while(queueMessages.hasMoreElements()) {
					Message myMessage=(Message) queueMessages.nextElement();
					System.out.println("RECEPTOR:" +myMessage.getStringProperty("receiver"));
					if (myMessage != null){
						if(myMessage.getStringProperty("receiver").compareTo(receiverId)==0){
							message = new MessageObj();
							TextMessage m= (TextMessage) myMessage;
							message.setText(m.getText());
							message.setReceiverId(myMessage.getStringProperty("receiver"));
							message.setSenderId(myMessage.getStringProperty("sender"));
							messages.add(message);
						}
					} 
				}
				break;
			}
			
			//Close all
			receiver.close();
			session.close();
			connection.close();
			
			//Set the list of messages as an attribute
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("/messages.jsp").forward(request, response);
			
		} catch(javax.jms.JMSException e) {
			
		}
	}
}


