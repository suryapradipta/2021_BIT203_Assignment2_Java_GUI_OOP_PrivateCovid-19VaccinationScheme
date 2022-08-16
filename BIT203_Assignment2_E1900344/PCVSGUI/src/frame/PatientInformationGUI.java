package frame;

import pcvs.PCVS;
import pcvs.Patient;
import pcvs.User;
import table.PatientTableModel;

import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.io.File;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import static java.awt.Color.PINK;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;
import static javax.swing.JOptionPane.*;
import static javax.swing.SwingConstants.CENTER;

/**
 * PatientInformationGUI class allow pcvs GUI application to
 * display detail Patient information.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class PatientInformationGUI extends JFrame {

    private final JPanel content_pnl;
    private final PatientTableModel patientTableModel;
    private JTable patientInfo_table;
    private PCVS pcvs;
    private File file = null;


    public PatientInformationGUI() {
        addWindowListener(new WindowAdapter() {
            /**
             * Invoke when JFrame is opened.
             * This method display detail Patient information
             * in table model.
             * @param e the event to process.
             */
            @Override
            public void windowOpened(WindowEvent e) {
                patientTableModel.clear();
                IntStream.range(0, pcvs.getPCVSUsers().size())

                    // get Patient object in User collection
                    .filter(i -> pcvs.getPCVSUsers().get(i)
                        instanceof Patient)

                    // avoid duplicate
                    .filter(i -> !patientTableModel
                        .contains((Patient) pcvs.getPCVSUsers().get(i)))
                    .forEach(i -> patientTableModel
                        .add((Patient) pcvs.getPCVSUsers().get(i)));
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

        JPanel patientInfo_pnl = new JPanel();
        patientInfo_pnl.setLayout(null);
        patientInfo_pnl.setBackground(new Color(79, 189, 194));
        patientInfo_pnl.setBounds(0, 275, 300, 40);
        sideBarAdminMenu_pnl.add(patientInfo_pnl);

        JLabel patientInfo_lbl = new JLabel("Patient Information");
        patientInfo_lbl.setForeground(WHITE);
        patientInfo_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        patientInfo_lbl.setBounds(36, 6, 258, 28);
        patientInfo_pnl.add(patientInfo_lbl);

        JPanel patientInfoSel_pnl = new JPanel();
        patientInfoSel_pnl.setBackground(new Color(253, 210, 155));
        patientInfoSel_pnl.setBounds(0, 0, 5, 40);
        patientInfo_pnl.add(patientInfoSel_pnl);

        JPanel patientInfoTable_pnl = new JPanel();
        patientInfoTable_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(patientInfoTable_pnl);
        patientInfoTable_pnl.setLayout(null);

        JScrollPane patientInfo_scrollPane =
            new JScrollPane(patientInfo_table);
        patientInfo_scrollPane.setBorder(null);
        patientInfo_scrollPane.setBounds(0, 70, 700, 464);
        patientInfoTable_pnl.add(patientInfo_scrollPane);

        String[] colHeader = {"Username", "Password", "Email",
            "Full Name", "IC or Passport"};
        patientTableModel = new PatientTableModel(colHeader, 0);

        patientInfo_table = new JTable(patientTableModel);
        patientInfo_table.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 12));
        patientInfo_table.setBorder(null);
        patientInfo_scrollPane.setViewportView(patientInfo_table);

        JPanel patientInfoTitle_pnl = new JPanel();
        patientInfoTitle_pnl.setLayout(null);
        patientInfoTitle_pnl.setBackground(PINK);
        patientInfoTitle_pnl.setBounds(0, 0, 700, 70);
        patientInfoTable_pnl.add(patientInfoTitle_pnl);

        JLabel patientInfoTitle_lbl = new JLabel("Patient Information");
        patientInfoTitle_lbl.setHorizontalAlignment(CENTER);
        patientInfoTitle_lbl.setForeground(WHITE);
        patientInfoTitle_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 20));
        patientInfoTitle_lbl.setBounds(6, 6, 688, 58);
        patientInfoTitle_pnl.add(patientInfoTitle_lbl);

        JPanel patientBtn_pnl = new JPanel();
        patientBtn_pnl.setBackground(PINK);
        patientBtn_pnl.setBounds(0, 533, 700, 39);
        patientInfoTable_pnl.add(patientBtn_pnl);
        patientBtn_pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton originalOrder_btn = new JButton("Original Order");
        originalOrder_btn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 12));
        originalOrder_btn.addActionListener(e -> {
            patientTableModel.clear();
            IntStream.range(0, pcvs.getPCVSUsers().size())
                .filter(i -> pcvs.getPCVSUsers().get(i)
                    instanceof Patient)
                .filter(i -> !patientTableModel.contains((Patient)
                    pcvs.getPCVSUsers().get(i)))
                .forEach(i -> patientTableModel
                    .add((Patient) pcvs.getPCVSUsers().get(i)));
        });
        patientBtn_pnl.add(originalOrder_btn);

        JButton delete_btn = new JButton("Delete Selection");
        delete_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 12));
        delete_btn.addActionListener(e -> {
            int selRow = patientInfo_table.getSelectedRow();
            if (selRow != -1) {
                Patient p = patientTableModel.getPatient(selRow);
                int choice = showConfirmDialog(null,
                    "Are you sure you want to remove " +
                        p.getUsername(), "Warning",
                    OK_CANCEL_OPTION,
                    WARNING_MESSAGE);
                if (choice == YES_OPTION) {
                    patientTableModel.remove(selRow);
                    if (pcvs.getUser(p) != null) {
                        pcvs.getPCVSUsers().remove(pcvs.getUser(p));
                    }
                }
            } else
                showMessageDialog(null,
                    "Please select a row!", null,
                    WARNING_MESSAGE);
        });

        JButton sorted_btn = new JButton("Sorted by Full Name");
        sorted_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 12));
        sorted_btn.addActionListener(e -> {
            patientTableModel.clear();
            ArrayList<Patient> patients =
                IntStream.range(0, pcvs.getPCVSUsers().size())
                    .filter(i -> pcvs.getPCVSUsers().get(i)
                        instanceof Patient)
                    .mapToObj(i -> (Patient) pcvs.getPCVSUsers()
                        .get(i))

                    // compare by full name
                    .sorted(Comparator.comparing(User::getFullName))
                    .collect(Collectors.toCollection(ArrayList::new));
            patients.forEach(patientTableModel::add);
        });
        patientBtn_pnl.add(sorted_btn);
        delete_btn.setActionCommand("Cancel");
        patientBtn_pnl.add(delete_btn);

        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font(".AppleSystemUIFont", PLAIN, 12));
        back_btn.addActionListener(e -> {
            AdminMenuGUI adminMenu = new AdminMenuGUI();
            adminMenu.pack();
            adminMenu.setBounds(0, 0, 1000, 600);
            adminMenu
                .setLocationRelativeTo(PatientInformationGUI.this);
            adminMenu.setPCVS(pcvs);
            adminMenu.setFile(file);
            adminMenu.setVisible(true);
            setVisible(false);
        });
        patientBtn_pnl.add(back_btn);
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

}
