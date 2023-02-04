import React from 'react';
import {Card} from 'react-bootstrap';
import { Link } from 'react-router-dom';
 
function Expense(props) {

  const {username, expense_id, planned, amount, income_or_expense, information, due_date} = props.expense;
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
