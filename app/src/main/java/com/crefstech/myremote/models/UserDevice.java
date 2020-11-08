
package com.crefstech.myremote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDevice {

    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("user_ID")
    @Expose
    private String userID;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("customName")
    @Expose
    private String customName;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

}
