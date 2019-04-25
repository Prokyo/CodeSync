package net.prokyo.codesync.server.team;

import lombok.Getter;
import net.prokyo.codesync.server.CSConnection;

public class Member {

	@Getter private CSConnection connection;
	private int permissions;

	public Member(CSConnection connection, Permission... permissions) {
		this.connection = connection;
		this.permissions = Permission.calculate(permissions);
	}

	public boolean hasPermission(Permission permission) {
		return (permission.ordinal() & this.permissions) == permission.ordinal();
	}
}
