import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
        + "3. [Easy: Ashley Pupkin] Print all achievements\n"
        + "4. [Hard: Ashley Pupkin] Sort saved routes by date added\n"
        + "5. [Hard: Sammi Liu, Marcus Robertson] List the routes that end at location x\n"
        + "6. [Hard: Sammi Liu, Marcus Robertson] List routes starting at location y\n"
        + "7. [Hard: Sammi Liu, Marcus Robertson] Print the total distance ran on a certain day\n"
        + "8. [Hardest: Sammi Liu, Marcus Robertson] Order the liked routes from shortest to longest\n"
        + "9. [Hardest: Ashley Pupkin] Print total miles ran\n"
        + "10. [Hardest: Ashley Pupkin, Alan Perez] List all users who have met the achievement goal of 25 miles\n"
        + "11. [Hard: Ashley Pupkin, Alan Perez] Average distance of all saved routes\n"
        + "12. [Hardest: Ashley Pupkin] The users whose total run mileage exceed the average\n"
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
  //prints all achievements
  public static void printAllAchievements(List<Achievements> ach) { //Ashley
   System.out.printf("%s\n", "-".repeat(100));
   System.out.printf("%s %22s\n", "Run Streak", "Goal");
   System.out.printf("%s\n", "-".repeat(100));

   for(Achievements ac : ach){
     System.out.printf("%s %17s %17s\n", ac.getRunStreak(), "|", ac.getGoals());
   }
   System.out.printf("%s\n", "-".repeat(100));
  }
//prints sorted saved routes
   public static void listSortedRoutes(List<String> sr){//Ashley
        System.out.printf("%s\n", "-".repeat(70));
        System.out.printf("%-20s %-20s %-30s\n", "Start Location", "End Location", "Saved At");
        System.out.printf("%s\n", "-".repeat(70));
        for (String route : sr) {
            System.out.println(route);
        }
        System.out.printf("%s\n", "-".repeat(70));
    }

/**
* Displays the total amount of miles ran 
 */
public static void displayTotalMiles(double miles){//Ashley
        System.out.printf("%s\n", "-".repeat(100));
        System.out.printf("%-100s%n", "Total Miles Ran");
        System.out.printf("%s\n", "-".repeat(100));
        System.out.printf("Total: %.2f miles%n", miles);
        System.out.printf("%s\n", "-".repeat(100));
    }
 /**
  * Displays a list of routes with a specific ending location.
  *
  * @param routes the list of routes to display
  */

    public static void displayEndingRoutes(List<Routes> routes) { //Sammi & Marcus
        final int LINE_WIDTH = 100;

        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
        System.out.printf("%-25s %-25s %-8s %-6s %-20s %-10s\n",
                "endLocation", "startLocation", "routeID", "accID", "location", "distancePreference");
        
        for (Routes route : routes) {
            System.out.printf("%-25s %-25s %-8s %-6s %-20s %-10s\n",
                    route.getEndLocation(), route.getStartLocation(),
                    route.getRouteID(), route.getAccID(),
                    route.getLocation(), route.getDistancePreference());
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
  
public static void displayFriends(Map<Integer,String> friends) { //Sammi & Marcus & Ashley
    System.out.printf("%s\n", "-".repeat(70));
    System.out.printf("%-20s\n", "Friend Name");
    
   for (String name : friends.values()) {
        System.out.printf("%-20s\n", name);
    }

    System.out.printf("%s\n", "-".repeat(70));
  }

  /**
   * Displays a list of routes starting at a specific location.
   *
   * @param routes the list of routes to display
   */
  
  public static void displayStartingRoutes(List<Routes> routes) { //Sammi & Marcus
      final int LINE_WIDTH = 100;

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
  /**
*Display users who achieved the 25 mile achievement
*/
public static void displayStreak(List<String> list){//Ashley & Alan
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    System.out.println("Users");
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    for(String s: list){
        System.out.println(s);
    }
    System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
}
/**
*displays avg distance of saved routes
*/
 public static void displayAvgDistanceSaved(double miles){
        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
        System.out.println("Distance");
        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
        System.out.println(miles);
        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    }
/**
*/
    public static void displayOverAvg(List<String> list){//Ashley
        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
        System.out.println("Distance");
        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));

        for (String s: list){
            System.out.println(s);
        }
        System.out.printf("%s\n", "-".repeat(LINE_WIDTH));
    }

}
