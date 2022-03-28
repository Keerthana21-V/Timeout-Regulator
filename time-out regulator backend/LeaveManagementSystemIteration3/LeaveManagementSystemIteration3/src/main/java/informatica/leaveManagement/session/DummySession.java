package Project.leaveManagement.session;

import java.util.HashMap;

public class DummySession {
	
	private HashMap<String,Object> hmap;
	private String id;
	
	public String getId() {
		return id;
	}

	public Object getAttribute(String key) {
		return hmap.get(key);
	}
	
	public void setAttribute(String key,Object value) {
		hmap.put(key, value);
	}
	
	public DummySession(HashMap<String,Object> hmap,String id) {
		this.hmap = hmap;
		this.id = id;
	}

	public HashMap<String, Object> getHmap() {
		return hmap;
	}

	@Override
	public String toString() {
		return "DummySession [hmap=" + hmap + "]";
	}
	
}
