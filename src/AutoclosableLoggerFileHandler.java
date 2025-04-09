import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * An autoclosable file handler for logging.
 */
public class AutoclosableLoggerFileHandler extends FileHandler implements AutoCloseable {
  /**
   * Constructs a new AutoclosableLoggerFileHandler.
   *
   * @param pattern the pattern for naming the output file
   * @param append  specifies append mode
   * @throws IOException if there are IO problems opening the files
   */
  public AutoclosableLoggerFileHandler(String pattern, boolean append) throws IOException {
    super(pattern, append);
  }

  @Override
  public void close() {
    super.close();
  }
}
