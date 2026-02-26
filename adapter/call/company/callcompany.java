package adapter.call.company;

import decisionengine.service.AdapterLayer;
import java.util.HashMap;

public class callcompany implements AdapterLayer{
	@Override
    	public String execute(HashMap<String,String> data) {
		System.out.println("callcompany:"+data.get("number"));
        	return data.get("number");
    	}
}