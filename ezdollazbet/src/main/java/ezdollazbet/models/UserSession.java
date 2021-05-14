package ezdollazbet.models;

import lombok.Getter;

public final class UserSession {

	private static UserSession instance;
	
	@Getter
	private String login;
	@Getter
	private int userId;

	private UserSession(String login, int userId) {
		this.login = login;
		this.userId = userId;
	}

	public static UserSession getSession(String login, int userId) {
		if (instance == null) {
			instance = new UserSession(login, userId);
		}
		return instance;
	}
	public static UserSession getSession() {
		return instance;
	}

	public void cleanUserSession() {
		login = null;
		userId = 0;
		instance = null;
	}

	@Override
	public String toString() {
		return "SessionCredentials" + "login" + login;
	}
}