package net.prokyo.codesync.server.session;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;
import net.prokyo.codesync.server.CSConnection;
import net.prokyo.codesync.server.team.Member;
import net.prokyo.codesync.server.team.Permission;

public class Session {

	@Getter private List<Member> members;

	public Session() {
		this.members = new CopyOnWriteArrayList<>();
	}

	public Member addMember(CSConnection connection, boolean isHost) {
		UUID id;
		while (this.getMember(id = UUID.randomUUID()) != null) ;
		Member member = new Member(id, connection, isHost ? Permission.HOST : Permission.READ);
		this.members.add(member);
		return member;
	}

	public Member getMember(String name) {
		return this.members.stream().filter(member -> member.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public Member getMember(UUID id) {
		return this.members.stream().filter(member -> member.getId().equals(id)).findFirst().orElse(null);
	}

	public boolean isNameExisting(String name) {
		return this.getMember(name) != null;
	}
}
