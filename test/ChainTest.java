import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainTest {

    @Test
    void add() {
        Chain blockChain = new Chain();

        blockChain.add("This is the first block");
        blockChain.add("Second");
        blockChain.add("third");

        System.out.println(blockChain);
        System.out.println(blockChain.toJsonString());
    }

    @Test
    void isChainValid() {
        Chain blockChain = new Chain();

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
    }
}