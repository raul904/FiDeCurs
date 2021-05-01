/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act8;

/**
 *
 * @author Raul
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {
	
        static Scanner scan = new Scanner (System.in);
        
        static  String USER_AGENT; // "Mozilla/5.0";
        static String url;
        static String encapsulado;//gzip
        static String cache;//max-age=31536000
        static String type;//application/javascript
        static String idioma; //ca-es
        
	public static void main(String[] args) throws Exception {
                
                int seleccio;
		HttpURLConnectionExample http = new HttpURLConnectionExample();
                
                System.out.println("Digues la url");
                url = scan.nextLine();
                System.out.println("User Agent:");
                USER_AGENT =scan.nextLine();
                System.out.println("Content-Encoding: ");
                encapsulado = scan.nextLine();
                System.out.println("Cache-Control:");
                cache = scan.nextLine();
                System.out.println("Content-Type:");
                type = scan.nextLine();
                System.out.println("Accept-Language:");
                idioma = scan.nextLine();
                
                
                System.out.println("Selecciona el metode: ");
                  System.out.println("1-Get");
                  System.out.println("2-Post");
                     seleccio = scan.nextInt();
                  
                     if(seleccio==1){
                        System.out.println("Testing 1 - Send Http GET request");
                             http.sendGet(url);
                  }else if(seleccio==2){
                        System.out.println("\nTesting 2 - Send Http POST request");
                            http.sendPost(url);
                  }

	}

	// HTTP GET request
	private void sendGet(String url) throws Exception {
            
                //http://www.insbaixcamp.org
     
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Content-Encoding",encapsulado);
                con.setRequestProperty("Cache-Control",cache);
                con.setRequestProperty("Content-Type",type);
                con.setRequestProperty("Accept-Language", idioma);
           

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	// HTTP POST request
	private void sendPost(String url) throws Exception {
                //http://www.insbaixcamp.cat/moodle/

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Content-Encoding",encapsulado);
                con.setRequestProperty("Cache-Control",cache);
                con.setRequestProperty("Content-Type",type);
                con.setRequestProperty("Accept-Language", idioma);
                                
		
		//Query string
		String urlParameters = "categoryid=7";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}

}
