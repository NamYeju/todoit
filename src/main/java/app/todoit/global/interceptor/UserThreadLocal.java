package app.todoit.global.interceptor;

import app.todoit.domain.auth.entity.User;

public class UserThreadLocal {
	private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

	public static void set(User user) {
		threadLocal.set(user);
	}

	public static User get() {
		return threadLocal.get();
	}

	public static void delete(){
		threadLocal.remove();
	}

}
