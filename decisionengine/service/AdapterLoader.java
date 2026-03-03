package decisionengine.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class AdapterLoader extends ClassLoader {

    private static final String root = "adapter.";
    private final String classDir;
    private final Map<String, AdapterLayer> adapters = new HashMap<>();

    public AdapterLoader(String classDir) {
        this.classDir = classDir;
    }

    private Class<?> loadClassFromFile(String capability, String entity) throws IOException {
        String className = capability + entity + ".class";
        String classPath = capability + "." + entity + "." + capability + entity;
        File classFile = new File(classDir + "/" + capability + "/" + entity + "/" + className);

        System.out.println("cn: " + className);
        System.out.println("cp: " + classPath);
        System.out.println("cfn: " + classFile.getPath());

        byte[] classBytes = null;
        if (classFile.exists()) {
            classBytes = Files.readAllBytes(classFile.toPath());
            return defineClass(root + classPath, classBytes, 0, classBytes.length);
        }
        throw new IOException("Adaptor doesn't exist:" + className);
    }

    public AdapterLayer getAdaptor(String capability, String entity) {
        String adapter = capability + entity;
        if (!adapters.containsKey(adapter)) {
            try {
                Class<?> implClass = loadClassFromFile(capability, entity);
                adapters.put(adapter, (AdapterLayer) implClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                System.out.println("Adaptor error: " + e.getMessage());
                return null;
            }
        }
        return adapters.get(adapter);
    }
}