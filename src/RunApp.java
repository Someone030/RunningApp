import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * The RunApp class is the main entry point for the application.
 * It sets up logging, establishes a database connection, and starts the controller loop.
 */
/**
  public class MovieApp {
  private static final String LOG_FILE = "MovieApp.log";
  private static final Logger logger = Logger.getLogger(MovieApp.class.getName());

  private static final String DB_CONFIG_FILE_KEY = "db.config.file";
  private static final String DB_CONFIG_FILE = "db.properties";

  private static final String STORE_NO = "100001";

  private static final String CUSTOMER_ID_KEY = "customer.id";
  private static final String CUSTOMER_ID = "C12345678";
*/
  /**
   * The main method is the entry point of the application.
   * It sets up logging and establishes a database connection.
   *
   * @param args Command line arguments (unused).
   */
/** ALL OF THIS IS COPY AND PASTED FOR REFERENCE
   public static void main(String[] args) {
    try (AutoclosableLoggerFileHandler logFileHandler =
        new AutoclosableLoggerFileHandler(LOG_FILE, false)) {
      setup_logger(logFileHandler, logger);

      String dbConfigFile = System.getProperty(DB_CONFIG_FILE_KEY);
      if (dbConfigFile == null) {
        dbConfigFile = DB_CONFIG_FILE;
      }
      logger.log(Level.FINE, "dbConfigFile => " + dbConfigFile);
      String customerId = System.getProperty(CUSTOMER_ID_KEY);
      if (customerId == null) {
        customerId = CUSTOMER_ID;
      }

      try (Connection connection = getDbConnection(dbConfigFile)) {
        MovieController.controllerLoop(connection, STORE_NO, customerId);
      } catch (SQLException e) {
        MovieView.displaySystemErrorMsg(e);
        logger.log(Level.SEVERE, "Error operating on MariaDB: " + e.getMessage());
      }
    } catch (IOException e) {
      MovieView.displayLogErrorMsg(e);
    }
  }


  private static void setup_logger(AutoclosableLoggerFileHandler fileHandler, Logger logger)
      throws IOException {
    Logger rootLogger = Logger.getLogger("");
    for (Handler handler : rootLogger.getHandlers()) {
      rootLogger.removeHandler(handler);
    }

    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);

    rootLogger.addHandler(fileHandler);

    rootLogger.setLevel(Level.ALL);
    logger.setLevel(Level.ALL);
  }

  private static Properties loadDbProperties(String dbConfigFile) {
    Properties properties = new Properties();

    try (FileInputStream fin = new FileInputStream(dbConfigFile)) {
      properties.load(fin);
    } catch (IOException io) {
      logger.log(Level.SEVERE, "Error reading " + dbConfigFile + ": " + io.getMessage());
    }
    return properties;
  }

  private static Connection getDbConnection(String dbConfigFile) throws SQLException {
    Properties properties = loadDbProperties(dbConfigFile);
    String url = "jdbc:mariadb://" + properties.getProperty("host") + ":"
        + properties.getProperty("port") + "/" + properties.getProperty("database");
    logger.log(Level.FINE, "DB URL => '" + url + "'");
    String user = properties.getProperty("user");
    String password = properties.getProperty("password");

    Connection connection = null;
    connection = DriverManager.getConnection(url, user, password);
    logger.info("Connected to MariaDB successfully!");
    return connection;
  }
}
*/
