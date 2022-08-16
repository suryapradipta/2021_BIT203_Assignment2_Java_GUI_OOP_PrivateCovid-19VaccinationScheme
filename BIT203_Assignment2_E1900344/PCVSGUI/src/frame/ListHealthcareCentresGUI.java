package frame;

import pcvs.HealthcareCentre;
import pcvs.PCVS;
import pcvs.Vaccine;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.WindowAdapter;

import static java.awt.Color.*;
import static java.awt.Font.*;
import static javax.swing.ListSelectionModel.*;
import static javax.swing.SwingConstants.*;

/**
 * ListHealthcareCentresGUI class allow pcvs GUI application to
 * display healthcare centre name and addresses offering selected vaccine;
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class ListHealthcareCentresGUI extends JFrame {

    private final DefaultListModel<HealthcareCentre>
        healthcareCentresListModel;
    private JList<HealthcareCentre> healthcareCentres_lst;
    private PCVS pcvs;
    private File file = null;
    private int iPatient;
    private HealthcareCentre selectedHealthcareCentre;
    private int iHealthCentre;
    private Vaccine selectedVaccine;
    private int iVaccine;


    /**
     * Create the list healthcare centre frame.
     */
    public ListHealthcareCentresGUI() {
        addWindowListener(new WindowAdapter() {
            /**
             * Invoke when JFrame is opened.
             * This method display healthcare centre name
             * and addresses offering selected vaccine.
             * @param e the event to process.
             */
            @Override
            public void windowOpened(WindowEvent e) {
                healthcareCentresListModel.clear();

                // Traverse batch collection in vaccine.
                selectedVaccine.getBatches().stream()

                    // Equals batch at healthcare centre.
                    .mapToInt(batch -> pcvs.equalsBatch(batch))

                    // Avoid duplciate.
                    .filter(iBatch -> !healthcareCentresListModel
                        .contains(pcvs.getPCVSHealthcareCentres()
                            .get(iBatch)))

                    // Add to table.
                    .forEach(iBatch -> healthcareCentresListModel
                        .addElement(pcvs.getPCVSHealthcareCentres()
                            .get(iBatch)));
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);
        JPanel content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pnl);
        content_pnl.setLayout(null);

        pcvs = new PCVS();
        healthcareCentresListModel = new DefaultListModel<>();

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
        appointment_lbl.setForeground(WHITE);
        appointment_lbl.setFont(new Font(".AppleSystemUIFont", PLAIN, 16));
        appointment_lbl.setBounds(36, 6, 258, 28);
        appointment_pnl.add(appointment_lbl);

        JPanel appointmentSel_pnl = new JPanel();
        appointmentSel_pnl.setBackground(new Color(253, 210, 155));
        appointmentSel_pnl.setBounds(0, 0, 5, 40);
        appointment_pnl.add(appointmentSel_pnl);


        JPanel healthcareCentres_pnl = new JPanel();
        healthcareCentres_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(healthcareCentres_pnl);
        healthcareCentres_pnl.setLayout(null);

        JPanel healthcareCentresTitle_pnl = new JPanel();
        healthcareCentresTitle_pnl.setLayout(null);
        healthcareCentresTitle_pnl.setBackground(PINK);
        healthcareCentresTitle_pnl.setBounds(0, 0, 700, 70);
        healthcareCentres_pnl.add(healthcareCentresTitle_pnl);

        JLabel healthcareCentresTitle_lbl =
            new JLabel("Healthcare Centres");
        healthcareCentresTitle_lbl.setHorizontalAlignment(CENTER);
        healthcareCentresTitle_lbl.setForeground(WHITE);
        healthcareCentresTitle_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 20));
        healthcareCentresTitle_lbl.setBounds(6, 6, 688, 58);
        healthcareCentresTitle_pnl.add(healthcareCentresTitle_lbl);

        JPanel healthcareCentresBtn_pnl = new JPanel();
        healthcareCentresBtn_pnl.setBackground(PINK);
        healthcareCentresBtn_pnl.setBounds(0, 533, 700, 39);
        healthcareCentres_pnl.add(healthcareCentresBtn_pnl);
        healthcareCentresBtn_pnl.setLayout(
            new FlowLayout(FlowLayout.RIGHT));

        JButton select_btn = new JButton("Select");
        select_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 13));
        select_btn.addActionListener(e -> {
            iHealthCentre = healthcareCentres_lst.getSelectedIndex();
            selectedHealthcareCentre = pcvs.getPCVSHealthcareCentres()
                .get(iHealthCentre);


            PatientMenuGUI patientMenu = new PatientMenuGUI();
            patientMenu.pack();
            patientMenu.setBounds(0, 0, 1000, 600);
            patientMenu
                .setLocationRelativeTo(ListHealthcareCentresGUI.this);

            // Pass value to PatientMenuGUI.
            patientMenu.setPCVS(pcvs);
            patientMenu.setFile(file);
            patientMenu.setSelHealthCentre(selectedHealthcareCentre);
            patientMenu.setSelectedVaccine(selectedVaccine);
            patientMenu.setIndexHealthcareCentre(iHealthCentre);
            patientMenu.setIndexVaccine(iVaccine);
            patientMenu.setIndexPatient(iPatient);

            patientMenu.setVisible(true);
            setVisible(false);
        });
        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 13));
        back_btn.addActionListener(e -> {
            ListAvailableVaccinesGUI listAvailableVaccines =
                new ListAvailableVaccinesGUI();
            listAvailableVaccines.pack();
            listAvailableVaccines.setBounds(0, 0, 1000, 600);
            listAvailableVaccines
                .setLocationRelativeTo(ListHealthcareCentresGUI.this);

            listAvailableVaccines.setPCVS(pcvs);
            listAvailableVaccines.setFile(file);
            listAvailableVaccines.setIndexPatient(iPatient);

            listAvailableVaccines.setVisible(true);
            setVisible(false);
        });
        healthcareCentresBtn_pnl.add(back_btn);
        healthcareCentresBtn_pnl.add(select_btn);

        JPanel healthcareCentresList_pnl = new JPanel();
        healthcareCentresList_pnl.setLayout(null);
        healthcareCentresList_pnl.setBounds(0, 70, 700, 463);
        healthcareCentres_pnl.add(healthcareCentresList_pnl);

        healthcareCentres_lst = new JList<>(healthcareCentresListModel);
        healthcareCentres_lst.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 12));
        healthcareCentres_lst.setSelectionMode(SINGLE_SELECTION);
        healthcareCentres_lst.setBounds(0, 0, 700, 463);
        healthcareCentresList_pnl.add(healthcareCentres_lst);
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
        this.pcvs = pcvsObj;
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
     * Registers this vaccine based on selected vaccine in
     * ListAvailableVaccines GUI to display the available
     * batches of vaccine.
     *
     * @param selVaccine the selected value.
     */
    public void setSelectedVaccine(Vaccine selVaccine) {
        selectedVaccine = selVaccine;
    }

    /**
     * Registers this index vaccine to gets the object vaccine
     * based on index.
     *
     * @param idx the value to get.
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
     * @param idx patient want to appointment.
     */
    public void setIndexPatient(int idx) {
        iPatient = idx;
    }

}
