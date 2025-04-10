import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The RunningAppController class handles the user interactions and controls the flow of the running
 * motivation application.
 */

public class RunningAppController {
  private static final Logger logger = Logger.getLogger(RunningAppController.class.getName());
  private static final Scanner in = new Scanner(System.in);
  /**
   * Controls the main loop of the running application.
   *
   * @param connection the database connection
   * @param userId the user ID
   * @throws SQLException if a database access error occurs
   */
  public static void controllerLoop(Connection connection, String userId) throws SQLException {
    if (RunningAppDataModel.getUserById(connection, userId) == null) {
      RunningAppView.displayInvalidUserMsg();
      return;
    }
    logger.info("Starting the running application controller loop for User ID: " + userId);
    int action = 0;
    do {
      System.out.print(RunningAppView.getMenuText());
      action = in.nextInt();
      in.nextLine();
      switch (action) {
        case 1:
          listAllRoutes(connection, userId);
          break;
        case 2:
          listFriendsOfUser(connection, userId);
          break;
        case 3:
          listRoutesEndingAtLocation(connection);
          break;
        case 4:
          listRoutesStartingAtLocation(connection);
          break;
        case 5:
          orderRoutesByDistance(connection, userId);
          break;
        case 6:
          printTotalDistanceRanOnDay(connection, userId);
          break;
        case 0:
          RunningAppView.sayGoodbye();
          break;
        default:
          RunningAppView.promptForInvalidChoice();
      }
    } while (action != 0);
  }
  
  //note to myself cz I'm getting confused :D
  //get all routes would be in the running app data model file while the display routes is in the view file
  
  private static void listAllRoutes(Connection connection, String accID) throws SQLException {
    List<Routes> routes = RunningAppDataModel.getRoutes(connection, accID);
    RunningAppView.displayRoutes(routes);
  }

  private static void listFriendsOfUser(Connection connection, String accountID) throws SQLException {
    List<String> friends = RunningAppDataModel.getFriends(connection, accountID);
    RunningAppView.displayFriends(friends);
  }

  private static void listRoutesEndingAtLocation(Connection connection) throws SQLException {
    System.out.print("Enter the location where the routes end: ");
    String location = in.nextLine().trim();
    //get routes that end at the specified location
    List<Routes> routes = RunningAppDataModel.getRoutesEndingAtLocation(connection, location);
    RunningAppView.displayEndingRoutes(routes);  // Display the routes that end at the location
  }

  private static void listRoutesStartingAtLocation(Connection connection) throws SQLException {
    System.out.print("Enter the location where the routes start: ");
    String location = in.nextLine().trim();
    //get routes that start at the specified location
    List<Routes> routes = RunningAppDataModel.getRoutesStartingAtLocation(connection, location);
    RunningAppView.displayStartingRoutes(routes);  // Display the routes that start at the location
  }

  private static void orderRoutesByDistance(Connection connection, String accID) throws SQLException {
    List<Routes> routes = RunningAppDataModel.getRoutes(connection, accID);
    
    // Sort the liked routes based on distance from shortest to longest
    routes.sort((route1, route2) -> Double.compare(route1.getDistance(), route2.getDistance()));
    
    // Display the sorted list of routes
    RunningAppView.displayOrderedRoutes(routes);
  }

  private static void printTotalDistanceRanOnDay(Connection connection, String accoID) throws SQLException {
    System.out.print("Enter the date (YYYY-MM-DD) to get total distance: ");
    String date = in.next();
    
    // Get the list of distances ran by the user on that specific day
    List<Double> totalDistances = RunningAppDataModel.getTotalDistanceRanOnDay(connection, accoID, date);
    
    // Display the total distance
    RunningAppView.displayDistanceRoutes(totalDistances); // Pass the list of distances
  }
}
