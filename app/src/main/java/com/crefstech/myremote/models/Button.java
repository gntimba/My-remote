
package com.crefstech.myremote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Button {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("smsCommands")
    @Expose
    private String smsCommands;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSmsCommands() {
        return smsCommands;
    }

    public void setSmsCommands(String smsCommands) {
        this.smsCommands = smsCommands;
    }

}
