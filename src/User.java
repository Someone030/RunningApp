/**
 * Represents a user with a username, password, and icon.
 */
public class User {
    private String username;
    private String password;
    private String icon;

/**
 * Constructs a new User instance with the specified details.
 *
 * @param username the name of the user
 * @param password the password of the user
 * @param icon the profile icon used by the user
 */

 public User(final String username, final String password, final String icon){
     this.username = username;
     this.password = password;
     this.icon = icon;
 }

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public String getIcon() {return icon;}
}