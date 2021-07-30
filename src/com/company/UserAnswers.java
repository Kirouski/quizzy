package com.company;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserAnswers {

    private User user;
    private Map <String, String> results = new LinkedHashMap<>();

    public UserAnswers(User user, Map<String, String> results) {
        this.user = user;
        this.results = results;
    }

    static void saveAnswers () {

    }
}
