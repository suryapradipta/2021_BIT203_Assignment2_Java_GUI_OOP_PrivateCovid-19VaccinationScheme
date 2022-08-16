package frame;

import pcvs.Administrator;
import pcvs.PCVS;
import pcvs.User;
import table.AdministratorTableModel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.File;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.SwingConstants.*;

/**
 * AdministratorInformationGUI class to pcvs GUI application
 * to display detail Administrator information.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA
 */
public class AdministratorInformationGUI extends JFrame {

    private final AdministratorTableModel administratorTableModel;
    private JTable admin_table;
    private PCVS pcvs;
    private File file = null;

    /**
     * Create the AdminMenuFrame.
     */
    public AdministratorInformationGUI() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                administratorTableModel.clear();
                IntStream.range(0, pcvs.getPCVSUsers().size())

                    // get Administrator object in User collection
                    .filter(i -> pcvs.getPCVSUsers().get(i)
                        instanceof Administrator)
                    .mapToObj(i -> (Administrator)
                        pcvs.getPCVSUsers().get(i))

                    // avoid duplicate
                    .filter(administrator -> !administratorTableModel
                        .contains(administrator))
                    .forEach(administratorTableModel::add);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JPanel adminInfo_pnl = new JPanel();
        adminInfo_pnl.setLayout(null);
        adminInfo_pnl.setBackground(new Color(79, 189, 194));
        adminInfo_pnl.setBounds(0, 235, 300, 40);
        sideBarAdminMenu_pnl.add(adminInfo_pnl);

        JLabel adminInfo_lbl = new JLabel("Administarator Information");
        adminInfo_lbl.setForeground(Color.WHITE);
        adminInfo_lbl.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 16));
        adminInfo_lbl.setBounds(36, 6, 258, 28);
        adminInfo_pnl.add(adminInfo_lbl);

        JPanel adminInfoSel_pnl = new JPanel();
        adminInfoSel_pnl.setBackground(new Color(253, 210, 155));
        adminInfoSel_pnl.setBounds(0, 0, 5, 40);
        adminInfo_pnl.add(adminInfoSel_pnl);

        JPanel adminTable_pnl = new JPanel();
        adminTable_pnl.setBounds(300, 0, 700, 572);
        content_pnl.add(adminTable_pnl);
        adminTable_pnl.setLayout(null);

        JScrollPane admin_scrollPane = new JScrollPane(admin_table);
        admin_scrollPane.setBorder(null);
        admin_scrollPane.setBounds(0, 70, 700, 466);
        adminTable_pnl.add(admin_scrollPane);

        String[] adminColHeader = {"Username", "Password", "Email",
            "Full Name", "Staff ID"};
        administratorTableModel =
            new AdministratorTableModel(adminColHeader, 0);

        admin_table = new JTable(administratorTableModel);
        admin_table.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 12));
        admin_table.setBorder(null);
        admin_scrollPane.setViewportView(admin_table);

        JPanel adminInfoTitle_pnl = new JPanel();
        adminInfoTitle_pnl.setLayout(null);
        adminInfoTitle_pnl.setBackground(Color.PINK);
        adminInfoTitle_pnl.setBounds(0, 0, 700, 70);
        adminTable_pnl.add(adminInfoTitle_pnl);

        JLabel adminInfoTitle_lbl =
            new JLabel("Administrator Information");
        adminInfoTitle_lbl.setHorizontalAlignment(CENTER);
        adminInfoTitle_lbl.setForeground(Color.WHITE);
        adminInfoTitle_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", Font.PLAIN, 20));
        adminInfoTitle_lbl.setBounds(6, 6, 688, 58);
        adminInfoTitle_pnl.add(adminInfoTitle_lbl);

        JPanel adminBtn_pnl = new JPanel();
        adminBtn_pnl.setBackground(Color.PINK);
        adminBtn_pnl.setBounds(0, 533, 700, 39);
        adminTable_pnl.add(adminBtn_pnl);
        adminBtn_pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton originalOrder_btn = new JButton("Original Order");
        originalOrder_btn.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 13));
        originalOrder_btn.addActionListener(e -> {
            administratorTableModel.clear();
            IntStream.range(0, pcvs.getPCVSUsers().size())

                // get Administrator object in User collection
                .filter(i -> pcvs.getPCVSUsers().get(i)
                    instanceof Administrator)
                .mapToObj(i -> (Administrator)
                    pcvs.getPCVSUsers().get(i))

                // avoid duplicate
                .filter(administrator -> !administratorTableModel
                    .contains(administrator))
                .forEach(administratorTableModel::add);
        });
        adminBtn_pnl.add(originalOrder_btn);

        JButton sorted_btn = new JButton("Sorted by Full Name");
        sorted_btn.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 13));
        sorted_btn.addActionListener(e -> {
            administratorTableModel.clear();
            ArrayList<Administrator> administrators =
                IntStream.range(0, pcvs.getPCVSUsers().size())
                    .filter(i -> pcvs.getPCVSUsers().get(i)
                        instanceof Administrator)
                    .mapToObj(i -> (Administrator)
                        pcvs.getPCVSUsers().get(i))

                    // compare by full name
                    .sorted(Comparator.comparing(User::getFullName))
                    .collect(Collectors.toCollection(ArrayList::new));
            administrators.forEach(administratorTableModel::add);
        });
        adminBtn_pnl.add(sorted_btn);

        JButton delete_btn = new JButton("Delete Selection");
        delete_btn.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 13));
        delete_btn.addActionListener(e -> {
            int selRow = admin_table.getSelectedRow();
            if (selRow != -1) {
                Administrator a =
                    administratorTableModel.getAdministrator(selRow);
                int n = showConfirmDialog(null,
                    "Are you sure you want to delete " +
                        a.getUsername() + " with staff ID " +
                        a.getStaffID());
                if (n == JOptionPane.YES_OPTION) {
                    administratorTableModel.remove(selRow);
                    if (pcvs.getUser(a) != null) {
                        pcvs.getPCVSUsers().remove(pcvs.getUser(a));
                        int iAdmin = pcvs
                            .getHealthcareCentre(a.getStaffID());
                        pcvs.getPCVSHealthcareCentres().get(iAdmin)
                            .getAdministrators()
                            .remove(pcvs.getAdministrator(
                                a.getStaffID()));
                    }
                }
            } else
                JOptionPane.showMessageDialog(null,
                    "Please select a row!", null,
                    JOptionPane.WARNING_MESSAGE);
        });
        adminBtn_pnl.add(delete_btn);

        JButton back_btn = new JButton("Back");
        back_btn.setFont(new Font(".AppleSystemUIFont",
            Font.PLAIN, 13));
        back_btn.addActionListener(e -> {
            AdminMenuGUI adminMenu = new AdminMenuGUI();
            adminMenu.pack();
            adminMenu.setBounds(0, 0, 1000, 600);
            adminMenu.setLocationRelativeTo(
                AdministratorInformationGUI.this);
            adminMenu.setPCVS(pcvs);
            adminMenu.setFile(file);
            adminMenu.setVisible(true);
            setVisible(false);
        });
        adminBtn_pnl.add(back_btn);
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
}
