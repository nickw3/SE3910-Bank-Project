package com.example.SpringReact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.concurrent.ThreadLocalRandom;


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

    @GetMapping("searchAngler")
    public String searchAngler(Model model){

        return "searchAngler";

    }

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
}