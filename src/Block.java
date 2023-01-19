import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private final String data;
    /**
     * Number of millis since 1/1/1970
     */
    private final long timestamp;

    public static final String INITIAL_HASH = "0";

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        // Make sure we do this AFTER we've set the other values
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(previousHash + timestamp + data);
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", data='" + data + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
