package pkg;
import java.util.*;
import java.io.*;

public class BBoard {		// This is your main file that connects all classes.
	// Think about what your global variables need to be.
	public String name;
	public int counter;
	public User currentUser;
	public ArrayList<User> userList;
	public ArrayList<Message> messageList;

	// Default constructor that creates a board with a defaulttitle, empty user and message lists,
	// and no current user
	public BBoard() {
		name = "default board";
		currentUser = null;
		counter = 1;
		messageList = new ArrayList<Message>();
		userList = new ArrayList<User>();
	}

	// Same as the default constructor except it sets the title of the board
	public BBoard(String ttl) {	
		name = ttl;
		currentUser = null;
		counter = 1;
		messageList = new ArrayList<Message>();
		userList = new ArrayList<User>();
	}

	// Gets a filename of a file that stores the user info in a given format (users.txt)
	// Opens and reads the file of all authorized users and passwords
	// Constructs a User object from each name/password pair, and populates the userList ArrayList.
	public void loadUsers(String inputFile) throws FileNotFoundException {
		File f = new File(inputFile);
		Scanner fileScan = new Scanner(f);

		while (fileScan.hasNext())
		{
			String word = fileScan.nextLine();
			User a = new User(word.substring(0, word.indexOf(" ")), word.substring(word.indexOf(" ") + 1));
			userList.add(a); 
		}

		/* Check your user input below!
		for(int i = 0; i < userList.size(); i++) {   
			System.out.println(userList.get(i).getUsername());
			System.out.println(userList.get(i).getPassword());
		} 
		*/
	}

	// Asks for and validates a user/password. 
	// This function asks for a username and a password, then checks the userList ArrayList for a matching User.
	// If a match is found, it sets currentUser to the identified User from the list
	// If not, it will keep asking until a match is found or the user types 'q' or 'Q' as username to quit
	// When the users chooses to quit, sayu "Bye!" and return from the login function
	public void login(){
		while(currentUser == null)
		{
			System.out.print("Enter your username ('Q' or 'q' to quit): ");
			Scanner reader = new Scanner(System.in);
			//user inputs their username
			String loginUsername = reader.nextLine();
			if(loginUsername.equals("Q") || loginUsername.equals("q"))
			{
				return;
				//accounts for user choosing to quit instead of putting username
			}
			System.out.print("Enter your password: ");
			String loginPassword = reader.nextLine();
			//for loop that traverses the user array and finds which user is being logged in 
			//before welcoming the returning user or prompting a user to retry if they get their
			//login info wrong
			for(int i = 0; i < userList.size(); i++)
			{
				if(userList.get(i).getUsername().equals(loginUsername) && userList.get(i).getPassword().equals(loginPassword))
				{
					currentUser = userList.get(i);
					System.out.println("\nWelcome back " + currentUser.getUsername() + "!");
				}
				if(currentUser == null && i == userList.size() - 1)
				{
					System.out.println("Invalid password. Please try again.");
				}
			}
		}
	}
	
	// Contains main loop of Bulletin Board
	// IF and ONLY IF there is a valid currentUser, enter main loop, displaying menu items
	// --- Display Messages ('D' or 'd')
	// --- Add New Topic ('N' or 'n')
	// --- Add Reply ('R' or 'r')
	// --- Change Password ('P' or 'p')
	// --- Quit ('Q' or 'q')
	// With any wrong input, user is asked to try again
	// Q/q should reset the currentUser to 0 and then end return
	// Note: if login() did not set a valid currentUser, function must immediately return without showing menu
	public void run(){
		Scanner topicReader = new Scanner(System.in);
		System.out.println(name);
		login();

		//the main loop of the program that will repeatedly print the menu and route the user
		//to different methods depending on their input
		while(currentUser != null)
		{
			System.out.print("\nMenu\n  - Display Messages ('D' or 'd')\n  - Add New Topic ('N' or 'n')\n  - Add Reply ('R' or 'r')\n  - Change Password ('P' or 'p')\n  - Quit ('Q' or 'q')\nChoose an action: ");
			String userChoice = topicReader.nextLine();
			if(userChoice.equals("D") || userChoice.equals("d"))
			{
				display();
			}
			else if(userChoice.equals("N") || userChoice.equals("n"))
			{
				addTopic();
			}
			else if(userChoice.equals("R") || userChoice.equals("r"))
			{
				addReply();
			}
			else if(userChoice.equals("P") || userChoice.equals("p"))
			{
				setPassword();
			}
			else if(userChoice.equals("Q") || userChoice.equals("q"))
			{
				currentUser = null;
			}
			else 
			{
				//ensures that the code does not break if the user accidentally makes a typo in selecting from the menu
				System.out.println("Please type in a valid letter.");
			}
		}
		System.out.println("\nBye!");
	}

	// Traverse the BBoard's message list, and invoke the print function on Topic objects ONLY
	// It will then be the responsibility of the Topic object to invoke the print function recursively on its own replies
	// The BBoard display function will ignore all reply objects in its message list
	private void display(){
		//traverses the message list and prints all messages that are not replies
		for(int i = 0; i < messageList.size(); i++)
		{
			if(!messageList.get(i).isReply())
			{
				System.out.println("\n--------------------------------------------");
				messageList.get(i).print(0);
				System.out.println("--------------------------------------------");
			}
		}
	}


