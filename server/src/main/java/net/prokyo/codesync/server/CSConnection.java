package net.prokyo.codesync.server;

import de.prokyo.network.server.ClientConnection;
import lombok.Data;

@Data
public class CSConnection {

	private final ClientConnection prokyoConnection;

}
