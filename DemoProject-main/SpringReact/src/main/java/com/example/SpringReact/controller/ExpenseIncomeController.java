package com.example.SpringReact.controller;

import com.example.SpringReact.domain.ExpenseIncome;
import com.example.SpringReact.domain.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.SpringReact.domain.BankUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


import java.sql.*;

@Controller
public class ExpenseIncomeController {

    Statement statement;

    public ExpenseIncomeController() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Loaded");

        Connection connection = DriverManager.getConnection("" +
                "jdbc:mysql://localhost/bank", "root", "root");

        System.out.println("Database connected");
        statement = connection.createStatement();


    }

    @CrossOrigin
    @GetMapping("/expense")
    public ResponseEntity<?> findAll() throws SQLException {
        return new ResponseEntity<>(findExpenseIncome(), HttpStatus.OK);
    }

    public ArrayList<ExpenseIncome> findExpenseIncome () throws SQLException {
        ArrayList<ExpenseIncome> list = new ArrayList<ExpenseIncome>();

        ResultSet expenseIncomes = statement.executeQuery("SELECT * FROM bank.expenses");

        while(expenseIncomes.next()){
            ExpenseIncome expense = new ExpenseIncome();
            String username = expenseIncomes.getString("username");
            String expense_id = expenseIncomes.getString("expense_id");
            int planned = expenseIncomes.getInt("planned");
            double amount = expenseIncomes.getDouble("amount");
            int income_or_expense = expenseIncomes.getInt("income_or_expense");
            String information = expenseIncomes.getString("information");
            String due_date = expenseIncomes.getString("due_date");
            expense.setUsername(username);
            expense.setExpense_id(expense_id);
            expense.setPlanned(planned);
            expense.setAmount(amount);
            expense.setIncome_or_expense(income_or_expense);
            expense.setInformation(information);
            expense.setDue_date(due_date);
            list.add(expense);
        }

        return list;
    }

    @CrossOrigin
    @GetMapping("/expense/{id}")
    public ResponseEntity<?> findUserExpenses(@PathVariable("id") String id) throws SQLException {
        return new ResponseEntity<>(finderUserExpensesHelper(id), HttpStatus.OK);
    }

    public ArrayList<ExpenseIncome> finderUserExpensesHelper (String id) throws SQLException {
        ArrayList<ExpenseIncome> list = new ArrayList<ExpenseIncome>();

        ResultSet expenseIncomes = statement.executeQuery("SELECT * FROM bank.expenses where username = '" + id + "'");

        while(expenseIncomes.next()){
            ExpenseIncome expense = new ExpenseIncome();
            String username = expenseIncomes.getString("username");
            String expense_id = expenseIncomes.getString("expense_id");
            int planned = expenseIncomes.getInt("planned");
            double amount = expenseIncomes.getDouble("amount");
            int income_or_expense = expenseIncomes.getInt("income_or_expense");
            String information = expenseIncomes.getString("information");
            String due_date = expenseIncomes.getString("due_date");
            expense.setUsername(username);
            expense.setExpense_id(expense_id);
            expense.setPlanned(planned);
            expense.setAmount(amount);
            expense.setIncome_or_expense(income_or_expense);
            expense.setInformation(information);
            expense.setDue_date(due_date);
            list.add(expense);
        }

        return list;
    }

    @CrossOrigin
    @PutMapping("/createExpense")
    public ResponseEntity<?> createUserExpense(@RequestBody ExpenseIncome newExpenseIncome) throws SQLException {
        return new ResponseEntity<>(createUserExpenseHelper(newExpenseIncome), HttpStatus.OK);
    }

    public ExpenseIncome createUserExpenseHelper (ExpenseIncome newExpenseIncome) throws SQLException {
        ArrayList<ExpenseIncome> list = new ArrayList<ExpenseIncome>();

        String username = "";
        String expense_id = "";
        int planned;
        Double amount;
        int income_or_expense;
        String information = "";
        String due_date = "";

        username = newExpenseIncome.getUsername();
        for(int i = 0; i < 5; i++){
            expense_id += "" + ThreadLocalRandom.current().nextInt(0, 10);
        }
        planned = newExpenseIncome.getPlanned();
        amount = newExpenseIncome.getAmount();
        income_or_expense = newExpenseIncome.getIncome_or_expense();
        information = newExpenseIncome.getInformation();
        due_date = newExpenseIncome.getDue_date();

        ExpenseIncome test = new ExpenseIncome();
        test.setUsername(username);
        test.setUsername(expense_id);
        test.setUsername(planned + "");
        test.setUsername(amount + "");
        test.setUsername(income_or_expense + "");
        test.setUsername(information);
        test.setUsername(due_date);

        statement.execute("INSERT INTO bank.expenses VALUES ("
                                    + "'" + username + "', '"  + expense_id + "', "
                                    + "" + planned + ", "  + amount + ", "
                                    + "" + income_or_expense + ", '"  + information + "', "
                                    + "'" + due_date + "')");
        return test;
    }

    @CrossOrigin
    @RequestMapping(value = "/setSavingsGoal/{id}/{amount}", method = RequestMethod.GET)
    public ResponseEntity<?> setSavingsGoal(@PathVariable("id") String id, @PathVariable("amount") double amount) throws SQLException {
        boolean updated = setSavingsGoalHelper(id,amount);
        if(!updated){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean setSavingsGoalHelper(String username, double savingsGoal) throws SQLException {
        String updateSavingsGoal = "UPDATE bank.users SET savings_goal = " + savingsGoal + " WHERE username = '" + username + "'";
        int updated = statement.executeUpdate(updateSavingsGoal);
        return updated > 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* Old Code from previous database project with examples of querying a database

    @GetMapping("SearchAnglerID")
    public String SearchAnglerID(Model model,
                                 @ModelAttribute("angler_id") String anglerId) throws SQLException {

        ResultSet resultSet = statement.executeQuery("select " + "* from final.angler" +
                " where angler_id = '" + anglerId + "'");

        String output = "";
        while(resultSet.next()){
            String angler_id = resultSet.getString("angler_id");
            String name1 = resultSet.getString("name");
            String total_catches = resultSet.getString("total_catches");
            String larges_catch = resultSet.getString("largest_catch");
            output+="<div class='form-row'><div class='col-md-4'><div class='form-group'><label class='small mb-1'> Angler ID: "
                    +angler_id+" </label><br><label class='small mb-1'> Angler Name: "
                    +name1+"</label><label class='small mb-1'><br> Total # of Catches: "
                    +total_catches+"</label><label class='small mb-1'><br> Largest Catch: "
                    +larges_catch+"</label></div></div></div><br>";
        }
        model.addAttribute("data1", output);

        return "anglerSearchResult";

    }

    @GetMapping("SearchAnglerName")
    public String SearchAnglerName(Model model,
                                   @ModelAttribute("name") String name) throws SQLException {

        ResultSet resultSet = statement.executeQuery("select " + "* from final.angler" +
                " where name = '" + name + "'");

        String output = "";
        while(resultSet.next()){
            String angler_id = resultSet.getString("angler_id");
            String name1 = resultSet.getString("name");
            String total_catches = resultSet.getString("total_catches");
            String larges_catch = resultSet.getString("largest_catch");
            output+="<div class='form-row'><div class='col-md-4'><div class='form-group'><label class='small mb-1'> Angler ID: "
                    +angler_id+" </label><br><label class='small mb-1'> Angler Name: "
                    +name1+"</label><label class='small mb-1'><br> Total # of Catches: "
                    +total_catches+"</label><label class='small mb-1'><br> Largest Catch: "
                    +larges_catch+"</label></div></div></div><br>";
        }
        model.addAttribute("data1", output);

        return "anglerSearchResult";

    }

    @GetMapping("SearchAnglerTotalCatch")
    public String SearchAnglerTotalCatch(Model model,
                                         @ModelAttribute("total_catches") String total_catches) throws SQLException {

        ResultSet resultSet = statement.executeQuery("select " + "* from final.angler" +
                " where total_catches = '" + total_catches + "'");

        String output = "";
        while(resultSet.next()){
            String angler_id = resultSet.getString("angler_id");
            String name1 = resultSet.getString("name");
            String total_catches1 = resultSet.getString("total_catches");
            String larges_catch = resultSet.getString("largest_catch");
            output+="<div class='form-row'><div class='col-md-4'><div class='form-group'><label class='small mb-1'> Angler ID: "
                    +angler_id+" </label><br><label class='small mb-1'> Angler Name: "
                    +name1+"</label><label class='small mb-1'><br> Total # of Catches: "
                    +total_catches+"</label><label class='small mb-1'><br> Largest Catch: "
                    +larges_catch+"</label></div></div></div><br>";
        }
        model.addAttribute("data1", output);

        return "anglerSearchResult";

    }

    @GetMapping("SearchAnglerLargestCatch")
    public String SearchAnglerLargestCatch(Model model,
                                           @ModelAttribute("largest_catch") String largest_catch) throws SQLException {

        ResultSet resultSet = statement.executeQuery("select " + "* from final.angler" +
                " where largest_catch = '" + largest_catch + "'");

        String output = "";
        while(resultSet.next()){
            String angler_id = resultSet.getString("angler_id");
            String name1 = resultSet.getString("name");
            String total_catches = resultSet.getString("total_catches");
            String larges_catch = resultSet.getString("largest_catch");
            output+="<div class='form-row'><div class='col-md-4'><div class='form-group'><label class='small mb-1'> Angler ID: "
                    +angler_id+" </label><br><label class='small mb-1'> Angler Name: "
                    +name1+"</label><label class='small mb-1'><br> Total # of Catches: "
                    +total_catches+"</label><label class='small mb-1'><br> Largest Catch: "
                    +larges_catch+"</label></div></div></div><br>";
        }
        model.addAttribute("data1", output);

        return "anglerSearchResult";

    }

    @GetMapping("createAngler")
    public String createAngler(Model model){

        return "createAngler";

    }

    @GetMapping("anglerId")
    public String anglerID(Model model){

        return "anglerId";

    }

    @GetMapping("CreateAngler")
    public String CreateAngler(Model model, @ModelAttribute("name") String name,
                               @ModelAttribute("password") String password,
                               @ModelAttribute("total_catches") String total_catches,
                               @ModelAttribute("largest_catch") String largest_catch) throws SQLException {

        String angler_id = "";

        for(int i = 0; i < 5; i++){
            angler_id += "" + ThreadLocalRandom.current().nextInt(0, 10);
        }

        statement.execute("INSERT INTO final.angler VALUES" + " ('" + angler_id + "' , '" + name + "' , '" + password + "' , '" + total_catches + "' , '" + largest_catch + "')");
        statement.execute("INSERT INTO final.users VALUES" + " ('" + angler_id + "' , '" + password + "', '0')");

        model.addAttribute("anglerID", angler_id + "");

        return "anglerId";

    }

    @GetMapping("adminLogin")
    public String adminLogin(Model model){

        return "adminLogin";

    }

    @GetMapping("noPermission")
    public String noPermission(Model model){

        return "noPermission";

    }

    @GetMapping("Login")
    public String Login(Model model,
                        @ModelAttribute("angler_id") String anglerId,
                        @ModelAttribute("password") String password) throws SQLException {

        System.out.println(anglerId);

        ResultSet resultSet = statement.executeQuery("select " + "* from final.users" +
                " where angler_id = '" + anglerId + "' && password = '" + password + "'");

        String admin = "adminTools";
        String regular = "cannotLogin";
        String manager = "";

        while(resultSet.next()){
            manager = resultSet.getString("manager");
        }

        if(manager.equals("1")) {
            return "adminTools";
        }
        else{
            return "noPermission";
        }
    }

    @GetMapping("adminTools")
    public String adminTools(Model model){

        return "adminTools";

    }

    @GetMapping("UpdateCatch")
    public String UpdateCatch(Model model,
                              @ModelAttribute("catch_id") String catch_id,
                              @ModelAttribute("catch_time") String catch_time,
                              @ModelAttribute("catch_lat") String catch_lat,
                              @ModelAttribute("catch_long") String catch_long,
                              @ModelAttribute("catch_depth") String catch_depth,
                              @ModelAttribute("catch_temp") String catch_temp,
                              @ModelAttribute("angler_id") String angler_id,
                              @ModelAttribute("location_id") String location_id,
                              @ModelAttribute("fish_id") String fish_id) throws SQLException {

        statement.execute("UPDATE final.catch SET catch_time = '"+ catch_time + "', catch_lat = '" + catch_lat
                + "', catch_long = '" + catch_long + "', catch_depth = '" + catch_depth
                + "', catch_temp = '" + catch_temp + "', angler_id = '" + angler_id
                + "', location_id = '" + location_id + "', fish_id = '" + fish_id + "' "
                + "WHERE catch_id = '" + catch_id + "'");
        return "adminTools";
    }

    @GetMapping("DeleteCatch")
    public String DeleteCatch(Model model,
                              @ModelAttribute("catch_id") String catch_id,
                              @ModelAttribute("catch_time") String catch_time,
                              @ModelAttribute("catch_lat") String catch_lat,
                              @ModelAttribute("catch_long") String catch_long,
                              @ModelAttribute("catch_depth") String catch_depth,
                              @ModelAttribute("catch_temp") String catch_temp,
                              @ModelAttribute("angler_id") String angler_id,
                              @ModelAttribute("location_id") String location_id,
                              @ModelAttribute("fish_id") String fish_id) throws SQLException {

        statement.execute("DELETE FROM final.catch WHERE catch_id = '"+ catch_id + "'");
        return "adminTools";
    }

    @GetMapping("InsertCatch")
    public String InsertCatch(Model model,
                              @ModelAttribute("catch_id") String catch_id,
                              @ModelAttribute("catch_time") String catch_time,
                              @ModelAttribute("catch_lat") String catch_lat,
                              @ModelAttribute("catch_long") String catch_long,
                              @ModelAttribute("catch_depth") String catch_depth,
                              @ModelAttribute("catch_temp") String catch_temp,
                              @ModelAttribute("angler_id") String angler_id,
                              @ModelAttribute("location_id") String location_id,
                              @ModelAttribute("fish_id") String fish_id) throws SQLException {

        statement.execute("INSERT INTO final.catch VALUES" + " ('" + catch_id + "' , '" + catch_time + "' , '" + catch_lat + "' , '" + catch_long + "' , '" + catch_depth +
                "' , '" + catch_temp + "' , '" + angler_id + "' , '" + location_id + "' , '" + fish_id + "')");
        return "adminTools";
    }

    @GetMapping("AddAdmin")
    public String AddAdmin(Model model, @ModelAttribute("angler_id") String angler_id) throws SQLException {
        statement.execute("UPDATE final.users SET manager = '1' WHERE angler_id = '" + angler_id + "' ");

        return "adminTools";
    }

    @GetMapping("createCatch")
    public String createCatch(Model model){

        return "createCatch";

    }

    @GetMapping("AddCatch")
    public String AddCatch(Model model,
                           @ModelAttribute("catch_time") String catch_time,
                           @ModelAttribute("catch_lat") String catch_lat,
                           @ModelAttribute("catch_long") String catch_long,
                           @ModelAttribute("catch_depth") String catch_depth,
                           @ModelAttribute("catch_temp") String catch_temp,
                           @ModelAttribute("angler_id") String angler_id,
                           @ModelAttribute("location_id") String location_id,
                           @ModelAttribute("fish_id") String fish_id) throws SQLException {

        String catch_id = "";

        for(int i = 0; i < 5; i++){
            catch_id += "" + ThreadLocalRandom.current().nextInt(0, 10);
        }
        statement.execute("INSERT INTO final.catch VALUES" + " ('" + catch_id + "' , '" + catch_time + "' , '" + catch_lat + "' , '" + catch_long + "' , '" + catch_depth +
                "' , '" + catch_temp + "' , '" + angler_id + "' , '" + location_id + "' , '" + fish_id + "')");

        model.addAttribute("catchID", catch_id + "");

        return "catchId";
    }

    @GetMapping("leaderboard")
    public String leaderboard(Model model) throws SQLException {

        ResultSet largestCatch = statement.executeQuery("SELECT * FROM final.angler ORDER BY  cast(largest_catch as SIGNED) DESC");

        String largest = "";
        while(largestCatch.next()){
            String angler_id = largestCatch.getString("angler_id");
            String name1 = largestCatch.getString("name");
            String total_catches = largestCatch.getString("total_catches");
            String larges_catch = largestCatch.getString("largest_catch");
            largest+="<div class='form-row'><div class='col-md-4'><div class='form-group'><label class='small mb-1'> Angler ID: "
                    +angler_id+" </label><br><label class='small mb-1'> Angler Name: "
                    +name1+"</label><label class='small mb-1'><br> Total # of Catches: "
                    +total_catches+"</label><label class='small mb-1' ><br> <strong>Largest Catch: "
                    +larges_catch+"</strong></label></div></div></div><br>";
        }
        model.addAttribute("largest_fish", largest);

        ResultSet mostCaught = statement.executeQuery("SELECT * FROM final.angler ORDER BY  cast(total_catches as SIGNED) DESC");

        String most = "";
        while(mostCaught.next()){
            String angler_id = mostCaught.getString("angler_id");
            String name1 = mostCaught.getString("name");
            String total_catches = mostCaught.getString("total_catches");
            String larges_catch = mostCaught.getString("largest_catch");
            most+="<div class='form-row'><div class='col-md-4'><div class='form-group'><label class='small mb-1'> Angler ID: "
                    +angler_id+" </label><br><label class='small mb-1'> Angler Name: "
                    +name1+"</label><label class='small mb-1'><br><strong> Total # of Catches: "
                    +total_catches+"</strong></label><label class='small mb-1' ><br> Largest Catch: "
                    +larges_catch+"</label></div></div></div><br>";
        }
        model.addAttribute("most_fish", most);

        return "leaderboard";

    }
    */
}