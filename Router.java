/**
 * @author Jake Degiovanni
 *
 * This class is a Router object that stores router specific information provided the csv file.
 * It also serves to correctly format the patch message.
 * All data stored in here is valid; validity of data is ensured in RouterPatchCheck class. 
 */
public class Router {
    private String hostname;
    private String ipAddress;
    private Boolean patched;
    private double osVersion; // could be float depending on data and memory requirments
    private String notes;

    public Router(){
    }

    public Router(String hostname,
                  String ipAddress,
                  Boolean patched,
                  double osVersion,
                  String notes) {

        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.patched = patched;
        this.osVersion = osVersion;
        this.notes = notes;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean getPatched() {
        return patched;
    }

    public void setPatched(Boolean patched) {
        this.patched = patched;
    }

    public double getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(double osVersion) {
        this.osVersion = osVersion;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        if (notes == null) {
            return formatPatchMessage();
        } else {
            return formatPatchMessage() + " [" + notes + "]";
        }
    }

    private String formatPatchMessage() {
        return hostname + " (" + ipAddress + "), OS Version " + osVersion;
    }
}