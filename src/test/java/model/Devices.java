package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by swapnil on 25/05/17.
 */
public class Devices {
    @JsonProperty("devices")
    private List<Device> deviceList;

    public List<Device> getDeviceList() {
        return deviceList;
    }
}
