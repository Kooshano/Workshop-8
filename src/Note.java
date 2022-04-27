import java.io.Serializable;
import java.util.Random;

public class Note implements Serializable {
    Random random = new Random();
    private String name;
    private String body;
    private int day = random.nextInt(1,30);
    private int month = random.nextInt(1,12);
    private int year = random.nextInt(1390,1400);


    public Note(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return (getName() + "\n" + getBody());
    }
}
