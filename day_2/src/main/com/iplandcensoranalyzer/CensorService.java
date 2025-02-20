package com.iplandcensoranalyzer;

import java.util.HashMap;
import java.util.Map;

public class CensorService {
    public Match censorMatch(Match match) {
        Match censoredMatch = new Match();
        censoredMatch.setMatchId(match.getMatchId());
        censoredMatch.setTeam1(censorTeamName(match.getTeam1()));
        censoredMatch.setTeam2(censorTeamName(match.getTeam2()));
        censoredMatch.setWinner(censorTeamName(match.getWinner()));
        censoredMatch.setPlayerOfMatch("REDACTED");

        // Handle score map
        Map<String, Integer> censoredScore = new HashMap<>();
        match.getScore().forEach((team, score) ->
                censoredScore.put(censorTeamName(team), score));
        censoredMatch.setScore(censoredScore);

        return censoredMatch;
    }

    private String censorTeamName(String teamName) {
        if (teamName == null) return null;
        String[] parts = teamName.split(" ");
        if (parts.length > 1) {
            return parts[0] + " ***";
        }
        return teamName;
    }
}