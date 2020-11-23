package com.app.ws.ui.model.response;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field check documentation for required fields"),
    RECORD_ALREADY_EXISTS ("The record already exists"),
    INTERNAL_SERVERERROR("Internal server error"),
    NO_RECORD_FOUND("The id provided has no matching record"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("The record provided could not be updated"),
    COULD_NOT_DELETE_RECORD("The record could not be deleted"),
    EMAIL_ADDRESS_NOT_VERIFIED("The provided email address has not been verified");

    private String errorMessage;


    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String s){
        this.errorMessage = s;
    }
}




