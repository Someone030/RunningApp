import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    String query = "INSERT INTO Users (username, password, icon) VALUES (?, ?, ?)";
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
    String query = "SELECT * FROM Users WHERE username = ?";
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
    String query = "INSERT INTO Routes (start_loc, end_loc, route_id, acc_id, location, distance_pref) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, route.getStartLocation());
      stmt.setString(2, route.getEndLocation());
      stmt.setString(3, route.getRouteID());
      stmt.setString(4, route.getAccID());
      stmt.setString(5, route.getLocation());
      stmt.setString(6, route.getDistancePreference());
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
    String query = "SELECT * FROM Routes WHERE accID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Routes route = new Routes(
          rs.getString("start_loc"),
          rs.getString("end_loc"),
          rs.getString("route_id"),
          rs.getString("acc_id"),
          rs.getString("location"),
          rs.getString("distance_pref")
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
    String query = "INSERT INTO SavedRoutes (start_loc, end_loc, routes_id, saved_route_id, acco_id, date_saved) VALUES (?, ?, ?, ?, ?, ?)";
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
    String query = "SELECT * FROM SavedRoutes WHERE accoID = ?";
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
    String query = "INSERT INTO Achievements (run_streak, goals) VALUES (?, ?)";
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

    /**
     * Retrieves all achievements for a user from the database.
     * 
     * @param username The username of the user.
     * @return A list of Achievements objects.
     */
  public List<Achievements> getAchievements(String username) {
    List<Achievements> achievementList = new ArrayList<>();
    String query = "SELECT * FROM Achievements WHERE username = ?";
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
    public static List<Route> getLikedRoutes(Connection connection, String userId) throws SQLException {
        // List to store the liked routes
        List<Route> likedRoutes = new ArrayList<>();
        
        // SQL query to get liked routes based on user ID
        String query = "SELECT startLoc, endLoc, routeID, accoID, location, distancePreference FROM routes WHERE accoID = ? AND liked = true";
        
        // Prepare the statement to prevent SQL injection
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, userId);  // Set the user ID parameter

            // Execute the query and process the result
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // For each liked route, create a new Route object and add it to the list
                    String startLoc = rs.getString("startLoc");
                    String endLoc = rs.getString("endLoc");
                    String routeID = rs.getString("routeID");
                    String accoID = rs.getString("accoID");
                    String location = rs.getString("location");
                    double distancePreference = rs.getDouble("distancePreference");

                    // Assuming a Route constructor that takes these parameters
                    Route route = new Route(startLoc, endLoc, routeID, accoID, location, distancePreference);
                    likedRoutes.add(route);
                }
            }
        }
        
        // Return the list of liked routes
        return likedRoutes;
    }
     public static List<Route> getRoutes(Connection connection, String accID) throws SQLException {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT startLoc, endLoc, routeID, accoID, location, distancePreference FROM routes WHERE accoID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String startLoc = rs.getString("startLoc");
                    String endLoc = rs.getString("endLoc");
                    String routeID = rs.getString("routeID");
                    String location = rs.getString("location");
                    String distancePreference = rs.getString("distancePreference");

                    // Create a Route object and add it to the list
                    Route route = new Route(startLoc, endLoc, routeID, accID, location, distancePreference);
                    routes.add(route);
                }
            }
        }

        return routes;
    }
    // Other methods for updating, deleting, and retrieving data can be added as needed.
}
