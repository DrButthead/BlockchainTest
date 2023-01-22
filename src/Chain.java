import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Chain {
    public int difficulty;
    public List<Block> blockChain = new ArrayList<>();

    public Chain(int difficulty) {
        this.difficulty = difficulty;
    }

    public void add(String data) {
        String previousHash;

        if (blockChain.isEmpty()) {
            previousHash = Block.INITIAL_HASH;
        } else {
            Block previousBlock = blockChain.get(blockChain.size() - 1);
            previousHash = previousBlock.hash;
        }

        Block newBlock = new Block(data, previousHash);
        newBlock.mineBlock(difficulty);
        blockChain.add(newBlock);
    }

    public Boolean isChainValid() {
        Block previousBlock;
        Block currentBlock;
        String hashTarget = StringUtil.repeat('0', difficulty);

        // Loop through the whole chain to check the hashes are correct
        for (int i = 1; i < blockChain.size(); i++) {
            previousBlock = blockChain.get(i-1);
            currentBlock = blockChain.get(i);

            // Compare the registered hash and the calculated hash
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes do not match!");
                return false;
            }

            // Compare previous registered hash and the calculated hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes to not match!");
                return false;
            }

            // Make sure each hash starts with difficulty * '0'
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;

            }
        }
        return true;
    }


    @Override
    public String toString() {
        return blockChain.toString();
    }

    public String toJsonString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
    }
}
