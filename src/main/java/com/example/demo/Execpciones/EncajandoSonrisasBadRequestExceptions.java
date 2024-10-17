package com.example.demo.Execpciones;


public class EncajandoSonrisasBadRequestExceptions extends RuntimeException{
	
	private ExceptionDetails details;

    public EncajandoSonrisasBadRequestExceptions(String message, ExceptionDetails details, Throwable e) {

        super(message,e);
    }

    public EncajandoSonrisasBadRequestExceptions(String message, ExceptionDetails details) {

        super(message);
        setDetails(details);
    }

    public ExceptionDetails getDetails(){

        return this.details;
    }

    public void setDetails(ExceptionDetails details){

        this.details = details;
    }

}
