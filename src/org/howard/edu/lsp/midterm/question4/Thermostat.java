package org.howard.edu.lsp.midterm.question4;

/**
 * Thermostat is a networked device for temperature control.
 */
public class Thermostat extends Device implements Networked {
    
    private double temperatureC;
    
    /**
     * Constructor for Thermostat
     * @param id unique device identifier
     * @param location physical location on campus
     * @param initialTempC initial temperature in Celsius
     */
    public Thermostat(String id, String location, double initialTempC) {
        super(id, location);
        this.temperatureC = initialTempC;
    }
    
    // Accessors
    public double getTemperatureC() {
        return temperatureC;
    }
    
    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
    }
    
    // Networked implementation
    @Override
    public void connect() {
        setConnected(true);
    }
    
    @Override
    public void disconnect() {
        setConnected(false);
    }
    
    @Override
    public boolean isConnected() {
        return super.isConnected();
    }
    
    // Status reporting
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "Thermostat[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", tempC=" + temperatureC + "]";
    }
}
