package table;


import javax.swing.table.AbstractTableModel;
import pcvs.Patient;
import java.util.ArrayList;

/**
 * PatientTableModel class to display
 * this Patient collection to table model.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class PatientTableModel extends AbstractTableModel {

    private static final String[] patientCol_header = {"Username",
        "Password", "Email", "Full Name", "IC or Passport"};
    private final ArrayList<Patient> patients;

    /**
     * Constructor specifying attribute of table model to create.
     * Allow to enter a title in the table model.
     *
     * @param colHeader the number of column.
     * @param row       the value of row count.
     */
    public PatientTableModel(String[] colHeader, int row) {
        patients = new ArrayList<>();
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    /**
     * Returns the number of row in this table.
     *
     * @return the num of rows.
     */
    @Override
    public int getRowCount() {
        return patients.size();
    }

    /**
     * Returns the number of column in this table.
     * Determine how many column to crate and display by default.
     *
     * @return the num of columns.
     */
    @Override
    public int getColumnCount() {
        return patientCol_header.length;
    }

    /**
     * Returns the attribute value of administrator
     * at column index and row index.
     *
     * @param rowIndex    the queried row.
     * @param columnIndex the queried column.
     * @return Object value of administrator.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient pt = getPatients().get(rowIndex);
        return switch (columnIndex) {
            case 0 -> pt.getUsername();
            case 1 -> pt.getPassword();
            case 2 -> pt.getEmail();
            case 3 -> pt.getFullName();
            case 4 -> pt.getICPassport();
            default -> "";
        };
    }

    /**
     * Returns the column header using spreadsheet conventions.
     *
     * @param clm the queried column.
     * @return a string of column header.
     */
    @Override
    public String getColumnName(int clm) {
        return patientCol_header[clm];
    }

    /**
     * Registers patient object to this collection.
     *
     * @param patient the object value to registered.
     */
    public void add(Patient patient) {
        getPatients().add(patient);
        fireTableDataChanged();
    }

    /**
     * Return administrator object based on row index.
     *
     * @param rowIdx the row value of administrator.
     * @return the administrator object.
     */
    public Patient getPatient(int rowIdx) {
        return getPatients().get(rowIdx);
    }

    /**
     * Remove administrator object in table model
     * based on rowIndex index.
     *
     * @param rowIdx the row value of to remove.
     */
    public void remove(int rowIdx) {
        getPatients().remove(rowIdx);
        fireTableDataChanged();
    }

    /**
     * Remove all patient from table.
     */
    public void clear() {
        getPatients().clear();
        fireTableDataChanged();
    }

    /**
     * Returns true if patient in this collection,
     * false if not in this collection.
     *
     * @param patient the object value to compare.
     * @return condition value.
     */
    public boolean contains(Patient patient) {
        return getPatients().contains(patient);
    }
}
