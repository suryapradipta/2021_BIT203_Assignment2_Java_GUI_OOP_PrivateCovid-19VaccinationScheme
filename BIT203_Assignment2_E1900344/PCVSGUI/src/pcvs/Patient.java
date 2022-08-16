package pcvs;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Patient concrete subclass of User
 * defines a simple object type that represents a Patient.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */

public class Patient extends User implements Serializable {
    // ArrayList to hold collection of Vaccination
    private final ArrayList<Vaccination> vaccinations;
    private int ICPassport;

    /**
     * Constructor default value.
     */
    public Patient() {
        this("unknown", "unknown",
            "unknown", "unknown", 0);
    }

    /**
     * Constructor specifying attribute of Patient objects to create.
     *
     * @param usrname the value of Patient is username.
     * @param pwd     the value of Patient is password.
     * @param eml     the value of Patient is email.
     * @param fName   the value of Patient is full name.
     * @param icp     the value of Patient is ic passport.
     */
    public Patient(String usrname, String pwd, String eml,
                   String fName, int icp) {
        super(usrname, pwd, eml, fName);
        ICPassport = icp;
        // Instantiate a new ArrayList object of vaccinations
        this.vaccinations = new ArrayList<>();
    }

    /**
     * Returns this ic passport of Patient.
     *
     * @return a String value of ICPassport.
     */
    public int getICPassport() {
        return ICPassport;
    }

    /**
     * Registers new IC Passport to display in Patient.
     *
     * @param ICPassport the String to display.
     */
    public void setICPassport(int ICPassport) {
        this.ICPassport = ICPassport;
    }

    /**
     * Returns this Vaccination collection.
     *
     * @return a vaccination collection.
     */
    public ArrayList<Vaccination> getVaccinations() {
        return vaccinations;
    }

    /**
     * Registers the Vaccination object to this collection.
     *
     * @param vc the value to registered.
     */
    public void setVaccinations(Vaccination vc) {
        this.vaccinations.add(vc);
    }

    /**
     * Returns detail information of the Patient Object.
     *
     * @return String detail Patient information.
     */
    @Override
    public String toString() {
        // call to string of User.
        return super.toString() +
            "\nIC Passport: " + ICPassport;
    }
}
