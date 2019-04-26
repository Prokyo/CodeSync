package net.prokyo.codesync.server.team;

/**
 * The permissions a member can have.
 */
public enum Permission {

	READ,
	WRITE,
	HOST;

	/**
	 * Calculates the int value of the permission.
	 * @return
	 */
	public int value() {
		return (int) Math.pow(this.ordinal() + 1, 2);
	}

	/**
	 * Calculates the int value of all permission together.
	 * @param permissions The permission to be summed together.
	 * @return The int value, representing the given permissions.
	 */
	public static int calculate(Permission... permissions) {
		int value = 0;
		for (Permission permission : permissions) {
			value |= permission.value();
		}
		return value;
	}
}
