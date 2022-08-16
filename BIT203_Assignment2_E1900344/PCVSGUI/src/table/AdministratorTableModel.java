package table;

import java.util.ArrayList;
import pcvs.Administrator;
import javax.swing.table.AbstractTableModel;

/**
 * AdministratorTableModel class to display
 * this Administrator collection to table model.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class AdministratorTableModel extends AbstractTableModel {

    private static final String[] AdmCol_header = {"Username",
        "Password", "Email", "Full Name", "Staff ID"};
    private final ArrayList<Administrator> administrators;

    /**
     * Constructor specifying attribute of table model to create.
     * Allow to enter a title in the table model.
     *
     * @param colHeader the number of column.
     * @param row       the value of row count.
     */
    public AdministratorTableModel(String[] colHeader, int row) {
        administrators = new ArrayList<>();
    }

    /**
     * Returns Administrator in this collection.
     *
     * @return Administrator collection.
     */
    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    /**
     * Returns the number of row in this table.
     *
     * @return the num of rows.
     */
    @Override
    public int getRowCount() {
        return administrators.size();
    }

    /**
     * Returns the number of column in this table.
     * Determine how many column to crate and display by default.
     *
     * @return the num of columns.
     */
    @Override
    public int getColumnCount() {
        return AdmCol_header.length;
    }

    /**
     * Returns the attribute value of administrator
     * at column index and row index.
     *
     * @param rowIdx    the queried row.
     * @param colIdx the queried column.
     * @return Object value of administrator.
     */
    @Override
    public Object getValueAt(int rowIdx, int colIdx) {
        Administrator adm = getAdministrators().get(rowIdx);
        return switch (colIdx) {
            case 0 -> adm.getUsername();
            case 1 -> adm.getPassword();
            case 2 -> adm.getEmail();
            case 3 -> adm.getFullName();
            case 4 -> adm.getStaffID();
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
        return AdmCol_header[clm];
    }

    /**
     * Registers administrator object to this collection.
     *
     * @param administrator the object value to registered.
     */
    public void add(Administrator administrator) {
        getAdministrators().add(administrator);
        fireTableDataChanged();
    }

    /**
     * Return administrator object based on row index.
     *
     * @param rowIdx the row value of administrator.
     * @return the administrator object.
     */
    public Administrator getAdministrator(int rowIdx) {
        return getAdministrators().get(rowIdx);
    }

    /**
     * Remove administrator object in table model
     * based on rowIndex index.
     *
     * @param rowIndex the row value of to remove.
     */
    public void remove(int rowIndex) {
        getAdministrators().remove(rowIndex);
        fireTableDataChanged();
    }

    /**
     * Remove all administrator from table.
     */
    public void clear() {
        getAdministrators().clear();
        fireTableDataChanged();
    }

    /**
     * Returns true if administrator in this collection,
     * false if not in this collection.
     *
     * @param administrator the object value to compare.
     * @return condition value.
     */
    public boolean contains(Administrator administrator) {
        return getAdministrators().contains(administrator);
    }
}
