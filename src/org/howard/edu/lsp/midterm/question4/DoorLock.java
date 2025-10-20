package org.howard.edu.lsp.midterm.question4;

/**
 * DoorLock is a networked, battery-powered device for campus security.
 */
public class DoorLock extends Device implements Networked, BatteryPowered {
    
    private int batteryPercent;
    
    /**
     * Constructor for DoorLock
     * @param id unique device identifier
     * @param location physical location on campus
     * @param initialBattery initial battery percentage (0-100)
     */
    public DoorLock(String id, String location, int initialBattery) {
        super(id, location);
        setBatteryPercent(initialBattery);
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
    
    // BatteryPowered implementation
    @Override
    public int getBatteryPercent() {
        return batteryPercent;
    }
    
    @Override
    public void setBatteryPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("battery 0..100");
        }
        this.batteryPercent = percent;
    }
    
    // Status reporting
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "DoorLock[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}
