package com.example.SpringReact.controller;

import com.example.SpringReact.domain.BankUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.*;

@Controller
public class LoginController {

    Statement statement;

    public LoginController() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Loaded");

        Connection connection = DriverManager.getConnection("" +
                "jdbc:mysql://localhost/bank", "root", "root");

        System.out.println("Database connected");
        statement = connection.createStatement();


    }

    @CrossOrigin
    @PutMapping("/userLogin")
    public ResponseEntity<?> login(@RequestBody BankUser user) throws SQLException {

        String username = "";
        String password = "";

        username = user.getUsername();
        password = user.getPassword();

        System.out.println(username);
        System.out.println(password);

        if(checkLogin(username, password).getUsername() == null){
            return new ResponseEntity<>(checkLogin(username, password), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(checkLogin(username, password), HttpStatus.OK);
    }

    public BankUser checkLogin (String username, String password) throws SQLException {

        ResultSet user = statement.executeQuery("SELECT * FROM bank.users WHERE username = '" + username +
                                                            "' && password = '" + password + "'");
        BankUser bankUser = new BankUser();
        if(user.next()){
            bankUser.setUsername(user.getString("username"));
            bankUser.setName(user.getString("name"));
            bankUser.setTotal_balance(user.getDouble("total_balance"));
            bankUser.setSavings_goal(user.getDouble("savings_goal"));
        }

        return bankUser;
    }

    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id) throws SQLException {


        if(getUserHelper(id).getUsername().isEmpty()){
            return new ResponseEntity<>(getUserHelper(id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(getUserHelper(id), HttpStatus.OK);
    }

    public BankUser getUserHelper (String username) throws SQLException {

        ResultSet user = statement.executeQuery("SELECT * FROM bank.users WHERE username = '" + username + "'");
        BankUser bankUser = new BankUser();
        if(user.next()){
            bankUser.setUsername(user.getString("username"));
            bankUser.setName(user.getString("name"));
            bankUser.setTotal_balance(user.getDouble("total_balance"));
            bankUser.setSavings_goal(user.getDouble("savings_goal"));
        }

        return bankUser;
    }

    //set balance
    @CrossOrigin
    @RequestMapping(value = "/userInitialBalance/", method = RequestMethod.GET)
    public ResponseEntity<?> setInitialBalance(@RequestBody BankUser user) throws SQLException{
        String username = "";
        username = user.getUsername();
        double amount = user.getTotal_balance();

        if(setInitialBalanceHelper(username,amount) == null){
            return new ResponseEntity<>(setInitialBalanceHelper(username,amount), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(setInitialBalanceHelper(username,amount), HttpStatus.OK);
    }

    public BankUser setInitialBalanceHelper(String username, Double amount) throws SQLException {
        int user = statement.executeUpdate("UPDATE bank.users SET total_balance = " + amount + " WHERE username = '" + username + "'");
        ResultSet updatedUser = statement.executeQuery("SELECT * FROM bank.users WHERE username = '" + username + "'");
        BankUser bankUser = new BankUser();

        while (updatedUser.next()) {
            String name = updatedUser.getString("name");
            String password = updatedUser.getString("password");
            double total_balance = Double.parseDouble(updatedUser.getString("total_balance"));
            double savings_goal = Double.parseDouble(updatedUser.getString("savings_goal"));
            bankUser.setUsername(username);
            bankUser.setName(name);
            bankUser.setPassword(password);
            bankUser.setTotal_balance(total_balance);
            bankUser.setSavings_goal(savings_goal);
        }

        return bankUser;
    }

    //set balance
    @CrossOrigin
    @RequestMapping(value = "/setMonthlyBalance/{id}/{amount}", method = RequestMethod.GET)
    public ResponseEntity<?> setMonthlyBalance (@PathVariable("id") String id,@PathVariable("amount") double amount) throws
            SQLException {
        BankUser user = setMontlyBalanceHelper(id, amount);
        if (user.getUsername() == null) {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public BankUser setMontlyBalanceHelper (String username, Double amount) throws SQLException {
        int user = statement.executeUpdate("UPDATE bank.users SET savings_goal = " + amount + " WHERE username = '" + username + "'");
        ResultSet updatedUser = statement.executeQuery("SELECT * FROM bank.users WHERE username = '" + username + "'");
        BankUser bankUser = new BankUser();

        while (updatedUser.next()) {
            String name = updatedUser.getString("name");
            String password = updatedUser.getString("password");
            double total_balance = Double.parseDouble(updatedUser.getString("total_balance"));
            double savings_goal = Double.parseDouble(updatedUser.getString("savings_goal"));
            bankUser.setUsername(username);
            bankUser.setName(name);
            bankUser.setPassword(password);
            bankUser.setTotal_balance(total_balance);
            bankUser.setSavings_goal(savings_goal);
        }

        return bankUser;
    }
}