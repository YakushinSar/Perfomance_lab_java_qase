package lesson3;

import java.sql.*;

public class DBConnection {

    private final String URL = "jdbc:postgresql://82.142.167.36:52080/testingpolygon";
    private final String USER = "tp_crud_user";
    private final String PASSWORD = "change_me_now";

    private Connection connection;  // для подсоединения, разрыва к БД
    private Statement statement;    //  для отправления запросов к БД
    private ResultSet resultSet;    // это объект хранит в себе все то что мы получили с помощью запроса Select

    //  метод создания соединения
    public void connect() { // метод для подключения к БД
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);// создание соединения
            statement = connection.createStatement(); // создание возможности написания запросов
            System.out.println("Соединение с БД выполнено");
        } catch (SQLException e) {
            throw new RuntimeException("Соединение не выполнено", e);
        }
    }

    //  метод закрытия соединения
    public void close() {
        try { // закрытие соединений идет именно в таком порядке, то есть обратном от порядка подключения
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.out.println("Соединение с БД закрыто");
        } catch (SQLException e) {
            throw new RuntimeException("Соединение не закрылось", e);
        }
    }

    // метод селект
    public ResultSet select(String query) {
        try {
            return statement.executeQuery(query);// Отправляет SELECT-запрос в БД и сразу возвращает ResultSet

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // метод insert
    public void insert(String query) throws SQLException {
        statement.execute(query);
    }

    // метод delete
    public void delete(String query) throws SQLException {
        statement.execute(query);
    }

    // !!! запросы лучше делать с PreparedStatement, метод insert с параметрами, этот метод в себе создает запрос в БД, PreparedStatement обрезает ненужные символы и проводит данные к нужному формату
    public void insertPrepared(int balance, String cardNumber, Timestamp createdAt, int personId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO bank_cards (balance, card_number, created_at, person_id) VALUES (?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, balance);
        preparedStatement.setString(2, cardNumber);
        preparedStatement.setTimestamp(3, createdAt);
        preparedStatement.setInt(4, personId);
        preparedStatement.executeUpdate(); // выполнение запроса
        preparedStatement.close(); // закрытие соединения
    }
}