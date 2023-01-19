import org.junit.jupiter.api.Test;

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
}