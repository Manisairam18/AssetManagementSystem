package AssetManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Device {
    private String deviceId;
    private Employee owner;
    private List<Installation> installations;

    public Device(String deviceId, Employee owner) {
        this.deviceId = deviceId;
        this.owner = owner;
        this.installations = new ArrayList<>();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Employee getOwner() {
        return owner;
    }

    public List<Installation> getInstallations() {
        return new ArrayList<>(installations); // Return a copy to protect internal list
    }

    public void addInstallation(Installation installation) {
        if (installation != null) {
            installations.add(installation);
        }
    }

    @Override
    public String toString() {
        return String.format("Device[deviceId=%s, owner=%s, installationsCount=%d]",
                             deviceId, owner.getName(), installations.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(deviceId, device.deviceId) &&
               Objects.equals(owner, device.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, owner);
    }
}
