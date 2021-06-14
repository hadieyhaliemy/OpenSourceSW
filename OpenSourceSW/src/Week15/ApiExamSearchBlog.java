package Week15;

//네이버 검색 API 예제 - blog 검색
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiExamSearchBlog {

 public static void main(String[] args) throws IOException, ParseException {
	  Scanner sc = new Scanner(System.in);
      System.out.print("검색어를 입력하세요:");
      String movie_name =sc.nextLine();
      String clientId = "EypH_0axa5DfyjnJrEbs"; //애플리케이션 클라이언트 아이디값"
      String clientSecret = "ktVnkfgXox";
//	 String clientId = "sUeEuM5WmWO4uZA0bZlC"; //애플리케이션 클라이언트 아이디값"
//     String clientSecret = "Kk51cSLi3B"; //애플리케이션 클라이언트 시크릿값"

     String text = URLEncoder.encode(movie_name,"UTF-8");
     String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;

     URL url = new URL(apiURL);
     HttpURLConnection con = (HttpURLConnection)url.openConnection();
     con.setRequestMethod("GET");
     con.setRequestProperty("X-Naver-Client-Id", clientId);
     con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

     int responseCode = con.getResponseCode();

     BufferedReader br;

     if(responseCode == 200) {
         br = new BufferedReader(new InputStreamReader(con.getInputStream()));
     }else{
         br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
     }
     String inputLine;
     StringBuffer response = new StringBuffer();
     while((inputLine = br.readLine()) != null) {
         response.append(inputLine);
     }
     br.close();
 	
 	String text2 = response.toString();
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(text2);
    JSONArray infoArray = (JSONArray) jsonObject.get("items");
    for(int i=0; i<infoArray.size(); i++) {
        System.out.println("==================(item"+i+")=================");
        JSONObject itemobject = (JSONObject) infoArray.get(i);
        System.out.println("title:\t"+itemobject.get("title"));
        System.out.println("subtitle:\t"+itemobject.get("subtitle"));
        System.out.println("director:\t"+itemobject.get("director"));
        System.out.println("actor:\t"+itemobject.get("actor"));
        System.out.println("UserRating:\t"+itemobject.get("userRating")+"\n");
        }
 }
//
// private static String get(String apiUrl, Map<String, String> requestHeaders){
//     HttpURLConnection con = connect(apiUrl);
//     try {
//         con.setRequestMethod("GET");
//         for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
//             con.setRequestProperty(header.getKey(), header.getValue());
//         }
//
//         int responseCode = con.getResponseCode();
//         if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
//             return readBody(con.getInputStream());
//         } else { // 에러 발생
//             return readBody(con.getErrorStream());
//         }
//     } catch (IOException e) {
//         throw new RuntimeException("API 요청과 응답 실패", e);
//     } finally {
//         con.disconnect();
//     }
// }
//
// private static HttpURLConnection connect(String apiUrl){
//     try {
//         URL url = new URL(apiUrl);
//         return (HttpURLConnection)url.openConnection();
//     } catch (MalformedURLException e) {
//         throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
//     } catch (IOException e) {
//         throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
//     }
// }
//
// private static String readBody(InputStream body){
//     InputStreamReader streamReader = new InputStreamReader(body);
//
//     try (BufferedReader lineReader = new BufferedReader(streamReader)) {
//         StringBuilder responseBody = new StringBuilder();
//
//         String line;
//         while ((line = lineReader.readLine()) != null) {
//             responseBody.append(line);
//         }
//
//         return responseBody.toString();
//     } catch (IOException e) {
//         throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
//     }
// }
}