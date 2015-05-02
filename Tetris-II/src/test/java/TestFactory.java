import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.nsu.ccfit.bogolepov.tetris.model.Block;
import ru.nsu.ccfit.bogolepov.tetris.model.BlockFactory;

public class TestFactory extends Assert {

    static BlockFactory factory;

    @Before
    public void PrepareTest() {
        factory = new BlockFactory();
    }

    @Test
    public void testCreateRandomBlock() {
        Block block = factory.createRandomBlock();
        assertTrue(block != null);
    }

}