	// This function asks the user to create a new Topic (i.e. the first message of a new discussion "thread")
	// Every Topic includes a subject (single line), and body (single line)

	/* 
	Subject: "Thanks"
	Body: "I love this bulletin board that you made!"
	*/

	// Each Topic also stores the username of currentUser; and message ID, which is (index of its Message + 1)

	// For example, the first message on the board will be a Topic who's index will be stored at 0 in the messageList ArrayList,
	// so its message ID will be (0+1) = 1
	// Once the Topic has been constructed, add it to the messageList
	// This should invoke your inheritance of Topic to Message
	private void addTopic(){
		//prompts user to provide subject and body
		Scanner topicReader = new Scanner(System.in);
		System.out.print("\nSubject: ");
		String subjectInput = topicReader.nextLine();
		System.out.print("Body: ");
		String bodyInput = topicReader.nextLine();
		//creates a topic variable out of user input and places it in the messageList array
		Topic aTopic = new Topic(currentUser.getUsername(), subjectInput, bodyInput, counter);
		messageList.add(aTopic);
		counter++;
	}

	// This function asks the user to enter a reply to a given Message (which may be either a Topic or a Reply, so we can handle nested replies).
	//		The addReply function first asks the user for the ID of the Message to which they are replying;
	//		if the number provided is greater than the size of messageList, it should output and error message and loop back,
	// 		continuing to ask for a valid Message ID number until the user enters it or -1.
	// 		(-1 returns to menu, any other negative number asks again for a valid ID number)
	
	// If the ID is valid, then the function asks for the body of the new message, 
	// and constructs the Reply, pushing back the Reply on to the messageList.
	// The subject of the Reply is a copy of the parent Topic's subject with the "Re: " prefix.
	// e.g., suppose the subject of message #9 was "Thanks", the user is replying to that message:


	/*
			Enter Message ID (-1 for Menu): 9
			Body: It was a pleasure implementing this!
	*/

	// Note: As before, the body ends when the user enters an empty line.
	// The above dialog will generate a reply that has "Re: Thanks" as its subject
	// and "It was a pleasure implementing this!" as its body.

	// How will we know what Topic this is a reply to?
	// In addition to keeping a pointer to all the Message objects in BBoard's messageList ArrayList
	// Every Message (wheather Topic or Reply) will also store an ArrayList of pointers to all of its Replies.
	// So whenever we build a Reply, we must immediately store this Message in the parent Message's list. 
	// The Reply's constructor should set the Reply's subject to "Re: " + its parent's subject.
	// Call the addChild function on the parent Message to push back the new Message (to the new Reply) to the parent's childList ArrayList.
	// Finally, push back the Message created to the BBoard's messageList. 
	// Note: When the user chooses to return to the menu, do not call run() again - just return fro mthis addReply function. 
	private void addReply(){
		//takes user input for reply
		Scanner replyReader = new Scanner(System.in);
		System.out.print("\nEnter Message ID (-1 for Menu): ");
		String messID = replyReader.nextLine();
		//converts the user's string input to a readable integer
		int messIDReal = Integer.parseInt(messID);
		//allows the user to escape the addReply() sequence with an entry of "-1"
		if(messIDReal == -1)
		{
			return;
		}
		//collects input on the reply's body
		System.out.print("Body: ");
		String replyBody = replyReader.nextLine();
		//creates a reply variable out of user input and adds it to the messageList
		Reply standard = new Reply(currentUser.getUsername(), "Re: " + messageList.get(messIDReal - 1).getSubject(), replyBody, counter);
		messageList.add(standard);
		counter++;
		//additionally adds the reply to its parent's childList (important for print method)
		messageList.get(messIDReal - 1).addChild(standard);


	}

	// This function allows the user to change their current password.
	// The user is asked to provide the old password of the currentUser.
	// 		If the received password matches the currentUser password, then the user will be prompted to enter a new password.
	// 		If the received password doesn't match the currentUser password, then the user will be prompted to re-enter the password. 
	// 		The user is welcome to enter 'c' or 'C' to cancel the setting of a password and return to the menu.
	// Any password is allowed except 'c' or 'C' for allowing the user to quit out to the menu. 
	// Once entered, the user will be told "Password Accepted." and returned to the menu.
	private void setPassword(){
		Scanner passwordReader = new Scanner(System.in);
		String oldPasswordInput;
		//prompts the user to provide the old password and will repeat this sequence until either the
		//user gets their password right or the user quits using "C" or "c"
		while(true)
		{	
			System.out.println();
			System.out.print("Old Passsword ('c' or 'C' for Menu): ");
			oldPasswordInput = passwordReader.nextLine();
			if(oldPasswordInput.equals("C") || oldPasswordInput.equals("c"))
			{
				return;
			}
			else if(!currentUser.getPassword().equals(oldPasswordInput))
			{
				System.out.println("Invalid password, please re-enter.");
			}
			else
			{
				break;
			}
		}
		//takes in an sets new password
		System.out.print("Please enter your new password: ");
		String newPasswordInput = passwordReader.nextLine();
		currentUser.setPassword(oldPasswordInput, newPasswordInput);
		System.out.println("Password accepted.");
	}

}
