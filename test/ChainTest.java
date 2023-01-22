import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChainTest {

    @Test
    void add() {
        Chain blockChain = new Chain(5);

        blockChain.add("This is the first block");
        blockChain.add("Second");
        blockChain.add("third");

        System.out.println(blockChain);
        System.out.println(blockChain.toJsonString());
    }

    @Test
    void isChainValid() {
        int difficulty = 2;
        Chain blockChain = new Chain(difficulty);

        assertTrue(blockChain.isChainValid());

        blockChain.add("Number 1");
        blockChain.add("Number 2");
        blockChain.add("Number 3");
        blockChain.add("Number 4");

        assertTrue(blockChain.isChainValid());

        Block changeMe = blockChain.blockChain.get(1);
        changeMe = new Block("Number 2", changeMe.previousHash); // timestamp should be different
        blockChain.blockChain.set(1, changeMe);

        assertFalse(blockChain.isChainValid());

        // It hasn't been mined, lets mine it now. This should also fail
        changeMe.mineBlock(difficulty);
        blockChain.blockChain.set(1, changeMe);

        // The previousHash of the next block wont match up
        assertFalse(blockChain.isChainValid());
    }
}