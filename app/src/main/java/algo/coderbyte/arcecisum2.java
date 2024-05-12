package algo.coderbyte;





import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class arcecisum2 {

    public static void main(String[] args) {
       // List<String> input = new ArrayList<>(List.of(new String[]{"username", "EQUALS", "vinayk"})); //1

        //List<String> input = new ArrayList<>(List.of(new String[]{"username", "EQUALS", "Tom"}));

         //List<String> input = new ArrayList<>(List.of(new String[]{"address.city", "EQUALS", "Kolkata"}));

        //List<String> input = new ArrayList<>(List.of(new String[]{"address.city", "IN", "Mumbai,Kolkata"}));

        //List<String> input = new ArrayList<>(List.of(new String[]{"company.name", "IN", "tech infar world"}));
        List<String> input = new ArrayList<>(List.of(new String[]{"address.geo.lat", "IN", "-67.3159,29.4572"}));

        //List<String> input = new ArrayList<>(List.of(new String[]{"address.city", "IN", "Mumbai,Kolkata"}));
        //Input: ["username", "EQUALS", "vinayk"]

        List<Integer> output = searchContacts(input,3);

        System.out.println("Matching Contact IDs: " + output);
    }

    private static List<Integer> searchContacts(List<String> criteria, int size) {
        if (size != 3)
            throw new IllegalArgumentException("Invalid input criteria format. Expected [Object Field Name, Operation Type, Value]");

        String fieldName = criteria.get(0);
        String operation = criteria.get(1);
        String value = criteria.get(2);

        List<Integer> matchingIds = new ArrayList<>();

        try {
            // Get the JSON data from the API
            URL url = new URL("https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            Gson gson = new Gson();
            JsonElement element = gson.fromJson(reader, JsonElement.class);

             //Check if the parsed element is an array
//            if (element.isJsonArray()) {
//                JsonArray contacts = element.getAsJsonArray();
//                for (JsonElement contactElement : contacts) {
//                    JsonObject contact = contactElement.getAsJsonObject();
//                    if (isContactMatch(contact, fieldName, operation, value))
//                        matchingIds.add(contact.get("id").getAsInt());
//                }
//            }
            matchingIds = findMatchingIds(element,fieldName,operation,value);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (matchingIds.isEmpty()) matchingIds.add(-1); // Add -1 if no matching contacts found

        return matchingIds;
    }

    private static List<Integer> findMatchingIds(JsonElement element, String fieldName, String operation, String value) {
        List<Integer> matchingIds = new ArrayList<>();

        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (JsonElement arrayElement : array) {
                JsonObject jsonObject = arrayElement.getAsJsonObject();

                if (isContactMatch(jsonObject, fieldName, operation, value))
                    matchingIds.add(jsonObject.get("id").getAsInt());
//                        matchingIds.add(contact.get("id").getAsInt());
               // if (isMatch(jsonObject, fieldName, operation, value)) matchingIds.add(jsonObject.get("id").getAsInt());
            }
        }

        return matchingIds;
    }




    private static boolean isContactMatch1(JsonObject contact, String fieldName, String operation, String value) {
        JsonElement fieldValue = getField(contact, fieldName);

        if (fieldValue == null) return false;

        switch (operation) {
            case "EQUALS":
                return fieldValue.isJsonPrimitive() && fieldValue.getAsString().equals(value);
            case "IN":
                if (fieldValue.isJsonPrimitive()) {
                    String[] values = value.split(",");
                    for (String v : values) if (fieldValue.getAsString().equals(v.trim())) return true;
                }
                return false;
            default:
                return false;
        }
    }

    private static boolean isContactMatch(JsonObject contact, String fieldName, String operation, String value) {
        JsonElement fieldValue = getField(contact, fieldName);

        if (fieldValue == null) return false;

        switch (operation) {
            case "EQUALS":
                return fieldValue.isJsonPrimitive() && fieldValue.getAsString().equals(value);
            case "IN":
                return fieldValue.isJsonPrimitive() && isInValues(fieldValue.getAsString(), value);
            default:
                return false;
        }
    }

    private static boolean isInValues(String fieldValue, String value) {
        String[] values = value.split(",");
        return Arrays.stream(values)
                .map(String::trim)
                .anyMatch(v -> fieldValue.equals(v));
    }

//    private static JsonElement getField(JsonObject contact, String fieldName) {
//        return contact.get(fieldName);
//    }


    private static JsonElement getField1(JsonObject contact, String fieldName) {
        String[] fieldNames = fieldName.split("\\.");
        JsonElement field = contact;

        for (String name : fieldNames) {
            if (field == null || !field.isJsonObject()) return null;
            field = field.getAsJsonObject().get(name);
        }

        return field;
    }


    private static JsonElement getField(JsonObject contact, String fieldName) {

            String[] fieldNames = fieldName.split("\\.");
            JsonElement field = contact;

            for (String name : fieldNames) {
                if (field == null || !field.isJsonObject()) return null;
                field = ((JsonObject) field).get(name);
            }

            return field;



//        long count = Arrays.stream(data.split(","))
//                .map(String::trim)
//                .filter(item -> item.startsWith("age="))
//                .mapToInt(item -> Integer.parseInt(item.substring(4)))
//                .filter(age -> age >= minAge)
//                .count();


    }






}

