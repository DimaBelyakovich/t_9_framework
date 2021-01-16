package com.epam.service;

import java.util.List;

public class FindContains {
    public static boolean findContains(List<String> findsList, String searchedString){
        return findsList.stream().anyMatch(item -> item.contains(searchedString));
    }
}
