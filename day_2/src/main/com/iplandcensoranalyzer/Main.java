package com.iplandcensoranalyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            CensorService censorService = new CensorService();
            ObjectMapper mapper = new ObjectMapper();

            // Process JSON
            List<Match> matches = mapper.readValue(
                    new File("ipl_matches.json"),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, Match.class)
            );

            List<Match> censoredMatches = new ArrayList<>();
            for (Match match : matches) {
                censoredMatches.add(censorService.censorMatch(match));
            }

            // Write censored JSON
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("censored_matches.json"), censoredMatches);

            // Process CSV
            processCsvFile(censorService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processCsvFile(CensorService censorService) {
        try (BufferedReader reader = new BufferedReader(new FileReader("ipl_matches.csv"));
             PrintWriter writer = new PrintWriter("censored_matches.csv")) {

            // Write header
            String header = reader.readLine();
            writer.println(header);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Match match = new Match();
                match.setMatchId(Integer.parseInt(parts[0]));
                match.setTeam1(parts[1]);
                match.setTeam2(parts[2]);

                Map<String, Integer> scores = new HashMap<>();
                scores.put(parts[1], Integer.parseInt(parts[3]));
                scores.put(parts[2], Integer.parseInt(parts[4]));
                match.setScore(scores);

                match.setWinner(parts[5]);
                match.setPlayerOfMatch(parts[6]);

                Match censored = censorService.censorMatch(match);
                writer.println(String.format("%d,%s,%s,%d,%d,%s,%s",
                        censored.getMatchId(),
                        censored.getTeam1(),
                        censored.getTeam2(),
                        censored.getScore().get(censored.getTeam1()),
                        censored.getScore().get(censored.getTeam2()),
                        censored.getWinner(),
                        censored.getPlayerOfMatch()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}