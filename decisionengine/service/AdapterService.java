package decisionengine.service;

import java.util.HashMap;
import decisionengine.util.DataTransformer;

public class AdapterService {

    AdapterLoader adapterLoader;

    public AdapterService(){
        adapterLoader = new AdapterLoader(System.getProperty("adapter.path"));
    }

    public String execute(byte[] bdata) throws Exception{
        HashMap<String, String> data = DataTransformer.deserialize(bdata);
        System.out.println(data.get("capability"));
	    System.out.println(data.get("entity"));
	    System.out.println(data.get("number"));
        AdapterLayer adapterLayer = loadAdapter(
                data.get("capability"),
                data.get("entity")
        );
        return adapterLayer.execute(data);
    }

    private AdapterLayer loadAdapter(String capability,String entity) {
        return adapterLoader.getAdaptor(capability, entity);
    }
}