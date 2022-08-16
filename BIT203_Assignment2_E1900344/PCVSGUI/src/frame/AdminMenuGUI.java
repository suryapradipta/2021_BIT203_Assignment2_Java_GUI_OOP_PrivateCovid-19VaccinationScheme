package frame;

import pcvs.Batch;
import pcvs.PCVS;


import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;

import static java.awt.Color.*;
import static java.awt.Font.*;
import static javax.swing.JFrame.*;
import static javax.swing.JOptionPane.*;

/**
 * AdminMenuGUI class to allow Administrator
 * access menu pcvs GUI application.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class AdminMenuGUI extends JFrame {

    private final JTextField batchNum_tf;
    private final JTextField expDate_tf;
    private final JTextField qtyAvailable_tf;
    JRadioButton jnj_rdbtn;
    private PCVS pcvs;
    private File file = null;
    private int iAdmin;
    private Batch batch;


    /**
     * Class Constructor to create
     * admin menu for administrator.
     */
    public AdminMenuGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);
        JPanel content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pnl);
        content_pnl.setLayout(null);

        pcvs = new PCVS();

        JPanel adminMenu_pnl = new JPanel();
        adminMenu_pnl.setBounds(0, 0, 1000, 572);
        content_pnl.add(adminMenu_pnl);
        adminMenu_pnl.setLayout(null);

        JPanel sideBarAdminMenu_pnl = new JPanel();
        sideBarAdminMenu_pnl.setBackground(new Color(40, 143, 148));
        sideBarAdminMenu_pnl.setBounds(0, 0, 300, 572);
        adminMenu_pnl.add(sideBarAdminMenu_pnl);
        sideBarAdminMenu_pnl.setLayout(null);

        JPanel recordBatch_pnl = new JPanel();
        recordBatch_pnl.setLayout(null);
        recordBatch_pnl.setBackground(new Color(79, 189, 194));
        recordBatch_pnl.setBounds(0, 155, 300, 40);
        sideBarAdminMenu_pnl.add(recordBatch_pnl);

        JLabel recordBatch_lbl = new JLabel("Record Vaccine Batch");
        recordBatch_lbl.setForeground(WHITE);
        recordBatch_lbl.setFont(
            new Font(".AppleSystemUIFont", PLAIN, 16));
        recordBatch_lbl.setBounds(36, 6, 258, 28);
        recordBatch_pnl.add(recordBatch_lbl);

        JPanel recordBatchSel_panel = new JPanel();
        recordBatchSel_panel.setBackground(new Color(253, 210, 155));
        recordBatchSel_panel.setBounds(0, 0, 5, 40);
        recordBatch_pnl.add(recordBatchSel_panel);

        JPanel viewBatch_pnl = new JPanel();
        viewBatch_pnl.addMouseListener(
            new PanelMenuButtonMouseAdapter(viewBatch_pnl) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    VaccineBatchGUI vaccineBatch =
                        new VaccineBatchGUI();
                    vaccineBatch.pack();
                    vaccineBatch.setBounds(0, 0, 1000, 600);
                    vaccineBatch
                        .setLocationRelativeTo(AdminMenuGUI.this);
                    vaccineBatch.setPCVS(pcvs);
                    vaccineBatch.setFile(file);
                    vaccineBatch.setIndexAdmin(iAdmin);
                    vaccineBatch.setVisible(true);
                    setVisible(false);

                }
            });
        viewBatch_pnl.setLayout(null);
        viewBatch_pnl.setBackground(new Color(40, 143, 148));
        viewBatch_pnl.setBounds(0, 195, 300, 40);
        sideBarAdminMenu_pnl.add(viewBatch_pnl);

        JLabel viewBatch_lbl = new JLabel("View Vaccine Batch");
        viewBatch_lbl.setForeground(WHITE);
        viewBatch_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        viewBatch_lbl.setBounds(36, 6, 258, 28);
        viewBatch_pnl.add(viewBatch_lbl);

        JPanel adminInfo_pnl = new JPanel();
        adminInfo_pnl.addMouseListener(
            new PanelMenuButtonMouseAdapter(adminInfo_pnl) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    AdministratorInformationGUI adminInf =
                        new AdministratorInformationGUI();
                    adminInf.pack();
                    adminInf.setBounds(0, 0, 1000, 600);
                    adminInf.setLocationRelativeTo(AdminMenuGUI.this);
                    adminInf.setPCVS(pcvs);
                    adminInf.setFile(file);
                    adminInf.setVisible(true);
                    setVisible(false);
                }
            });
        adminInfo_pnl.setLayout(null);
        adminInfo_pnl.setBackground(new Color(40, 143, 148));
        adminInfo_pnl.setBounds(0, 235, 300, 40);
        sideBarAdminMenu_pnl.add(adminInfo_pnl);

        JLabel adminInfo_lbl = new JLabel("Administrator Information");
        adminInfo_lbl.setForeground(WHITE);
        adminInfo_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        adminInfo_lbl.setBounds(36, 6, 258, 28);
        adminInfo_pnl.add(adminInfo_lbl);

        JPanel patientInfo_pnl = new JPanel();
        patientInfo_pnl.addMouseListener(
            new PanelMenuButtonMouseAdapter(patientInfo_pnl) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    PatientInformationGUI patientInf =
                        new PatientInformationGUI();
                    patientInf.pack();
                    patientInf.setBounds(0, 0, 1000, 600);
                    patientInf
                        .setLocationRelativeTo(AdminMenuGUI.this);
                    patientInf.setPCVS(pcvs);
                    patientInf.setFile(file);

                    patientInf.setVisible(true);
                    setVisible(false);
                }
            });
        patientInfo_pnl.setLayout(null);
        patientInfo_pnl.setBackground(new Color(40, 143, 148));
        patientInfo_pnl.setBounds(0, 275, 300, 40);
        sideBarAdminMenu_pnl.add(patientInfo_pnl);

        JLabel patientInfo_lbl = new JLabel("Patient Information");
        patientInfo_lbl.setForeground(WHITE);
        patientInfo_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        patientInfo_lbl.setBounds(36, 6, 258, 28);
        patientInfo_pnl.add(patientInfo_lbl);

        JPanel vaccinationInfo_pnl = new JPanel();
        vaccinationInfo_pnl.addMouseListener(
            new PanelMenuButtonMouseAdapter(vaccinationInfo_pnl) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    VaccinationInformationGUI vaccinationInfo =
                        new VaccinationInformationGUI();
                    vaccinationInfo.pack();
                    vaccinationInfo.setBounds(0, 0, 1000, 600);
                    vaccinationInfo
                        .setLocationRelativeTo(AdminMenuGUI.this);
                    vaccinationInfo.setPCVS(pcvs);
                    vaccinationInfo.setFile(file);
                    vaccinationInfo.setVisible(true);
                    setVisible(false);
                }
            });
        vaccinationInfo_pnl.setLayout(null);
        vaccinationInfo_pnl.setBackground(new Color(40, 143, 148));
        vaccinationInfo_pnl.setBounds(0, 315, 300, 40);
        sideBarAdminMenu_pnl.add(vaccinationInfo_pnl);

        JLabel vaccinationInfo_lbl = new JLabel("Vaccination Information");
        vaccinationInfo_lbl.setForeground(WHITE);
        vaccinationInfo_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        vaccinationInfo_lbl.setBounds(36, 6, 258, 28);
        vaccinationInfo_pnl.add(vaccinationInfo_lbl);

        JButton logOut_btn = new JButton("Log Out");
        logOut_btn.addActionListener(e -> {
            int choice = showConfirmDialog(AdminMenuGUI.this,
                "Are you sure want to log out?",
                "Warning", YES_NO_CANCEL_OPTION,
                WARNING_MESSAGE);
            if (choice == YES_OPTION) {
                PCVSGUI pcvs_gui = new PCVSGUI();
                pcvs_gui.pack();
                pcvs_gui.setBounds(0, 0, 1000, 600);
                pcvs_gui.setLocationRelativeTo(AdminMenuGUI.this);
                pcvs_gui.setPCVS(pcvs);
                pcvs_gui.setFileDir(file);
                pcvs_gui.setVisible(true);
                setVisible(false);

            }
        });
        logOut_btn.setForeground(DARK_GRAY);
        logOut_btn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        logOut_btn.setBounds(6, 475, 288, 40);
        sideBarAdminMenu_pnl.add(logOut_btn);

        JPanel adminContent_pnl = new JPanel();
        adminContent_pnl.setBounds(300, 0, 700, 572);
        adminMenu_pnl.add(adminContent_pnl);
        adminContent_pnl.setLayout(null);

        JPanel recordBatchFrame_pnl = new JPanel();
        recordBatchFrame_pnl.setBackground(WHITE);
        recordBatchFrame_pnl.setBounds(0, 0, 700, 572);
        adminContent_pnl.add(recordBatchFrame_pnl);
        recordBatchFrame_pnl.setLayout(null);

        JLabel vacIDSel_lbl = new JLabel("Select Vaccine ID");
        vacIDSel_lbl.setBounds(219, 117, 123, 18);
        recordBatchFrame_pnl.add(vacIDSel_lbl);
        vacIDSel_lbl.setToolTipText("");
        vacIDSel_lbl.setForeground(DARK_GRAY);
        vacIDSel_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
            PLAIN, 14));

        JLabel vaccine_lbl = new JLabel("");
        vaccine_lbl.setHorizontalAlignment(SwingConstants.CENTER);
        vaccine_lbl.setToolTipText("");
        vaccine_lbl.setForeground(DARK_GRAY);
        vaccine_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 12));
        vaccine_lbl.setBounds(107, 182, 486, 18);
        recordBatchFrame_pnl.add(vaccine_lbl);

        jnj_rdbtn = new JRadioButton("JNJ");
        jnj_rdbtn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        jnj_rdbtn.addItemListener(e -> {
            if (jnj_rdbtn.isSelected()) {
                vaccine_lbl.setText(pcvs.getPCVSVaccines().get(0)
                    .toString());
            } else
                vaccine_lbl.setText("");
        });
        jnj_rdbtn.setSelected(true);
        ButtonGroup vaccineIDBtn = new ButtonGroup();
        vaccineIDBtn.add(jnj_rdbtn);
        jnj_rdbtn.setForeground(DARK_GRAY);
        jnj_rdbtn.setBounds(219, 147, 64, 23);
        recordBatchFrame_pnl.add(jnj_rdbtn);

        JRadioButton asz_rdbtn = new JRadioButton("ASZ");
        asz_rdbtn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        asz_rdbtn.addItemListener(e -> {
            if (asz_rdbtn.isSelected()) {
                vaccine_lbl.setText(pcvs.getPCVSVaccines().get(1)
                    .toString());
            } else
                vaccine_lbl.setText("");
        });
        vaccineIDBtn.add(asz_rdbtn);
        asz_rdbtn.setForeground(DARK_GRAY);
        asz_rdbtn.setBounds(417, 147, 64, 23);
        recordBatchFrame_pnl.add(asz_rdbtn);

        JLabel batchNum_lbl = new JLabel("Batch Number");
        batchNum_lbl.setToolTipText("");
        batchNum_lbl.setForeground(DARK_GRAY);
        batchNum_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
            PLAIN, 14));
        batchNum_lbl.setBounds(219, 223, 97, 18);
        recordBatchFrame_pnl.add(batchNum_lbl);

        JPanel batchNum_pnl = new JPanel();
        batchNum_pnl.setLayout(null);
        batchNum_pnl.setOpaque(false);
        batchNum_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        batchNum_pnl.setBounds(219, 253, 262, 40);
        recordBatchFrame_pnl.add(batchNum_pnl);

        batchNum_tf = new JTextField();
        batchNum_tf.setForeground(DARK_GRAY);
        batchNum_tf.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        batchNum_tf.setColumns(10);
        batchNum_tf.setBorder(null);
        batchNum_tf.setBackground(WHITE);
        batchNum_tf.setBounds(6, 6, 250, 28);
        batchNum_pnl.add(batchNum_tf);

        JLabel expDate_lbl = new JLabel("Expiry Date (mm dd yyyy)");
        expDate_lbl.setToolTipText("");
        expDate_lbl.setForeground(DARK_GRAY);
        expDate_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
            PLAIN, 14));
        expDate_lbl.setBounds(219, 305, 173, 18);
        recordBatchFrame_pnl.add(expDate_lbl);

        JPanel expDate_pnl = new JPanel();
        expDate_pnl.setLayout(null);
        expDate_pnl.setOpaque(false);
        expDate_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        expDate_pnl.setBounds(219, 335, 262, 40);
        recordBatchFrame_pnl.add(expDate_pnl);

        expDate_tf = new JTextField();
        expDate_tf.setForeground(DARK_GRAY);
        expDate_tf.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        expDate_tf.setColumns(10);
        expDate_tf.setBorder(null);
        expDate_tf.setBackground(WHITE);
        expDate_tf.setBounds(6, 6, 250, 28);
        expDate_pnl.add(expDate_tf);

        JLabel qtyAvailable_lbl = new JLabel("Quantity Available");
        qtyAvailable_lbl.setToolTipText("");
        qtyAvailable_lbl.setForeground(DARK_GRAY);
        qtyAvailable_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
            PLAIN, 14));
        qtyAvailable_lbl.setBounds(219, 387, 123, 18);
        recordBatchFrame_pnl.add(qtyAvailable_lbl);

        JPanel qtyAvailable_pnl = new JPanel();
        qtyAvailable_pnl.setLayout(null);
        qtyAvailable_pnl.setOpaque(false);
        qtyAvailable_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        qtyAvailable_pnl.setBounds(219, 417, 262, 40);
        recordBatchFrame_pnl.add(qtyAvailable_pnl);

        qtyAvailable_tf = new JTextField();
        qtyAvailable_tf.setForeground(DARK_GRAY);
        qtyAvailable_tf.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        qtyAvailable_tf.setColumns(10);
        qtyAvailable_tf.setBorder(null);
        qtyAvailable_tf.setBackground(WHITE);
        qtyAvailable_tf.setBounds(6, 6, 250, 28);
        qtyAvailable_pnl.add(qtyAvailable_tf);

        JButton record_btn = new JButton("Record");
        record_btn.setFont(new Font("Neue Haas Grotesk Text Pro",
            PLAIN, 13));
        record_btn.addActionListener(e -> {
            String batchNorStr = batchNum_tf.getText().trim();
            String expiryDate = expDate_tf.getText().trim();
            String qtyAvailableStr = qtyAvailable_tf.getText().trim();
            int iVaccine = jnj_rdbtn.isSelected() ? 0 : 1;

            if (batchNorStr.isEmpty() || expiryDate.isEmpty()
                || qtyAvailableStr.isEmpty()) {
                showMessageDialog(AdminMenuGUI.this,
                    "Please fill up all fields", null,
                    WARNING_MESSAGE);
                batchNum_tf.requestFocus();
            } else {
                int batchNo = Integer.parseInt(batchNorStr);
                int qtyAvailbale = Integer.parseInt(qtyAvailableStr);
                clearText();
                batch = new Batch(batchNo, expiryDate, qtyAvailbale, 0);

                showMessageDialog(AdminMenuGUI.this,
                    "The batch is recorded for the vaccine " +
                        "and healthcare centre\n\n" + batch,
                    null, PLAIN_MESSAGE);

                pcvs.getPCVSVaccines().get(iVaccine).setBatch(batch);
                pcvs.getPCVSHealthcareCentres().get(iAdmin)
                    .setBatches(batch);
            }
        });
        record_btn.setBounds(219, 469, 130, 40);
        recordBatchFrame_pnl.add(record_btn);

        JPanel recordBatchTitle_pnl = new JPanel();
        recordBatchTitle_pnl.setBackground(PINK);
        recordBatchTitle_pnl.setBounds(0, 0, 700, 70);
        recordBatchFrame_pnl.add(recordBatchTitle_pnl);
        recordBatchTitle_pnl.setLayout(null);

        JLabel recordBatchTitle_lbl = new JLabel("Record Vaccine Batch");
        recordBatchTitle_lbl.setHorizontalAlignment(SwingConstants.CENTER);
        recordBatchTitle_lbl.setForeground(WHITE);
        recordBatchTitle_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 20));
        recordBatchTitle_lbl.setBounds(6, 6, 688, 58);
        recordBatchTitle_pnl.add(recordBatchTitle_lbl);

    }

    /**
     * Remove the field when the user
     * has successfully entered the information.
     */
    private void clearText() {
        batchNum_tf.setText("");
        expDate_tf.setText("");
        qtyAvailable_tf.setText("");
        jnj_rdbtn.setSelected(true);
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

    /**
     * PanelButtonMouseAdapter inner class to display a different color
     * when the mouse entered the panel.
     */
    private static class PanelMenuButtonMouseAdapter extends MouseAdapter {
        JPanel panel;

        /**
         * PanelButtonMouseAdapter constructor for invocation in listeners.
         *
         * @param panel determine which panel receives the action.
         */
        public PanelMenuButtonMouseAdapter(JPanel panel) {
            this.panel = panel;
        }

        /**
         * Change this color when a mouse button
         * has been entered on a component.
         *
         * @param e the event to process.
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            panel.setBackground(new Color(79, 189, 194));
        }

        /**
         * Change this color when a mouse button
         * has been exited on a component.
         *
         * @param e the event to process.
         */
        @Override
        public void mouseExited(MouseEvent e) {
            panel.setBackground(new Color(40, 143, 148));
        }

        /**
         * Change this color when a mouse button
         * has been pressed on a component.
         *
         * @param e the event to process.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            panel.setBackground(new Color(40, 143, 148));
        }

        /**
         * Change this color when mouse button
         * has been released.
         *
         * @param e the event to process.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            panel.setBackground(new Color(79, 189, 194));
        }
    }
}
