package Project.leaveManagement.session;

public class SessionMainClass {

	public static void main(String[] args) {
		
		String sessionId = SessionManager.createSession();
		System.out.println(sessionId);
		
		DummySession d = SessionManager.getSession(sessionId);
		d.setAttribute("eid",100);
		d.setAttribute("ename","SaDash");
		sessionId = SessionManager.storeSession(d);
		//DummySession d;
		d = SessionManager.getSession(sessionId);
		System.out.println(d);
	}

}
