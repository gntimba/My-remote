
package com.crefstech.myremote.models;


import java.io.Serializable;

import lombok.Data;

@Data
public class MainDevice implements Serializable {
    Device device;
    String phone;
    public MainDevice(){
    }
}
