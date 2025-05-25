package com.example.servletdemo; // Цей пакет повинен відповідати пакету сервісу

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

// Анотація змінена, щоб обробляти запити з ID в URL (наприклад, /api/smartphones/123)
@WebServlet("/api/smartphones/*")
public class SmartphoneServlet extends HttpServlet {

    // Створюємо екземпляр сервісу. Помилка "may be 'final'" - це лише попередження,
    // що його можна зробити final, оскільки він не змінюється. Можна ігнорувати.
    private SmartphoneService smartphoneService = new SmartphoneService();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Обробка GET-запитів (отримання списку або одного смартфона)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo(); // Отримуємо частину URL після /api/smartphones

        if (pathInfo == null || pathInfo.equals("/")) {
            // Запит на отримання всіх смартфонів: /api/smartphones
            List<Smartphone> smartphones = smartphoneService.getAllSmartphones(); // Використання методу сервісу
            response.getWriter().write(gson.toJson(smartphones));
        } else {
            // Запит на отримання смартфона за ID: /api/smartphones/{id}
            String id = pathInfo.substring(1); // Видаляємо початковий слеш
            Optional<Smartphone> smartphone = smartphoneService.getSmartphoneById(id); // Використання методу сервісу

            if (smartphone.isPresent()) {
                response.getWriter().write(gson.toJson(smartphone.get()));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
                response.getWriter().write("{\"error\": \"Smartphone not found\"}");
            }
        }
    }

    // Обробка POST-запитів (додавання нового смартфона)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = request.getReader()) {
            Smartphone newSmartphone = gson.fromJson(reader, Smartphone.class);
            smartphoneService.addSmartphone(newSmartphone); // Використання методу сервісу
            response.setStatus(HttpServletResponse.SC_CREATED); // 201 Created
            response.getWriter().write(gson.toJson(newSmartphone)); // Повертаємо доданий об'єкт
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
            response.getWriter().write("{\"error\": \"Invalid request body or data: " + e.getMessage() + "\"}");
            e.printStackTrace(); // Для налагодження
        }
    }

    // Обробка PUT-запитів (оновлення існуючого смартфона)
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Smartphone ID is required for update\"}");
            return;
        }
        String idFromPath = pathInfo.substring(1); // ID з URL

        try (BufferedReader reader = request.getReader()) {
            Smartphone updatedSmartphone = gson.fromJson(reader, Smartphone.class);

            // Перевіряємо, чи ID в шляху відповідає ID в тілі запиту
            if (updatedSmartphone.getId() == null || !updatedSmartphone.getId().equals(idFromPath)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"ID in path and body do not match or body ID is missing\"}");
                return;
            }

            boolean success = smartphoneService.updateSmartphone(updatedSmartphone); // Використання методу сервісу
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK); // 200 OK
                response.getWriter().write(gson.toJson(updatedSmartphone));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
                response.getWriter().write("{\"error\": \"Smartphone with ID " + idFromPath + " not found\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid request body or data: " + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

    // Обробка DELETE-запитів (видалення смартфона)
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Smartphone ID is required for delete\"}");
            return;
        }
        String id = pathInfo.substring(1); // ID з URL

        boolean success = smartphoneService.deleteSmartphone(id); // Використання методу сервісу
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
            response.getWriter().write("{\"error\": \"Smartphone with ID " + id + " not found\"}");
        }
    }
}