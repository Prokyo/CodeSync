package net.prokyo.codesync.server.team;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import net.prokyo.codesync.server.CSConnection;

public class Member {

	@Getter private CSConnection connection;
	@Getter private UUID id;
	@Getter @Setter private String name;
	private int permissions;

	public Member(UUID id, CSConnection connection, Permission... permissions) {
		this.id = id;
		this.connection = connection;
		this.permissions = Permission.calculate(permissions);
	}

	public boolean hasPermission(Permission permission) {
		return (permission.value() & this.permissions) == permission.value();
	}

	public void addPermission(Permission permission) {
		this.permissions |= permission.value();
	}

	public void removePermission(Permission permission) {
		this.permissions &= ~permission.value();
	}
}
