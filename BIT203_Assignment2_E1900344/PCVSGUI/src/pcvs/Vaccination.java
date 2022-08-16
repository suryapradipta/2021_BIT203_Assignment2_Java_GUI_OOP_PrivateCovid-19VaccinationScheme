package pcvs;

import java.io.Serializable;

/**
 * Vaccination class defines a simple object type
 * that represents a Vaccination.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */

public class Vaccination implements Serializable {
    private String vaccinationID;
    private String status;
    private String remarks;
    private String appointmentDate;


    /**
     * Constructor to create object with default attribute value.
     */
    public Vaccination() {
        this("unknown", "unknown",
            "unknown", "unknown");
    }

    /**
     * Constructor specifying attribute of Vaccination objects to create.
     *
     * @param vacID the vaccination id of Vaccination.
     * @param dt    the appointment date of Vaccination.
     * @param stts  the status of Vaccination.
     * @param rmks  the remarks of Vaccination.
     */
    public Vaccination(String vacID, String dt,
                       String stts, String rmks) {
        vaccinationID = vacID;
        appointmentDate = dt;
        status = stts;
        remarks = rmks;
    }

    /**
     * Returns this vaccination id of Vaccination object.
     *
     * @return a String value of vaccinationID.
     */
    public String getVaccinationID() {
        return vaccinationID;
    }

    /**
     * Registers the new vaccination id to display in Vaccination.
     *
     * @param vacId the String to display.
     */
    public void setVaccinationID(String vacId) {
        this.vaccinationID = vacId;
    }

    /**
     * Returns this appointment date of Vaccination object.
     *
     * @return this Vaccination is date.
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * Registers the new appointment date to display in Vaccination.
     *
     * @param dt the String to display.
     */
    public void setAppointmentDate(String dt) {
        appointmentDate = dt;
    }

    /**
     * Returns this remarks of Vaccination object.
     *
     * @return this Vaccination is remarks.
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Registers the new remarks to display in Vaccination.
     *
     * @param rmks the String to display.
     */
    public void setRemarks(String rmks) {
        remarks = rmks;
    }

    /**
     * Returns this status of Vaccination object.
     *
     * @return this Vaccination is status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Registers the new status to display in Vaccination.
     *
     * @param stts the value to display.
     */
    public void setStatus(String stts) {
        status = stts;
    }

    /**
     * Returns detail information of the Vaccination Object.
     *
     * @return String detail Vaccination information.
     */
    @Override
    public String toString() {
        return "Vaccination ID: " + vaccinationID +
            "\nAppointment date: " + appointmentDate +
            "\nStatus: " + status + "\n";
    }
}

