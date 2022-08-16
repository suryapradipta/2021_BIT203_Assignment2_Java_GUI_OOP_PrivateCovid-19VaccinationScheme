package frame;

import pcvs.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.stream.IntStream;
import java.io.File;

import static java.awt.Color.*;
import static java.awt.Font.*;
import static javax.swing.JOptionPane.*;
import static javax.swing.ListSelectionModel.*;
import static javax.swing.SwingConstants.*;

/**
 * AppointmentInformationGUI class allow pcvs GUI application to
 * display detail appointment inforamtion:vaccine, patient,
 * and vaccination in table format.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class AppointmentInformationGUI extends JFrame
    implements ActionListener {

    private final DefaultListModel<String> appointmentListModel;
    private final JButton confirm_btn;
    private final JButton reject_btn;
    private final JButton record_btn;

    private PCVS pcvs;
    private String vaccintionID;
    private int batchNum;
    private int iAdmin;
    private File file = null;


    /**
     * Constructor to create the frame.
     */
    public AppointmentInformationGUI() {
        addWindowListener(new WindowAdapter() {
            /**
             * Invoke when JFrame is opened.
             * This method display information about
             * vaccine, patient, and vaccination in table format.
             * @param e the event to process.
             */
            @Override
            public void windowOpened(WindowEvent e) {
                appointmentListModel.clear();
                pcvs.getPCVSVaccines()
                    // Traverse Batch in Vaccine collection.
                    .forEach(vaccine -> vaccine.getBatches()

                        // Traverse Vaccination in Batch collection.
                        .forEach(batch -> batch.getVaccinations().stream()
                            .filter(vacc -> vacc.getVaccinationID()

                                // Same vaccination ID.
                                .equals(vaccintionID)).forEach(vacc -> {
                    int bound1 = pcvs.getPCVSUsers().size();
                    // Traverse Vaccination in Patient collection.
                    IntStream.range(0, bound1).forEach(x -> {
                        if (pcvs.getPCVSUsers().get(x) instanceof
                            Patient pt) {
                            int bound = ((Patient) pcvs.getPCVSUsers()
                                .get(x)).getVaccinations().size();

                            // Add information to table.
                            IntStream.range(0, bound).filter(y -> vacc
                                .equals(pt.getVaccinations().get(y)))
                                .filter(y -> !appointmentListModel
                                    .contains(pt) &&
                                !appointmentListModel.contains(batch) &&
                                !appointmentListModel.contains(vaccine))
                                .forEach(y -> {
                                appointmentListModel
                                    .addElement("Full Name: " +
                                        pt.getFullName());
                                appointmentListModel
                                    .addElement("IC or Passport: " +
                                        pt.getICPassport());
                                appointmentListModel
                                    .addElement("Batch Number: "
                                        + batch.getBatchNo());
                                appointmentListModel
                                    .addElement("Expiry Date: " +
                                        batch.getExpiryDate());
                                appointmentListModel.addElement("Vaccine: "
                                    + vaccine);
                            });
                        }
                    });
                })));
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);
        JPanel content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pnl);
        content_pnl.setLayout(null);

        pcvs = new PCVS();
        appointmentListModel = new DefaultListModel<>();

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

        JPanel appointment_pnl = new JPanel();
        appointment_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(appointment_pnl);
        appointment_pnl.setLayout(null);

        JPanel appointmentTitle_pnl = new JPanel();
        appointmentTitle_pnl.setLayout(null);
        appointmentTitle_pnl.setBackground(PINK);
        appointmentTitle_pnl.setBounds(0, 0, 700, 70);
        appointment_pnl.add(appointmentTitle_pnl);

        JLabel appointment_lbl =
            new JLabel("Vaccination Appointment Information");
        appointment_lbl.setHorizontalAlignment(CENTER);
        appointment_lbl.setForeground(WHITE);
        appointment_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro",
                PLAIN, 20));
        appointment_lbl.setBounds(6, 6, 688, 58);
        appointmentTitle_pnl.add(appointment_lbl);

        JPanel appointmentBtn_pnl = new JPanel();
        appointmentBtn_pnl.setBackground(PINK);
        appointmentBtn_pnl.setBounds(0, 533, 700, 39);
        appointment_pnl.add(appointmentBtn_pnl);
        appointmentBtn_pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 13));
        back_btn.addActionListener(e -> {
            VaccinationListGUI vaccList = new VaccinationListGUI();
            vaccList.pack();
            vaccList.setBounds(0, 0, 1000, 600);
            vaccList
                .setLocationRelativeTo(AppointmentInformationGUI.this);
            vaccList.setPCVS(pcvs);
            vaccList.setFile(file);
            vaccList.setBatchNumber(batchNum);
            vaccList.setIndexAdmin(iAdmin);
            vaccList.setBatchNumber(batchNum);
            vaccList.setVisible(true);
            setVisible(false);
        });

        JButton logOut_btn = new JButton("Log Out");
        logOut_btn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        logOut_btn.addActionListener(e -> {
            PCVSGUI pcvs_gui = new PCVSGUI();
            pcvs_gui.pack();
            pcvs_gui.setBounds(0, 0, 1000, 600);
            pcvs_gui.setLocationRelativeTo(AppointmentInformationGUI.this);
            pcvs_gui.setPCVS(pcvs);
            pcvs_gui.setFileDir(file);
            pcvs_gui.setVisible(true);
            setVisible(false);
        });
        appointmentBtn_pnl.add(logOut_btn);
        appointmentBtn_pnl.add(back_btn);

        reject_btn = new JButton("Reject");
        reject_btn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        appointmentBtn_pnl.add(reject_btn);

        confirm_btn = new JButton("Confirm");
        confirm_btn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        appointmentBtn_pnl.add(confirm_btn);

        record_btn = new JButton("Record");
        record_btn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        appointmentBtn_pnl.add(record_btn);

        reject_btn.addActionListener(this);
        confirm_btn.addActionListener(this);
        record_btn.addActionListener(this);


        JPanel appointmentList_pnl = new JPanel();
        appointmentList_pnl.setLayout(null);
        appointmentList_pnl.setBounds(0, 70, 700, 463);
        appointment_pnl.add(appointmentList_pnl);

        JList<String> appointment_lst =
            new JList<>(appointmentListModel);
        appointment_lst.setSelectionMode(
            SINGLE_SELECTION);
        appointment_lst.setBounds(0, 0, 700, 463);
        appointmentList_pnl.add(appointment_lst);
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
     * Registers this vaccination id to display
     * appointment information in this JFrame.
     *
     * @param id the value to record.
     */
    public void setVaccinationID(String id) {
        vaccintionID = id;
    }

    /**
     * Registers this batch number to display appointment
     * information in this JFrame.
     *
     * @param num batch number to record.
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

    /**
     * Assign specific actions allow administrator to
     * confirm, reject, or record the vaccination apointment.
     *
     * @param e determine the object where the event occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        IntStream.range(0, pcvs.getPCVSUsers().size())
            .filter(x -> pcvs.getPCVSUsers().get(x) instanceof Patient)
            .mapToObj(x -> (Patient) pcvs.getPCVSUsers().get(x))
            .forEach(patient -> {
                int bound = patient.getVaccinations().size();
                IntStream.range(0, bound).forEach(y -> {
                    Vaccination vaccination = patient
                        .getVaccinations().get(y);

                    // same vaccination id
                    if (patient.getVaccinations().get(y).getVaccinationID()
                        .equals(vaccintionID)) {
                        if (e.getSource() == confirm_btn) {

                            // confirm the appointment
                            patient.getVaccinations().get(y)
                                .setStatus("confirmed");

                            showMessageDialog(
                                AppointmentInformationGUI.this,
                                "Appointment \"confirmed\"\n" +
                                    "Sent email notification to patient",
                                null, PLAIN_MESSAGE);
                        } else if (e.getSource() == reject_btn) {
                            // reject the appointment
                            patient.getVaccinations().get(y)
                                .setStatus("rejected");
                            String remarks =
                                showInputDialog("Enter remarks: ");
                            vaccination.setRemarks(remarks);
                            showMessageDialog(
                                AppointmentInformationGUI.this,
                                "Appointment \"rejected\"\n" +
                                    "Sent email notification to patient",
                                null, PLAIN_MESSAGE);
                        }
                    }
                });
            });

        if (e.getSource() == record_btn) {
            int adCount = 0;
            for (int x = 0; x < pcvs.getPCVSUsers().size(); x++) {

                // Downcast User collection to Patient collection
                if (pcvs.getPCVSUsers().get(x) instanceof Patient pt) {

                    // Traverse Vaccination collection
                    // in Patient collection
                    for (int y = 0;
                         y < pt.getVaccinations().size(); y++) {
                        Vaccination vaccination =
                            pt.getVaccinations().get(y);
                        // Same Vaccination ID
                        if (pt.getVaccinations().get(y)
                            .getVaccinationID()
                            .equals(vaccintionID)) {
                            pt.getVaccinations().get(y)
                                .setStatus("administered");
                            String remarks =
                                showInputDialog("Enter remarks: ");

                            adCount++;
                            pcvs.setQuantityAdministered(adCount);
                            vaccination.setRemarks(remarks);

                            showMessageDialog(
                                AppointmentInformationGUI.this,
                                "The vaccination status " +
                                    "is set to \"administered\" and " +
                                    "an email sent to the patient",
                                null, PLAIN_MESSAGE);

                        }
                    }
                }
            }
        }
        AdminMenuGUI adminMenu = new AdminMenuGUI();
        adminMenu.pack();
        adminMenu.setBounds(0, 0, 1000, 600);
        adminMenu.setLocationRelativeTo(AppointmentInformationGUI.this);
        adminMenu.setPCVS(pcvs);
        adminMenu.setFile(file);
        adminMenu.setIndexAdmin(iAdmin);

        adminMenu.setVisible(true);
        setVisible(false);
    }
}
