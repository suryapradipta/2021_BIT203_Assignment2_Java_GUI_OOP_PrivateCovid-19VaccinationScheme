package frame;

import dialog.LoginDialog;
import pcvs.Administrator;
import pcvs.PCVS;
import pcvs.Patient;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.*;

import static java.awt.Color.*;
import static java.awt.Font.*;
import static javax.swing.JFileChooser.*;
import static javax.swing.JOptionPane.*;
import static javax.swing.SwingConstants.*;

/**
 * PCVSGUI class that will display the main page to the user and
 * as a repository for pcvs data objects to file.
 * PCVSGUI allows users to register for accounts
 * as a patient or administrator.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class PCVSGUI extends JFrame
    implements ActionListener {

    private static Integer admID = 0;
    final JFileChooser flChooser = new JFileChooser();
    private final JPanel signUpAdmin_pnl;
    private final JPanel patientSignUp_pnl;
    private final JTextField usernameAdmin_tf;
    private final JTextField emailAdmin_tf;
    private final JTextField fullNameAdmin_tf;
    private final JPasswordField passAdmin_pf;
    JMenuItem open_mntm;
    JMenuItem new_mntm;
    JMenuItem save_mntm;
    JMenuItem saveAs_mntm;
    JMenuItem exit_mntm;
    private final JTextField usernamePatient_tf;
    private final JTextField emailPatient_tf;
    private final JTextField fullNamePatient_tf;
    private final JPasswordField passPatient_pf;
    private final JTextField ICPassport_tf;
    private final JPanel administratorSel_pnl;
    private final JPanel patientSel_pnl;
    JRadioButton balimed_rdbtn;
    // Global attribute used on methods or listeners.
    private PCVS pcvs;
    private String usernameLogin;
    private File fileDir = null;
    private String passwordLogin;
    private String userLogin;
    private Patient patient;
    private int iAdmin;
    private int iPatient;
    private int iHealthCentre;
    private Administrator administrator;

    /**
     * Class constructor to
     * create GUI for sign up user.
     */
    public PCVSGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);

        JMenuBar pcvsMenuBar = new JMenuBar();
        pcvsMenuBar.setFont(new Font("Montserrat", PLAIN, 14));
        pcvsMenuBar.setBorderPainted(false);
        pcvsMenuBar.setBackground(WHITE);
        setJMenuBar(pcvsMenuBar);

        JMenuItem file_mn = new JMenu("File");
        file_mn.setFont(new Font(".AppleSystemUIFont", PLAIN, 14));
        pcvsMenuBar.add(file_mn);

        new_mntm = new JMenuItem("New");
        file_mn.add(new_mntm);

        open_mntm = new JMenuItem("Open");
        file_mn.add(open_mntm);

        save_mntm = new JMenuItem("Save");
        file_mn.add(save_mntm);

        saveAs_mntm = new JMenuItem("Save As");
        file_mn.add(saveAs_mntm);

        exit_mntm = new JMenuItem("Exit");
        file_mn.add(exit_mntm);

        // Add a listener to call when the condition is true.
        new_mntm.addActionListener(this);
        saveAs_mntm.addActionListener(this);
        open_mntm.addActionListener(this);
        save_mntm.addActionListener(this);
        exit_mntm.addActionListener(this);

        // Global component used on methods or listeners.
        JPanel content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(content_pnl);
        content_pnl.setLayout(null);

        pcvs = new PCVS();

        JPanel logo_pnl = new JPanel();
        logo_pnl.setBounds(0, 0, 400, 550);
        content_pnl.add(logo_pnl);
        logo_pnl.setLayout(null);

        JLabel logo_lbl = new JLabel("");
        logo_lbl.setIcon(new ImageIcon(
            PCVSGUI.class.getResource("/res/signUp.png")));
        logo_lbl.setBounds(0, 0, 400, 550);
        logo_pnl.add(logo_lbl);

        JPanel signUp_pnl = new JPanel();
        signUp_pnl.setBackground(WHITE);
        signUp_pnl.setBounds(400, 0, 600, 550);
        content_pnl.add(signUp_pnl);
        signUp_pnl.setLayout(null);

        JPanel login_pnl = new JPanel();
        login_pnl.setOpaque(false);
        login_pnl.setBounds(0, 0, 600, 125);
        signUp_pnl.add(login_pnl);
        login_pnl.setBackground(WHITE);
        login_pnl.setLayout(null);

        JLabel signUptitle_lbl = new JLabel("Sign up to PCVS ");
        signUptitle_lbl.setForeground(DARK_GRAY);
        signUptitle_lbl.setBounds(130, 57, 179, 40);
        login_pnl.add(signUptitle_lbl);
        signUptitle_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
            PLAIN, 18));

        JLabel description_lbl = new JLabel("Already have account?");
        description_lbl.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 13));
        description_lbl.setBounds(130, 103, 179, 16);
        login_pnl.add(description_lbl);
        description_lbl.setForeground(GRAY);

        JButton login_btn = new JButton("Log in");
        login_btn.addActionListener(e -> {
            LoginDialog login = new LoginDialog(PCVSGUI.this);
            login.pack();
            login.setBounds(0, 0, 1000, 600);
            login.setLocationRelativeTo(PCVSGUI.this);
            login.setVisible(true);

            usernameLogin = login.getUsernameAcc();
            passwordLogin = login.getPassAcc();

            // Gets the user type , either patient or administrator.
            userLogin = pcvs.getUserType(usernameLogin, passwordLogin);

            // Get admin and patient indexes
            // as a way to get objects by index.
            iAdmin = pcvs.validationAdminLogin(
                usernameLogin, passwordLogin);
            iPatient = pcvs.validationPatientLogin(
                usernameLogin, passwordLogin);

            if (usernameLogin != null && passwordLogin != null) {
                if (userLogin != null) {
                    if (userLogin.equals("admin")) {
                        showMessageDialog(PCVSGUI.this,
                            "Welcome to " +
                                pcvs.getPCVSHealthcareCentres()
                                    .get(iAdmin)
                                    .getCentreName(),
                            null, PLAIN_MESSAGE);

                        // Launch the administrator menu GUI.
                        AdminMenuGUI adminMenu = new AdminMenuGUI();
                        adminMenu.pack();
                        adminMenu.setBounds(0, 0, 1000, 600);
                        adminMenu.setLocationRelativeTo(PCVSGUI.this);

                        // Copy value of attribute to another JFrame.
                        adminMenu.setPCVS(pcvs);
                        adminMenu.setFile(fileDir);
                        adminMenu.setIndexAdmin(iAdmin);

                        adminMenu.setVisible(true);
                        setVisible(false);
                    } else if (userLogin.equals("patient")) {
                        showMessageDialog(PCVSGUI.this,
                            "Welcome, " + pcvs.getPCVSUsers()
                                .get(iPatient)
                                .getFullName(),
                            null, PLAIN_MESSAGE);

                        // Launch patient menu GUI.
                        PatientMenuGUI patientMenu = new PatientMenuGUI();
                        patientMenu.pack();
                        patientMenu.setBounds(0, 0, 1000, 600);
                        patientMenu.setLocationRelativeTo(PCVSGUI.this);

                        // Copy value of attribute to another JFrame.
                        patientMenu.setPCVS(pcvs);
                        patientMenu.setIndexPatient(iPatient);
                        patientMenu.setFile(fileDir);

                        patientMenu.setVisible(true);
                        setVisible(false);
                    }
                } else
                    showMessageDialog(PCVSGUI.this,
                        "Can't find your account", null,
                        WARNING_MESSAGE);
            } else
                showMessageDialog(PCVSGUI.this,
                    "Sign in canceled", null,
                    WARNING_MESSAGE);
        });
        login_btn.setVerticalAlignment(BOTTOM);
        login_btn.setForeground(new Color(79, 189, 194));
        login_btn.setBackground(WHITE);
        login_btn.setBorder(null);
        login_btn.setOpaque(true);
        login_btn.setBounds(285, 90, 39, 29);
        login_pnl.add(login_btn);

        signUpAdmin_pnl = new JPanel();
        signUpAdmin_pnl.setLayout(null);
        signUpAdmin_pnl.setBackground(WHITE);
        signUpAdmin_pnl.setBounds(0, 124, 600, 426);
        signUp_pnl.add(signUpAdmin_pnl);

        JLabel usernameAdmin_lbl = new JLabel("Username");
        usernameAdmin_lbl.setForeground(DARK_GRAY);
        usernameAdmin_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        usernameAdmin_lbl.setBounds(130, 83, 94, 18);
        signUpAdmin_pnl.add(usernameAdmin_lbl);

        JPanel usernameAdmin_pnl = new JPanel();
        usernameAdmin_pnl.setLayout(null);
        usernameAdmin_pnl.setOpaque(false);
        usernameAdmin_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        usernameAdmin_pnl.setBounds(130, 113, 125, 40);
        signUpAdmin_pnl.add(usernameAdmin_pnl);

        usernameAdmin_tf = new JTextField();
        usernameAdmin_tf.setFont(
            new Font(".AppleSystemUIFont", PLAIN, 12));
        usernameAdmin_tf.setForeground(DARK_GRAY);
        usernameAdmin_tf.setColumns(10);
        usernameAdmin_tf.setBorder(null);
        usernameAdmin_tf.setBackground(WHITE);
        usernameAdmin_tf.setBounds(6, 6, 113, 28);
        usernameAdmin_pnl.add(usernameAdmin_tf);

        JLabel emailAdmin_lbl = new JLabel("Email");
        emailAdmin_lbl.setForeground(DARK_GRAY);
        emailAdmin_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        emailAdmin_lbl.setBounds(130, 165, 94, 18);
        signUpAdmin_pnl.add(emailAdmin_lbl);

        JPanel emailAdmin_pnl = new JPanel();
        emailAdmin_pnl.setLayout(null);
        emailAdmin_pnl.setOpaque(false);
        emailAdmin_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        emailAdmin_pnl.setBounds(130, 195, 262, 40);
        signUpAdmin_pnl.add(emailAdmin_pnl);

        emailAdmin_tf = new JTextField();
        emailAdmin_tf.setFont(
            new Font(".AppleSystemUIFont", PLAIN, 12));
        emailAdmin_tf.setForeground(DARK_GRAY);
        emailAdmin_tf.setColumns(10);
        emailAdmin_tf.setBorder(null);
        emailAdmin_tf.setBackground(WHITE);
        emailAdmin_tf.setBounds(6, 6, 250, 28);
        emailAdmin_pnl.add(emailAdmin_tf);

        JLabel fullNameAdmin_lbl = new JLabel("Full Name");
        fullNameAdmin_lbl.setForeground(DARK_GRAY);
        fullNameAdmin_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        fullNameAdmin_lbl.setToolTipText("");
        fullNameAdmin_lbl.setBounds(130, 247, 94, 18);
        signUpAdmin_pnl.add(fullNameAdmin_lbl);

        JPanel fullNameAdmin_pnl = new JPanel();
        fullNameAdmin_pnl.setLayout(null);
        fullNameAdmin_pnl.setOpaque(false);
        fullNameAdmin_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        fullNameAdmin_pnl.setBounds(130, 277, 262, 40);
        signUpAdmin_pnl.add(fullNameAdmin_pnl);

        fullNameAdmin_tf = new JTextField();
        fullNameAdmin_tf.setForeground(DARK_GRAY);
        fullNameAdmin_tf.setFont(
            new Font(".AppleSystemUIFont", PLAIN, 12));
        fullNameAdmin_tf.setColumns(10);
        fullNameAdmin_tf.setBorder(null);
        fullNameAdmin_tf.setBackground(WHITE);
        fullNameAdmin_tf.setBounds(6, 6, 250, 28);
        fullNameAdmin_pnl.add(fullNameAdmin_tf);

        JLabel passwordAdmin_lbl = new JLabel("Password");
        passwordAdmin_lbl.setForeground(DARK_GRAY);
        passwordAdmin_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        passwordAdmin_lbl.setBounds(267, 83, 94, 18);
        signUpAdmin_pnl.add(passwordAdmin_lbl);

        JPanel passAdmin_pnl = new JPanel();
        passAdmin_pnl.setLayout(null);
        passAdmin_pnl.setOpaque(false);
        passAdmin_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        passAdmin_pnl.setBounds(267, 113, 125, 40);
        signUpAdmin_pnl.add(passAdmin_pnl);

        passAdmin_pf = new JPasswordField();
        passAdmin_pf.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 9));
        passAdmin_pf.setForeground(DARK_GRAY);
        passAdmin_pf.setBorder(null);
        passAdmin_pf.setBounds(6, 6, 113, 28);
        passAdmin_pnl.add(passAdmin_pf);

        patientSignUp_pnl = new JPanel();
        patientSignUp_pnl.setLayout(null);
        patientSignUp_pnl.setBackground(WHITE);
        patientSignUp_pnl.setBounds(0, 124, 600, 426);
        signUp_pnl.add(patientSignUp_pnl);

        JLabel usernamePatient_lbl = new JLabel("Username");
        usernamePatient_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        usernamePatient_lbl.setBounds(130, 16, 94, 18);
        patientSignUp_pnl.add(usernamePatient_lbl);

        JPanel usernamePatient_pnl = new JPanel();
        usernamePatient_pnl.setLayout(null);
        usernamePatient_pnl.setOpaque(false);
        usernamePatient_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        usernamePatient_pnl.setBounds(130, 46, 125, 40);
        patientSignUp_pnl.add(usernamePatient_pnl);

        usernamePatient_tf = new JTextField();
        usernamePatient_tf.setForeground(DARK_GRAY);
        usernamePatient_tf.setFont(
            new Font("Helvetica", PLAIN, 13));
        usernamePatient_tf.setColumns(10);
        usernamePatient_tf.setBorder(null);
        usernamePatient_tf.setBackground(WHITE);
        usernamePatient_tf.setBounds(6, 6, 113, 28);
        usernamePatient_pnl.add(usernamePatient_tf);

        JLabel emailPatient_lbl = new JLabel("Email");
        emailPatient_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        emailPatient_lbl.setBounds(130, 98, 94, 18);
        patientSignUp_pnl.add(emailPatient_lbl);

        JPanel emailPatient_pnl = new JPanel();
        emailPatient_pnl.setLayout(null);
        emailPatient_pnl.setOpaque(false);
        emailPatient_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        emailPatient_pnl.setBounds(130, 128, 262, 40);
        patientSignUp_pnl.add(emailPatient_pnl);

        emailPatient_tf = new JTextField();
        emailPatient_tf.setForeground(DARK_GRAY);
        emailPatient_tf.setFont(new Font("Helvetica", PLAIN, 13));
        emailPatient_tf.setColumns(10);
        emailPatient_tf.setBorder(null);
        emailPatient_tf.setBackground(WHITE);
        emailPatient_tf.setBounds(6, 6, 250, 28);
        emailPatient_pnl.add(emailPatient_tf);

        JLabel fullNamePatient_lbl = new JLabel("Full Name");
        fullNamePatient_lbl.setToolTipText("");
        fullNamePatient_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        fullNamePatient_lbl.setBounds(130, 180, 94, 18);
        patientSignUp_pnl.add(fullNamePatient_lbl);

        JPanel fullNamePatient_pnl = new JPanel();
        fullNamePatient_pnl.setLayout(null);
        fullNamePatient_pnl.setOpaque(false);
        fullNamePatient_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        fullNamePatient_pnl.setBounds(130, 210, 262, 40);
        patientSignUp_pnl.add(fullNamePatient_pnl);

        fullNamePatient_tf = new JTextField();
        fullNamePatient_tf.setForeground(DARK_GRAY);
        fullNamePatient_tf.setFont
            (new Font("Helvetica", PLAIN, 13));
        fullNamePatient_tf.setColumns(10);
        fullNamePatient_tf.setBorder(null);
        fullNamePatient_tf.setBackground(WHITE);
        fullNamePatient_tf.setBounds(6, 6, 250, 28);
        fullNamePatient_pnl.add(fullNamePatient_tf);

        JLabel passPatient_lbl = new JLabel("Password");
        passPatient_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        passPatient_lbl.setBounds(267, 16, 94, 18);
        patientSignUp_pnl.add(passPatient_lbl);

        JPanel passPatient_pnl = new JPanel();
        passPatient_pnl.setLayout(null);
        passPatient_pnl.setOpaque(false);
        passPatient_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        passPatient_pnl.setBounds(267, 46, 125, 40);
        patientSignUp_pnl.add(passPatient_pnl);

        passPatient_pf = new JPasswordField();
        passPatient_pf.setForeground(DARK_GRAY);
        passPatient_pf.setFont(new Font("Helvetica", PLAIN, 9));
        passPatient_pf.setBorder(null);
        passPatient_pf.setBounds(6, 6, 113, 28);
        passPatient_pnl.add(passPatient_pf);

        JLabel ICPassportPatient_lbl = new JLabel("IC or Passport");
        ICPassportPatient_lbl.setToolTipText("");
        ICPassportPatient_lbl.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        ICPassportPatient_lbl.setBounds(130, 262, 98, 18);
        patientSignUp_pnl.add(ICPassportPatient_lbl);

        JPanel ICPassport_pnl = new JPanel();
        ICPassport_pnl.setLayout(null);
        ICPassport_pnl.setOpaque(false);
        ICPassport_pnl.setBorder(new LineBorder(
            new Color(238, 238, 238), 2, true));
        ICPassport_pnl.setBounds(130, 292, 262, 40);
        patientSignUp_pnl.add(ICPassport_pnl);

        ICPassport_tf = new JTextField();
        ICPassport_tf.setForeground(DARK_GRAY);
        ICPassport_tf.setFont(new Font(".AppleSystemUIFont", PLAIN, 13));
        ICPassport_tf.setColumns(10);
        ICPassport_tf.setBorder(null);
        ICPassport_tf.setBackground(WHITE);
        ICPassport_tf.setBounds(6, 6, 250, 28);
        ICPassport_pnl.add(ICPassport_tf);

        JButton btnCreateAnAccountPT = new JButton("Create an Account");
        btnCreateAnAccountPT.addActionListener(e -> {
            String usrname = usernamePatient_tf.getText().trim();
            String pwd = passPatient_pf.getText().trim();
            String eml = emailPatient_tf.getText().trim();
            String fName = fullNamePatient_tf.getText().trim();
            String icPassStr = ICPassport_tf.getText().trim();

            if (usrname.isEmpty() || pwd.isEmpty() || eml.isEmpty()
                || fName.isEmpty() || icPassStr.isEmpty()) {

                showMessageDialog(PCVSGUI.this,
                    "Please fill in all fields",
                    null, WARNING_MESSAGE);
                usernamePatient_tf.requestFocus();
            } else {
                try {
                    int ICPassport = Integer.parseInt(icPassStr);
                    patient = new Patient(usrname, pwd, eml,
                        fName, ICPassport);

                    // Validate username to avoid duplicate username.
                    if (pcvs.usernameValidation(patient.getUsername())) {
                        showMessageDialog(PCVSGUI.this,
                            "That username is taken! " +
                                "Try another username",
                            null, WARNING_MESSAGE);
                    } else {
                        pcvs.addUser(patient);
                        showMessageDialog(PCVSGUI.this,
                            "Sign up success! A Patient account " +
                                "is created\n\n"
                                + patient.toString(),
                            null, PLAIN_MESSAGE);
                        clearText();
                    }
                } catch (NumberFormatException nfe) {
                    showMessageDialog(null,
                        "Invalid Integers!",
                        null, WARNING_MESSAGE);
                    ICPassport_tf.setText("");
                    ICPassport_tf.requestFocus();
                }
            }
        });
        btnCreateAnAccountPT.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        btnCreateAnAccountPT.setBounds(130, 344, 137, 40);

        patientSignUp_pnl.add(btnCreateAnAccountPT);
        getRootPane().setDefaultButton(btnCreateAnAccountPT);

        JPanel administrator_pnl = new JPanel();
        administrator_pnl.setBounds(320, 0, 140, 40);
        signUp_pnl.add(administrator_pnl);
        administrator_pnl.addMouseListener(
            new PanelButtonMouseAdapter(administrator_pnl) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    signUpMenuClicked(signUpAdmin_pnl);
                    userClicked(administratorSel_pnl);
                }
            });
        administrator_pnl.setLayout(null);
        administrator_pnl.setBorder(null);
        administrator_pnl.setBackground(new Color(79, 189, 194));

        JLabel lblAdministrator = new JLabel("Administrator");
        lblAdministrator.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        lblAdministrator.setHorizontalAlignment(CENTER);
        lblAdministrator.setForeground(WHITE);
        lblAdministrator.setBorder(null);
        lblAdministrator.setBackground(new Color(79, 189, 194));
        lblAdministrator.setBounds(6, 6, 128, 28);
        administrator_pnl.add(lblAdministrator);

        administratorSel_pnl = new JPanel();
        administratorSel_pnl.setBackground(new Color(253, 210, 155));
        administratorSel_pnl.setBounds(0, 0, 140, 3);
        administrator_pnl.add(administratorSel_pnl);

        JPanel Patient_pnl = new JPanel();
        Patient_pnl.setBounds(460, 0, 140, 40);
        signUp_pnl.add(Patient_pnl);
        Patient_pnl.addMouseListener(
            new PanelButtonMouseAdapter(Patient_pnl) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    signUpMenuClicked(patientSignUp_pnl);
                    userClicked(patientSel_pnl);
                }
            });
        Patient_pnl.setLayout(null);
        Patient_pnl.setBorder(null);
        Patient_pnl.setBackground(new Color(79, 189, 194));

        JLabel lblPatient = new JLabel("Patient");
        lblPatient.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        lblPatient.setHorizontalAlignment(CENTER);
        lblPatient.setForeground(WHITE);
        lblPatient.setBorder(null);
        lblPatient.setBackground(new Color(79, 189, 194));
        lblPatient.setBounds(6, 6, 128, 28);
        Patient_pnl.add(lblPatient);

        patientSel_pnl = new JPanel();
        patientSel_pnl.setBackground(new Color(253, 210, 155));
        patientSel_pnl.setBounds(0, 0, 140, 3);
        Patient_pnl.add(patientSel_pnl);

        JLabel healthcareCentres_lbl = new JLabel("");
        healthcareCentres_lbl.setForeground(GRAY);
        healthcareCentres_lbl.setHorizontalAlignment(LEFT);
        healthcareCentres_lbl.setFont(
            new Font(".AppleSystemUIFont", PLAIN, 12));
        healthcareCentres_lbl.setBounds(130, 53, 395, 18);
        signUpAdmin_pnl.add(healthcareCentres_lbl);

        balimed_rdbtn = new JRadioButton("Balimed");
        balimed_rdbtn.setForeground(DARK_GRAY);
        balimed_rdbtn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 14));
        balimed_rdbtn.addItemListener(e -> {
            // Show different healthcare centre
            // when JRadioButton is clicked.
            if (balimed_rdbtn.isSelected()) {
                healthcareCentres_lbl.setText(
                    pcvs.getPCVSHealthcareCentres().get(0).toString());
            } else
                healthcareCentres_lbl.setText("");
        });
        balimed_rdbtn.setSelected(true);
        ButtonGroup centreNameBtnGrp = new ButtonGroup();
        centreNameBtnGrp.add(balimed_rdbtn);
        balimed_rdbtn.setBounds(130, 16, 94, 23);
        signUpAdmin_pnl.add(balimed_rdbtn);

        JRadioButton primaMedika_rdbtn = new JRadioButton("Prima Medika");
        primaMedika_rdbtn.setForeground(DARK_GRAY);
        primaMedika_rdbtn.setFont(new Font(".AppleSystemUIFont",
            PLAIN, 14));
        primaMedika_rdbtn.addItemListener(e -> {
            // Show different healthcare centre
            // when JRadioButton is clicked.
            if (primaMedika_rdbtn.isSelected()) {
                healthcareCentres_lbl.setText(
                    pcvs.getPCVSHealthcareCentres().get(1).toString());
            } else
                healthcareCentres_lbl.setText("");
        });
        centreNameBtnGrp.add(primaMedika_rdbtn);
        primaMedika_rdbtn.setBounds(265, 16, 127, 23);
        signUpAdmin_pnl.add(primaMedika_rdbtn);

        JButton createAccount_btn = new JButton("Create an Account");
        createAccount_btn.addActionListener(e -> {
            String usrname = usernameAdmin_tf.getText().trim();
            String pwd = passAdmin_pf.getText().trim();
            String eml = emailAdmin_tf.getText().trim();
            String fName = fullNameAdmin_tf.getText().trim();
            iHealthCentre = balimed_rdbtn.isSelected() ? 0 : 1;
            if (usrname.isEmpty() || pwd.isEmpty()
                || eml.isEmpty() || fName.isEmpty()) {
                showMessageDialog(PCVSGUI.this,
                    "Please fill in all fields", null,
                    WARNING_MESSAGE);
                usernameAdmin_tf.requestFocus();
            } else {

                // Validate username to avoid duplicate username.
                if (pcvs.usernameValidation(usrname)) {
                    showMessageDialog(PCVSGUI.this,
                        "That username is taken! " +
                            "Try another username",
                        null, WARNING_MESSAGE);
                    usernameAdmin_tf.requestFocus();
                } else {
                    administrator = new Administrator(usrname, pwd,
                        eml, fName, generateStaffID());

                    // Save administrator object to pcvs
                    pcvs.getPCVSHealthcareCentres().get(iHealthCentre)
                        .setAdministrator(administrator);
                    pcvs.addUser(administrator);

                    showMessageDialog(PCVSGUI.this,
                        "Sign up success! A Healthcare " +
                            "Administrator account is created\n\n"
                            + administrator.toString(),
                        null, PLAIN_MESSAGE);
                    clearText();
                }
            }
        });
        createAccount_btn.setFont(
            new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
        createAccount_btn.setBounds(130, 329, 137, 40);
        signUpAdmin_pnl.add(createAccount_btn);

        userClicked(administratorSel_pnl);
        signUpMenuClicked(signUpAdmin_pnl);
    }

    /**
     * Launch the main page of pcvs application.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PCVSGUI frm = new PCVSGUI();
                frm.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Calculates this length of the Administrator ID.
     * Returns two positive integers + id, zero , or one integer + id.
     *
     * @return a String with the increment of id.
     */
    public static String generateStaffID() {
        // if length id 1 digit add with 00 + id
        String id = (admID.toString().length() == 1) ? ("00" + admID)
            // if length id 2 digit add with 0 + id
            : ((admID.toString().length() == 2) ? ("0" + admID)
            : admID.toString());
        admID++; // increment
        return "ADM" + id;
    }

    /**
     * Assign specific actions to menu item components
     * for managing files in pcvs applications.
     *
     * @param evt determine the object where the event occurs.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == open_mntm || evt.getSource() == new_mntm
            || evt.getSource() == exit_mntm) {
            int choice = showConfirmDialog(this,
                "Do you want to save the changes made " +
                    "to the file?",
                "Warning", YES_NO_CANCEL_OPTION,
                WARNING_MESSAGE);

            if (choice == YES_OPTION) {
                // Existing directory file.
                if (fileDir != null)
                    saveFile();
                else
                    showFileChooser();
            } else if (choice == NO_OPTION) {
                int retVal = flChooser.showOpenDialog(this);
                if (retVal == APPROVE_OPTION) {
                    fileDir = flChooser.getSelectedFile();

                    // Read this file.
                    openFile();

                    showMessageDialog(PCVSGUI.this,
                        "File opened: " + fileDir.getName()
                            + " success!",
                        null, PLAIN_MESSAGE);
                }
            }
        } else if (evt.getSource() == saveAs_mntm ||
            (evt.getSource() == save_mntm && fileDir == null)) {
            showFileChooser();
        } else if (evt.getSource() == save_mntm && fileDir != null) {
            saveFile();
            showMessageDialog(PCVSGUI.this,
                "Saving: " + fileDir.getName(), null,
                PLAIN_MESSAGE);
        }
    }

    /**
     * Save the Serializable object into a file
     * based on the file directory selected by the user.
     */
    public void saveFile() {
        FileOutputStream fileoutStrm;
        try {
            fileoutStrm = new FileOutputStream(fileDir);
            ObjectOutputStream objoutStr =
                new ObjectOutputStream(fileoutStrm);
            objoutStr.writeObject(pcvs);
            objoutStr.flush();
            objoutStr.close();
        } catch (IOException exc) {
            showMessageDialog(PCVSGUI.this,
                "Save file failed", null,
                WARNING_MESSAGE);
        }
    }

    /**
     * Reading data in user-selected directory files.
     */
    public void openFile() {
        FileInputStream fileInStrm;
        try {
            fileInStrm = new FileInputStream(fileDir);
            ObjectInputStream objInStrm =
                new ObjectInputStream(fileInStrm);
            try {
                pcvs = (PCVS) objInStrm.readObject();
            } catch (IOException | ClassNotFoundException exc) {
                exc.printStackTrace();
            }

            objInStrm.close();
        } catch (Exception e) {
            showMessageDialog(PCVSGUI.this,
                "Open file failed", null,
                WARNING_MESSAGE);
        }
    }

    /**
     * Display the file directory to the user.
     */
    public void showFileChooser() {
        int retVal = flChooser.showSaveDialog(this);
        if (retVal == APPROVE_OPTION) {
            fileDir = flChooser.getSelectedFile();
            saveFile();
            showMessageDialog(PCVSGUI.this,
                "Saving: " + fileDir.getName(), null,
                PLAIN_MESSAGE);
        } else
            showMessageDialog(PCVSGUI.this,
                "Save cancelled", null,
                WARNING_MESSAGE);
    }

    /**
     * Remove the field when the user
     * has successfully entered the information.
     */
    private void clearText() {
        usernameAdmin_tf.setText("");
        passAdmin_pf.setText("");
        emailAdmin_tf.setText("");
        fullNameAdmin_tf.setText("");
        balimed_rdbtn.setSelected(true);
        usernamePatient_tf.setText("");
        passPatient_pf.setText("");
        emailPatient_tf.setText("");
        fullNamePatient_tf.setText("");
        ICPassport_tf.setText("");
    }

    /**
     * Shows the sign-up menu between administrator and patient,
     * hiding it when not selected by the user.
     *
     * @param panel user-selected menu.
     */
    public void signUpMenuClicked(JPanel panel) {
        signUpAdmin_pnl.setVisible(false);
        patientSignUp_pnl.setVisible(false);
        panel.setVisible(true);
    }

    /**
     * Show highlights on user-selected menu.
     *
     * @param panel user-selected menu.
     */
    public void userClicked(JPanel panel) {
        administratorSel_pnl.setOpaque(false);
        patientSel_pnl.setOpaque(false);
        panel.setOpaque(true);
    }

    /**
     * Registers this pcvs object to another pcvs attribute
     * in another JFrame.
     * This method will save the current pcvs object
     * to avoid create new data of pcvs.
     *
     * @param pcvsDate the current pcvs object to save.
     */
    public void setPCVS(PCVS pcvsDate) {
        pcvs = pcvsDate;
    }

    /**
     * Registers this file directory to another
     * file attribute in another JFrame.
     * This method will save the current file directory
     * to avoid current file lost.
     *
     * @param directory the directory file to save.
     */
    public void setFileDir(File directory) {
        fileDir = directory;
    }

    /**
     * PanelButtonMouseAdapter inner class to display a different color
     * when the mouse entered the panel.
     */
    private static class PanelButtonMouseAdapter extends MouseAdapter {
        JPanel panel;

        /**
         * PanelButtonMouseAdapter constructor for invocation in listeners.
         *
         * @param panel determine which panel receives the action.
         */
        public PanelButtonMouseAdapter(JPanel panel) {
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
            panel.setBackground(new Color(209, 244, 240));
        }

        /**
         * Change this color when a mouse button
         * has been exited on a component.
         *
         * @param e the event to process.
         */
        @Override
        public void mouseExited(MouseEvent e) {
            panel.setBackground(new Color(79, 189, 194));
        }

        /**
         * Change this color when a mouse button
         * has been pressed on a component.
         *
         * @param e the event to process.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            panel.setBackground(new Color(79, 189, 194));
        }

        /**
         * Change this color when mouse button
         * has been released.
         *
         * @param e the event to process.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            panel.setBackground(new Color(209, 244, 240));
        }
    }
}