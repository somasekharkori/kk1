package com.cc.util;
/**
 * Errors details 
 * @author Nitin Raut
 *
 */
public class ErrorDetails {

	private int status;
	private String message;
	private Class type;
	
	public ErrorDetails(int status,String message, Class type) {
		this.status=status;
		this.message=message;
		this.type=type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}
	
}
