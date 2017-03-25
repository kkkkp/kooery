import static org.junit.Assert.*;

import org.junit.Test;

public class MovieQueryTest {

	@Test
	public void testSearchByGenre() {
		MovieQuery mq = new MovieQuery();
		try {
			String response = mq.searchByGenre(28);
			boolean isStartingWith = response.startsWith("{\"id\":");
			assertEquals("Should equals", isStartingWith, true);
		}
		catch(Exception e) {
			assertEquals("Shouldn't be here", true, false);
		}
		
	}

}
