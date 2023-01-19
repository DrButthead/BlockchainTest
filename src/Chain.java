import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Chain {
    public List<Block> blockChain = new ArrayList<>();

    public Chain() {}

    public void add(String data) {
        String previousHash;

        if (blockChain.isEmpty()) {
            previousHash = Block.INITIAL_HASH;
        } else {
            Block previousBlock = blockChain.get(blockChain.size() - 1);
            previousHash = previousBlock.hash;
        }

        blockChain.add(new Block(data, previousHash));
    }


    @Override
    public String toString() {
        return blockChain.toString();
    }

    public String toJsonString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
    }
}
