package messageJMS;

public class MessageObj {
	
	private String senderId;
	private String receiverId;
	private String text;
	
	//Empty constructor
	public MessageObj() {
		
	}
	
	//Getters & Setters
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
