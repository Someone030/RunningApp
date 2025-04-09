import java.util.List;

//everything commented out, copied for reference
/**
 * This class is a view class that provides methods to display various
 * movie-related information.
 */
/**
public class MovieView {
  private static final int LINE_WIDTH = 68;
*/
  /**
   * Returns the menu text for the movie application.
   *
   * @return the menu text as a String
   */
/**
  public static String getMenuText() {
    return "1. [Easy: Sammi Liu, Marcus Robertson] List all routes\n"
        + "2. [Easy: Alan perez, Ashley Pupkin] List the friends of a user\n"
        + "3. [Hard: Sammi Liu, Marcus Robertson] List the routes that end at location x\n"
        + "4. [Hard: Alan Perez, Ashley Pupkin] List routes starting at location y\n"
        + "5. [Hard: Alan Perez, Ashley Pupkin] Print the total distance ran on a certain day\n"
        + "6. [Hardest: Sammi Liu, Marcus Robertson] Order the liked routes from shortest to longest\n"
        + "0. Exit\n" + "Enter your choice: ";
  }

  public static void displayInvalidCustomerMsg() {
    System.out.printf("Invalid customer ID. Ensure the customer exists in the database.\n");
  }
*/
  /**
   * Displays a list of routes with their details.
   *
   * @param routes the list of routes to display
   */
/**
  public static void displayRoutes(List<Routes> routes) { //Sammi & Marcus
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.printf("%-22s %-4s %-3s %-5s %-20s %-9s\n", "startLocation", "endLocation", "routeID", "accID",
        "location", "distancePreference");
    for (Routes route : routes) {
      System.out.printf("%-22s %-4s %3s %5s %-20s %-9s\n", route.getStartLocation(), route.getEndLocation(),
          route.getRouteID(), route.getAccID(), route.getLocation(), route.getDistancePreference());
    }
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
  }

 /**
  * Displays a list of routes with a specific ending location.
  *
  * @param routes the list of routes to display
  */
/**
    public static void displayEndingRoutes(List<Routes> routes) { //Sammi & Marcus
        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
        System.out.printf("%-22s %-4s %-3s %-5s %-20s %-9s\n", "endLocation", "startLocation", "routeID", "accID",
            "location", "distancePreference");
        for (Routes route : routes) {
            System.out.printf("%-22s %-4s %3s %5s %-20s %-9s\n", route.getEndLocation(), route.getStartLocation(),
                route.getRouteID(), route.getAccID(), route.getLocation(), route.getDistancePreference());
    }
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
 }

 /**
 * Displays a list of routes ordered from shortest length to greatest.
 *
 * @param routes the list of routes to display
 */
/**
    public static void displayDistanceRoutes(List<Routes> routes) { //Sammi & Marcus
        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
        System.out.printf("%-22s %-4s %-3s %-5s %-20s %-9s\n", "startLocation", "endLocation", "routeID", "accID",
            "location", "distancePreference");
    for (Routes route : routes) {
        System.out.printf("%-22s %-4s %3s %5s %-20s %-9s\n", route.getStartLocation(), route.getStartLocation(),
            route.getRouteID(), route.getAccID(), route.getLocation(), route.getDistancePreference());
    }
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
 }
*/
  /**
   * Displays a list of studios with their details.
   *
   * @param studios the list of studios to display
   */
/**
  public static void displayFriends(List<Studio> studios) {
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.printf("%-24s %10s %32s\n", "Name", "Address ID", "President Certification Number");
    for (Studio studio : studios) {
      System.out.printf("%-24s %10s %32s\n", studio.getName(), studio.getAddressId(),
          studio.getPresCertNum());
    }
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
  }
*/

  /**
   * Displays a list of customer transactions with their details.
   *
   * @param transactions the list of customer transactions to display
   */
/**
  public static void displayStartingRoutes(List<CustomerTransaction> transactions) {
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.printf("%-9s %16s %20s %10s %9s\n", "Trans No", "Customer ID", "Customer Name",
        "Date", "Total");
    for (CustomerTransaction transaction : transactions) {
      System.out.printf("%-9s %16s %20s %10s %9.2f\n", transaction.getTransactionNo(),
          transaction.getCustomerId(), transaction.getCustomerName(),
          transaction.getTransactionDate(), transaction.getTotal());
    }
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
  }
*/
  /**
   * Displays the customer invoice with its details.
   *
   * @param invoice the invoice to display
   */

/**
  public static void displayOrderedRoutes(Invoice invoice) {
    if (invoice == null || invoice.isEmpty()) {
      System.out.printf("No invoice found for the transaction number or the customer.\n");
      return;
    }

    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.printf("Store No: %s\n", invoice.getStoreNo());
    System.out.printf("Customer: %s (%s)\n", invoice.getCustomerName(), invoice.getCustomerId());
    System.out.printf("Transaction No: %s\n", invoice.getTransactionNumber());
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.printf("%-8s %-22s %4s %6s %8s %3s %10s\n", "Item", "Title", "Year", "Medium",
        "Price", "QTY", "Item Total");
    for (InvoiceItem item : invoice) {
      System.out.printf("%-8d %-22s %4d %6s %8.2f %3d %10.2f\n", item.getItemNum(),
          item.getMovieTitle(), item.getMovieYear(), item.getMediaType(), item.getItemPrice(),
          item.getQuantity(), item.getLineTotal());
    }
    System.out.printf("\n");

    System.out.printf("Invoice Tax: %54.2f\n", invoice.getTax());
    System.out.printf("Invoice Total: %52.2f\n", invoice.getTotal());
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
  }
*/


/**


}
*/
