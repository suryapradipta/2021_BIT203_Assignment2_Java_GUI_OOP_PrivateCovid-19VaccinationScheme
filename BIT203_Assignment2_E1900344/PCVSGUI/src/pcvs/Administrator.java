package pcvs;

import java.io.Serializable;

/**
 * Administrator concrete subclass of User
 * defines a simple object type that represents a Healthcare Administrator.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */

public class Administrator extends User
    implements Serializable {

    private String staffID;

    /**
     * Constructor default value.
     */
    public Administrator() {
        this("unknown", "unknown", "unknown",
            "unknown", "unknown");
    }

    /**
     * Constructor specifying attribute of Administrator objects to create.
     *
     * @param usrname the value of Administrator is username.
     * @param pwd     the value of Administrator is password.
     * @param eml     the value of Administrator is email.
     * @param fName   the value of Administrator is full name.
     * @param stfID   the value of Administrator is staff id.
     */
    public Administrator(String usrname, String pwd, String eml,
                         String fName, String stfID) {
        super(usrname, pwd, eml, fName);
        staffID = stfID;
    }

    /**
     * Returns this Administrator account is staff id.
     *
     * @return a staff id of Administrator account.
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Registers the new value of staff id to display
     * in Administrator account.
     *
     * @param stfID the String to display.
     */
    public void setStaffID(String stfID) {
        this.staffID = stfID;
    }

    /**
     * Returns detail information of the Administrator Object.
     *
     * @return String detail Administrator information.
     */
    @Override
    public String toString() {
        // call to string of User.
        return super.toString() +
            "\nStaff ID: " + staffID;
    }
}
