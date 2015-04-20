import java.io.Reader;
import java.util.List;

/**
 * Parsing input from FlowController
 */
public interface Parser {
    public List<String> parse(Reader reader);
}
