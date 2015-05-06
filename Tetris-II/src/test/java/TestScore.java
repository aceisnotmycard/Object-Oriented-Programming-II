import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.nsu.ccfit.bogolepov.tetris.model.Score;

import java.util.List;


public class TestScore extends Assert {

    @Before
    public void prepareScoreTest() {

    }

    @Test
    public void testSaveIfBest() {
        Score.save(1);
    }

    @Test
    public void testGetBestRecords() {
        Score.save(1);
        Score.save(5);
        List<Integer> records = Score.getResults();

        assertTrue(records.size() == 3);
        Score.save(4);
    }
}
