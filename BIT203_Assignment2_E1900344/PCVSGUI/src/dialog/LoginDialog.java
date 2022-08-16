package dialog;

import frame.PCVSGUI;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.*;
import java.awt.event.WindowEvent;

import static java.awt.BorderLayout.*;
import static java.awt.Color.*;
import static java.awt.Font.*;

public class LoginDialog extends JDialog {

    private final JTextField usernameUser_tf;
    private final JPasswordField passUser_pf;

    private String usernameLogin;
    private String passwordLogin;

    /**
     * LoginDialog class allow pcvs user login to the application
     * based on valid account.
     *
     * @author I Nyoman Surya Pradipta
     * Student ID: E1900344
     * Date: 01-06-2021
     * Java version: java 17 2021-09-14 LTS
     * IDE : IntelliJ IDEA & Eclipse
     */
    public LoginDialog(JFrame parent) {
        super(parent, true);
        setTitle("Log in");
        setBounds(0, 0, 1000, 600);
        getContentPane().setLayout(new BorderLayout());
        JPanel content_pnl = new JPanel();
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content_pnl, CENTER);
        content_pnl.setLayout(null);

        {
            JPanel logo_pnl = new JPanel();
            logo_pnl.setBounds(0, 0, 400, 533);
            content_pnl.add(logo_pnl);
            logo_pnl.setLayout(null);

            JLabel logo_lbl = new JLabel("");
            logo_lbl.setIcon(new ImageIcon(PCVSGUI.class
                .getResource("/res/signUp.png")));
            logo_lbl.setBounds(0, 0, 400, 533);
            logo_pnl.add(logo_lbl);
        }
        {
            JPanel signIn_pnl = new JPanel();
            signIn_pnl.setBackground(WHITE);
            signIn_pnl.setBounds(400, 0, 600, 533);
            content_pnl.add(signIn_pnl);
            signIn_pnl.setLayout(null);
            {
                JLabel title_lbl = new JLabel("Sign in to PCVS ");
                title_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
                    PLAIN, 18));
                title_lbl.setBounds(155, 145, 179, 40);
                signIn_pnl.add(title_lbl);
                title_lbl.setForeground(DARK_GRAY);
            }
            {
                JLabel desc_lbl = new JLabel(
                    "Use your Administrator or Patient account");
                desc_lbl.setFont(new Font(".AppleSystemUIFont",
                    PLAIN, 13));
                desc_lbl.setForeground(GRAY);
                desc_lbl.setBounds(153, 197, 276, 16);
                signIn_pnl.add(desc_lbl);
            }
            {
                JLabel username_lbl = new JLabel("Username");
                username_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
                    PLAIN, 14));
                username_lbl.setBounds(155, 225, 94, 18);
                signIn_pnl.add(username_lbl);
            }
            {
                JPanel username_pnl = new JPanel();
                username_pnl.setLayout(null);
                username_pnl.setOpaque(false);
                username_pnl.setBorder(new LineBorder(
                    new Color(238, 238, 238), 2, true));
                username_pnl.setBounds(155, 255, 262, 40);
                signIn_pnl.add(username_pnl);
                {
                    usernameUser_tf = new JTextField();
                    usernameUser_tf.setForeground(DARK_GRAY);
                    usernameUser_tf.setFont(new Font(".AppleSystemUIFont",
                        PLAIN, 13));
                    usernameUser_tf.setColumns(10);
                    usernameUser_tf.setBorder(null);
                    usernameUser_tf.setBackground(WHITE);
                    usernameUser_tf.setBounds(6, 6, 250, 28);
                    username_pnl.add(usernameUser_tf);
                }
            }
            {
                JLabel pass_lbl = new JLabel("Password");
                pass_lbl.setFont(new Font("Neue Haas Grotesk Text Pro",
                    PLAIN, 14));
                pass_lbl.setBounds(155, 307, 94, 18);
                signIn_pnl.add(pass_lbl);
            }
            {
                JPanel pass_pnl = new JPanel();
                pass_pnl.setLayout(null);
                pass_pnl.setOpaque(false);
                pass_pnl.setBorder(new LineBorder(
                    new Color(238, 238, 238), 2, true));
                pass_pnl.setBounds(155, 337, 262, 40);
                signIn_pnl.add(pass_pnl);
                {
                    passUser_pf = new JPasswordField();
                    passUser_pf.setForeground(DARK_GRAY);
                    passUser_pf.setFont(new Font(".AppleSystemUIFont",
                        PLAIN, 9));
                    passUser_pf.setBorder(null);
                    passUser_pf.setBounds(6, 6, 250, 28);
                    pass_pnl.add(passUser_pf);
                }
            }
            {
                JButton signIn_btn = new JButton("Sign In");
                signIn_btn.setFont(new Font("Neue Haas Grotesk Text Pro",
                    PLAIN, 14));
                signIn_btn.setBounds(155, 389, 130, 40);
                signIn_pnl.add(signIn_btn);
                signIn_btn.addActionListener(e -> {
                    String username = usernameUser_tf.getText().trim();
                    String password = passUser_pf.getText().trim();

                    if (username.equals("") || password.equals("")) {
                        JOptionPane.showMessageDialog(LoginDialog.this,
                            "Please fill up all fields", null,
                            JOptionPane.WARNING_MESSAGE);
                        usernameUser_tf.requestFocus();
                    } else {
                        clearText();
                        usernameLogin = username;
                        passwordLogin = password;
                        setVisible(false);
                    }
                });
                getRootPane().setDefaultButton(signIn_btn);
            }
        }
        addWindowListener(new WindowAdapter() {
            // anonymous class if user chooses to close dialog
            public void windowClosing(WindowEvent we) {
                usernameLogin = null;
                passwordLogin = null;
            }
        });
        {
            JPanel btn_pnl = new JPanel();
            btn_pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(btn_pnl, SOUTH);
            {
                JButton cancel_btn = new JButton("Cancel");
                cancel_btn.addActionListener(e -> setVisible(false));
                {
                    JButton clear_btn = new JButton("Clear");
                    clear_btn.addActionListener(e -> clearText());
                    btn_pnl.add(clear_btn);
                }
                btn_pnl.add(cancel_btn);
            }
        }
    }

    /**
     * Remove the field when the user
     * has successfully entered the information.
     */
    private void clearText() {
        usernameUser_tf.setText("");
        passUser_pf.setText("");
    }

    /**
     * Returns this username of User input
     * to validate in SignUpGUI.
     * If username and password valid, pcvs GUI application
     * allow User access the menu.
     *
     * @return User account is username.
     */
    public String getUsernameAcc() {
        return usernameLogin;
    }

    /**
     * Returns this password of User input
     * to validate in SignUpGUI.
     * If username and password valid, pcvs GUI application
     * sallow User access the menu.
     *
     * @return User account is password.
     */
    public String getPassAcc() {
        return passwordLogin;
    }
}
