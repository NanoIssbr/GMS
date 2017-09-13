/**
 * 
 */
package org.gms.beans;


/**
 * @author hero
 *
 */
public class ErrorObject {
	private String index ;
	private String messages;
	/**
	 * constructor superClass 
	 */
	public ErrorObject(String ind , String m){
		this.setIndex(ind);
		this.setMessages(m);
	}
	public ErrorObject(String message){
		this.setMessages(message);
		this.setIndex("No Index");
	}
	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}
	/**
	 * @return the messages
	 */
	public String getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	

}
