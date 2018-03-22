package utils;

import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.IncidencePOJO;

public class Incidence2Pojo {

	public static IncidencePOJO convert(Incidence i2) {
		IncidencePOJO pojo = new IncidencePOJO(); 
		pojo.id = i2.getId().toString();
		pojo.agent = i2.getAgent().toString();
		pojo.inciName = i2.getInciName();
		pojo.location = i2.getLocation().toString();
		pojo.state = i2.getState().toString();
		return pojo; 
		
	}

}
