package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class OrderInfo {
        private OrderInfo() {
        }

        private static String getFullName(Faker faker) {
            String fullName = faker.name().fullName();
            while (fullName.contains("ё") || fullName.contains("Ё")) {
                fullName = faker.name().fullName();
            }
            return fullName;
        }

        private static String getUserPassord(Faker faker) {
            String password = "";
            return password;
        }

        private static String getUserName(Faker faker) {
            String userName = "";
            return userName;
        }

        public static LocalDateTime generateOrderDate() {
            return LocalDateTime.now().plusDays(3L + (long) (Math.random() * (360L - 7L)));
        }
    }
}
