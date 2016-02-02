package sep.pcc.rest.model;

import java.util.ArrayList;
import java.util.List;


public class Response {

	private List<String> messages;
	
	private boolean success;
	
	public Response(){
		messages = new ArrayList<String>();
	}

	public List<String> getMessage() {
		return messages;
	}

	public void setMessage(List<String> messages) {
		this.messages = messages;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void addMessage(String message)
	{
		messages.add(message);
	}
	
}
