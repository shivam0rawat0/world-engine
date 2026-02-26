import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class Request {
    public static void main(String[] args) throws Exception {
        HashMap<String, String> data = new HashMap<>();

        Path path = Path.of(args[0]);
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String pair[] = line.split("=");
            data.put(pair[0],pair[1]);
        }

        // Serialize to a file
        try (FileOutputStream fos = new FileOutputStream("data.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        }

        System.out.println("graph.bin created successfully!");
    }
}
