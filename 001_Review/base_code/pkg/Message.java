package pkg;
import java.util.*;
import java.io.*;

public class Message {
	public String author;
	public String subject;
	public String body;
	public int id;
	public ArrayList<Message> replies;
	// Default Constructor
	public Message() {
		author = null;
		subject = null;
		body = null;
		id = 0;
		replies = new ArrayList<Message>();
	}
	
	// Parameterized Constructor
	public Message(String auth, String subj, String bod, int i) {
		author = auth;
		subject = subj;
		body = bod;
		id = i;
		replies = new ArrayList<Message>();
	}

	// This function is responsbile for printing the Message
	// (whether Topic or Reply), and all of the Message's "subtree" recursively:

	// After printing the Message with indentation n and appropriate format (see output details),
	// it will invoke itself recursively on all of the Replies inside its childList, 
	// incrementing the indentation value at each new level.

	// Note: Each indentation increment represents 2 spaces. e.g. if indentation ==  1, the reply should be indented 2 spaces, 
	// if it's 2, indent by 4 spaces, etc. 
	public void print(int indentation){
		//this if statement is not totally necessary but helps with making sure
		//that the spacing between displayed messages is appropriate and easy to look at
		if(isReply())
		{
			System.out.println();
		}
		//this loop sets the indentation of the subject line
		for(int i = 0; i < indentation; i++)
		{
			System.out.print("  ");
		}
		//prints the subject line
		System.out.print("Message #" + id + ": ");
		System.out.println("\"" + subject + "\"");
		//this loop sets the indentation of the body line
		for(int i = 0; i < indentation; i++)
		{
			System.out.print("  ");
		}
		//prints the body line
		System.out.print("From " + author + ": \"" + body + "\"\n");

		//recursively calls the print method on all of the messages' replies
		if(replies != null)
		{
			for(int i = 0; i < replies.size(); i++)
			{
				replies.get(i).print(indentation + 1);
			}
		}
	}

	// Default function for inheritance
	public boolean isReply(){
		return false;
	}

	// Returns the subject String
	public String getSubject(){
		return subject;
	} 

	// Returns the ID
	public int getId(){
		return id;
	}

	// Adds a child pointer to the parent's childList.
	public void addChild(Message child){
		replies.add(child);
	}

}
