/**
 * Represents an account with an accountName, accountNumber, userID, accountID, and
 * friends.
 */
public class Account {
    private String accountName;
    private String accountNumber;
    private String userID;
    private String accountID;
    private String friends;

/**
 * Constructs a new Account instance with the specified details.
 *
 * @param accountName the name of the account
 * @param accountNumber the number of the account
 * @param userID the ID associated with the account's user
 * @param accountID the ID associated with the account
 * @param friends the friends that the account has
 */

 public Account(final String accountName, final String accountNumber, final String userID,
                final String accountID, final String friends){
    this.accountName = accountName;
    this.accountNumber = accountNumber;
    this.userID = userID;
    this.accountID = accountID;
    this.friends = friends;
 }

    public String getAccountName() {return accountName;}

    public String getAccountNumber() {return accountNumber;}

    public String getUserID() {return userID;}

    public String getAccountID() {return accountID;}

    public String getFriends() {return friends;}

    public List<String> getFriendList() {
        List<String> friendList = new ArrayList<>();
        if (friends != null && !friends.isEmpty()) {
            String[] friendArray = friends.split(",");
            for (String friend : friendArray) {
                friendList.add(friend.trim());
            }
        }
        return friendList;
    }

}
