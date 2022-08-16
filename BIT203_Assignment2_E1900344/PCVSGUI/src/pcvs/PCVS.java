package pcvs;

import java.util.Arrays;
import java.util.Calendar;
import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;

import static java.util.stream.IntStream.range;

/**
 * The PCVS controller class defines a database
 * to control the Private Covid-19 Vaccination Scheme
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class PCVS implements Serializable {
    // ArrayList to hold collection of HealthcareCentre, User, and Vaccine
    private final ArrayList<HealthcareCentre> PCVSHealthcareCentres;
    private final ArrayList<User> PCVSUsers;
    private final ArrayList<Vaccine> PCVSVaccines;


    public PCVS() {
        // Instantiate a new ArrayList object
        PCVSUsers = new ArrayList<>();
        PCVSVaccines = new ArrayList<>();
        PCVSHealthcareCentres = new ArrayList<>();

        // Add two valid hard-coded Healthcare Centre values
        PCVSHealthcareCentres.add(new HealthcareCentre(
            "Balimed Hospital",
            "Jl. Mahendradatta No.57 X"));
        PCVSHealthcareCentres.add(new HealthcareCentre(
            "Prima Medika Hospital",
            "Jl. Raya Sesetan No.10"));

        // Add two valid hard-coded Vaccine values
        PCVSVaccines.add(new Vaccine("JNJ",
            "Janssen Pharmaceutical Companies",
            "Johnson & Johnson"));
        PCVSVaccines.add(new Vaccine("ASZ",
            "AstraZeneca, University of Oxford",
            "AstraZeneca"));
    }

    /**
     * Returns array of expiry date of Batch.
     *
     * @param str String to split to array.
     * @return an array value of split String
     */
    public static int[] splitToArray(String str) {
        String[] splitArray = str.split(" ");
        return Arrays.stream(splitArray).mapToInt(Integer::parseInt)
            .toArray();
    }

    /**
     * Returns this User collection.
     *
     * @return collection of User.
     */
    public ArrayList<User> getPCVSUsers() {
        return PCVSUsers;
    }

    /**
     * Returns this Healthcare Centre collection.
     *
     * @return collection of Healthcare Centre.
     */
    public ArrayList<HealthcareCentre> getPCVSHealthcareCentres() {
        return PCVSHealthcareCentres;
    }

    /**
     * Returns this Vaccine collection.
     *
     * @return collection of vaccine.
     */
    public ArrayList<Vaccine> getPCVSVaccines() {
        return PCVSVaccines;
    }

    /**
     * Registers new User to this collection.
     *
     * @param user registered User object.
     */
    public void addUser(User user) {
        PCVSUsers.add(user);
    }

    /**
     * Gets the type of User by comparing the username and password
     * in the User collection.
     * User can be patient or administrator.
     *
     * @param username the value to compare.
     * @param password the value to compare.
     * @return type of User.
     */
    public String getUserType(String username, String password) {
        for (Iterator<User> i =
             PCVSUsers.iterator(); i.hasNext(); ) {
            User pcvsUser = i.next();
            if (pcvsUser.getUsername().equals(username)
                && pcvsUser.getPassword().equals(password)) {
                if (pcvsUser instanceof Patient) {
                    return "patient";
                } else if (pcvsUser instanceof Administrator) {
                    return "admin";
                }
            }
        }
        return null;
    }

    /**
     * Gets User object by comparing the object reference.
     *
     * @param obj the object to compared.
     * @return Patient or Administrator object.
     * null if object is not found.
     */
    public Object getUser(Object obj) {
        for (User pcvsUser : PCVSUsers) {
            if (pcvsUser == obj) {
                return pcvsUser;
            }
        }
        return null;
    }

    /**
     * Returns the index of Healthcare Centre object by comparing
     * the staff ID of Administrator.
     *
     * @param staffID the String to compare.
     * @return index of Healthcare Centre.
     */
    public int getHealthcareCentre(String staffID) {
        for (int k = 0; k < PCVSHealthcareCentres.size(); k++) {
            HealthcareCentre hc = PCVSHealthcareCentres.get(k);
            for (int l = 0; l < hc.getAdministrators().size(); l++) {
                if (hc.getAdministrators().get(l).getStaffID()
                    .equals(staffID))
                    return k;
            }
        }
        return -1;
    }

    /**
     * Returns the Administrator object by comparing staff id.
     *
     * @param staffID the String to compare.
     * @return Administrator object, null if not found.
     */
    public Administrator getAdministrator(String staffID) {
        for (HealthcareCentre hc : PCVSHealthcareCentres) {
            for (int idx = 0; idx < hc.getAdministrators().size(); idx++) {
                if (hc.getAdministrators().get(idx).getStaffID()
                    .equals(staffID))
                    return hc.getAdministrators().get(idx);
            }
        }
        return null;
    }

    /**
     * Returns boolean value by comparing usernames in Users collection.
     *
     * @param usrname the String to compare.
     * @return boolean value true for equal, false for not equal.
     */
    public boolean usernameValidation(String usrname) {
        return PCVSUsers.stream()
            .anyMatch(usr -> usr.getUsername()
                .equalsIgnoreCase(usrname));
    }

    /**
     * Returns index of Healthcare Centre object by comparing
     * the username and password of Administrator to this collection
     *
     * @param username first String to compare.
     * @param password second String to compare.
     * @return an int index of HealthcareCentre object.
     */
    public int validationAdminLogin(String username, String password) {
        for (int i = 0; i < getPCVSHealthcareCentres().size(); i++) {

            // Save healthcare collection.
            HealthcareCentre hc = getPCVSHealthcareCentres().get(i);
            for (int j = 0; j < hc.getAdministrators().size(); j++) {

                // Compare username
                if (hc.getAdministrators().get(j).getUsername()
                    .equals(username))

                    // Compare password
                    if (hc.getAdministrators().get(j).getPassword()
                        .equals(password))

                        // Get index of Healthcare Centre object
                        return i;
            }
        }
        // Cannot find HealthcareCentre object
        return -1;
    }

    /**
     * Returns index Patient by comparing the username and password
     * to allow Patient login to PCVS application.
     *
     * @param username first String to compare.
     * @param password second String to compare.
     * @return an int index of Patient object.
     */
    public int validationPatientLogin(String username, String password) {
        // Traverse the User collection
        for (int i = 0; i < getPCVSUsers().size(); i++) {

            // Downcast User collection to Patient object
            if (getPCVSUsers().get(i) instanceof Patient patient) {

                // Compare username
                if (patient.getUsername().equals(username))

                    // Compare password
                    if (patient.getPassword().equals(password))

                        // Get index of Patient object
                        return i;
            }
        }
        // Cannot find Patient object
        return -1;
    }

    /**
     * Returns index of Healthcare Centre based on Batch object
     * to get Healthcare Centre that offering vaccines.
     *
     * @param batch object to compare.
     * @return an int index of Healthcare Centre.
     */
    public int equalsBatch(Batch batch) {
        return range(0, getPCVSHealthcareCentres().size())
            .filter(i -> range(0, getPCVSHealthcareCentres().get(i)
                .getBatches().size())
                .anyMatch(j -> getPCVSHealthcareCentres().get(i)
                    .getBatches().get(j).equals(batch)))
            .findFirst().orElse(-1);
    }

    /**
     * Returns number of pending of Batch.
     * Sum the pending of all Batch.
     *
     * @param iBatchNo the index batch number to compare.
     * @return an int value of number of pending.
     */
    public int getNumOfPendingByBatch(int iBatchNo) {
        // Traverse the Vaccine collection
        return getPCVSVaccines().stream()
            .mapToInt(tempVC -> range(0, tempVC.getBatches().size())
                // Same batch number
                .filter(j -> tempVC.getBatches().get(j)
                    .getBatchNo() == iBatchNo)
                .mapToObj(j -> tempVC.getBatches().get(j))
                .mapToInt(tempBatch -> (int) range(0, tempBatch
                    .getVaccinations().size())
                    .filter(k -> tempBatch
                        .getVaccinations().get(k)
                        .getStatus().equals("pending"))
                    .count()).sum()).sum();
    }

    /**
     * Returns the number of pending based on Patient collection.
     * Calculate the pending vaccination status.
     *
     * @return an int value of number of pending appointments.
     */
    public int getNumOfPendingByPatient() {
        return range(0, getPCVSUsers().size())
            .filter(i -> getPCVSUsers().get(i) instanceof Patient)
            // Downcast
            .mapToObj(i -> (Patient) getPCVSUsers().get(i))
            // Traverse Vaccination collection in Patient
            .mapToInt(patient -> (int) range(0, patient
                .getVaccinations().size())
                .filter(j -> patient.getVaccinations().get(j)
                    .getStatus().equals("pending"))
                // Increment
                .count()).sum();
    }

    /**
     * Returns the number of quantity administered Batch
     * in this Vaccine collection.
     *
     * @return an int value of number quantity administered.
     */
    public int getQuantityAdministered() {
        // Traverse Vaccine collection
        return getPCVSVaccines()
            .stream()
            // Get Batch in the Vaccine collection
            .mapToInt(tempVC -> tempVC.getBatches().stream()
                // Count quantity administered in Batch
                .mapToInt(Batch::getQuantityAdministered)
                .sum()).sum();
    }

    /**
     * Registers new quantity administered to display in Batch.
     *
     * @param inQty the value to set quantity administered.
     */
    public void setQuantityAdministered(int inQty) {
        // Traverse the Vaccine collection and Batch collection
        getPCVSVaccines().forEach(tempVaccine -> tempVaccine.getBatches()

            // Set quantity administered with inQty value
            .forEach(tempBatch -> tempBatch
                .setQuantityAdministered(inQty)));
    }

    /**
     * Returns boolean value to set the local time and
     * compare the appointment date with expiry date from Batch.
     *
     * @param appointmentDate the String to compare.
     * @return a boolean value true if appointment is come first
     * before expires and vice versa.
     */
    public boolean appointmentDate(Object expiryDate,
                                   String appointmentDate) {
        // Split all Batch expiry date using method splitToArray
        int[] splitExpiryDate = splitToArray((String) expiryDate);

        // Create object expires to get local time from Calendar
        Calendar expires = Calendar.getInstance();

        // Set expires using expiry date from Batches
        expires.set(splitExpiryDate[2], splitExpiryDate[0],
            splitExpiryDate[1]);

        // Split appointment date from user input
        int[] splitAppointmentDate = splitToArray(appointmentDate);

        // Create object appointment to get local time from Calendar
        Calendar appointment = Calendar.getInstance();

        // Set appointment date using array
        // return value from splitToArray method
        appointment.set(splitAppointmentDate[2], splitAppointmentDate[0],
            splitAppointmentDate[1]);

        // Appointment is come first before expires
        return !expires.after(appointment);
    }
}

