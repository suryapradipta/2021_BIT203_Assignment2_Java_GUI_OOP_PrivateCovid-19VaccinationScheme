package pcvs;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Vaccine class defines a simple object type that represents a Vaccine.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class Vaccine implements Serializable {
    private String vaccineID;
    private String manufacturer;
    private String vaccineName;
    // ArrayList to hold collection of Batch
    private final ArrayList<Batch> batches;


    /**
     * Constructor to create object with default attribute value.
     */
    public Vaccine() {
        this("unknown", "unknown", "unknown");
    }

    /**
     * Constructor specifying attribute of Vaccine objects to create.
     *
     * @param vac_id   the value of vaccine is id.
     * @param mnfturer the value of vaccine is manufacturer.
     * @param vac_name the value of vaccine is name.
     */
    public Vaccine(String vac_id, String mnfturer,
                   String vac_name) {
        // Instantiate a new ArrayList object
        // of administrators and batches
        this.batches = new ArrayList<>();
        vaccineID = vac_id;
        manufacturer = mnfturer;
        vaccineName = vac_name;
    }

    /**
     * Returns this Batch collection.
     *
     * @return a Batch collection.
     */
    public ArrayList<Batch> getBatches() {
        return batches;
    }

    /**
     * Registers Batch object to this collectoin.
     *
     * @param batch registered batch object.
     */
    public void setBatch(Batch batch) {
        this.batches.add(batch);
    }

    /**
     * Returns this vaccine id of Vaccine object.
     *
     * @return a String value of vaccineID.
     */
    public String getVaccineID() {
        return vaccineID;
    }

    /**
     * Registers new value of vaccine id
     * to display in vaccine.
     *
     * @param vacID the string to display.
     */
    public void setVaccineID(String vacID) {
        this.vaccineID = vacID;
    }

    /**
     * Returns this Vaccine is manufacturer.
     *
     * @return this manufacturer of Vaccine value.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Registers new manufacturer to display in Vaccine.
     *
     * @param mnfturer the string to display.
     */
    public void setManufacturer(String mnfturer) {
        manufacturer = mnfturer;
    }

    /**
     * Returns this vaccine name of Vaccine object.
     *
     * @return a String value of vaccineName.
     */
    public String getVaccineName() {
        return vaccineName;
    }

    /**
     * Registers new vaccine name to display in Vaccine.
     *
     * @param vac_name the string to display.
     */
    public void setVaccineName(String vac_name) {
        vaccineName = vac_name;
    }

    /**
     * Returns detail information of the Vaccine object.
     *
     * @return String detail Vaccine information.
     */
    @Override
    public String toString() {
        return vaccineName + " vaccine, developed by " + manufacturer;
    }
}


