package pcvs;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * HealthcareCentre class defines a simple object type
 * that represents a HealthcareCentre.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class HealthcareCentre implements Serializable {
    // ArrayList to hold collection of Administrator and Batch
    private final ArrayList<Administrator> administrators;
    private final ArrayList<Batch> batches;
    private String centreName;
    private String address;

    /**
     * Constructor specifying attribute of Healthcare Centre
     * objects to create.
     *
     * @param cent_name   the Healthcare Centre is name.
     * @param cent_addres the Healthcare Centre is address.
     */
    public HealthcareCentre(String cent_name, String cent_addres) {
        centreName = cent_name;
        address = cent_addres;
        // Instantiate a new ArrayList object of administrators and batches
        this.administrators = new ArrayList<>();
        this.batches = new ArrayList<>();
    }

    /**
     * Returns this Administrator collection.
     *
     * @return this collection of Administrator.
     */
    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    /**
     * Registers Administrator object to HealthcareCentre collection.
     *
     * @param admin registered administrator object.
     */
    public void setAdministrator(Administrator admin) {
        this.administrators.add(admin);
    }

    /**
     * Returns this Batch collection.
     *
     * @return this Batch collection.
     */
    public ArrayList<Batch> getBatches() {
        return batches;
    }

    /**
     * Registers Batch object to Healthcare Collection.
     *
     * @param bacth registered Batch object.
     */
    public void setBatches(Batch bacth) {
        this.batches.add(bacth);
    }

    /**
     * Gets this centre name of Healthcare Centre.
     *
     * @return the Healthcare Centre is name.
     */
    public String getCentreName() {
        return centreName;
    }

    /**
     * Registers the new centre name to display in Healthcare Centre.
     *
     * @param cent_name the String to display.
     */
    public void setCentreName(String cent_name) {
        centreName = cent_name;
    }

    /**
     * Gets the Healthcare Centre is address.
     *
     * @return Healthcare Centre is address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Registers the Healthcare Centre is address to display.
     *
     * @param cent_address the String to display.
     */
    public void setAddress(String cent_address) {
        this.address = cent_address;
    }

    /**
     * Returns Healthcare Centre is detail information.
     *
     * @return the String detail Healthcare Centre information.
     */
    @Override
    public String toString() {
        return centreName + " is located at " + address;
    }
}

