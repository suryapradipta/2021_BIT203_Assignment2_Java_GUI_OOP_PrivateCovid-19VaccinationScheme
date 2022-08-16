package pcvs;

import java.util.ArrayList;
import java.io.Serializable;


/**
 * Batch class defines a simple object type that represents a Batch.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */

public class Batch implements Serializable {
    private int batchNo;
    private int quantityAvailable;
    private int quantityAdministered;
    private String expiryDate;
    // ArrayList to hold collection of Vaccination
    private final ArrayList<Vaccination> vaccinations;


    /**
     * Constructor to create object with default attribute value.
     */
    public Batch() {
        this(0, "", 0, 0);
    }

    /**
     * Constructor specifying attribute of Batch objects to create.
     *
     * @param bNo              the batch number of Batch.
     * @param exp              the expiry date of Batch.
     * @param qty_available    the quantity available of Batch.
     * @param qty_administered the quantity administered of Batch.
     */
    public Batch(int bNo, String exp, int qty_available,
                 int qty_administered) {
        // Instantiate a new ArrayList object of vaccinations
        this.vaccinations = new ArrayList<>();
        batchNo = bNo;
        expiryDate = exp;
        quantityAvailable = qty_available;
        quantityAdministered = qty_administered;
    }

    /**
     * Gets the vaccination collection in Batch collection.
     *
     * @return an collection of vaccination.
     */
    public ArrayList<Vaccination> getVaccinations() {
        return vaccinations;
    }

    /**
     * Registers the vaccination to Batch collection.
     *
     * @param vaccination the registered vaccination object.
     */
    public void setVaccinations(Vaccination vaccination) {
        this.vaccinations.add(vaccination);
    }

    /**
     * Returns this batch number of Batch.
     *
     * @return batch number of Batch.
     */
    public int getBatchNo() {
        return batchNo;
    }

    /**
     * Registers the new batch number to display in Batch.
     *
     * @param bNo the String to display.
     */
    public void setBatchNo(int bNo) {
        batchNo = bNo;
    }

    /**
     * Returns this expiry date of Batch.
     *
     * @return a String value of expiry date.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Registers the expiry date to display in Batch.
     *
     * @param exp the String to display.
     */
    public void setExpiryDate(String exp) {
        this.expiryDate = exp;
    }

    /**
     * Returns this quantity available of Batch.
     *
     * @return an int value of quantity available.
     */
    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Registers the quantity available to display in Batch.
     *
     * @param qty_available the int to display.
     */
    public void setQuantityAvailable(int qty_available) {
        quantityAvailable = qty_available;
    }

    /**
     * Returns this quantity administered of Batch.
     *
     * @return an int value of quantity administered.
     */
    public int getQuantityAdministered() {
        return quantityAdministered;
    }

    /**
     * Registers the quantity administered to display in Batch.
     *
     * @param qty_administered the int to display.
     */
    public void setQuantityAdministered(int qty_administered) {
        this.quantityAdministered = qty_administered;
    }

    /**
     * Returns detail information of Batch.
     *
     * @return String detail Batch information.
     */
    @Override
    public String toString() {
        return "Batch information:" +
            "\nBatch number: " + batchNo +
            "\nExpiry date: " + expiryDate +
            "\nQuantity available: " + quantityAvailable + "\n";
    }
}

