package frame;

import pcvs.Batch;
import pcvs.HealthcareCentre;
import pcvs.PCVS;
import pcvs.Vaccine;
import table.VaccineBatchTableModel;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.util.stream.IntStream;

import static java.awt.Color.PINK;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;
import static javax.swing.JOptionPane.*;
import static javax.swing.SwingConstants.CENTER;

/**
 * VaccineBatchGUI class to allow healthcare administrator to
 * list the available batch of vaccines.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class VaccineBatchGUI extends JFrame {

    // Global component used on methods or listeners.
    private final JPanel content_pnl;
    private final VaccineBatchTableModel batchesTableModel;
    private JTable vaccBatchInfo_table;
    // Global attribute used on methods or listeners.
    private PCVS pcvs;
    private File file = null;
    private int iAdmin;
    private int batchNum;

    /**
     * Create the frame.
     */
    public VaccineBatchGUI() {
        addWindowListener(new WindowAdapter() {
            /**
             * Invoke when JFrame is opened.
             * This method display the available
             * batch of vaccine at the healthcare centres.
             * @param e the event to process.
             */
            @Override
            public void windowOpened(WindowEvent e) {
                batchesTableModel.clear();
                HealthcareCentre hc =
                    pcvs.getPCVSHealthcareCentres().get(iAdmin);

                // Traverse vaccines.
                for (int p = 0; p < pcvs.getPCVSVaccines().size(); p++) {
                    Vaccine vaccine = pcvs.getPCVSVaccines().get(p);

                    // Traverse batches in vaccines.
                    for (int r = 0; r < vaccine.getBatches().size(); r++) {
                        Batch batchVacc = vaccine.getBatches().get(r);

                        // Traverse vaccinatons in vaccines.
                        for (int s = 0; s <
                            batchVacc.getVaccinations().size(); s++) {

                            // Traverse batches at healthcare centre.
                            for (int x = 0; x < hc
                                .getBatches().size(); x++) {
                                Batch batchHC = hc.getBatches().get(x);
                                String status = batchVacc.getVaccinations()
                                    .get(s).getStatus();

                                // Same batch.
                                if (batchVacc.equals(batchHC)) {
                                    if (status.equals("pending") ||
                                        status.equals("confirmed")) {

                                        // Avoid duplicate.
                                        if (!batchesTableModel
                                            .contains(batchVacc)) {
                                            batchesTableModel
                                                .add(batchVacc);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);
        content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pnl);
        content_pnl.setLayout(null);

        pcvs = new PCVS();

        JPanel sideBarAdminMenu_pnl = new JPanel();
        sideBarAdminMenu_pnl.setLayout(null);
        sideBarAdminMenu_pnl.setBackground(new Color(40, 143, 148));
        sideBarAdminMenu_pnl.setBounds(0, 0, 300, 572);
        content_pnl.add(sideBarAdminMenu_pnl);

        JPanel vaccBatch_pnl = new JPanel();
        vaccBatch_pnl.setLayout(null);
        vaccBatch_pnl.setBackground(new Color(79, 189, 194));
        vaccBatch_pnl.setBounds(0, 195, 300, 40);
        sideBarAdminMenu_pnl.add(vaccBatch_pnl);

        JLabel vaccBatch_lbl = new JLabel("View Vaccine Batch");
        vaccBatch_lbl.setForeground(WHITE);
        vaccBatch_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        vaccBatch_lbl.setBounds(36, 6, 258, 28);
        vaccBatch_pnl.add(vaccBatch_lbl);

        JPanel vaccBatchSel_pnl = new JPanel();
        vaccBatchSel_pnl.setBounds(0, 0, 5, 40);
        vaccBatch_pnl.add(vaccBatchSel_pnl);
        vaccBatchSel_pnl.setBackground(new Color(253, 210, 155));

        JPanel vaccBatchInfo_pnl = new JPanel();
        vaccBatchInfo_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(vaccBatchInfo_pnl);
        vaccBatchInfo_pnl.setLayout(null);

        JPanel vaccBatchInfoTitle_pnl = new JPanel();
        vaccBatchInfoTitle_pnl.setLayout(null);
        vaccBatchInfoTitle_pnl.setBackground(PINK);
        vaccBatchInfoTitle_pnl.setBounds(0, 0, 700, 70);
        vaccBatchInfo_pnl.add(vaccBatchInfoTitle_pnl);

        JLabel vaccBatchInfo_lbl =
            new JLabel("View Vaccine Batch Information");
        vaccBatchInfo_lbl.setHorizontalAlignment(CENTER);
        vaccBatchInfo_lbl.setForeground(WHITE);
        vaccBatchInfo_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
            PLAIN, 20));
        vaccBatchInfo_lbl.setBounds(6, 6, 688, 58);
        vaccBatchInfoTitle_pnl.add(vaccBatchInfo_lbl);

        JPanel vaccBatchInfoList_pnl = new JPanel();
        vaccBatchInfoList_pnl.setBounds(0, 69, 700, 465);
        vaccBatchInfo_pnl.add(vaccBatchInfoList_pnl);
        vaccBatchInfoList_pnl.setLayout(null);

        JScrollPane vaccBatchInfo_scrollPane =
            new JScrollPane(vaccBatchInfo_table);
        vaccBatchInfo_scrollPane.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        vaccBatchInfo_scrollPane.setBorder(null);
        vaccBatchInfo_scrollPane.setBounds(0, 0, 700, 465);
        vaccBatchInfoList_pnl.add(vaccBatchInfo_scrollPane);

        String[] colHeaderVaccineBatch = {"Name", "Batch Number",
            "Expiry Date", "Quantity Available"};
        batchesTableModel = new VaccineBatchTableModel(
            colHeaderVaccineBatch, 0);

        vaccBatchInfo_table = new JTable(batchesTableModel);
        vaccBatchInfo_table.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 12));
        vaccBatchInfo_table.setBorder(null);
        vaccBatchInfo_scrollPane.setViewportView(vaccBatchInfo_table);

        JPanel vaccBatchInfoBtn_pnl = new JPanel();
        vaccBatchInfoBtn_pnl.setBackground(PINK);
        vaccBatchInfoBtn_pnl.setBounds(0, 533, 700, 39);
        vaccBatchInfo_pnl.add(vaccBatchInfoBtn_pnl);
        vaccBatchInfoBtn_pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton sel_btn = new JButton("Select");
        sel_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 13));
        sel_btn.addActionListener(e -> {
            int selRow = vaccBatchInfo_table.getSelectedRow();
            if (selRow != -1) {
                Object batchNumObject =
                    vaccBatchInfo_table.getValueAt(selRow, 0);
                // Gets this batch number
                batchNum = (Integer) batchNumObject;

                // Find batch based on batch number.
                pcvs.getPCVSVaccines().forEach(vaccine -> IntStream
                    .range(0, vaccine.getBatches().size())
                    .filter(j -> vaccine.getBatches().get(j)
                        .getBatchNo() == batchNum)
                    .mapToObj(j -> vaccine.getBatches().get(j))
                    .forEach(batch ->
                        showMessageDialog(
                            VaccineBatchGUI.this,
                            "Batch expiry date: "
                                + batch.getExpiryDate() +
                                "\nNumber of available: "
                                + batch.getQuantityAvailable() +
                                "\nAdministered vaccinations: "
                                + batch.getQuantityAdministered() +
                                "\nNumber of pending: "
                                + pcvs.getNumOfPendingByBatch(
                                batchNum),
                            null, PLAIN_MESSAGE)));

                VaccinationListGUI vaccinationList =
                    new VaccinationListGUI();
                vaccinationList.pack();
                vaccinationList.setBounds(0, 0, 1000, 600);
                vaccinationList.setLocationRelativeTo(
                    VaccineBatchGUI.this);

                vaccinationList.setPCVS(pcvs);
                vaccinationList.setFile(file);
                vaccinationList.setBatchNumber(batchNum);
                vaccinationList.setIndexAdmin(iAdmin);

                vaccinationList.setVisible(true);
                setVisible(false);
            } else {
                showMessageDialog(
                    VaccineBatchGUI.this,
                    "Please select the batch", null,
                    WARNING_MESSAGE);
            }
        });
        vaccBatchInfoBtn_pnl.add(sel_btn);
    }

    /**
     * Registers this pcvs object to another pcvs attribute
     * in another JFrame.
     * This method will save the current pcvs object
     * to avoid create new data of pcvs.
     *
     * @param pcvsObj the current pcvs object to save.
     */
    public void setPCVS(PCVS pcvsObj) {
        pcvs = pcvsObj;
    }

    /**
     * Registers this file directory to another
     * file attribute in another JFrame.
     * This method will save the current file directory
     * to avoid current file lost.
     *
     * @param directory the directory file to save.
     */
    public void setFile(File directory) {
        file = directory;
    }

    /**
     * Registers this index admin to another
     * index admin attribute in another JFrame
     * to avoid lost value.
     *
     * @param idx the value to save.
     */
    public void setIndexAdmin(int idx) {
        iAdmin = idx;
    }
}
