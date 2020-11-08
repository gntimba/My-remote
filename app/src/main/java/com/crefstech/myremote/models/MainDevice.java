
package com.crefstech.myremote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainDevice {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("picture")
    @Expose
    private Object picture;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("description")
    @Expose
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
