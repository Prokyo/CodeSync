package net.prokyo.codesync.server.session;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;
import net.prokyo.codesync.server.CSConnection;
import net.prokyo.codesync.server.team.Member;
import net.prokyo.codesync.server.team.Permission;

/**
 * The session stores data about the current project CodeSync syncs like all members connected to the session.
 * It also is the intersect of the team management with the server.
 */
public class Session {

	/**
	 * All members connected to the session.
	 */
	@Getter private List<Member> members;

	public Session() {
		this.members = new CopyOnWriteArrayList<>();
	}

	/**
	 * Adds a member to the members list of this session.
	 * @param connection The CodeSync-Network-Connection of the member.
	 * @param isHost     True if this member is the host of the session.
	 * @return The created and registered member object-
	 */
	public Member addMember(CSConnection connection, boolean isHost) {
		UUID id;
		while (this.getMember(id = UUID.randomUUID()) != null) ;
		Member member = new Member(id, connection, isHost ? Permission.HOST : Permission.READ);
		this.members.add(member);
		return member;
	}

	/**
	 * Returns the member by the given name.
	 * @param name The name of the member to be searching for.
	 * @return The member object of the given name or null if no member with this name could be found.
	 */
	public Member getMember(String name) {
		return this.members.stream().filter(member -> member.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	/**
	 * Returns the member by the given UUID.
	 * @param id The UUID of the member to be searching for.
	 * @return The member object of the given UUID or null if no member could be found.
	 */
	public Member getMember(UUID id) {
		return this.members.stream().filter(member -> member.getId().equals(id)).findFirst().orElse(null);
	}

	/**
	 * Checks if the given name is already existing by checking if a member by this name already exists.
	 * @param name The name to be checked.
	 * @return True if the name exists.
	 */
	public boolean isNameExisting(String name) {
		return this.getMember(name) != null;
	}
}
