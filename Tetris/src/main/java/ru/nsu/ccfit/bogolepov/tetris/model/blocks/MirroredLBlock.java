package ru.nsu.ccfit.bogolepov.tetris.model.blocks;

import ru.nsu.ccfit.bogolepov.tetris.model.Block;
import ru.nsu.ccfit.bogolepov.tetris.model.Field;

/**
 * Created by aceisnotmycard on 4/28/15.
 */
public class MirroredLBlock extends Block {

    public MirroredLBlock(Field field, int x, int y) {
        super(field, x, y);
        super.blockSize = 4;
        super.type = BlockType.MirroredL;
        super.pattern = new int[][] {
                {0, 0}, {0, 1}, {0, 2}, {-1, 2}
        };
    }
}
