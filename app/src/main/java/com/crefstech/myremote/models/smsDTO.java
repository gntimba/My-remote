package com.crefstech.myremote.models;


import java.io.Serializable;

import lombok.Data;

@Data
public class smsDTO implements Serializable {
    String command;
    String id;
    String phone;
    String message;
    public smsDTO(String command, String id, String phone) {
        this.command = command;
        this.id = id;
        this.phone = phone;
    }
    public smsDTO(){}


}
