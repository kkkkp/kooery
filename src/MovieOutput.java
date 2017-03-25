/**
 * This class outlines the chat dialog flow and the responses of the chatbot to different user inputs processed by Watson
 */

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;


public class MovieOutput {

	private String output;
	private HashMap<String, Integer> genres;
	private ArrayList<String> items;
	private HashMap<String, String> responses;
	private String dialogState;
	private HashMap<String, Integer> movies;
	private int movieSelectedId;

	/**
	 * Constructor of the class
	 */
	public MovieOutput () {
		output = "";
		genres = new HashMap<String, Integer>();
		items = new ArrayList<String>();
		responses = new HashMap<String, String>();
		movies = new HashMap<String, Integer>();
		movieSelectedId = 0;
		dialogState = "search_movies";

		genres.put("action", 28);
		genres.put("adventure", 12);
		genres.put("animation", 16);
		genres.put("comedy", 35);
		genres.put("documentary", 99);
		genres.put("drama", 18);
		genres.put("family", 10751);
		genres.put("fantasy", 14);
		genres.put("history", 36);
		genres.put("horror", 27);
		genres.put("music", 10402);
		genres.put("mystery", 9648);
		genres.put("science fiction", 878);
		genres.put("thriller", 53);
		genres.put("tv movie", 10770);
		genres.put("war", 10752);
		genres.put("western", 37);
		genres.put("romance", 10749);

		items.add("tagline");
		items.add("budget");
		items.add("overview");
		items.add("release_date");
		items.add("revenue");
		items.add("runtime");
		items.add("vote_average");

		responses.put("new_search", "How would you like to select your movie recommendation?\n1) upcoming releases\n2) currently playing\n3) genre");
		responses.put("upcoming", "Movies: ");
		responses.put("current", "Movies: ");
		responses.put("genre", "What genre do you prefer?");
		responses.put("select_genre", "Movies: ");
		responses.put("movie_selected", "What detail about this movie can I find for you? (plot, tagline, budget,\n                    release date, revenue, runtime, audience rating)");
		responses.put("movie_info_more_question_no","Would you like to do another movie search?");
		responses.put("end", "Thank you for using Kooery! Goodbye!");
		responses.put("invalid", "Input is not valid, please try again!");
	}

	/**
	 * Getter method for the output (the response after processing the user input and feedback from Watson)
	 * @return the output
	 */
	public String getOutput () {
		return output;
	}

	/**
	 * Getter method for the dialog state
	 * @return the dialog state
	 */
	public String getDialogState () {
		return dialogState;
	}
	/**
	 * Method to set the output from the chatbot based on user input/Watson response and the current dialog state
	 * @param watsonResponse string containing the response from Watson/the GUI based on the user input
	 * @throws JSONException if the parsing of the API response is unsuccessful
	 * @throws Exception if the API call is unsuccessful
	 */
	public void processWatsonResponse (String watsonResponse) throws JSONException, Exception {
		if (dialogState.equals("search_movies")) {
			if (watsonResponse.equals("upcoming") || watsonResponse.equals("current") || watsonResponse.equals("genre")) {
				output = responses.get(watsonResponse);
				if (watsonResponse.equals("genre")) {
					dialogState = "select_genre";
				} else {
					dialogState = "list_movies";
					if (watsonResponse.equals("upcoming")) {
						output = output + "\n" + Printer.movieListToString(MovieQuery.getUpcomingMovies());
						movies = Printer.movieListHM(MovieQuery.getUpcomingMovies());
					}
					if (watsonResponse.equals("current")) {
						output = output + "\n" + Printer.movieListToString(MovieQuery.getNowPlaying());
						movies = Printer.movieListHM(MovieQuery.getNowPlaying());
					}
				}
			} else {
				output = responses.get("invalid");
			}
		} else if (dialogState.equals("select_genre")) {
			if (genres.containsKey(watsonResponse)) {
				dialogState = "list_movies";
				output = responses.get("select_genre");
				output = output + "\n" + Printer.movieListToString(MovieQuery.searchByGenre(genres.get(watsonResponse)));
				movies = Printer.movieListHM(MovieQuery.searchByGenre(genres.get(watsonResponse)));
			} else {
				output = responses.get("invalid");
			}
		} else if (dialogState.equals("list_movies")) {
			if (movies.containsKey(watsonResponse)) {
				dialogState = "movie_info";
				movieSelectedId = movies.get(watsonResponse);
				output = responses.get("movie_selected");
			} else {
				output = responses.get("invalid");
			}
		} else if (dialogState.equals("movie_info")) {
			if (items.contains(watsonResponse)) {
				dialogState = "movie_info_more_question";
				output = Printer.getMovieInfo(MovieQuery.getMovieDetails(movieSelectedId), watsonResponse);
				
				StringBuilder sb = new StringBuilder();
				int len = output.length();
				int charac = 80;
				for (int i = 0; i < len; i = i + charac) {
					String sub;
					if (i + charac > len) {
						sub = output.substring(i, len);
					} else {
						sub = output.substring(i, i + charac);
					}
					sb.append(sub);
					sb.append("\n                    ");
				}
				output = sb.toString();
				output =  output + "\n\n" + "[Kooery] >> Would you like to know anything else about this movie?";
			} else {
				output = responses.get("invalid");
			}
		} else if (dialogState.equals("movie_info_more_question")) {
			if (watsonResponse.equals("yes")) {
				dialogState = "movie_info";
				output = responses.get("movie_selected");
			} else if (watsonResponse.equals("no"))  {
				dialogState = "new_search_question";
				output = responses.get("movie_info_more_question_no");
			} else {
				output = responses.get("invalid");
			}
		} else if (dialogState.equals("new_search_question")) {
			if (watsonResponse.equals("yes")) {
				dialogState = "search_movies";
				output = responses.get("new_search");
			} else if (watsonResponse.equals("no")) {
				dialogState = "end";
				output = responses.get("end");
			} else {
				output = responses.get("invalid");
			}
		}
	}

}
