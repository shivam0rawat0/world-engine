package adapter.getBalance.person;

import decisionengine.service.AdapterLayer;
import java.util.HashMap;

public class getBalanceperson implements AdapterLayer{
	@Override
    	public String execute(HashMap<String,String> data) {
			if("1".equals(data.get("isNative"))){
				return "native dont hold accoutn owenship";
			}
        	return "bank says:" + data.get("account-number");
    	}
}