package models;

/**
 * The User class represents a general user of the gym system.
 * This is an abstract class that provides common fields and methods
 * for all types of users, including Admin, Trainer, and Member.
 */
public abstract class User {
    protected int userId;
    protected String username;
    protected String passwordHash;
    protected String email;
    protected String phoneNumber;
    protected String address;
    protected String role;

    /**
     * Constructs a new User object.
     *
     * @param userId        The unique ID of the user.
     * @param username      The user's username.
     * @param passwordHash  The hashed password of the user.
     * @param email         The user's email address.
     * @param phoneNumber   The user's phone number.
     * @param address       The user's address.
     * @param role          The role of the user (Admin, Trainer, or Member).
     */
    public User(int userId, String username, String passwordHash, String email, String phoneNumber, String address, String role) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    /**
     * Gets the user's ID.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the user's username.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user's hashed password.
     *
     * @return The hashed password.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Gets the user's email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's phone number.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the user's address.
     *
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the user's role.
     *
     * @return The role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's username.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the user's hashed password.
     *
     * @param passwordHash The new hashed password.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phoneNumber The new phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the user's address.
     *
     * @param address The new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the user's role.
     *
     * @param role The new role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Displays the role-specific menu for the user.
     */
    public abstract void displayMenu();
}