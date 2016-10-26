package by.academy.it.services;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    // класс извлекает информацию из файла config.properties
    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        System.out.println("Зашли в ConfigurationManager");
        return resourceBundle.getString(key);
    }
}