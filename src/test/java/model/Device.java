package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {

    @JsonProperty("applicationName")
    private String applicationName;
    @JsonProperty("androidVersion")
    private String androidVersion;
    @JsonProperty("emulator")
    private Boolean emulator;

    public Device() {
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public Boolean isEmulator() {
        return emulator;
    }
}
