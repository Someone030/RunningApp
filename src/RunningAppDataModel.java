import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Map;
import java.util.HashMap;

/**
 * This class provides methods to interact with the Running App database, Along with a few other classes,
 * this class constitutes the "model" of the application.
 */

public class RunningAppDataModel {
  private Connection connection;
  /**
     * Constructor that initializes the database connection.
     * 
     * @param dbConnection The database connection to be used by the model.
     */
  public RunningAppDataModel(Connection dbConnection) {
    this.connection = dbConnection;
  }
  
  /**
     * Creates a new user in the database.
     * 
     * @param user The User object containing user details.
     * @return true if the user was successfully created, false otherwise.
     */
  public boolean createUser(User user) {
    String query = "INSERT INTO usersT (username, password, icon) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getIcon());
      int result = stmt.executeUpdate();
      return result > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
  
  /**
     * Retrieves a user by username from the database.
     * 
     * @param username The username of the user to retrieve.
     * @return The User object containing user details, or null if the user does not exist.
     */
  public User getUser(String username) {
    String query = "SELECT * FROM usersT WHERE username = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new User(
          rs.getString("username"),
          rs.getString("password"),
          rs.getString("icon")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
return null;
  }
  
  /**
     * Creates a new route in the database.
     * 
     * @param route The Route object containing route details.
     * @return true if the route was successfully created, false otherwise.
     */
  public boolean createRoute(Routes route) {
    String query = "INSERT INTO routesT (startLocation, endLocation, route_id, acc_id, distance_pref) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, route.getStartLocation());
      stmt.setString(2, route.getEndLocation());
      stmt.setString(3, route.getRouteID());
      stmt.setString(4, route.getAccID());
      
      stmt.setString(5, route.getDistancePreference());
      int result = stmt.executeUpdate();
      return result > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
  
  /**
     * Retrieves a list of all routes from the database.
     * 
     * @return A list of Routes objects.
     */
  public List<Routes> getAllRoutes(String accID) {
    List<Routes> routes = new ArrayList<>();
    String query = "SELECT * FROM routesT WHERE accID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Routes route = new Routes(
          rs.getString("startLocation"),
          rs.getString("endLocation"),
          rs.getString("routeID"),
          rs.getString("accID"),
          rs.getString("distancePreferences")
        );
        routes.add(route);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return routes;
  }
  
  /**
     * Creates a new saved route for a user in the database.
     * 
     * @param savedRoute The SavedRoutes object containing the saved route details.
     * @return true if the saved route was successfully created, false otherwise.
     */
  public boolean saveRoute(SavedRoutes savedRoute) {
    String query = "INSERT INTO savedRoutesT (start_loc, end_loc, routes_id, saved_route_id, acco_id, date_saved) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, savedRoute.getStartLoc());
      stmt.setString(2, savedRoute.getEndLoc());
      stmt.setString(3, savedRoute.getRoutesID());
      stmt.setString(4, savedRoute.getSavedRouteID());
      stmt.setString(5, savedRoute.getAccoID());
      stmt.setTimestamp(6, savedRoute.getSavedAt()); // Timestamp
      int result = stmt.executeUpdate();
      return result > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
}

  /**
     * Retrieves the saved routes for a user from the database.
     * 
     * @param username The username of the user.
     * @return A list of SavedRoutes objects.
     */
  public List<SavedRoutes> getSavedRoutes(String accoID) {
    List<SavedRoutes> savedRoutes = new ArrayList<>();
    String query = "SELECT * FROM savedRoutesT WHERE accoID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, accoID);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        SavedRoutes savedRoute = new SavedRoutes(
                rs.getString("start_loc"),
                rs.getString("end_loc"),
                rs.getString("routes_id"),
                rs.getString("saved_route_id"),
                rs.getString("acco_id"),
                rs.getTimestamp("saved_At")
            );
        savedRoutes.add(savedRoute);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return savedRoutes;
  }
  
