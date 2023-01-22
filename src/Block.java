import java.util.Date;

public class Block {

    public static final String INITIAL_HASH = "0";
    private final String data;
    /**
     * Number of millis since 1/1/1970
     */
    private final long timestamp;
    public String hash;
    public String previousHash;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        // Make sure we do this AFTER we've set the other values
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(previousHash + timestamp + nonce + data);
    }

    public void mineBlock(int difficulty) {
        // Create a String with difficulty * "0"
        String target = StringUtil.repeat('0', difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined!");
    }

    @Override
    public String toString() {
        return "Block{" + "hash='" + hash + '\'' + ", previousHash='" + previousHash + '\'' + ", data='" + data + '\'' + ", timestamp=" + timestamp + '}';
    }
}
