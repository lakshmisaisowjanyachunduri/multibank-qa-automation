package com.multibank.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataReader {

    private static JsonNode rootNode;
    private static final String DATA_PATH = "test-data/testdata.json";

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File(DATA_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Could not load testdata.json: " + e.getMessage());
        }
    }

    public static List<String> getNavigationMenuItems() {
        return getStringList("navigation", "expectedMenuItems");
    }

    public static List<String> getTradingCategories() {
        return getStringList("trading", "expectedCategories");
    }

    public static String getAppStoreUrl() {
        return rootNode.path("appLinks").path("appStore").asText();
    }

    public static String getGooglePlayUrl() {
        return rootNode.path("appLinks").path("googlePlay").asText();
    }

    public static List<String> getWhyMultibankComponents() {
        return getStringList("whyMultibank", "expectedComponents");
    }

    private static List<String> getStringList(String section, String key) {
        List<String> items = new ArrayList<>();
        JsonNode node = rootNode.path(section).path(key);
        if (node.isArray()) {
            node.forEach(item -> items.add(item.asText()));
        }
        return items;
    }
}