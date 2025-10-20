package org.howard.edu.lsp.midterm.question4;

/**
 * Base class for all campus devices.
 */
public abstract class Device {
    private String id;
    private String location;
    private boolean connected;
    
    /**
     * Constructor for Device
     * @param id unique device identifier
     * @param location physical location on campus
     */
    public Device(String id, String location) {
        this.id = id;
        this.location = location;
        this.connected = false;
    }
    
    /**
     * Get device ID
     * @return device ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Get device location
     * @return device location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Set connection status
     * @param connected true if connected, false otherwise
     */
    protected void setConnected(boolean connected) {
        this.connected = connected;
    }
    
    /**
     * Check if device is connected
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * Get device status - to be overridden by subclasses
     * @return status string
     */
    public abstract String getStatus();
}
