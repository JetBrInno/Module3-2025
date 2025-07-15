package org.example;

import builderExample.User;
import builderExample.UserBuilder;

public class Main {
    public static void main(String[] args) {
        // МЕТОД
        // URL
        // HEADERS
        // BODY
       // HttpClient httpClient = new HttpClientBuilder();

//        UserBuilder userBuilder = UserBuilder.create();
//        userBuilder.setCity("Moscow");
//        userBuilder.setSurname("Ivanov");
//        userBuilder.setName("Alex");
//        User user = userBuilder.build();
//        System.out.println(user);

        User user = UserBuilder.create().setName("Alex").setCity("Moscow").setSurname("Ivanov").build();
        User user2 = UserBuilder.create().setName("Sam").setCity("Moscow").setSurname("Ivanov").build();
        User user3 = UserBuilder.create().setName("Alex").setCity("Moscow").setSurname("Ivanov").build();
        User user4 = UserBuilder.create().setName("Alex").setCity("Moscow").setSurname("Ivanov").build();
        User user5 = UserBuilder.create().setName("Alex").setCity("Moscow").setSurname("Ivanov").build();
        User user6 = UserBuilder.create().setName("Alex").setCity("Moscow").setSurname("Ivanov").build();
        System.out.println(user);
        System.out.println(user2);
    }
}