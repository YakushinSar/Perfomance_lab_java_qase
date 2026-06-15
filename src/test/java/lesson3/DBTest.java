package lesson3;

import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBTest {

    @Test
    public void checkCRUD() throws SQLException {
        DBConnection connection = new DBConnection();
        connection.connect();

        // делаем запрос на изменение данных
        connection.insert("INSERT INTO bank_cards (balance, card_number, created_at, person_id) VALUES (3000, '1234123412341115', '2026-06-15 14:54:23+04', '57')");
        // делаем запрос на удаление данных
        connection.delete("DELETE FROM bank_cards WHERE card_number = '1234123412341114' ");

        // делаем запрос select на получение данных и сохраняем его в result в виде объекта
        ResultSet result = connection.select("SELECT * FROM bank_cards");

        System.out.println(result);
        //  вывод результата, next() ходит как курсов по всех
        while (result.next()) {
            System.out.print(result.getInt("id") + " ");
            System.out.print(result.getInt("balance") + " ");
            System.out.print(result.getString("card_number") + " ");
            System.out.print(result.getString("created_at") + " ");
            System.out.print(result.getInt("person_id") + " ");
            System.out.println();
        }
        connection.delete("DELETE FROM bank_cards WHERE card_number = '1234123412341114' ");
        connection.close();
    }

    @Test
    public void checkCRUD2() throws SQLException {
        DBConnection connection = new DBConnection();
        connection.connect();

        // делаем запрос на изменение данных
//        connection.insert("INSERT INTO bank_cards (balance, card_number, created_at, person_id) VALUES (3000, '1234123412341115', '2026-06-15 14:54:23+04', '57')");
        connection.insertPrepared(1000, "1234123412341114'", Timestamp.valueOf("2026-06-15 14:54:23"), 57);

        // делаем запрос на удаление данных
        connection.delete("DELETE FROM bank_cards WHERE card_number = '1234123412341114' ");

        // делаем запрос select на получение данных и сохраняем его в result в виде объекта
        ResultSet result = connection.select("SELECT * FROM bank_cards");

        System.out.println(result);
        //  вывод результата, next() ходит как курсов по всех
        while (result.next()) {
            System.out.print(result.getInt("id") + " ");
            System.out.print(result.getInt("balance") + " ");
            System.out.print(result.getString("card_number") + " ");
            System.out.print(result.getString("created_at") + " ");
            System.out.print(result.getInt("person_id") + " ");
            System.out.println();
        }
        connection.delete("DELETE FROM bank_cards WHERE card_number = '1234123412341114' ");
        connection.close();
    }
}
