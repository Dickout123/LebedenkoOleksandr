package com.example.servletdemo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/api/smartphones")
public class SmartphoneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Smartphone> smartphones = Arrays.asList(
                // Використовуємо наявні фото та додаємо нові телефони
                new Smartphone("Galaxy S23", "s23.jpg", "Флагман Samsung з потужною камерою", 999.99, "Samsung"),
                new Smartphone("iPhone 15", "iphone15.jpg", "Новий iPhone з покращеним процесором", 1099.99, "Apple"),
                new Smartphone("Pixel 7", "pixel7.jpg", "Google телефон з чистим Android", 799.99, "Google"),

                // Додаємо нові телефони, декілька з яких використовують існуючі фото
                new Smartphone("Galaxy A54", "s23.jpg", "Середньобюджетний телефон Samsung", 499.99, "Samsung"), // Використовує s23.jpg
                new Smartphone("iPhone 13 mini", "iphone15.jpg", "Компактний iPhone", 699.99, "Apple"), // Використовує iphone15.jpg
                new Smartphone("Pixel 6a", "pixel7.jpg", "Доступний Pixel з чудовою камерою", 399.99, "Google"), // Використовує pixel7.jpg

                // Інші нові телефони (Вам потрібно буде додати відповідні фото в папку assets, або вони не відобразяться)
                new Smartphone("Xiaomi 13 Ultra", "xiaomi13u.jpg", "Фотофлагман з великим сенсором", 1299.99, "Xiaomi"),
                new Smartphone("OnePlus 12", "oneplus12.jpg", "Висока продуктивність та швидка зарядка", 949.99, "OnePlus"),
                new Smartphone("Galaxy Z Flip 5", "zflip5.jpg", "Стильний складаний смартфон", 999.99, "Samsung"),
                new Smartphone("iPhone 14 Pro", "iphone14pro.jpg", "Професійна серія минулого року", 899.99, "Apple"),
                new Smartphone("Sony Xperia 1 V", "xperia1v.jpg", "Кінематографічний досвід у кишені", 1199.99, "Sony"),
                new Smartphone("ASUS ROG Phone 7", "rog7.jpg", "Ігровий смартфон для максимальних розваг", 999.99, "ASUS"),
                new Smartphone("Motorola Edge 40 Pro", "motoedge40.jpg", "Елегантний дизайн та швидка зарядка", 799.99, "Motorola")
        );

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(smartphones));
    }
}