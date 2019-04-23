package net.prokyo.codesync.server.event.events;

import de.prokyo.network.common.event.Event;
import lombok.Data;
import net.prokyo.codesync.server.CSConnection;

@Data
public class ClientConnectedEvent implements Event {

	private final CSConnection connection;

}
