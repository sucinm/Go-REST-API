package starter.utils;

public class Payload {
    public String randomBodyRequestPostUser() {
        String jsonString;
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();
        int nameLength = 7;
        int counter = 0;
        while(counter < nameLength){
            stringBuilder.append(characters.charAt((int) (Math.random() * nameLength)));
            counter++;
        }

        jsonString = "{";
        jsonString += "\"name\": \"" + stringBuilder.toString() + "\",";
        jsonString += "\"gender\": \"male\",";
        jsonString += "\"email\": \"" + stringBuilder.toString() + "@gmail.com\",";
        jsonString += "\"status\": \"inactive\"";
        jsonString += "}";

        return jsonString;
    }

    public String customBodyRequestPostUser(String name, String gender, String email, String status) {
        String jsonString;
        jsonString = "{";
        jsonString += "\"name\": \"" + name + "\",";
        jsonString += "\"gender\": \"" + gender + "\",";
        jsonString += "\"email\": \"" + email + "\",";
        jsonString += "\"status\": \"" + status + "\"";
        jsonString += "}";

        return jsonString;
    }
}
