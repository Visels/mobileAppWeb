package com.app.ws.ui.model.response;

import java.util.Date;

public class ErrorMessage {

    private Date d;
    private String message;

    public ErrorMessage(Date d, String message){
        this.d = d;
        this.message = message;
    }
}
