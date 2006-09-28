/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */
package freenet.client.events;

import freenet.keys.ClientKey;

/**
 * Fetched a block of data.
 */
public class GotBlockEvent implements ClientEvent {

	public static final int code = 0x02;
	public final ClientKey key; 
	
	public GotBlockEvent(ClientKey key) {
		this.key = key;
	}

	public String getDescription() {
		return "Fetched a block of data: "+key.getURI();
	}

	public int getCode() {
		return code;
	}

}
