package net.prokyo.codesync.server.team;

public enum Permission {

	READ,
	WRITE,
	HOST;

	public static int calculate(Permission... permissions) {
		int value = 0;
		for (Permission permission : permissions) {
			value |= permission.ordinal();
		}
		return value;
	}
}
