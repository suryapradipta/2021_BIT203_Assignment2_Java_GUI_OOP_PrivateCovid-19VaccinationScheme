package table;

import javax.swing.table.AbstractTableModel;
import pcvs.Batch;
import java.util.ArrayList;

/**
 * VaccineBatchTableModel class defined to display
 * this Batch collection to table model.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class VaccineBatchTableModel extends AbstractTableModel {
    private static final String[] batchColHeader = {"Batch Number",
        "Expiry Date", "Quantity Available"};
    private final ArrayList<Batch> batches;

    /**
     * Constructor specifying attribute of table model to create.
     * Allow to enter a title in the table model.
     *
     * @param colHeader the number of column.
     * @param row       the value of row count.
     */
    public VaccineBatchTableModel(String[] colHeader, int row) {
        batches = new ArrayList<>();
    }

    /**
     * Returns Batch in this collection.
     *
     * @return Batch collection.
     */
    public ArrayList<Batch> getBatches() {
        return batches;
    }

    /**
     * Returns the number of row in this table model.
     *
     * @return the num of rows.
     */
    @Override
    public int getRowCount() {
        return batches.size();
    }

    /**
     * Returns the number of column in this table model.
     * Determine how many column to crate and display by default.
     *
     * @return the num of columns.
     */
    @Override
    public int getColumnCount() {
        return batchColHeader.length;
    }

    /**
     * Returns the attribute value of batch
     * at column index and row index.
     *
     * @param rowIdx    the queried row.
     * @param columnIdx the queried column.
     * @return Object value of administrator.
     */
    @Override
    public Object getValueAt(int rowIdx, int columnIdx) {
        Batch bth = getBatches().get(rowIdx);
        return switch (columnIdx) {
            case 0 -> bth.getBatchNo();
            case 1 -> bth.getExpiryDate();
            case 2 -> bth.getQuantityAvailable();
            case 3 -> bth.getQuantityAdministered();
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
        return batchColHeader[clm];
    }

    /**
     * Registers batch object to this collection.
     *
     * @param batch the object value to registered.
     */
    public void add(Batch batch) {
        getBatches().add(batch);
        fireTableDataChanged();
    }

    /**
     * Remove all batch from table.
     */
    public void clear() {
        getBatches().clear();
        fireTableDataChanged();
    }

    /**
     * Returns true if batch in this collection,
     * false if not in this collection.
     *
     * @param batch the object value to compare.
     * @return condition value.
     */
    public boolean contains(Batch batch) {
        return getBatches().contains(batch);
    }
}