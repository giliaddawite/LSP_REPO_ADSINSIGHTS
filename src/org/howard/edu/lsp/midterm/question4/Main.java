package org.howard.edu.lsp.midterm.question4;

/**
 * Main class to demonstrate device polymorphism and interface implementation.
 */
public class Main {
    public static void main(String[] args) {
        // Create devices
        DoorLock lock1 = new DoorLock("DL001", "Building A - Room 101", 85);
        Camera cam1 = new Camera("CAM001", "Building A - Lobby", 92);
        Thermostat thermo1 = new Thermostat("TEMP001", "Building A - Room 101", 22.5);
        
        // Test polymorphism with Device array
        Device[] devices = {lock1, cam1, thermo1};
        
        System.out.println("=== Initial Status ===");
        for (Device device : devices) {
            System.out.println(device.getStatus());
        }
        
        // Test network connectivity
        System.out.println("\n=== Testing Network Connectivity ===");
        for (Device device : devices) {
            if (device instanceof Networked) {
                Networked networked = (Networked) device;
                networked.connect();
                System.out.println(device.getId() + " connected: " + networked.isConnected());
            }
        }
        
        // Test battery operations
        System.out.println("\n=== Testing Battery Operations ===");
        for (Device device : devices) {
            if (device instanceof BatteryPowered) {
                BatteryPowered battery = (BatteryPowered) device;
                System.out.println(device.getId() + " battery: " + battery.getBatteryPercent() + "%");
                battery.setBatteryPercent(75);
                System.out.println(device.getId() + " battery after change: " + battery.getBatteryPercent() + "%");
            }
        }
        
        // Test exception handling for invalid battery
        System.out.println("\n=== Testing Exception Handling ===");
        try {
            lock1.setBatteryPercent(150);
            System.out.println("ERROR: Exception should have been thrown!");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        
        // Final status
        System.out.println("\n=== Final Status ===");
        for (Device device : devices) {
            System.out.println(device.getStatus());
        }
    }
}
