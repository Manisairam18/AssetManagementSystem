package AssetManagementSystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Software {
    private String name;
    private Vendor vendor;
    private double costPerDevice;
    private Date expiryDate;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Software(String name, Vendor vendor, double costPerDevice, Date expiryDate) {
        this.name = name;
        this.vendor = vendor;
        this.costPerDevice = costPerDevice;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public double getCostPerDevice() {
        return costPerDevice;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return String.format("Software[name=%s, vendor=%s, costPerDevice=%.2f, expiryDate=%s]",
                             name, vendor.getName(), costPerDevice, DATE_FORMAT.format(expiryDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Software software = (Software) o;
        return Double.compare(software.costPerDevice, costPerDevice) == 0 &&
               Objects.equals(name, software.name) &&
               Objects.equals(vendor, software.vendor) &&
               Objects.equals(expiryDate, software.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vendor, costPerDevice, expiryDate);
    }
}
