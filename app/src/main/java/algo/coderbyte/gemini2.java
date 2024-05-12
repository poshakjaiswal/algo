package algo.coderbyte;

// public class gemini {}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class gemini2 {

  public static void main(String[] args) throws IOException {
    //final String url = "https://coderbyte.com/api/challenges/json/age-counting";

    final int minAge = 50; // Set minimum age for counting

    try {
      // Perform HTTP request with error handling and JSON parsing
//      HttpResponse<String> response =
//          HttpClient.newHttpClient()
//              .send(
//                  HttpRequest.newBuilder(URI.create(url)).GET().build(),
//                  HttpResponse.BodyHandlers.ofString());

      URL url = new URL("https://coderbyte.com/api/challenges/json/age-counting");
      URLConnection connection = url.openConnection();
      InputStream inputStream = connection.getInputStream();

      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) response.append(line);
      reader.close();

      // Extract the "data" value from the JSON response
      String jsonData = response.toString();

      if (true) {
       // String jsonData = response.body();

        String data = jsonData.substring(jsonData.indexOf("\"data\":\"") + 8, jsonData.length() - 2);

        long count = Arrays.stream(data.split(","))
                .map(String::trim)
                .filter(item -> item.startsWith("age="))
                .mapToInt(item -> Integer.parseInt(item.substring(4)))
                .filter(age -> age >= minAge)
                .count();

//        String[] items = data.split(",");
//
//        int count = 0;
//        Set<String> seen = new HashSet<>();
//        for (String item : items) {
//          String trimmedItem = item.trim();
//          if (trimmedItem.startsWith("age=")) {
//            int age = Integer.parseInt(trimmedItem.substring(4));
//            if (age >= minAge) count++;
//          }
//        }

        // Stream extraction, age parsing, and filtering using streams
//        IntStream count =
//            Stream.of(jsonData.split(", "))
//                .filter(item -> item.startsWith("age-"))
//                .mapToInt(item -> Integer.parseInt(item.substring(4)))
//                .filter(age -> age >= minAge); // Ensure correct stream type

        // Conditionally print count or error message
        System.out.println(count);
      } else System.out.println("Error receiving data: Status code ");
    } catch (IOException e) {
      System.out.println("Error making HTTP request: " + e.getMessage());
    }
  }
}
