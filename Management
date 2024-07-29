package AssetManagementSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Management {
    private List<Software> softwares;
    private List<Device> devices;
    private List<Employee> employees;
    private List<Vendor> vendors;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public Management() {
        this.softwares = new ArrayList<>();
        this.devices = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.vendors = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.dateFormat.setLenient(false);
    }

    // Methods to add entities
    private void addSoftware(Software software) {
        softwares.add(software);
    }

    private void addDevice(Device device) {
        devices.add(device);
    }

    private void addEmployee(Employee employee) {
        employees.add(employee);
    }

    private void addVendor(Vendor vendor) {
        vendors.add(vendor);
    }

    // Methods to install software
    private void installSoftwareOnDevice(Device device, Software software, Date installationDate) {
        Installation installation = new Installation(software, installationDate);
        device.addInstallation(installation);
    }

    // Report methods
    private int numberOfInstallations(String softwareName) {
        return (int) devices.stream()
                .flatMap(device -> device.getInstallations().stream())
                .filter(installation -> installation.getSoftware().getName().equals(softwareName))
                .count();
    }

    private int numberOfSoftwareInstalledOnDevice(String deviceId) {
        return devices.stream()
                .filter(device -> device.getDeviceId().equals(deviceId))
                .mapToInt(device -> device.getInstallations().size())
                .findFirst().orElse(0);
    }

    private int numberOfSoftwareInstalledForEmployee(String employeeId) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .flatMap(employee -> employee.getDevices().stream())
                .mapToInt(device -> device.getInstallations().size())
                .sum();
    }

    private double amountSpentOnSoftware(String softwareName) {
        return devices.stream()
                .flatMap(device -> device.getInstallations().stream())
                .filter(installation -> installation.getSoftware().getName().equals(softwareName))
                .mapToDouble(installation -> installation.getSoftware().getCostPerDevice())
                .sum();
    }

    private double amountSpentForEmployee(String employeeId) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .flatMap(employee -> employee.getDevices().stream())
                .flatMap(device -> device.getInstallations().stream())
                .mapToDouble(installation -> installation.getSoftware().getCostPerDevice())
                .sum();
    }

    private int numberOfInstallationsFromVendor(String vendorId) {
        return devices.stream()
                .flatMap(device -> device.getInstallations().stream())
                .filter(installation -> installation.getSoftware().getVendor().getVendorId().equals(vendorId))
                .mapToInt(e -> 1)
                .sum();
    }

    private List<Device> devicesWithExpiredSoftware(Date currentDate) {
        List<Device> expiredDevices = new ArrayList<>();
        for (Device device : devices) {
            if (device.getInstallations().stream()
                .anyMatch(installation -> installation.getSoftware().getExpiryDate().before(currentDate))) {
                expiredDevices.add(device);
            }
        }
        return expiredDevices;
    }

    // Method to get user input and perform actions
    public void run() {
        while (true) {
            System.out.println("\nAsset Management System Menu:");
            System.out.println("1. Add Vendor");
            System.out.println("2. Add Software");
            System.out.println("3. Add Employee");
            System.out.println("4. Add Device");
            System.out.println("5. Install Software on Device");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = getIntInput();

            switch (option) {
                case 1 -> addVendor();
                case 2 -> addSoftware();
                case 3 -> addEmployee();
                case 4 -> addDevice();
                case 5 -> installSoftware();
                case 6 -> generateReport();
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addVendor() {
        String vendorId = getStringInput("Enter Vendor ID: ");
        String name = getStringInput("Enter Vendor Name: ");
        addVendor(new Vendor(vendorId, name));
        System.out.println("Vendor added successfully.");
    }

    private void addSoftware() {
        String name = getStringInput("Enter Software Name: ");
        String vendorId = getStringInput("Enter Vendor ID: ");
        Vendor vendor = findVendorById(vendorId);
        if (vendor == null) {
            System.out.println("Vendor not found. Please add the vendor first.");
            return;
        }
        double costPerDevice = getDoubleInput("Enter Cost Per Device: ");
        Date expiryDate = getDateInput("Enter Expiry Date (yyyy-MM-dd): ");
        if (expiryDate == null) {
            System.out.println("Invalid date format.");
            return;
        }
        addSoftware(new Software(name, vendor, costPerDevice, expiryDate));
        System.out.println("Software added successfully.");
    }

    private void addEmployee() {
        String employeeId = getStringInput("Enter Employee ID: ");
        String name = getStringInput("Enter Employee Name: ");
        addEmployee(new Employee(employeeId, name));
        System.out.println("Employee added successfully.");
    }

    private void addDevice() {
        String deviceId = getStringInput("Enter Device ID: ");
        String employeeId = getStringInput("Enter Employee ID: ");
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found. Please add the employee first.");
            return;
        }
        Device device = new Device(deviceId, employee);
        employee.addDevice(device);
        addDevice(device);
        System.out.println("Device added successfully.");
    }

    private void installSoftware() {
        String deviceId = getStringInput("Enter Device ID: ");
        Device device = findDeviceById(deviceId);
        if (device == null) {
            System.out.println("Device not found. Please add the device first.");
            return;
        }
        String softwareName = getStringInput("Enter Software Name: ");
        Software software = findSoftwareByName(softwareName);
        if (software == null) {
            System.out.println("Software not found. Please add the software first.");
            return;
        }
        Date installationDate = getDateInput("Enter Installation Date (yyyy-MM-dd): ");
        if (installationDate == null) {
            System.out.println("Invalid date format.");
            return;
        }
        installSoftwareOnDevice(device, software, installationDate);
        System.out.println("Software installed successfully on device.");
    }

    private void generateReport() {
        System.out.println("\nReport Menu:");
        System.out.println("1. Number of installations of a particular software");
        System.out.println("2. Number of software installed on a device");
        System.out.println("3. Number of software installed for an employee");
        System.out.println("4. Amount spent on a software");
        System.out.println("5. Amount spent for an employee");
        System.out.println("6. Number of installations from a vendor");
        System.out.println("7. Devices with expired software");
        System.out.print("Choose a report option: ");
        int reportOption = getIntInput();

        switch (reportOption) {
            case 1 -> {
                String softwareName = getStringInput("Enter Software Name: ");
                System.out.println("Number of installations of " + softwareName + ": " + numberOfInstallations(softwareName));
            }
            case 2 -> {
                String deviceId = getStringInput("Enter Device ID: ");
                System.out.println("Number of software installed on device " + deviceId + ": " + numberOfSoftwareInstalledOnDevice(deviceId));
            }
            case 3 -> {
                String employeeId = getStringInput("Enter Employee ID: ");
                System.out.println("Number of software installed for employee " + employeeId + ": " + numberOfSoftwareInstalledForEmployee(employeeId));
            }
            case 4 -> {
                String softwareNameForAmount = getStringInput("Enter Software Name: ");
                System.out.println("Amount spent on " + softwareNameForAmount + ": " + amountSpentOnSoftware(softwareNameForAmount));
            }
            case 5 -> {
                String employeeIdForAmount = getStringInput("Enter Employee ID: ");
                System.out.println("Amount spent for employee " + employeeIdForAmount + ": " + amountSpentForEmployee(employeeIdForAmount));
            }
            case 6 -> {
                String vendorId = getStringInput("Enter Vendor ID: ");
                System.out.println("Number of installations from vendor " + vendorId + ": " + numberOfInstallationsFromVendor(vendorId));
            }
            case 7 -> {
                Date currentDate = getDateInput("Enter Current Date (yyyy-MM-dd): ");
                if (currentDate != null) {
                    List<Device> expiredDevices = devicesWithExpiredSoftware(currentDate);
                    System.out.println("Devices with expired software:");
                    expiredDevices.forEach(device -> System.out.println(device.getDeviceId()));
                }
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private Vendor findVendorById(String vendorId) {
        return vendors.stream()
                .filter(vendor -> vendor.getVendorId().equals(vendorId))
                .findFirst().orElse(null);
    }

    private Software findSoftwareByName(String softwareName) {
        return softwares.stream()
                .filter(software -> software.getName().equals(softwareName))
                .findFirst().orElse(null);
    }

    private Employee findEmployeeById(String employeeId) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst().orElse(null);
    }

    private Device findDeviceById(String deviceId) {
        return devices.stream()
                .filter(device -> device.getDeviceId().equals(deviceId))
                .findFirst().orElse(null);
    }

    private Date getDateInput(String prompt) {
        System.out.print(prompt);
        try {
            return dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            return null;
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // Clear invalid input
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return value;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        Management system = new Management();
        system.run();
    }
}

