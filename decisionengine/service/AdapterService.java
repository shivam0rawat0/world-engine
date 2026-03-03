package decisionengine.service;

import java.util.HashMap;
import decisionengine.util.DataTransformer;

public class AdapterService {

    AdapterLoader adapterLoader;

    public AdapterService() {
        adapterLoader = new AdapterLoader(System.getProperty("adapter.path"));
    }

    public String execute(byte[] bdata) throws Exception {
        HashMap<String, String> data = DataTransformer.deserialize(bdata);
        String capability = data.get("capability");
        String entity = data.get("entity");
        
        if (capability == null || entity == null) {
            throw new IllegalArgumentException(
                    "LLM extraction error: missing capability or entity");
        }

        AdapterLayer adapterLayer = loadAdapter(capability, entity);
        if (adapterLayer == null) {
            throw new RuntimeException(
                    "Adapter not found: " + capability + "." + entity +
                            " - Did developer forget to deploy it?");
        }
        return adapterLayer.execute(data);
    }

    private AdapterLayer loadAdapter(String capability, String entity) {
        return adapterLoader.getAdaptor(capability, entity);
    }
}