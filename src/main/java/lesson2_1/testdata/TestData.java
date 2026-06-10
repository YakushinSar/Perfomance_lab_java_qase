package lesson2_1.testdata;

import lesson2_1.models.User;

public class TestData {

    // это класс для создания пользователя, например если много пользователей чтобы не создавать их в тестовом методе
    public static final User DEFAULT_USER = User.builder()
            .username("username")
            .firstName("firstName")
            .lastName("lastName")
            .email("email")
            .phone("password")
            .userStatus(0)
            .build();

    public static final User INVALID_USER = User.builder().build();
}
