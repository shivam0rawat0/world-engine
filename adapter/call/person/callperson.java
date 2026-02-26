package adapter.call.person;

import decisionengine.service.AdapterLayer;
import java.util.HashMap;

public class callperson implements AdapterLayer{
	@Override
    	public String execute(HashMap<String,String> data) {
		System.out.println("callperson:"+data.get("number"));
        	return data.get("number");
    	}
}