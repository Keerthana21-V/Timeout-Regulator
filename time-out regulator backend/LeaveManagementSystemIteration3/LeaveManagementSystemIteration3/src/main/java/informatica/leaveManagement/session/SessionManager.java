package Project.leaveManagement.session;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SessionManager {
	
	public static String filePath = "C:\\Sessions\\";

	public static DummySession getSession(String fileName) {
		
		ObjectMapper mapper = new ObjectMapper();
		DummySession d = null;
		try { 
			File jsonFile = new File(
					filePath+fileName+".json"); 
			HashMap<String, Object> mapObject = mapper.readValue(
				jsonFile, new TypeReference<HashMap<String,Object>>(){});
			d = new DummySession(mapObject,fileName);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}  
		return d;
		
	}
	
	public static String storeSession(DummySession d) {
		
		ObjectMapper objectMapper = new ObjectMapper(); 
		try { 
			objectMapper.writeValue(
					new File(
						filePath+d.getId()+".json"),d.getHmap()); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return d.getId();
	}
	
	public static String createSession() {
		
		String fileName = 
				RandomStringUtils.randomAlphaNumeric(10);
		try { 
			File jsonFile = new File(
					filePath+fileName+".json"); 
			FileWriter fw = new FileWriter(jsonFile);
			fw.write("{}");
			fw.close();
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		return fileName;
	}
	
	public static boolean deleteSession(String sessionId) {
		
		try { 
			File jsonFile = new File(
					filePath+sessionId+".json"); 
			jsonFile.delete();
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
			return false;
		}
		return true;
	}
	
}
