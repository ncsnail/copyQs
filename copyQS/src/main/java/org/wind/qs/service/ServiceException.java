package org.wind.qs.service;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1874255603935306165L;

	public ServiceException(){
		super();
	}
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(Throwable cause){
		super(cause);
	}
	
	public ServiceException(String message,Throwable cause){
		super(message,cause);
	}
}
