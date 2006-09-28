/* This code is part of Freenet. It is distributed under the GNU General
 * Public License, version 2 (or at your option any later version). See
 * http://www.gnu.org/ for further details of the GPL. */
package freenet.node.fcp;

import freenet.keys.FreenetURI;
import freenet.node.Node;
import freenet.support.SimpleFieldSet;

public class PutFetchableMessage extends FCPMessage {

	PutFetchableMessage(String ident, FreenetURI uri) {
		this.identifier = ident;
		this.uri = uri;
	}
	
	final String identifier;
	final FreenetURI uri;
	
	public SimpleFieldSet getFieldSet() {
		SimpleFieldSet fs = new SimpleFieldSet();
		fs.put("Identifier", identifier);
		if(uri != null)
			fs.put("URI", uri.toString(false));
		return fs;
	}

	public String getName() {
		return "PutFetchable";
	}

	public void run(FCPConnectionHandler handler, Node node)
			throws MessageInvalidException {
		throw new MessageInvalidException(ProtocolErrorMessage.INVALID_MESSAGE, "PutFetchable goes from server to client not the other way around", identifier);
	}

}
