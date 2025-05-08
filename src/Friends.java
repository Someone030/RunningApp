public class Friends {
private String name;
private int accountID;

public Friends(final int account, final String name){
accountID = account;
this.name = name;
}

public String getName(){
return name;
}

public int getAccountID(){
return accountID;
}


}
