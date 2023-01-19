import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    @Test
    void testChain() {
        Block genesisBlock = new Block("Hi, I'm the first block!!", "0");
        System.out.println("Hash for block 1: " + genesisBlock.hash);

        Block secondBlock = new Block("This is number 2", genesisBlock.hash);
        System.out.println("Hash for block 2: " + secondBlock.hash);

        Block thirdBlock = new Block("Number 3 goes here", secondBlock.hash);
        System.out.println("Hash for block 3: " + thirdBlock.hash);

        assertNotEquals(genesisBlock.hash, secondBlock.hash);
        assertNotEquals(genesisBlock.hash, thirdBlock.hash);
        assertNotEquals(secondBlock.hash, thirdBlock.hash);

        assertEquals(genesisBlock.hash, secondBlock.previousHash);
        assertEquals(secondBlock.hash, thirdBlock.previousHash);
    }
}