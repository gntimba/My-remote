
package com.crefstech.myremote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Device implements Serializable {


    private String createdAt;

    private String id;

    private String customName;

    private String model;

    private String description;

    private String type;

    private List<Commands> commands = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Device() {
    }

    /**
     *
     * @param createdAt
     * @param description
     * @param customName
     * @param model
     * @param id
     * @param type
     * @param commands
     */
    public Device(String createdAt, String id, String customName, String model, String description, String type, List<Commands> commands) {
        super();
        this.createdAt = createdAt;
        this.id = id;
        this.customName = customName;
        this.model = model;
        this.description = description;
        this.type = type;
        this.commands = commands;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Commands> getCommands() {
        return commands;
    }

    public void setCommands(List<Commands> commands) {
        this.commands = commands;
    }

}
