package com.example.demo.EncajandoSonrisasExceptions;

public class ExceptionDetails {

    private String userMenssage;
    private String severity;

    public ExceptionDetails(String userMenssage, String severity){
        
        super();
        this.userMenssage = userMenssage;
        this.severity = severity;
    }

    public String getUserMenssage(){

        return this.userMenssage;
    } 

    public String getSeverity(){

        return this.severity;
    } 

    public void setSeverity(String severity){

        this.severity = severity;
    } 

    public void setUserMenssage(String userMenssage){

        this.userMenssage = userMenssage;
    } 
}
