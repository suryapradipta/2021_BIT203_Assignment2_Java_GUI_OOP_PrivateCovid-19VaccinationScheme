package pcvs;

import java.io.Serializable;

/**
 * User is abstract class
 * defines a simple object type that represents a User
 * and superclass of Patient and Administrator class.
 *
 * @author I Nyoman Surya Pradipta
 * Student ID: E1900344
 * Date: 01-06-2021
 * Java version: java 17 2021-09-14 LTS
 * IDE : IntelliJ IDEA & Eclipse
 */
public abstract class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String fullName;

    /**
     * Constructor specifying attribute of Patient or
     * Administrator objects to create.
     *
     * @param usrname the value of User is username.
     * @param pwd     the value of User is password.
     * @param eml     the value of User is email.
     * @param fName   the value of User is full name.
     */
    public User(String usrname, String pwd, String eml,
                String fName) {
        username = usrname;
        password = pwd;
        email = eml;
        fullName = fName;
    }

    /**
     * Returns this User is username account.
     *
     * @return a username of User account.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Registers the new value of username to display in User account.
     *
     * @param usr the String to display.
     */
    public void setUsername(String usr) {
        username = usr;
    }

    /**
     * Returns this User is password account.
     *
     * @return a password of User account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Registers the new value of password to display in User account.
     *
     * @param pwd the String to display.
     */
    public void setPassword(String pwd) {
        password = pwd;
    }

    /**
     * Returns this User is email account.
     *
     * @return an email of User account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Registers the new email to display in User.
     *
     * @param email the String to display.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Registers the new value of full name to display in User account.
     *
     * @return a full name of User account
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Registers the new full name to display in User.
     *
     * @param fullName the String to display.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns detail information of the Patient or Administrator object.
     *
     * @return String detail Patient or Administrator information.
     */
    @Override
    public String toString() {
        return "User information:" +
            "\nUsername: " + username +
            "\nPassword: " + password +
            "\nEmail: " + email +
            "\nFull name: " + fullName;
    }
}
