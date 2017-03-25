/**
 * This class establishes the connection to The Movie DB API
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;


public class MovieApi {
	
	private final static String API_BASE = "https://api.themoviedb.org/3/"; 
	private final static String MY_KEY = "a8ad415c7dceacdc6208bdcdce6ac437";

	/**
	 * Method to create the URL needed to make a GET request from the API
	 * @param resource resource being use from the API
	 * @param params set of parameters that are part of the API call
	 * @return
	 */
	public static String buildUrl(String resource, String[] params) {
		StringBuilder url = new StringBuilder(API_BASE + resource + "?api_key=" + MY_KEY);
		
		for (String param : params) {
			url.append("&");
			url.append(param);
		}
		
		return url.toString();
	}

	/**
	 * Method to send the API call using the URL containing the GET request
	 * @param url string with the URL containing the GET request
	 * @return string with the response from the API
	 * @throws Exception if the API call is unsuccessful
	 */
	public static String doApiCall(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
	}
}
	