package net.prokyo.codesync.server.team;

public enum Permission {

	READ,
	WRITE,
	HOST;

	public int value() {
		return (int) Math.pow(this.ordinal() + 1, 2);
	}

	public static int calculate(Permission... permissions) {
		int value = 0;
		for (Permission permission : permissions) {
			value |= permission.value();
		}
		return value;
	}
}
