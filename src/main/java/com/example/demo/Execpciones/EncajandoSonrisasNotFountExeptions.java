package com.example.demo.Execpciones;

public class EncajandoSonrisasNotFountExeptions extends RuntimeException{
	
	private ExceptionDetails details;

    public EncajandoSonrisasNotFountExeptions(String message, ExceptionDetails details, Throwable e) {

        super(message,e);
    }

    public EncajandoSonrisasNotFountExeptions(String message, ExceptionDetails details) {

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
