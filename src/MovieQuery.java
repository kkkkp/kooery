/**
 * This class contains the different API requests used by our program in the API calls to The Movie DB
 *
 */
public class MovieQuery {
	/**
	 * Method to get a list of movies of a specific genre from the API
	 * @param genreId the id of the genre of interest, as defined in the API
	 * @return	string with the API response with the movie information 
	 * @throws Exception if the API call is unsuccessful
	 */
	public static String searchByGenre(int genreId) throws Exception {
		String[] params = new String[] { "language=en-US", "sort_by=created_at.asc",};
		String url = MovieApi.buildUrl("genre/"+genreId+"/movies", params);
		String response = MovieApi.doApiCall(url);	
		return response;
	}
	/**
	 * Method to get a list of movies that are currently playing in theaters from the API
	 * @return string with the API response with the movie information
	 * @throws Exception if the API call is unsuccessful
	 */
	public static String getNowPlaying() throws Exception {
		String[] params = new String[] { "language=en-US",};
		String url = MovieApi.buildUrl("movie/now_playing", params);
		String response = MovieApi.doApiCall(url);	
		return response;
	}
	/**
	 * Method to get a list of movies that are coming soon to theaters from the API
	 * @return string with the API response with the movie information
	 * @throws Exception if the API call is unsuccessful
	 */
	public static String getUpcomingMovies() throws Exception {
		String[] params = new String[] { "language=en-US",};
		String url = MovieApi.buildUrl("movie/upcoming", params);
		String response = MovieApi.doApiCall(url);	
		return response;
	}
	/**
	 * Method to get specific movie information from a movie from the API
	 * @param id the movie id as specified by the API
	 * @return string with the API response with the movie information
	 * @throws Exception if the API call is unsuccessful
	 */
	public static String getMovieDetails(int id) throws Exception {
		String[] params = new String[] {"",};	
		String url = MovieApi.buildUrl("movie/"+id, params);
		String response = MovieApi.doApiCall(url);
		return response;
	}	
	
}
