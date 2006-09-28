/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */
package freenet.node;

import freenet.crypt.DiffieHellman;
import freenet.crypt.Yarrow;
import freenet.io.comm.NotConnectedException;
import freenet.io.comm.PeerParseException;
import freenet.support.FileLoggerHook;
import freenet.support.Logger;
import freenet.support.SimpleFieldSet;

/**
 * @author amphibian
 * 
 * When the code is invoked via this class, it:
 * - Creates two nodes.
 * - Connects them to each other
 * - Sends pings from the first node to the second node.
 * - Prints on the logger when packets are sent, when they are
 *   received, (by both sides), and their sequence numbers.
 */
public class RealNodePingTest {

    public static void main(String[] args) throws FSParseException, PeerParseException, InterruptedException {
        FileLoggerHook fh = Logger.setupStdoutLogging(Logger.MINOR, "");
        Yarrow yarrow = new Yarrow();
        DiffieHellman.init(yarrow);
        // Create 2 nodes
        Node node1 = new Node(5001, yarrow, null, "pingtest-", 0, false, fh, 0);
        Node node2 = new Node(5002, yarrow, null, "pingtest-", 0, false, fh, 0);
        SimpleFieldSet node1ref = node1.exportPublicFieldSet();
        SimpleFieldSet node2ref = node2.exportPublicFieldSet();
        // Connect
        node1.peers.connect(node2ref);
        node2.peers.connect(node1ref);
        // No swapping
        node1.start(null);
        node2.start(null);
        // Ping
        PeerNode pn = node1.peers.myPeers[0];
        int pingID = 0;
        Thread.sleep(20000);
        //node1.usm.setDropProbability(4);
        while(true) {
            Logger.minor(RealNodePingTest.class, "Sending PING "+pingID);
            boolean success;
            try {
                success = pn.ping(pingID);
            } catch (NotConnectedException e1) {
                Logger.normal(RealNodePingTest.class, "Not connected");
                continue;
            }
            if(success)
                Logger.normal(RealNodePingTest.class, "PING "+pingID+" successful");
            else
                Logger.normal(RealNodePingTest.class, "PING FAILED: "+pingID);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Shouldn't happen
            }
            pingID++;
        }
    }
}
