package algo.coderbyte;




import java.util.*;
        import java.io.*;
        import java.net.*;

public class tes {
    public static void main(String[] args) {
        System.setProperty("http.agent", "Chrome");

        try {
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
            int startIndex = jsonData.indexOf("\"data\":") + 8;
            int endIndex = jsonData.lastIndexOf("\"");
            String data = jsonData.substring(startIndex, endIndex);

            // Split the data into individual items
            String[] items = data.split(", ");

            // Count the items with age >= 50
            int count = 0;
            for (String item : items)
                if (item.startsWith("age-")) {
                    int age = Integer.parseInt(item.substring(4));
                    if (age >= 50) count++;
                }

            // Print the final count
            System.out.println(count);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