  /**
     * Creates a new achievements for a user in the database.
     * 
     * @param achievements The Achievements object containing achievements details.
     * @return true if the achievements was successfully created, false otherwise.
     */
  public boolean createAchievements(Achievements achievements) {
    String query = "INSERT INTO achievementsT (run_streak, goals) VALUES (?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, achievements.getRunStreak());
      stmt.setString(2, achievements.getGoals());
      int result = stmt.executeUpdate();
      return result > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
  public static List<Achievements> getAllAchievements(Connection connection) throws SQLException{
    List<Achievements> ach = new ArrayList<Achievements>();
    String sql = "SELECT * FROM achievementsT";
    PreparedStatement pstmt = connection.prepareStatement(sql);
ResultSet resultSet = pstmt.executeQuery();
      while (resultSet.next()) {
        ach.add(new Achievements(resultSet.getString("runStreak"), resultSet.getString("goals")));
      }
    return ach;
  }
/**
*returns the total miles the user ran
*/
     public static double totalMilesRan(Connection connection) throws SQLException{
        double totalMiles = 0;
        String sql = "SELECT SUM(distancePreference) FROM routesT;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            totalMiles = resultSet.getDouble(1);
        }

        return totalMiles;
    }
/**
*returns all saved routed
*/
/**
*returns all saved routed
*/
    public static List<String> sortedSavedRoutes(Connection connection) throws SQLException{
        List<String> sr = new ArrayList<String>();
        String sql = "SELECT r.startLocation, r.endLocation, s.savedAt FROM routesT AS r JOIN savedRoutesT AS s ON s.routeID = r.routeID ORDER BY s.savedAt DESC;";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        while(resultSet.next()){
            String start = resultSet.getString("startLocation");
            String end = resultSet.getString("endLocation");

            String savedAt = resultSet.getTimestamp("savedAt").toString(); 

            sr.add(start + "    " + end + "     " + savedAt);
        }
        return sr;
    }
    
    /**
     * Retrieves all achievements for a user from the database.
     * 
     * @param username The username of the user.
     * @return A list of Achievements objects.
     */
public List<Achievements> getAchievements(String username) {
    List<Achievements> achievementList = new ArrayList<>();
    String query = "SELECT * FROM achievementsT WHERE username = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Achievements achievements = new Achievements(
          rs.getString("run_streak"),
          rs.getString("goals")
        );
        achievementList.add(achievements);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return achievementList;
  } 
/**
     * Retrieves the distances ran by a user on a specific date.
     *
     * @param connection the database connection
     * @param accoID the account ID of the user
     * @param date the date to fetch the runs for (format: YYYY-MM-DD)
     * @return a list of distances ran on the specified date
     * @throws SQLException if an error occurs while interacting with the database
     */
    public static List<Double> getTotalDistanceRanOnDay(Connection connection, String accoID, String date) throws SQLException {
        // List to store the distances ran on the specific day
        List<Double> distances = new ArrayList<>();
        
        // SQL query to get the distances based on account ID and date
        String query = "SELECT distance FROM runs WHERE accoID = ? AND DATE(runDate) = ?";
        
        // Prepare the statement to prevent SQL injection
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accoID);  // Set the account ID parameter
            stmt.setString(2, date);    // Set the date parameter

            // Execute the query and process the result
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Add each distance to the list
                    distances.add(rs.getDouble("distance"));
                }
            }
        }
        
        // Return the list of distances
        return distances;
        }
  /**
     * Retrieves the liked routes of a user.
     *
     * @param connection the database connection
     * @param userId the ID of the user whose liked routes are to be fetched
     * @return a list of liked routes for the given user
     * @throws SQLException if an error occurs while interacting with the database
     */
     // List to store the liked routes
        List<SavedRoutes> likedRoutes = new ArrayList<>();
        
        // SQL query to get liked routes based on user ID
        String query = "SELECT startLoc, endLoc, routesID, savedRouteID, accoID, savedAt FROM savedRoutesT WHERE accoID = ?";
        
        // Prepare the statement to prevent SQL injection
         try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, accoID);  // Set the account ID parameter

        // Execute the query and process the result
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // For each liked route, create a new SavedRoutes object and add it to the list
                String startLoc = rs.getString("startLoc");
                String endLoc = rs.getString("endLoc");
                String routesID = rs.getString("routesID");
                String savedRouteID = rs.getString("savedRouteID");
                String accoIDFromDB = rs.getString("accoID");  // It might be useful to check if this matches the parameter
                Timestamp savedAt = rs.getTimestamp("savedAt");

                // Create a new SavedRoutes object
                SavedRoutes route = new SavedRoutes(startLoc, endLoc, routesID, savedRouteID, accoIDFromDB, savedAt);
                likedRoutes.add(route);
            }
        }
    }
    return likedRoutes;
    }
 public static List<Routes> getRoutes(Connection connection, String accID) throws SQLException {
      List<Routes> routes = new ArrayList<>();
      String query = "SELECT startLocation, endLocation, routeID, accID, distancePreference FROM routesT WHERE accID = ?";
      try (PreparedStatement stmt = connection.prepareStatement(query)) {
          stmt.setString(1, accID);
          try (ResultSet rs = stmt.executeQuery()) {
              while (rs.next()) {
                  String startLocation = rs.getString("startLocation");
                  String endLocation = rs.getString("endLocation");
                  String routeID = rs.getString("routeID");
                  
                  String distancePreference = rs.getString("distancePreference");
  
                  // Create a Routes object and add it to the list
                  Routes route = new Routes(startLocation, endLocation, routeID, accID, distancePreference);
                  routes.add(route);
              }
          }
      }
      return routes;
    }
