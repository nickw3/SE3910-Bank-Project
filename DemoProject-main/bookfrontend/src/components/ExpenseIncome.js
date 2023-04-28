import React from 'react';
import {Card} from 'react-bootstrap';
import { Link } from 'react-router-dom';
 
function Expense(props) {

  var {username, expense_id, planned, amount, income_or_expense, information, due_date} = props.expense;
  if(income_or_expense === 0){
    income_or_expense = 'expense';
  }
  if(income_or_expense === 1){
    income_or_expense = 'income';
  }
  if(planned === 0){
    planned = 'unplanned';
  }
  if(planned === 1){
    planned = 'planned';
  }
  return (
    <tr>
        <td>{username}</td>
        <td>{expense_id}</td>
        <td>{planned}</td>
        <td>{amount}</td>
        <td>{income_or_expense}</td>
        <td>{information}</td>
        <td>{due_date}</td>
      </tr>
  );
}

export default Expense;
