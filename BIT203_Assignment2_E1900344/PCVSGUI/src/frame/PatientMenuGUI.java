package frame;

import dialog.AppointmentDialog;
import pcvs.*;
import table.BatchTableModel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.File;
import java.util.stream.IntStream;

import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.SwingConstants.*;


/**
 * PatientMenuGUI class allow patient to request an vaccination
 * appointment and view the appointment status.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class PatientMenuGUI extends JFrame {

    private static Integer vID = 0;
    private JTable batch_table;
    private PCVS pcvs;
    private File file = null;
    private HealthcareCentre selHealthCentre;
    private Vaccine selectedVaccine;
    private BatchTableModel batchTableModel;
    private int iHealthCentre;
    private int iVaccine;
    private int iPatient;


    /**
     * Create the patient menu frame.
     */
    public PatientMenuGUI() {
        addWindowListener(new WindowAdapter() {
            /**
             * Invoke when JFrame is opened.
             * This method display the available vaccine batch
             * after patient select the available vaccine.
             * @param e the event to process.
             */
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    for (int i = 0; i < selHealthCentre
                        .getBatches().size(); i++) {
                        // Get the expiryDate attribute from Batch object
                        // and split from String to array
                        int[] splitExpiryDate = PCVS.splitToArray(
                            selHealthCentre.getBatches().get(i)
                                .getExpiryDate());
                        // Real time
                        Calendar today = Calendar.getInstance();
                        Calendar expires = Calendar.getInstance();
                        // Set expires by expiry date attribute
                        expires.set(splitExpiryDate[2],
                            splitExpiryDate[0], splitExpiryDate[1]);
                        // Traverse Batch object in Vaccine collection
                        for (int j = 0; j <
                            selectedVaccine.getBatches().size(); j++) {

                            // Compare Batch from HealthcareCentre
                            // and Vaccine
                            if (selHealthCentre.getBatches().get(i)
                                .equals(selectedVaccine.getBatches()
                                    .get(j)) &&
                                selHealthCentre.getBatches().get(i)
                                    .getQuantityAvailable() > 0 &&
                                !today.after(expires)) {
                                if (!batchTableModel
                                    .contains(selectedVaccine.getBatches()
                                        .get(j))) {
                                    batchTableModel
                                        .add(selectedVaccine.getBatches()
                                            .get(j));
                                }
                            }
                        }
                    }
                } catch (NullPointerException ignored) {
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);
        JPanel content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pnl);
        content_pnl.setLayout(null);

        pcvs = new PCVS();

        JPanel patientMenu_pnl = new JPanel();
        patientMenu_pnl.setLayout(null);
        patientMenu_pnl.setBackground(new Color(40, 143, 148));
        patientMenu_pnl.setBounds(0, 0, 300, 572);
        content_pnl.add(patientMenu_pnl);

        JPanel appointment_pnl = new JPanel();
        appointment_pnl.setLayout(null);
        appointment_pnl.setBackground(new Color(79, 189, 194));
        appointment_pnl.setBounds(0, 225, 300, 40);
        patientMenu_pnl.add(appointment_pnl);

        JLabel appointment_lbl = new JLabel("Vaccination Appointment");
        appointment_lbl.setForeground(Color.WHITE);
        appointment_lbl.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 16));
        appointment_lbl.setBounds(36, 6, 258, 28);
        appointment_pnl.add(appointment_lbl);

        JPanel appointmentSel_pnl = new JPanel();
        appointmentSel_pnl.setBackground(new Color(253, 210, 155));
        appointmentSel_pnl.setBounds(0, 0, 5, 40);
        appointment_pnl.add(appointmentSel_pnl);

        JPanel appointmentStatus_pnl = new JPanel();
        appointmentStatus_pnl.addMouseListener(
            new PanelMenuButtonMouseAdapter(appointmentStatus_pnl) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ArrayList<Patient> patients = new ArrayList<>();
                    ArrayList<String> statusPatients = new ArrayList<>();
                    IntStream.range(0, pcvs.getPCVSUsers().size())
                        .filter(x -> pcvs.getPCVSUsers().get(x)
                            instanceof Patient)
                        .mapToObj(x -> (Patient)
                            pcvs.getPCVSUsers().get(iPatient))
                        .forEach(pt -> {
                            pt.getVaccinations().stream()
                                .map(Vaccination::getStatus)
                                // avoid duplicate
                                .filter(status -> !statusPatients
                                    .contains(status))
                                .forEach(status -> {
                                    // Not in statusPatients collection
                                    showMessageDialog(
                                        PatientMenuGUI.this,
                                        "Your appointment status: " +
                                            status,
                                        null, PLAIN_MESSAGE);

                                    // Add to statusPatients collection
                                    statusPatients.add(status);
                                });
                            if (!patients.contains(pt)) {
                                // Patient not yet aggregation
                                // with Vaccination
                                if (pt.getVaccinations().size() == 0) {
                                    showMessageDialog(
                                        PatientMenuGUI.this,
                                        "Sorry, you haven't made an " +
                                            "vaccination appointment",
                                        null, WARNING_MESSAGE);

                                    // Add to patients collection
                                    patients.add(pt);
                                }
                            }
                        });
                }
            });
        appointmentStatus_pnl.setLayout(null);
        appointmentStatus_pnl.setBackground(new Color(40, 143, 148));
        appointmentStatus_pnl.setBounds(0, 265, 300, 40);
        patientMenu_pnl.add(appointmentStatus_pnl);

        JLabel appointmenStatus_lbl =
            new JLabel("Vaccination Appointment Status");
        appointmenStatus_lbl.setForeground(Color.WHITE);
        appointmenStatus_lbl.setFont(
            new Font(".AppleSystemUIFont", Font.PLAIN, 16));
        appointmenStatus_lbl.setBounds(36, 6, 264, 28);
        appointmentStatus_pnl.add(appointmenStatus_lbl);

        JButton logOut_btn = new JButton("Log Out");
        logOut_btn.addActionListener(e -> {

            int choice = showConfirmDialog(PatientMenuGUI.this,
                "Are you sure want to log out?",
                "Warning", YES_NO_CANCEL_OPTION,
                WARNING_MESSAGE);
            if (choice == YES_OPTION) {
                PCVSGUI pcvs_gui = new PCVSGUI();
                pcvs_gui.pack();
                pcvs_gui.setBounds(0, 0, 1000, 600);
                pcvs_gui.setLocationRelativeTo(PatientMenuGUI.this);
                pcvs_gui.setPCVS(pcvs);
                pcvs_gui.setFileDir(file);
                pcvs_gui.setVisible(true);
                setVisible(false);
            }
        });
        logOut_btn.setForeground(Color.DARK_GRAY);
        logOut_btn.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 16));
        logOut_btn.setBounds(6, 460, 288, 40);
        patientMenu_pnl.add(logOut_btn);

        JPanel vaccAppointment_pnl = new JPanel();
        vaccAppointment_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(vaccAppointment_pnl);
        vaccAppointment_pnl.setLayout(null);

        JPanel vaccAppointmentTitle_pnl = new JPanel();
        vaccAppointmentTitle_pnl.setLayout(null);
        vaccAppointmentTitle_pnl.setBackground(Color.PINK);
        vaccAppointmentTitle_pnl.setBounds(0, 0, 700, 70);
        vaccAppointment_pnl.add(vaccAppointmentTitle_pnl);

        JLabel vaccAppointment_lbl =
            new JLabel("Request Vaccination Appointment");
        vaccAppointment_lbl.setHorizontalAlignment(CENTER);
        vaccAppointment_lbl.setForeground(Color.WHITE);
        vaccAppointment_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", Font.PLAIN, 20));
        vaccAppointment_lbl.setBounds(6, 6, 688, 58);
        vaccAppointmentTitle_pnl.add(vaccAppointment_lbl);

        JPanel vaccAppointmentBtn_pnl = new JPanel();
        vaccAppointmentBtn_pnl.setBackground(Color.PINK);
        vaccAppointmentBtn_pnl.setBounds(0, 533, 700, 39);
        vaccAppointment_pnl.add(vaccAppointmentBtn_pnl);
        vaccAppointmentBtn_pnl.setLayout
            (new FlowLayout(FlowLayout.RIGHT));

        JButton view_btn = new JButton("View Available Vaccine");
        view_btn.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
        view_btn.addActionListener(e -> {

            ListAvailableVaccinesGUI availableVaccine =
                new ListAvailableVaccinesGUI();
            availableVaccine.pack();
            availableVaccine.setBounds(0, 0, 1000, 600);
            availableVaccine.setLocationRelativeTo(
                PatientMenuGUI.this);
            availableVaccine.setPCVS(pcvs);
            availableVaccine.setFile(file);
            availableVaccine.setIndexPatient(iPatient);
            availableVaccine.setVisible(true);
            setVisible(false);
            batchTableModel.clear();
        });
        JButton request_btn = new JButton("Request Appointment");
        request_btn.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 13));
        request_btn.addActionListener(e -> {
            int selRow = batch_table.getSelectedRow();
            if (selRow != -1) {
                Object expiryDate = batchTableModel
                    .getValueAt(selRow, 1);
                int qty = pcvs.getNumOfPendingByPatient() +
                    pcvs.getQuantityAdministered();
                Object batchNumObject = batchTableModel
                    .getValueAt(selRow, 0);
                int batchNum = (Integer) batchNumObject;
                int iBatch = -1;
                HealthcareCentre hc = pcvs.getPCVSHealthcareCentres()
                    .get(iHealthCentre);
                Vaccine vc = pcvs.getPCVSVaccines().get(iVaccine);

                // get selected batch number.
                for (int x = 0; x < hc.getBatches().size(); x++) {
                    for (int y = 0; y < vc.getBatches().size(); y++) {
                        if (batchNum == vc.getBatches().get(y)
                            .getBatchNo()) {
                            iBatch = y;
                        }
                    }
                }

                showMessageDialog(PatientMenuGUI.this,
                    "Expiry date: " + expiryDate +
                        "\nQuantity available: " + qty,
                    null, PLAIN_MESSAGE);
                AppointmentDialog appointmentDialog =
                    new AppointmentDialog(PatientMenuGUI.this);
                appointmentDialog.pack();
                appointmentDialog.setSize(700, 440);
                appointmentDialog
                    .setLocationRelativeTo(PatientMenuGUI.this);
                appointmentDialog.setVisible(true);

                String appointmentDate =
                    appointmentDialog.getUpcomingDate();
                try {
                    boolean appointment =
                        pcvs.appointmentDate(expiryDate, appointmentDate);
                    while (!appointment) {
                        showMessageDialog(PatientMenuGUI.this,
                            "Appointment date after " +
                                "the batch expiration date!",
                            null, WARNING_MESSAGE);
                        appointmentDialog.setVisible(true);
                        appointmentDate =
                            appointmentDialog.getUpcomingDate();
                        appointment = pcvs
                            .appointmentDate(expiryDate, appointmentDate);
                    }
                } catch (ArrayIndexOutOfBoundsException io) {
                    showMessageDialog(PatientMenuGUI.this,
                        "Invalid date!",
                        null, WARNING_MESSAGE);
                }
                // create object vaccination for patient.
                Vaccination vaccination = new Vaccination();
                vaccination.setAppointmentDate(appointmentDate);
                vaccination.setVaccinationID(generateVaccinationID());
                vaccination.setStatus("pending");

                // Registers vaccination.
                Patient patient = (Patient) pcvs.getPCVSUsers()
                    .get(iPatient);
                patient.setVaccinations(vaccination);
                selectedVaccine.getBatches().get(iBatch)
                    .setVaccinations(vaccination);

                showMessageDialog(PatientMenuGUI.this,
                    "The vaccination is recorded for " +
                        "the patient and the batch",
                    null, PLAIN_MESSAGE);
                batchTableModel.clear();
            } else {
                showMessageDialog(PatientMenuGUI.this,
                    "Please select available vaccine " +
                        "and then select batch!",
                    null, WARNING_MESSAGE);
            }
        });
        vaccAppointmentBtn_pnl.add(request_btn);
        vaccAppointmentBtn_pnl.add(view_btn);

        JPanel vaccAppointmentTable_pnl = new JPanel();
        vaccAppointmentTable_pnl.setBounds(0, 70, 700, 464);
        vaccAppointment_pnl.add(vaccAppointmentTable_pnl);
        vaccAppointmentTable_pnl.setLayout(null);

        JScrollPane batch_scrollPane = new JScrollPane(batch_table);
        batch_scrollPane.setBorder(null);
        batch_scrollPane.setBounds(0, 0, 700, 464);
        vaccAppointmentTable_pnl.add(batch_scrollPane);

        String[] batchColHeader = {"Batch Number", "Expiry Date",
            "Quantity Available"};
        batchTableModel = new BatchTableModel(batchColHeader, 0);

        batch_table = new JTable(batchTableModel);
        batch_table.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 12));
        batch_table.setBorder(null);
        batch_scrollPane.setViewportView(batch_table);
    }

    /**
     * Calculates the length of the Vaccination ID.
     * Returns two positive integers + id, zero , or one integer + id.
     *
     * @return a String with the increment of id.
     */
    public static String generateVaccinationID() {
        // if length id 1 digit add with 00 + id
        String id = (vID.toString().length() == 1) ? ("00" + vID)
            // if length id 2 digit add with 0 + id
            : ((vID.toString().length() == 2) ? ("0" + vID)
            : vID.toString());
        vID++; // increment
        return "V" + id;
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
     * Registers this healthcare centre attribute to
     * display batches of vaccine.
     *
     * @param healthcareCentre selected healthcare centre.
     */
    public void setSelHealthCentre(HealthcareCentre healthcareCentre) {
        selHealthCentre = healthcareCentre;
    }

    /**
     * Registers this vaccine attribute to display
     * batches of vaccine.
     *
     * @param vaccine selected vaccine.
     */
    public void setSelectedVaccine(Vaccine vaccine) {
        selectedVaccine = vaccine;
    }

    /**
     * Registers this index Healthcare Centre to
     * gets healthcare centre object by index.
     *
     * @param idx selected index healthcare centre.
     */
    public void setIndexHealthcareCentre(int idx) {
        iHealthCentre = idx;
    }

    /**
     * Registers this index vaccine to gets
     * vaccine object by index.
     *
     * @param idx Vaccine object to search.
     */
    public void setIndexVaccine(int idx) {
        iVaccine = idx;
    }

    /**
     * Registers this index patient to get
     * the Patient object by index.
     * This method allow recording vaccination appointment
     * to Patient.
     *
     * @param idx Patient object to search.
     */
    public void setIndexPatient(int idx) {
        iPatient = idx;
    }

    /**
     * PanelButtonMouseAdapter inner class to display a different color
     * when the mouse entered the panel.
     */
    private static class PanelMenuButtonMouseAdapter
        extends MouseAdapter {
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
