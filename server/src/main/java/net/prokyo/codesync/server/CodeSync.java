package net.prokyo.codesync.server;

import de.prokyo.network.server.ProkyoServer;
import de.prokyo.network.server.event.ConnectionEstablishedEvent;
import lombok.Getter;
import net.prokyo.codesync.server.event.ProkyoNetListener;
import net.prokyo.codesync.server.session.Session;

public class CodeSync {

	@Getter private final ProkyoServer server = new ProkyoServer();
	private final ProkyoNetListener listener = new ProkyoNetListener(this);
	@Getter private Session session;
	private boolean initialized;

	public void init() {
		if (this.initialized) return;
		this.session = new Session();
		this.server.getEventManager().register(ConnectionEstablishedEvent.class, this.listener::handleConnectionEstablishedEvent);
		this.initialized = true;
	}

	public void start(String host, int port) {
		this.init();

		try {
			server.start(host, port);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String... args) {
		new CodeSync().start("127.0.0.1", 1337);
	}

}