public static Map<Integer, String> getFriends(Connection connection, String accountID) throws SQLException {
    Map<Integer, String> map = new HashMap<>();

    String query = "SELECT * FROM accountFriendsT WHERE accountID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, accountID);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) { // Loop through all rows
                int friendID = rs.getInt("accountid");
                String friendName = rs.getString("friendName");

                map.put(friendID, friendName);
            }
        }
    }

    return map;
}
  public static List<Routes> getRoutesStartingAtLocation(Connection connection, String location) throws SQLException {
    List<Routes> routes = new ArrayList<>();
    String query = "SELECT * FROM routesT WHERE startLocation = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, location);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String startLoc = rs.getString("startLocation");
                String endLoc = rs.getString("endLocation");
                String routeID = rs.getString("routeID");
                String accoID = rs.getString("accID");
                
                String distancePreference = rs.getString("distancePreference");
              
                Routes route = new Routes(startLoc, endLoc, routeID, accoID, distancePreference);
                routes.add(route);
            }
        }
    }
    return routes;
  }

 public static List<Routes> getRoutesEndingAtLocation(Connection connection, String location) throws SQLException {
    List<Routes> routes = new ArrayList<>();
    String query = "SELECT * FROM routesT WHERE endLocation = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, location);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String startLoc = rs.getString("startLocation");
                String endLoc = rs.getString("endLocation");
                String routeID = rs.getString("routeID");
                String accoID = rs.getString("accID");
                
                String distancePreference = rs.getString("distancePreference");

                Routes route = new Routes(startLoc, endLoc, routeID, accoID, distancePreference);
                routes.add(route);
            }
        }
    }
    return routes;
  }
public static Account getUserById(Connection connection, String accountID) throws SQLException {
      // SQL query to get user by their account ID
      String query = "SELECT * FROM accountT WHERE accountID = ?";
  
      try (PreparedStatement stmt = connection.prepareStatement(query)) {
          stmt.setString(1, accountID);  // Set the accountID parameter
  
          try (ResultSet rs = stmt.executeQuery()) {
              if (rs.next()) {
                  String accountName = rs.getString("accountName");
                  String accountNumber = rs.getString("accountNumber");
                  String userID = rs.getString("userID");
                  String accountIDFromDB = rs.getString("accountID");
                  // Return a new Account object
                  return new Account(accountName, accountNumber, userID, accountID);
              } else {
                  return null;  // Account not found
              }
          }
      }
  }
/**
*return list of all users that reached 25 miles
*/
public static List<String> userStreak(Connection connection) throws SQLException {
    List<String> list = new ArrayList<>();
    String sql = "SELECT accountName FROM accountT WHERE accountID IN ( SELECT accountID FROM achievementsT " +
            "WHERE goals LIKE '%25 Days%');";

    try (PreparedStatement pstmt = connection.prepareStatement(sql);
         ResultSet resultSet = pstmt.executeQuery()) {

        while (resultSet.next()) {
            list.add(resultSet.getString("accountName"));
        }
    }

    return list;
}
/**
*avg distance of all saved routes
*/
public static double avgDistSaved(Connection connection) throws SQLException {
    String sql = "SELECT AVG(r.distancePreference) " +
                 "FROM routesT AS r JOIN savedRoutesT AS s ON r.routeID = s.routeID";

    try (PreparedStatement pstmt = connection.prepareStatement(sql);
         ResultSet resultSet = pstmt.executeQuery()) {

        if (resultSet.next()) {
            return resultSet.getDouble(1); // or resultSet.getDouble("AVG(r.distancePreference)")
        }
    }

    return 0.0;
}
/**
*Users whose total miles exceed the average
*/
public static List<String> aboveAvg(Connection connection) throws SQLException {
        List<String> list = new ArrayList<>();
        String createView = "CREATE OR REPLACE VIEW accDist AS SELECT a.accountName, b.runStreak FROM accountT AS a " +
                "JOIN achievementsT AS b ON a.accountID = b.accountID;";
        String query =  "SELECT accountName FROM accDist " +
                "WHERE runStreak > (SELECT AVG(runStreak) FROM accDist);";

        try (PreparedStatement pstmt = connection.prepareStatement(createView)) {
            pstmt.execute();
        }
        try (PreparedStatement pstmt2 = connection.prepareStatement(query);
             ResultSet resultSet2 = pstmt2.executeQuery()) {

            while (resultSet2.next()) {
                list.add(resultSet2.getString("accountName"));
            }
        }
            return list;
    }

    // Other methods for updating, deleting, and retrieving data can be added as needed.
}

