/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */
package freenet.io.comm;

/**
 * @author amphibian
 * 
 * Everything that is needed to send a message, including the Peer.
 * Implemented by PeerNode, for example.
 */
public interface PeerContext {
    // Largely opaque interface for now
    Peer getPeer();

    /** Force the peer to disconnect */
	void forceDisconnect();

	/** Is the peer connected? Have we established the session link? */
	boolean isConnected();
	
	/** Is the peer connected? are we able to route requests to it? */
	boolean isRoutable();
}
