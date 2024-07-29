package AssetManagementSystem;

import java.util.Objects;

public class Vendor {
    private String vendorId;
    private String name;

    public Vendor(String vendorId, String name) {
        this.vendorId = vendorId;
        this.name = name;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Vendor[vendorId=%s, name=%s]", vendorId, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(vendorId, vendor.vendorId) &&
               Objects.equals(name, vendor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorId, name);
    }
}
