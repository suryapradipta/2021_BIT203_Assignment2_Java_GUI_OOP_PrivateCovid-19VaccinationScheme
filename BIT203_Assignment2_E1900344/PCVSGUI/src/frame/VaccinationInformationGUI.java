package frame;

import pcvs.PCVS;
import table.VaccinationTableModel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static java.awt.Color.*;
import static java.awt.Font.*;
import static javax.swing.JFrame.*;
import static javax.swing.SwingConstants.*;

/**
 * VaccinationInformationGUI class allow pcvs GUI application to
 * display detail vaccination information in table model.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class VaccinationInformationGUI extends JFrame {

    private final JPanel content_pnl;
    private final VaccinationTableModel vaccinationTableModel;
    private JTable vaccInfo_table;
    private PCVS pcvs;
    private File file = null;

    /**
     * Create the vaccination information frame.
     */
    public VaccinationInformationGUI() {
        addWindowListener(new WindowAdapter() {
            /**
             * Invoke when JFrame is opened.
             * This method display detail Vaccination information.
             * @param e the event to process.
             */
            @Override
            public void windowOpened(WindowEvent e) {
                vaccinationTableModel.clear();
                pcvs.getPCVSVaccines()
                    // traverse vaccination obj in batch collection
                    .forEach(vaccine -> vaccine.getBatches().stream()
                        .flatMap(batch -> batch.getVaccinations().stream())
                        // avoid duplicate
                        .filter(vaccination -> !vaccinationTableModel
                            .contains(vaccination))
                        .forEach(vaccinationTableModel::add));
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

        JPanel vaccInfoMenu_pnl = new JPanel();
        vaccInfoMenu_pnl.setLayout(null);
        vaccInfoMenu_pnl.setBackground(new Color(79, 189, 194));
        vaccInfoMenu_pnl.setBounds(0, 315, 300, 40);
        sideBarAdminMenu_pnl.add(vaccInfoMenu_pnl);

        JLabel vaccInfo_lbl = new JLabel("Vaccination Information");
        vaccInfo_lbl.setForeground(WHITE);
        vaccInfo_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 16));
        vaccInfo_lbl.setBounds(36, 6, 258, 28);
        vaccInfoMenu_pnl.add(vaccInfo_lbl);

        JPanel vaccInfoSel_pnl = new JPanel();
        vaccInfoSel_pnl.setBackground(new Color(253, 210, 155));
        vaccInfoSel_pnl.setBounds(0, 0, 5, 40);
        vaccInfoMenu_pnl.add(vaccInfoSel_pnl);

        JPanel vaccInfo_pnl = new JPanel();
        vaccInfo_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(vaccInfo_pnl);
        vaccInfo_pnl.setLayout(null);

        JPanel vaccInfoTitle_pnl = new JPanel();
        vaccInfoTitle_pnl.setLayout(null);
        vaccInfoTitle_pnl.setBackground(PINK);
        vaccInfoTitle_pnl.setBounds(0, 0, 700, 70);
        vaccInfo_pnl.add(vaccInfoTitle_pnl);

        JLabel vaccInfoTitle_lbl = new JLabel("Vaccination Information");
        vaccInfoTitle_lbl.setHorizontalAlignment(
            CENTER);
        vaccInfoTitle_lbl.setForeground(WHITE);
        vaccInfoTitle_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 20));
        vaccInfoTitle_lbl.setBounds(6, 6, 688, 58);
        vaccInfoTitle_pnl.add(vaccInfoTitle_lbl);

        JPanel vaccInfoTable_pnl = new JPanel();
        vaccInfoTable_pnl.setBounds(0, 70, 700, 464);
        vaccInfo_pnl.add(vaccInfoTable_pnl);
        vaccInfoTable_pnl.setLayout(null);

        JScrollPane vaccInfo_scrollPane = new JScrollPane(vaccInfo_table);
        vaccInfo_scrollPane.setBorder(null);
        vaccInfo_scrollPane.setBounds(0, 0, 700, 464);
        vaccInfoTable_pnl.add(vaccInfo_scrollPane);

        String[] colHeaderVaccination = {"Vaccination ID",
            "Appointment Date", "Status", "Remaks"};
        vaccinationTableModel =
            new VaccinationTableModel(colHeaderVaccination, 0);

        vaccInfo_table = new JTable(vaccinationTableModel);
        vaccInfo_table.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 12));
        vaccInfo_table.setBorder(null);
        vaccInfo_scrollPane.setViewportView(vaccInfo_table);

        JPanel vaccInfoBtn_pnl = new JPanel();
        vaccInfoBtn_pnl.setBackground(PINK);
        vaccInfoBtn_pnl.setBounds(0, 533, 700, 39);
        vaccInfo_pnl.add(vaccInfoBtn_pnl);
        vaccInfoBtn_pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        back_btn.addActionListener(e -> {
            AdminMenuGUI adminMenu = new AdminMenuGUI();
            adminMenu.pack();
            adminMenu.setBounds(0, 0, 1000, 600);
            adminMenu
                .setLocationRelativeTo(VaccinationInformationGUI.this);
            adminMenu.setPCVS(pcvs);
            adminMenu.setFile(file);
            adminMenu.setVisible(true);
            setVisible(false);
        });
        vaccInfoBtn_pnl.add(back_btn);
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
