/**
 * This class parses the information from the API responses for ease of use by other classes
 */

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Printer {

	/**
	 * Method to get the comma-separated titles of the movies in the API response
	 * @param response string with JSON object containing the API response with the movie list information
	 * @return string with comma-separated titles of the movies in the API response
	 * @throws JSONException
	 */
	public static String movieListToString(String response) throws JSONException {
		JSONArray items = new JSONObject(response).getJSONArray("results");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < items.length(); i++) {
		    sb.append("\n " + items.getJSONObject(i).getString("title") + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	/**
	 * Method to return a hashmap with movie titles as keys and ids as values from the API response 
	 * @param response string with JSON object containing the API response with the movie list information
	 * @return hashmap containing titles of the movies in the API response as keys and their respective ids as values
	 * @throws JSONException if the parsing is unsuccessful
	 */
	public static HashMap<String, Integer> movieListHM(String response) throws JSONException {
		HashMap<String, Integer> movies = new HashMap<String, Integer>();
		JSONArray items = new JSONObject(response).getJSONArray("results");
		for (int i = 0; i < items.length(); i++) {
		    movies.put(items.getJSONObject(i).getString("title"), items.getJSONObject(i).getInt("id"));
		}		
		return movies;
	}

	/**
	 * Method to return the information category requested of a movie
	 * @param response string with JSON object containing the API response with the specific movie information
	 * @param item information category of interest of the movie
	 * @return string with information on the item of the specific movie from the API response
	 * @throws JSONException if the parsing is unsuccessful
	 */
	public static String getMovieInfo(String response, String item) throws JSONException {
		JSONObject responseObject = new JSONObject(response);
		StringBuilder sb = new StringBuilder();
		sb.append(item + ":");
		if (item.equals("overview") || item.equals("tagline") || item.equals("release_date")) {
			sb.append(" " + responseObject.getString(item));
		} else {
			sb.append(" " + responseObject.getInt(item));
		}
		
		return sb.toString();
	}

}
