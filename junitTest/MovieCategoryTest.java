import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class performs the JUnit testing for the Movie Category class
 */
public class MovieCategoryTest {
	private MovieCategory mc;
	public MovieCategoryTest() {
		mc = new MovieCategory();
	}

	@Test
	
	/**
	 * If the user indicates an intention to watch an action film, Watson should return the word "action"
	 */
	public void testCategoryResponseForAction() {
		mc.setUserInput("I want to watch an action movie");
		//have an assertion to compare expected value to actual value.
		assertEquals("The category should be action", "action", mc.getSearchCategory());
	}
	
	/**
	 * If the user indicates an intention to watch a romance film, Watson should return the word "romance"
	 */
	@Test
	public void testCategoryResponseForRomance() {
		mc.setUserInput("I want to watch an romance movie");
		assertEquals("The category should be romance", "romance", mc.getSearchCategory());
	}
	
	/**
	 * If the user indicates an intention to watch a documentary film, Watson should return the word "documentary"
	 */
	
	@Test
	public void testCategoryResponseForDocumentary() {
		mc.setUserInput("I want to watch an documentary movie");
		assertEquals("The category should be documentary", "documentary", mc.getSearchCategory());
	}
	
	
	/**
	 * If the user types an invalid input, an exception should be thrown
	 */
	@Test
	public void testCategoryResponseForInvalidOutput() {
		mc.setUserInput("Test for invalid input!!!");
		boolean thrown = false;
		try {
			mc.getSearchCategory();
		}
		catch (Exception e) {
			thrown = true;
		}
		assertEquals("The method should throw some exception", true, thrown);
	}
	
	/**
	 * If the user indicates an intention to watch an animated film, Watson should return the word "animation"
	 */
	@Test
	public void testCategoryResponseforAnimation(){
		mc.setUserInput("I want to watch an animation movie");
		assertEquals("The category should be animation", "animation", mc.getSearchCategory());
	}
	
	/**
	 * If the user indicates an intention to watch an adventurous film, Watson should return the word "adventure"
	 */
	
	@Test
	public void testCategoryResponseforAdventure(){
		mc.setUserInput("I want to watch an adventurous movie");
		assertEquals("The category should be adventure", "adventure", mc.getSearchCategory());
	}
	
	/**
	 * If the user indicates an intention to watch a science fiction film, Watson should return the word "science fiction"
	 */
	
	@Test
	public void testCategoryResponseforScienceFiction(){
		mc.setUserInput("I want to watch a sci fi movie");
		assertEquals("The category should be science fiction", "science fiction", mc.getSearchCategory());
	}
	
	/**
	 * When the user expresses an intent of learning about the revenue of the film, Watson should return "revenue"
	 */
	@Test
	public void testCategoryResponseforRevenue(){
		mc.setUserInput("How much did the movie earn?");
		assertEquals("The category should be revenue", "revenue", mc.getSearchCategory());
	}
	
	/**
	 * When the user expresses an intent in getting the overview of the film, Watson should return "overview"
	 */
	@Test
	public void testCategoryResponseforOverview(){
		mc.setUserInput("Give me a summary of the movie");
		assertEquals("The category should be overview", "overview", mc.getSearchCategory());
	}
	
	/**
	 * When the user expresses an intent in getting the rating of the film, Watson should return "vote_average"
	 */
	@Test
	public void testCategoryResponseforVoteAverage(){
		mc.setUserInput("What rating did this movie recieve?");
		assertEquals("The category should be vote_average", "vote_average", mc.getSearchCategory());
	}
	
	/**
	 * When the user expresses an intent in getting the genre of the film, Watson should return "genre"
	 */
	@Test
	public void testCategoryResponseforGenre(){
		mc.setUserInput("I want to narrow my search by genre");
		assertEquals("The category should be genre", "genre", mc.getSearchCategory());
	}
	
	/**
	 * Watson should recognize affirmatives and negatives in user responses
	 */
	@Test
	public void testCategoryResponseforDecision(){
		mc.setUserInput("I'd rather not");
		assertEquals("The output should be no", "no", mc.getSearchCategory());
	}
	
	
}
