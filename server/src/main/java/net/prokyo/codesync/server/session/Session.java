package net.prokyo.codesync.server.session;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.prokyo.codesync.server.team.Member;

public class Session {

	private List<Member> members;

	public Session() {
		this.members = new CopyOnWriteArrayList<>();
	}
}
