package com.company.Laba6;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        String password = "**********";
        String user = "postgres";
        String url = "jdbc:postgresql://localhost:5432/Laba6?user=" + user + "&password=" + password;
        Connection conn = DriverManager.getConnection(url);
        //log(conn);
        //addZapis(conn);
        //addPreZapis(conn);
        //upd(conn);
        delete(conn);
    }
    public static void addZapis(Connection conn) throws SQLException {
        Scanner scn = new Scanner(System.in);
        System.out.println("enter client code");
        String code = scn.nextLine();
        System.out.println("enter name of company");
        String name = scn.nextLine();
        System.out.println("enter country");
        String country = scn.nextLine();
        System.out.println("enter city");
        String city = scn.nextLine();
        System.out.println("enter addres");
        String addres = scn.nextLine();
        System.out.println("enter phone");
        String number = scn.nextLine();
        System.out.println("enter email");
        String email = scn.nextLine();


        String sql = "INSERT INTO Laba6(user_id, company_name, country_name, city_name, addres, phone_number, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setInt(1, Integer.parseInt(code));
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, country);
        preparedStatement.setString(4, city);
        preparedStatement.setString(5, addres);
        preparedStatement.setString(6, number);
        preparedStatement.setString(7, email);
        preparedStatement.executeUpdate();
    }

    public static void addPreZapis(Connection conn) throws SQLException {
        String sql = "INSERT INTO Laba6(user_id, company_name, country_name, city_name, addres, phone_number, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setInt(1, 112);
        preparedStatement.setString(2, "ООО Шуба");
        preparedStatement.setString(3, "Россия");
        preparedStatement.setString(4, "Чебоксары");
        preparedStatement.setString(5, "ул. Петрова, д.4 ");
        preparedStatement.setString(6, "237-89-08 ");
        preparedStatement.setString(7, "mex@mail.ru");
        preparedStatement.executeUpdate();
    }

    public static void log(Connection conn) throws SQLException {
        String sqlQuery = "SELECT * from Laba6";
        PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            System.out.println(rs.getString("company_name") + "\t "
                    + rs.getString("country_name") + "\t "
                    + rs.getString("city_name") + "\t "
                    + rs.getString("addres") + "\t "
                    + rs.getString("phone_number") + "\t "
                    + rs.getString("email") + "\t ");
        }

    }

    public static void upd(Connection conn) throws SQLException {
        String sqlQuery =
                "UPDATE Laba6 SET company_name = 'Институт ядерных технологий' WHERE company_name = 'НИИ Атоммашстрой';" +
                "UPDATE Laba6 SET phone_number = '511-11-11' WHERE phone_number = '288-99-22';";
        PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
        pstmt.executeUpdate();
    }

    public static void delete(Connection conn) throws SQLException {
        String sqlQuery =
                "DELETE FROM Laba6 WHERE user_id = 5;";
        PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
        pstmt.executeUpdate();
    }
}

//UPDATE test SET username = 'shkila' WHERE username = 'vasek';

//INSERT INTO Laba6 VALUES ('1', 'ОАО Богатейка', 'Россия', 'Казань', 'ул. Серова, д.2', '567-89-08', 'rich@mail.ru')

//INSERT INTO Laba6 VALUES (
//	'2',
//	'Попов В.В.',
//	'Россия',
//	'Казань',
//	'ул. Фрунзе, д. 3, кв. 10',
//	'544-67-98',
//	null
//);

//INSERT INTO Laba6 VALUES (
//	'3',
//	'Иванова Л.В.',
//	'Россия',
//	'Москва',
//	'ул. Строителей, д.2, кв. 10',
//	'276-78-96',
//	null
//);

//INSERT INTO Laba6 VALUES (
//	'4',
//	'НИИ Атоммашстрой',
//	'Россия',
//	'Казань',
//	'ул. Пушкина, д.6',
//	'288-99-22',
//	'atom@yandex.ru'
//);

//INSERT INTO Laba6 VALUES (
//	'5',
//	'Левачев Н.Н',
//	'Россия',
//	'Воронеж',
//	'ул. Речная, д.2, кв. 23',
//	'45-09-89',
//	'levnn@inbox.ru'
//);

//INSERT INTO Laba6 VALUES (
//	'6',
//	'Smirnoff I.P.',
//	'UK',
//	'Londin',
//	'35 King George',
//	'(171) 555-0297',
//	'Smirnoff@yahoo.com'
//);