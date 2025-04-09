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
      switch (action) {
        case 1:
          listAllLikedRoutes(connection, userId);
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
          orderLikedRoutesByDistance(connection, userId);
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
  
  private static void listAllLikedRoutes(Connection connection, String userId) throws SQLException {
    List<Route> likedRoutes = RunningAppDataModel.getLikedRoutes(connection, userId);
    RunningAppView.displayRoutes(likedRoutes);
  }

  private static void listFriendsOfUser(Connection connection, String userId) throws SQLException {
    List<User> friends = RunningAppDataModel.getFriends(connection, userId);
    RunningAppView.displayFriends(friends);
  }

  private static void listRoutesEndingAtLocation(Connection connection) throws SQLException {
    System.out.print("Enter the location where the routes end: ");
    String location = in.next();
    //get routes that end at the specified location
    List<Route> routes = RunningAppDataModel.getRoutesEndingAtLocation(connection, location);
    RunningAppView.displayEndingRoutes(routes);  // Display the routes that end at the location
  }

  private static void listRoutesStartingAtLocation(Connection connection) throws SQLException {
    System.out.print("Enter the location where the routes start: ");
    String location = in.next();
    //get routes that start at the specified location
    List<Route> routes = RunningAppDataModel.getRoutesStartingAtLocation(connection, location);
    RunningAppView.displayStartingRoutes(routes);  // Display the routes that start at the location
  }

  private static void orderLikedRoutesByDistance(Connection connection, String userId) throws SQLException {
    List<Route> likedRoutes = RunningAppDataModel.getLikedRoutes(connection, userId);
    
    // Sort the liked routes based on distance from shortest to longest
    likedRoutes.sort((route1, route2) -> Double.compare(route1.getDistance(), route2.getDistance()));
    
    // Display the sorted list of routes
    RunningAppView.displayOrderedRoutes(likedRoutes);
  }

  private static void printTotalDistanceRanOnDay(Connection connection, String accoID) throws SQLException {
    System.out.print("Enter the date (YYYY-MM-DD) to get total distance: ");
    String date = in.next();
    
    // get total distance ran by the user on the specific day
    double totalDistance = 0;
    for (Double distance : totalDistances) {
        totalDistance += distance;
    }
    
    // Display the total distance
    RunningAppView.displayTotalDistance(totalDistance);
  }
}
