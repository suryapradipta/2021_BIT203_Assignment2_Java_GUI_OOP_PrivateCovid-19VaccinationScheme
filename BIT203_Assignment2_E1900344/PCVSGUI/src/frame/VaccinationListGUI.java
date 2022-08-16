package frame;

import pcvs.PCVS;
import table.VaccinationListTableModel;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.Objects;
import java.io.File;
import java.util.stream.IntStream;

import static java.awt.Color.PINK;
import static java.awt.Color.WHITE;
import static java.awt.FlowLayout.*;
import static java.awt.Font.PLAIN;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingConstants.CENTER;

/**
 * VaccinationListGUI class to allow pcvs application
 * to display list of vaccination by selected batch number.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class VaccinationListGUI extends JFrame {

    private final VaccinationListTableModel vaccinationTableModel;
    private JTable vaccinationInfo_table;
    private PCVS pcvs;
    private File file = null;
    private int iAdmin;
    private int batchNum;
    private String vaccintionID;

    /**
     * Constructor to create the frame.
     */
    public VaccinationListGUI() {
        addWindowListener(new WindowAdapter() {
            /**
             * Invoke when JFrame is opened.
             * This method display the list of vaccination
             * by selected batch number.
             * @param e the event to process.
             */
            @Override
            public void windowOpened(WindowEvent e) {
                // Traverse vaccine collection.
                pcvs.getPCVSVaccines().forEach(vaccine -> {
                    int bound = vaccine.getBatches().size();
                    IntStream.range(0, bound).filter(j ->
                        vaccine.getBatches().get(j)
                            // Equals batch number.
                            .getBatchNo() == batchNum).mapToObj(j ->
                        vaccine.getBatches().get(j)).forEach(batch -> {
                        int bound1 = batch
                            .getVaccinations().size();
                        // Add pending and confirmed vaccination
                        // to table
                        IntStream.range(0, bound1).filter(k ->
                            Objects.equals(batch.getVaccinations()
                            .get(k).getStatus(), "pending") ||
                            Objects.equals(batch.getVaccinations()
                                .get(k).getStatus(), "confirmed"))
                            .filter(k -> !vaccinationTableModel.contains(
                            batch.getVaccinations().get(k)))
                            .forEach(k -> vaccinationTableModel.add(
                            batch.getVaccinations().get(k)));
                    });
                });
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);
        JPanel content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pnl);
        content_pnl.setLayout(null);

        pcvs = new PCVS();

        JPanel sideBarAdminMenu_pnl = new JPanel();
        sideBarAdminMenu_pnl.setLayout(null);
        sideBarAdminMenu_pnl.setBackground(new Color(40, 143, 148));
        sideBarAdminMenu_pnl.setBounds(0, 0, 300, 572);
        content_pnl.add(sideBarAdminMenu_pnl);

        JPanel vacBatch_pnl = new JPanel();
        vacBatch_pnl.setLayout(null);
        vacBatch_pnl.setBackground(new Color(79, 189, 194));
        vacBatch_pnl.setBounds(0, 195, 300, 40);
        sideBarAdminMenu_pnl.add(vacBatch_pnl);

        JLabel vacBatch_lbl = new JLabel("View Vaccine Batch");
        vacBatch_lbl.setForeground(WHITE);
        vacBatch_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        vacBatch_lbl.setBounds(36, 6, 258, 28);
        vacBatch_pnl.add(vacBatch_lbl);

        JPanel vacBatchSel_pnl = new JPanel();
        vacBatchSel_pnl.setBackground(new Color(253, 210, 155));
        vacBatchSel_pnl.setBounds(0, 0, 5, 40);
        vacBatch_pnl.add(vacBatchSel_pnl);

        JPanel vaccinationInfo_pnl = new JPanel();
        vaccinationInfo_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(vaccinationInfo_pnl);
        vaccinationInfo_pnl.setLayout(null);

        JPanel vaccinationInfoTitle_pnl = new JPanel();
        vaccinationInfoTitle_pnl.setLayout(null);
        vaccinationInfoTitle_pnl.setBackground(PINK);
        vaccinationInfoTitle_pnl.setBounds(0, 0, 700, 70);
        vaccinationInfo_pnl.add(vaccinationInfoTitle_pnl);

        JLabel vaccinationInfo_lbl =
            new JLabel("List Of Vaccination Information");
        vaccinationInfo_lbl.setHorizontalAlignment(CENTER);
        vaccinationInfo_lbl.setForeground(WHITE);
        vaccinationInfo_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro",
                PLAIN, 20));
        vaccinationInfo_lbl.setBounds(6, 6, 688, 58);
        vaccinationInfoTitle_pnl.add(vaccinationInfo_lbl);

        JPanel vaccinationInfoList_pnl = new JPanel();
        vaccinationInfoList_pnl.setLayout(null);
        vaccinationInfoList_pnl.setBounds(0, 69, 700, 465);
        vaccinationInfo_pnl.add(vaccinationInfoList_pnl);

        JScrollPane vaccinationInfo_scrollPane =
            new JScrollPane(vaccinationInfo_table);
        vaccinationInfo_scrollPane.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        vaccinationInfo_scrollPane.setBorder(null);
        vaccinationInfo_scrollPane.setBounds(0, 0, 700, 465);
        vaccinationInfoList_pnl.add(vaccinationInfo_scrollPane);

        String[] colHeaderVaccination = {"Vaccination ID",
            "Appointment Date", "Status"};
        vaccinationTableModel =
            new VaccinationListTableModel(colHeaderVaccination, 0);

        vaccinationInfo_table = new JTable(vaccinationTableModel);
        vaccinationInfo_table.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 12));
        vaccinationInfo_table.setBorder(null);
        vaccinationInfo_scrollPane.setViewportView(vaccinationInfo_table);

        JPanel vaccinationInfoBtn_panel = new JPanel();
        vaccinationInfoBtn_panel.setBackground(PINK);
        vaccinationInfoBtn_panel.setBounds(0, 533, 700, 39);
        vaccinationInfo_pnl.add(vaccinationInfoBtn_panel);
        vaccinationInfoBtn_panel.setLayout(
            new FlowLayout(RIGHT));

        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 13));
        back_btn.addActionListener(e -> {
            VaccineBatchGUI vaccineBatch = new VaccineBatchGUI();
            vaccineBatch.pack();
            vaccineBatch.setBounds(0, 0, 1000, 600);
            vaccineBatch.setLocationRelativeTo(VaccinationListGUI.this);

            vaccineBatch.setPCVS(pcvs);
            vaccineBatch.setFile(file);
            vaccineBatch.setIndexAdmin(iAdmin);

            vaccineBatch.setVisible(true);
            setVisible(false);
        });
        vaccinationInfoBtn_panel.add(back_btn);

        JButton sel_btn = new JButton("Select");
        sel_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 13));
        sel_btn.addActionListener(e -> {
            int selRow = vaccinationInfo_table.getSelectedRow();
            if (selRow != -1) {
                Object vaccinationIDObj =
                    vaccinationInfo_table.getValueAt(selRow, 0);
                vaccintionID = (String) vaccinationIDObj;

                AppointmentInformationGUI appointment =
                    new AppointmentInformationGUI();
                appointment.pack();
                appointment.setBounds(0, 0, 1000, 600);
                appointment.setLocationRelativeTo(VaccinationListGUI.this);

                appointment.setPCVS(pcvs);
                appointment.setFile(file);
                appointment.setVaccinationID(vaccintionID);
                appointment.setIndexAdmin(iAdmin);
                appointment.setBatchNumber(batchNum);

                appointment.setVisible(true);
                setVisible(false);
            } else {
                showMessageDialog(VaccinationListGUI.this,
                    "Please select the vaccination",
                    null, WARNING_MESSAGE);
            }
        });
        vaccinationInfoBtn_panel.add(sel_btn);
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
     * Registers this batch number to display appointment
     * information in another JFrame.
     *
     * @param num batch number to save.
     */
    public void setBatchNumber(int num) {
        batchNum = num;
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
