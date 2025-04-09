import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
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
    String query = "INSERT INTO Users (username, password, email, icon) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getEmail());
      stmt.setString(4, user.getIcon());
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
          rs.getString("email"),
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
    String query = "INSERT INTO Routes (route_name, distance, time, location) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, route.getRouteName());
      stmt.setDouble(2, route.getDistance());
      stmt.setDouble(3, route.getTime());
      stmt.setString(4, route.getLocation());
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
  public List<Routes> getAllRoutes() {
    List<Routes> routes = new ArrayList<>();
    String query = "SELECT * FROM Routes";
    try (Statement stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        Routes route = new Routes(
          rs.getString("route_name"),
          rs.getDouble("distance"),
          rs.getDouble("time"),
          rs.getString("location")
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
    String query = "INSERT INTO SavedRoutes (username, route_name, date_saved) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, savedRoute.getUsername());
      stmt.setString(2, savedRoute.getRouteName());
      stmt.setTimestamp(3, savedRoute.getDateSaved());
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
  public List<SavedRoutes> getSavedRoutes(String username) {
    List<SavedRoutes> savedRoutes = new ArrayList<>();
    String query = "SELECT * FROM SavedRoutes WHERE username = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        SavedRoutes savedRoute = new SavedRoutes(
          rs.getString("username"),
          rs.getString("route_name"),
          rs.getTimestamp("date_saved")
        );
        savedRoutes.add(savedRoute);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return savedRoutes;
  }
  
  /**
     * Creates a new achievement for a user in the database.
     * 
     * @param achievement The Achievement object containing achievement details.
     * @return true if the achievement was successfully created, false otherwise.
     */
  public boolean createAchievement(Achievement achievement) {
    String query = "INSERT INTO Achievements (username, achievement_name, date_earned) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, achievement.getUsername());
      stmt.setString(2, achievement.getAchievementName());
      stmt.setTimestamp(3, achievement.getDateEarned());
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
     * @return A list of Achievement objects.
     */
  public List<Achievement> getAchievements(String username) {
    List<Achievement> achievements = new ArrayList<>();
    String query = "SELECT * FROM Achievements WHERE username = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Achievement achievement = new Achievement(
          rs.getString("username"),
          rs.getString("achievement_name"),
          rs.getTimestamp("date_earned")
        );
        achievements.add(achievement);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return achievements;
  } // Other methods for updating, deleting, and retrieving data can be added as needed.
}
