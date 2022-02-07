package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import xtext.sML.SML;

public class CoZeroProxiesApi {

	private static final String ROOT_URL = "http://127.0.0.1:5000/";
	private static final String ROOT_ZEN_SCORE_URL = "api/v1/zenScore";
	
	public String callZenScore(String json, String filename) throws IOException {
		
		String file_name = (filename.split("/")[2]).split(".py")[0];
		
		String strReturn = "";
		
		URL url = new URL (ROOT_URL+ROOT_ZEN_SCORE_URL);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		String jsonInputString = "{\"architecture\" : \"" + json + "\",\"filename\" :\"" + file_name + "\"}";
		OutputStream os = con.getOutputStream();
	    byte[] input = jsonInputString.getBytes("utf-8");
		os.write(input, 0, input.length);			
		os.flush();
		os.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			strReturn = response.toString();
		} else {
			System.out.println("POST request not worked");
			strReturn = "POST request not worked";
		}
		return strReturn;
	}
}
