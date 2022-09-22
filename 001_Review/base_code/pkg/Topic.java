package pkg;
import java.util.*;
import java.io.*;

public class Topic extends Message {

	// Default Constructor
	public Topic() {
	}

	// Parameterized constructor
	public Topic(String auth, String subj, String bod, int i) {
		//leeching off of Message.java's parameterized constructor like usual
		super(auth, subj, bod, i);
	}

	// Returns if it's a reply (false)
	public boolean isReply(){
		return false;
	}
}
