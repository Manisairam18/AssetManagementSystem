package AssetManagementSystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Installation {
    private Software software;
    private Date installationDate;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Installation(Software software, Date installationDate) {
        this.software = software;
        this.installationDate = installationDate;
    }

    public Software getSoftware() {
        return software;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    @Override
    public String toString() {
        return String.format("Installation[software=%s, installationDate=%s]",
                             software.getName(), DATE_FORMAT.format(installationDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Installation that = (Installation) o;
        return Objects.equals(software, that.software) &&
               Objects.equals(installationDate, that.installationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(software, installationDate);
    }
}
