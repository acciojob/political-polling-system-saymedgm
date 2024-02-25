package com.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PoliticalPollingSystem {

    private static final String API_ENDPOINT = "https://example.com/api/vote";

    public void castVote(String party) {
        try {
            URL url = new URL(API_ENDPOINT);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Send the voting data to the remote backend API
            String postData = "party=" + party;
            connection.getOutputStream().write(postData.getBytes());

            // Check the API response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Vote successfully recorded for party: " + party);
            } else {
                System.out.println("Failed to record vote. Response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            System.err.println("Error while connecting to the remote backend API: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Political Polling System!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PoliticalPollingSystem pollingSystem = new PoliticalPollingSystem();

        try {
            System.out.println("Please enter your preferred political party:");
            String party = reader.readLine();

            pollingSystem.castVote(party);
        } catch (IOException e) {
            System.err.println("Error while reading user input: " + e.getMessage());
        }
    }
}
