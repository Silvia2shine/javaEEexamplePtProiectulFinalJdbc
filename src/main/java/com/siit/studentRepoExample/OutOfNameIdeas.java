package com.siit.studentRepoExample;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.siit.studentRepoExample.model.Student;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OutOfNameIdeas {

    public Student getMeAName() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.parser.name/?api_key=96d70575314d980827e0e034e517fb2d&endpoint=generate&country_code=RO"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                Gson gson = new Gson();
                JsonObject json = gson.fromJson(responseBody, JsonObject.class);
                JsonArray data = json.getAsJsonArray("data");
                JsonObject userData = data.get(0).getAsJsonObject();
                JsonObject name = userData.getAsJsonObject("name");

                String firstName = name.getAsJsonObject("firstname").get("name").getAsString();
                String lastName = name.getAsJsonObject("lastname").get("name").getAsString();

                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@scoala-informala.ro";
                return new Student(firstName + " " + lastName, email);
            }
        } catch (IOException | InterruptedException ignored) {
            //todo do some real handling, maybe logging and retrying
        }
        throw new RuntimeException("can't get data");
    }
}