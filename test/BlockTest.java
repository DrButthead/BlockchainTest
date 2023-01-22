import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BlockTest {
    private final Logger logger = new Logger();

    private int difficulty;
    private Block genesisBlock;

    @BeforeEach
    void setup() {
        difficulty = 5;
        genesisBlock = new Block("I'm the first block!", "0");
    }

    @AfterEach
    void after() {
        difficulty = 0;
        genesisBlock = null;
    }

    @Test
    void testChain() {
        Block genesisBlock = new Block("Hi, I'm the first block!!", "0");
        logger.log("Hash for block 1: " + genesisBlock.hash);

        Block secondBlock = new Block("This is number 2", genesisBlock.hash);
        logger.log("Hash for block 2: " + secondBlock.hash);

        Block thirdBlock = new Block("Number 3 goes here", secondBlock.hash);
        logger.log("Hash for block 3: " + thirdBlock.hash);

        assertNotEquals(genesisBlock.hash, secondBlock.hash);
        assertNotEquals(genesisBlock.hash, thirdBlock.hash);
        assertNotEquals(secondBlock.hash, thirdBlock.hash);

        assertEquals(genesisBlock.hash, secondBlock.previousHash);
        assertEquals(secondBlock.hash, thirdBlock.previousHash);
    }

    @Test
    void mineBlock() {
        difficulty = 3;
        String target = StringUtil.repeat('0', difficulty);

        logger.log("Trying to mine genesis block...");
        genesisBlock.mineBlock(difficulty);
        logger.log(genesisBlock);

        String genesisStart = genesisBlock.hash.substring(0, difficulty);
        assertEquals(genesisStart, target);

        Block secondBlock = new Block("This is the second block", genesisBlock.hash);
        logger.log("Trying to mine second block...");
        secondBlock.mineBlock(difficulty);
        logger.log(secondBlock);

        String secondStart = secondBlock.hash.substring(0, difficulty);
        assertEquals(secondStart, target);
    }
}