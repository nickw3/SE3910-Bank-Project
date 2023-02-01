package com.example.SpringReact.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //  mapping an object state to database column
public class ExpenseIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // use the auto increment of the database
    private String username;
    private String expense_id;
    private String planned;
    private double amount;
    private int income_or_expense;
    private String information;
    private String due_date;



}
