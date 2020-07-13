package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class RegistrationInfo {
        private RegistrationInfo() {
        }

        public static String getUserStatus(String Locale) {
            Faker faker = new Faker(new Locale(Locale));
            String[] userStatusList = new String[]{"active", "blocked"};
            return userStatusList[faker.random().nextInt(0, userStatusList.length - 1)];
        }

        public static String getUserPassword(String Locale) {
            Faker faker = new Faker(new Locale(Locale));
            return faker.internet().password();
        }

        public static String getUserName(String Locale) {
            Faker faker = new Faker(new Locale(Locale));
            return faker.name().username();
        }

        public static UserInfo generateRandomUserInfo(String Locale) {
            return new UserInfo(
                    getUserName(Locale),
                    getUserPassword(Locale),
                    getUserStatus(Locale)
            );
        }

        public static UserInfo generateBlockedUserInfo(String Locale) {
            return new UserInfo(
                    getUserName(Locale),
                    getUserPassword(Locale),
                    "blocked"
            );
        }

        public static UserInfo generateActiveUserInfo(String Locale) {
            return new UserInfo(
                    getUserName(Locale),
                    getUserPassword(Locale),
                    "active"
            );
        }
    }
}
