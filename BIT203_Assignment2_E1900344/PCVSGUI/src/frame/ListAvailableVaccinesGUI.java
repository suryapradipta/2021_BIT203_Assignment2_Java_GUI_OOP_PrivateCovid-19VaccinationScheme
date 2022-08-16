package frame;

import pcvs.HealthcareCentre;
import pcvs.PCVS;
import pcvs.Vaccine;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

import static java.awt.Color.*;
import static java.awt.Font.*;
import static javax.swing.ListSelectionModel.*;
import static javax.swing.SwingConstants.*;

/**
 * ListAvailableVaccinesGUI class allow pcvs GUI application to
 * display list available vaccine and manufacterer.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class ListAvailableVaccinesGUI extends JFrame {

    private final DefaultListModel<Vaccine> listModelAvailableVaccines;
    private final JList<Vaccine> availableVacc_lst;

    private PCVS pcvs;
    private File file = null;
    private int iPatient;
    private int vaccineRecorded;
    private Vaccine selectedVaccine;

    /**
     * Create the list available vaccine frame.
     */
    public ListAvailableVaccinesGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);
        JPanel content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pnl);
        content_pnl.setLayout(null);

        pcvs = new PCVS();

        listModelAvailableVaccines = new DefaultListModel<>();
        IntStream.range(0, pcvs.getPCVSVaccines().size())
            .forEach(i -> listModelAvailableVaccines
                .addElement(pcvs.getPCVSVaccines().get(i)));

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
        appointment_lbl.setFont(
            new Font(".AppleSystemUIFont", PLAIN, 16));
        appointment_lbl.setBounds(36, 6, 258, 28);
        appointment_pnl.add(appointment_lbl);

        JPanel appointmentSel_pnl = new JPanel();
        appointmentSel_pnl.setBackground(new Color(253, 210, 155));
        appointmentSel_pnl.setBounds(0, 0, 5, 40);
        appointment_pnl.add(appointmentSel_pnl);

        JPanel availableVacc_pnl = new JPanel();
        availableVacc_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(availableVacc_pnl);
        availableVacc_pnl.setLayout(null);

        JPanel availableVaccTitle_pnl = new JPanel();
        availableVaccTitle_pnl.setLayout(null);
        availableVaccTitle_pnl.setBackground(PINK);
        availableVaccTitle_pnl.setBounds(0, 0, 700, 70);
        availableVacc_pnl.add(availableVaccTitle_pnl);

        JLabel availableVacc_lbl = new JLabel("Available Vaccines");
        availableVacc_lbl.setHorizontalAlignment(CENTER);
        availableVacc_lbl.setForeground(WHITE);
        availableVacc_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 20));
        availableVacc_lbl.setBounds(6, 6, 688, 58);
        availableVaccTitle_pnl.add(availableVacc_lbl);

        JPanel availableVaccBtn_pnl = new JPanel();
        availableVaccBtn_pnl.setBackground(PINK);
        availableVaccBtn_pnl.setBounds(0, 533, 700, 39);
        availableVacc_pnl.add(availableVaccBtn_pnl);
        availableVaccBtn_pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton record_btn = new JButton("Record");
        record_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 13));
        record_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vaccineRecorded = availableVacc_lst.getSelectedIndex();
                selectedVaccine = pcvs.getPCVSVaccines()
                    .get(vaccineRecorded);

                DefaultListModel<HealthcareCentre>
                    healthcareCentresListModel;
                healthcareCentresListModel = new DefaultListModel<>();

                // traverse batch collection based on
                // selected vaccine
                selectedVaccine.getBatches().stream()
                    .mapToInt(batch -> pcvs.equalsBatch(batch))
                    .filter(iBatch -> !healthcareCentresListModel
                        .contains(pcvs.getPCVSHealthcareCentres()
                            .get(iBatch)))
                    .forEach(iBatch -> healthcareCentresListModel
                        .addElement(pcvs.getPCVSHealthcareCentres()
                            .get(iBatch)));

                ListHealthcareCentresGUI listHealthcareCentres =
                    new ListHealthcareCentresGUI();
                listHealthcareCentres.pack();
                listHealthcareCentres.setBounds(0, 0, 1000, 600);
                listHealthcareCentres
                    .setLocationRelativeTo(ListAvailableVaccinesGUI.this);

                // Pass value to ListHealthcareCentresGUI.
                listHealthcareCentres.setPCVS(pcvs);
                listHealthcareCentres.setFile(file);
                listHealthcareCentres.setSelectedVaccine(selectedVaccine);
                listHealthcareCentres.setIndexVaccine(vaccineRecorded);
                listHealthcareCentres.setIndexPatient(iPatient);

                listHealthcareCentres.setVisible(true);
                setVisible(false);
            }
        });
        availableVaccBtn_pnl.add(record_btn);

        JPanel availableVaccList_pnl = new JPanel();
        availableVaccList_pnl.setBounds(0, 70, 700, 464);
        availableVacc_pnl.add(availableVaccList_pnl);
        availableVaccList_pnl.setLayout(null);

        availableVacc_lst = new JList<>(listModelAvailableVaccines);
        availableVacc_lst.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 12));
        availableVacc_lst.setBounds(0, 0, 700, 464);
        availableVaccList_pnl.add(availableVacc_lst);
        availableVacc_lst.setSelectionMode(SINGLE_SELECTION);
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
