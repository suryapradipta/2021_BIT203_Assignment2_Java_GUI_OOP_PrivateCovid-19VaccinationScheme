package dialog;

import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.border.LineBorder;

import static java.awt.BorderLayout.*;
import static java.awt.Color.*;
import static java.awt.Font.*;
import static javax.swing.JOptionPane.*;

/**
 * AppointmentDialog class allow pcvs GUI application to get
 * upcoming date from Patient.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public class AppointmentDialog extends JDialog {

    private final JPanel content_pnl = new JPanel();
    private final JTextField date_tf;

    private String upcomingDate;

    /**
     * Create the appointment dialog.
     */
    public AppointmentDialog(JFrame parent) {
        super(parent, true);
        setTitle("Request Vaccination Appointment");
        setBounds(0, 0, 700, 440);
        getContentPane().setLayout(new BorderLayout());
        content_pnl.setBackground(WHITE);
        content_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(content_pnl, CENTER);
        content_pnl.setLayout(null);
        {
            JLabel date_lbl = new JLabel("Upcoming Date (mm dd yyyy)");
            date_lbl.setToolTipText("");
            date_lbl.setForeground(DARK_GRAY);
            date_lbl.setFont(
                new Font("Neue Haas Grotesk Text Pro", PLAIN, 14));
            date_lbl.setBounds(219, 136, 199, 18);
            content_pnl.add(date_lbl);
        }
        {
            JPanel date_pnl = new JPanel();
            date_pnl.setLayout(null);
            date_pnl.setOpaque(false);
            date_pnl.setBorder(new LineBorder(
                new Color(238, 238, 238), 2, true));
            date_pnl.setBounds(219, 166, 262, 40);
            content_pnl.add(date_pnl);
            {
                date_tf = new JTextField();
                date_tf.setForeground(DARK_GRAY);
                date_tf.setFont(new Font(".AppleSystemUIFont",
                    PLAIN, 13));
                date_tf.setColumns(10);
                date_tf.setBorder(null);
                date_tf.setBackground(WHITE);
                date_tf.setBounds(6, 6, 250, 28);
                date_pnl.add(date_tf);
            }
        }

        {
            JPanel btn_pnl = new JPanel();
            btn_pnl.setLayout(new FlowLayout(
                FlowLayout.RIGHT));
            getContentPane().add(btn_pnl, SOUTH);
            {
                JButton request_btn = new JButton("Request");
                request_btn.setFont(new Font(".AppleSystemUIFont",
                    PLAIN, 13));
                request_btn.addActionListener(e -> {
                    upcomingDate = date_tf.getText().trim();
                    if (upcomingDate.equals("")) {
                        showMessageDialog(
                            AppointmentDialog.this,
                            "Please fill up all fields",
                            null, WARNING_MESSAGE);
                        date_tf.requestFocus();
                    } else {
                        date_tf.setText("");
                        setVisible(false);
                    }
                });
                btn_pnl.add(request_btn);
                getRootPane().setDefaultButton(request_btn);
            }
        }
    }

    /**
     * Returns valid upcoming date to
     * record the vaccination appointment.
     *
     * @return value of date in format (mm dd yyyy)
     */
    public String getUpcomingDate() {
        return upcomingDate;
    }
}
