package table;

import javax.swing.table.AbstractTableModel;
import pcvs.Vaccination;
import java.util.ArrayList;

/**
 * VaccinationListTableModel class to display
 * this Vaccination collection to table model.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class VaccinationTableModel extends AbstractTableModel {

    private static final String[] vacColHeader = {"Vaccination ID",
        "Appointment Date", "Status", "Remarks"};
    private final ArrayList<Vaccination> vaccinations;

    /**
     * Constructor specifying attribute of table model to create.
     * Allow to enter a title in the table model.
     *
     * @param colHeader the number of column.
     * @param row       the value of row count.
     */
    public VaccinationTableModel(String[] colHeader, int row) {
        vaccinations = new ArrayList<>();
    }

    /**
     * Returns vaccination in this collection.
     *
     * @return vaccination collection.
     */
    public ArrayList<Vaccination> getVaccinations() {
        return vaccinations;
    }

    /**
     * Returns the number of row in this table.
     *
     * @return the num of rows.
     */
    @Override
    public int getRowCount() {
        return vaccinations.size();
    }


    /**
     * Returns the number of column in this table.
     * Determine how many column to crate and display by default.
     *
     * @return the num of columns.
     */
    @Override
    public int getColumnCount() {
        return vacColHeader.length;
    }

    /**
     * Returns the attribute value of Vaccination
     * at column index and row index.
     *
     * @param rowIdx the queried row.
     * @param colIdx the queried column.
     * @return Object value of vaccination.
     */
    @Override
    public Object getValueAt(int rowIdx, int colIdx) {
        Vaccination vacc = getVaccinations().get(rowIdx);
        return switch (colIdx) {
            case 0 -> vacc.getVaccinationID();
            case 1 -> vacc.getAppointmentDate();
            case 2 -> vacc.getStatus();
            case 3 -> vacc.getRemarks();
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
        return vacColHeader[clm];
    }

    /**
     * Registers vaccination object to this collection.
     *
     * @param vaccination the object value to registered.
     */
    public void add(Vaccination vaccination) {
        getVaccinations().add(vaccination);
        fireTableDataChanged();
    }

    /**
     * Remove all vaccination from table.
     */
    public void clear() {
        getVaccinations().clear();
        fireTableDataChanged();
    }

    /**
     * Returns true if vaccination in this collection,
     * false if not in this collection.
     *
     * @param vaccination the object value to compare.
     * @return condition value.
     */
    public boolean contains(Vaccination vaccination) {
        return getVaccinations().contains(vaccination);
    }
}
