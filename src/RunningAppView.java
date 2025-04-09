import java.util.List;

/**
 * This class is a view class that provides methods to display various
 * running-related information.
 */

public class RunningAppView {
  private static final int LINE_WIDTH = 68;

  /**
   * Returns the menu text for the running application.
   *
   * @return the menu text as a String
   */

  public static String getMenuText() {
    return "1. [Easy: Sammi Liu, Marcus Robertson] List all routes\n"
        + "2. [Easy: Sammi Liu, Marcus Robertson] List the friends of a user\n"
        + "3. [Hard: Sammi Liu, Marcus Robertson] List the routes that end at location x\n"
        + "4. [Hard: Sammi Liu, Marcus Robertson] List routes starting at location y\n"
        + "5. [Hard: Sammi Liu, Marcus Robertson] Print the total distance ran on a certain day\n"
        + "6. [Hardest: Sammi Liu, Marcus Robertson] Order the liked routes from shortest to longest\n"
        + "0. Exit\n" + "Enter your choice: ";
  }

  public static void displayInvalidUserMsg() {
    System.out.printf("Invalid user ID. Ensure the customer exists in the database.\n");
  }

  /**
   * Displays a list of routes with their details.
   *
   * @param routes the list of routes to display
   */
  public static void displayRoutes(List<Routes> routes) { //Sammi & Marcus
      final int LINE_WIDTH = 100;

      if (routes == null || routes.isEmpty()) {
          System.out.println("No routes to display.");
          return;
      }

      System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
      System.out.printf("%-25s %-25s %-8s %-6s %-20s %-10s\n",
              "startLocation", "endLocation", "routeID", "accID", "location", "distancePreference");

      for (Routes route : routes) {
          System.out.printf("%-25s %-25s %-8s %-6s %-20s %-10s\n",
                  route.getStartLocation(), route.getEndLocation(),
                  route.getRouteID(), route.getAccID(),
                  route.getLocation(), route.getDistancePreference());
      }

      System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
  }

 /**
  * Displays a list of routes with a specific ending location.
  *
  * @param routes the list of routes to display
  */

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

  public static void displayDistanceRoutes(List<Double> distances) { //Sammi & Marcus
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.printf("%-22s %-9s\n", "Total Distance", "Total");
    double totalDistance = 0;
    for (Double distance : distances) {
        totalDistance += distance;
    }
    System.out.printf("%-22s %-9.2f\n", "Distance Ran:", totalDistance);
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
  }

  public static void sayGoodbye() {
    System.out.println("Goodbye!");
  }

  public static void promptForInvalidChoice() {
    System.out.println("Invalid choice. Please try again.");
  }

  public static void displaySystemErrorMsg(Exception e) {
    System.err.println("System error. Call technical support! " + e.getMessage());
  }

  public static void displayLogErrorMsg(Exception e) {
    System.err.println("Error initializing log file: " + e.getMessage());
  }
  
  /**
   * Displays a list of friends with their details.
   *
   * @param friends the list of friends to display
   */
  
  public static void displayFriends(List<String> friends) { //Sammi & Marcus
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.printf("%-20s\n", "Friend Name");
    
    for (String friendName : friends) {
        System.out.printf("%-20s\n", friendName);
    }
    
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
  }

  /**
   * Displays a list of routes starting at a specific location.
   *
   * @param routes the list of routes to display
   */
  
  public static void displayStartingRoutes(List<Routes> routes) { //Sammi & Marcus
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
   * Displays a list of routes ordered by distance.
   *
   * @param orderedRoutes the list of ordered routes to display
   */
  
  public static void displayOrderedRoutes(List<Routes> orderedRoutes) { //Sammi & Marcus
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.printf("%-22s %-4s %-3s %-5s %-20s %-9s\n", "startLocation", "endLocation", "routeID", "accID",
        "location", "distancePreference");
    for (Routes route : orderedRoutes) {
      System.out.printf("%-22s %-4s %3s %5s %-20s %-9s\n", route.getStartLocation(), route.getEndLocation(),
          route.getRouteID(), route.getAccID(), route.getLocation(), route.getDistancePreference());
    }
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
  }
}
