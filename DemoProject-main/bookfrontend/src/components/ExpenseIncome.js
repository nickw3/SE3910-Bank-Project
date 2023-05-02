import React from 'react';
import {Card} from 'react-bootstrap';
import { Link } from 'react-router-dom';
import {Button} from 'react-bootstrap';
 
function Expense(props) {

  const deleteExpense =()=>{
    fetch("http://localhost:8080/deleteExpense/" + expense_id, {method:"GET"})
      .then(res=>{
        console.log(1,res);
        if(res.status === 200){
          window.location.reload();
          return res.json();
        }else{
          return null;
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });
    }
    
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
        <td>{planned}</td>
        <td>{amount}</td>
        <td>{income_or_expense}</td>
        <td>{information}</td>
        <td>{due_date}</td>
        <td><Button type="button" variant="secondary" onClick={deleteExpense}>Delete</Button></td>
      </tr>
  );
}

export default Expense;
