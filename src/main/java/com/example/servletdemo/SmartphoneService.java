package com.example.servletdemo; // Цей пакет повинен відповідати пакету сервлета

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SmartphoneService {
    // Шлях до файлу. Зберігаємо в Tomcat temp dir для Lab3.
    // System.getProperty("catalina.base") дає шлях до TOMCAT_HOME,
    // а "temp" - це піддиректорія для тимчасових файлів.
    private static final String FILE_NAME = "smartphones.json";
    private static final Path FILE_PATH = Paths.get(System.getProperty("catalina.base"), "temp", FILE_NAME);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Smartphone> smartphones;

    public SmartphoneService() {
        try {
            // Створюємо директорії, якщо вони не існують
            Files.createDirectories(FILE_PATH.getParent());
        } catch (IOException e) {
            System.err.println("Помилка створення директорії для файлу: " + e.getMessage());
            // Обробка помилки, можливо, вихід з програми або завантаження в пам'ять
        }
        loadSmartphones();
    }

    private void loadSmartphones() {
        if (Files.exists(FILE_PATH)) {
            try (FileReader reader = new FileReader(FILE_PATH.toFile())) {
                Type listType = new TypeToken<ArrayList<Smartphone>>() {}.getType();
                smartphones = gson.fromJson(reader, listType);
                if (smartphones == null) {
                    smartphones = new ArrayList<>();
                }
            } catch (IOException e) {
                System.err.println("Помилка завантаження смартфонів з " + FILE_PATH + ": " + e.getMessage());
                smartphones = new ArrayList<>();
            }
        } else {
            smartphones = new ArrayList<>();
            System.out.println("Файл " + FILE_PATH + " не знайдено. Створено новий порожній список.");
        }
    }

    private void saveSmartphones() {
        try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
            gson.toJson(smartphones, writer);
        } catch (IOException e) {
            System.err.println("Помилка збереження смартфонів у " + FILE_PATH + ": " + e.getMessage());
        }
    }

    public List<Smartphone> getAllSmartphones() {
        return Collections.unmodifiableList(smartphones);
    }

    public Optional<Smartphone> getSmartphoneById(String id) {
        return smartphones.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public void addSmartphone(Smartphone smartphone) {
        // Перевіряємо, чи смартфон вже має ID. Якщо ні, генеруємо.
        // Це важливо, якщо ви надсилаєте об'єкт з фронтенду без ID для нового створення.
        if (smartphone.getId() == null || smartphone.getId().isEmpty()) {
            smartphone.setId(UUID.randomUUID().toString());
        }
        this.smartphones.add(smartphone);
        saveSmartphones();
    }

    public boolean updateSmartphone(Smartphone updatedSmartphone) {
        for (int i = 0; i < smartphones.size(); i++) {
            if (smartphones.get(i).getId().equals(updatedSmartphone.getId())) {
                smartphones.set(i, updatedSmartphone);
                saveSmartphones();
                return true;
            }
        }
        return false;
    }

    public boolean deleteSmartphone(String id) {
        boolean removed = smartphones.removeIf(s -> s.getId().equals(id));
        if (removed) {
            saveSmartphones();
        }
        return removed;
    }
}