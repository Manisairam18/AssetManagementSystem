package AssetManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
    private String employeeId;
    private String name;
    private List<Device> devices;

    public Employee(String employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
        this.devices = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public List<Device> getDevices() {
        return new ArrayList<>(devices); // Return a copy to protect internal list
    }

    public void addDevice(Device device) {
        if (device != null) {
            devices.add(device);
        }
    }

    @Override
    public String toString() {
        return String.format("Employee[employeeId=%s, name=%s, devicesCount=%d]",
                             employeeId, name, devices.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) &&
               Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name);
    }
}
