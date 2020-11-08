
package com.crefstech.myremote.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commands {

    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("button")
    @Expose
    private List<Button> button = null;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

}
