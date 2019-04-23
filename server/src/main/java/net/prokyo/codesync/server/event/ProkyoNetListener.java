package net.prokyo.codesync.server.event;

import de.prokyo.network.server.event.ConnectionEstablishedEvent;
import lombok.RequiredArgsConstructor;
import net.prokyo.codesync.server.CSConnection;
import net.prokyo.codesync.server.CodeSync;
import net.prokyo.codesync.server.event.events.ClientConnectedEvent;

@RequiredArgsConstructor
public class ProkyoNetListener {

	private final CodeSync codeSync;

	public void handleConnectionEstablishedEvent(ConnectionEstablishedEvent event) {
		event.getClientConnection().enableCompression();
		this.codeSync.getServer().getEventManager().fire(new ClientConnectedEvent(new CSConnection(
			event.getClientConnection()
		)));
	}

}
