/**
 * This method performs an API call for the Watson Conversation service
 * It extracts relevant key words to ensure the dialogue flow proceeds smoothly
 */

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class MovieCategory {
	
	
	/**
	 * The instance variable userInput refers to whatever is typed out by the user
	 * Watson will recognize and extract key words from the phrase or sentence entered
	 */
	private String userInput;
	
	public MovieCategory(){

	}
	
	/**
	 * This method stores the user input into a variable
	 */
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	
	/**
	 * This method identifies the key words from a phrase
	 * @ return the category that matches the user input
	 */
	public String getSearchCategory() {
//		String userInput = ws.getQuery();
		ConversationService service = new ConversationService("2016-09-20");
		service.setUsernameAndPassword("43415b83-03f5-4146-b771-a5beb873f1b9", "ZO6Jg55UXFyq");
		
		MessageRequest newMessage = new MessageRequest.Builder()
				  .inputText(userInput)
				  // Replace with the context obtained from the initial request
				  //.context(...)
				  .build();
		
		String workspaceId = "8cd63865-1b04-452a-a8e9-779d1c413be8";

		MessageResponse response = service
		  .message(workspaceId, newMessage)
		  .execute();
		
		String category = response.getEntities().get(0).getValue();
		return category;
	}

}
