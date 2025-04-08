import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

//everything copied and pasted for reference
/**
 * The MovieController class handles the user interactions and controls the flow of the movie
 * application.
 */
/**
public class MovieController {
  private static final Logger logger = Logger.getLogger(MovieController.class.getName());
  private static final Scanner in = new Scanner(System.in);
*/
  /**
   * Controls the main loop of the movie application.
   *
   * @param connection the database connection
   * @param storeNo the store number
   * @param customerId the customer ID
   * @throws SQLException if a database access error occurs
   */
  /**
  public static void controllerLoop(Connection connection, String storeNo, String customerId)
      throws SQLException {
    if (MovieDataModel.getCustomerById(connection, customerId) == null) {
      MovieView.displayInvalidCustomerMsg();
      return;
    }
    logger.info("Starting the movie application controller loop for Customer ID: " + customerId);

    int action = 0;
    do {
      System.out.print(MovieView.getMenuText());
      action = in.nextInt();
      switch (action) {
        case 1:
          listAllMovies(connection);
          break;
        case 2:
          listAllStudios(connection);
          break;
        case 3:
          listAmblinStudios(connection);
          break;
        case 4:
          listCustomerTransactionSummary(connection, storeNo, customerId);
          break;
        case 5:
          printCustomerInvoice(connection, storeNo, customerId);
          break;
        case 6:
          orderMovieMedia(connection, storeNo, customerId);
          break;
        case 0:
          MovieView.sayGoodbye();
          break;
        default:
          MovieView.promptForInvalidChoice();
      }
    } while (action != 0);
  }

  private static void listAllMovies(Connection connection) throws SQLException {
    List<Movie> movies = MovieDataModel.getAllMovies(connection);
    MovieView.displayMovies(movies);
  }

  private static void listAllStudios(Connection connection) throws SQLException {
    List<Studio> studios = MovieDataModel.getAllStudios(connection);
    MovieView.displayStudios(studios);
  }

  private static void listAmblinStudios(Connection connection) throws SQLException {
    List<Studio> studios = MovieDataModel.getAmblinStudios(connection);
    MovieView.displayStudios(studios);
  }

  private static void listCustomerTransactionSummary(Connection connection, String storeNo,
      String customerId) throws SQLException {
    List<CustomerTransaction> transactions =
        MovieDataModel.getCustomerTransactions(connection, storeNo, customerId);
    MovieView.displayTransactions(transactions);
  }

  private static void printCustomerInvoice(Connection connection, String storeNo, String customerId)
      throws SQLException {
    MovieView.promptForTransactionNo();
    String transactionNo = in.next();
    Invoice invoice = MovieDataModel.getCustomerInvoiceByTransactionNo(connection, storeNo,
        customerId, transactionNo);
    MovieView.displayCustomerInvoice(invoice);
  }


  private static void orderMovieMedia(Connection connection, String storeNo, String customerId)
      throws SQLException {
    ShoppingCart cart = new ShoppingCart();
    List<MovieItem> movieItems = MovieDataModel.getAllMovieItems(connection);
    while (true) {
      MovieView.displayItemOrderMenu(movieItems);
      int action = in.nextInt();
      if (action < 0) {
        MovieView.displayCancelMsg();
        break;
      }
      if (action == 0 && cart.isEmpty()) {
        MovieView.displayEmptyCartMsg();
        continue;
      }
      if (action == 0) {
        String transNo = MovieDataModel.confirmOrder(connection, storeNo, customerId, cart);
        MovieView.displayConfirmedMsg(transNo);
        break;
      }
      if (action > 0 && action <= movieItems.size()) {
        int row = action;
        MovieItem item = movieItems.get(row - 1);
        CartItem cartItem = new CartItem(item, 1);
        cart.addItem(cartItem);
        item.setQuantity(item.getQuantity() - 1);
      }
    }
  }
}
*/
