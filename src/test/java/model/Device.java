package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created by swapnil on 25/05/17.
 */
public class Device {
    @JsonProperty("applicationName")
    private String applicationName;
    @JsonProperty("androidVersion")
    private String androidVersion;

    public String getApplicationName() {
        return applicationName;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }
}
