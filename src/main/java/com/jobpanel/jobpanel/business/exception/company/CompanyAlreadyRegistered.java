package com.jobpanel.jobpanel.business.exception.company;

public class CompanyAlreadyRegistered extends RuntimeException{
    public CompanyAlreadyRegistered(String message){
        super(message);
    }
}
