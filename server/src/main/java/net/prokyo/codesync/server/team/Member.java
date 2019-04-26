package net.prokyo.codesync.server.team;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import net.prokyo.codesync.server.CSConnection;

/**
 * The member is the object uniting the pure network connection with the CodeSync team management and general project management.
 * It offers the functionality for permission-handling and subdivision of members by name and UUID.
 */
public class Member {

	/**
	 * The CodeSync-Network-Connection of the member.
	 */
	@Getter private CSConnection connection;
	/**
	 * The UUID of the member. This ID is - as the abbreviation tells - unique and must not existing.
	 */
	@Getter private UUID id;
	/**
	 * The name of the member. The name should be unique as well, but does not have to.
	 */
	@Getter @Setter private String name;
	/**
	 * The permissions shifted into a int value.
	 */
	private int permissions;

	public Member(UUID id, CSConnection connection, Permission... permissions) {
		this.id = id;
		this.connection = connection;
		this.permissions = Permission.calculate(permissions);
	}

	/**
	 * Checks if the member has the given permission.
	 * @param permission The permission to be checked.
	 * @return True if the member has the given permission.
	 */
	public boolean hasPermission(Permission permission) {
		return (permission.value() & this.permissions) == permission.value();
	}

	/**
	 * Adds a permission to the permissions of the member.
	 * @param permission The permission to be added.
	 */
	public void addPermission(Permission permission) {
		this.permissions |= permission.value();
	}

	/**
	 * Revokes a permission from the permissions of the member.
	 * @param permission The permission to be revoked.
	 */
	public void removePermission(Permission permission) {
		this.permissions &= ~permission.value();
	}
}
