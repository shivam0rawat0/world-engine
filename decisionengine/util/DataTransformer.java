package decisionengine.util;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class DataTransformer {
    public static byte[] serialize(HashMap<String, String> map) throws Exception {
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(map);
            return baos.toByteArray();
        }
    }

    // Deserialize byte[] back to HashMap
    @SuppressWarnings("unchecked")
    public static HashMap<String, String> deserialize(byte[] bytes) throws Exception {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (HashMap<String, String>) ois.readObject();
        }
    }
}
