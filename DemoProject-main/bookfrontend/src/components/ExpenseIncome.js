import React from 'react';
import {Card} from 'react-bootstrap';
import { Link } from 'react-router-dom';
 
function Expense(props) {

  const {username, expense_id, planned, amount, income_or_expense, information, due_date} = props.expense;
  return (
    <Card>
        <Card.Body>
            <Card.Title> {username} : {expense_id} : </Card.Title>
            <Card.Text> {planned} {amount} {income_or_expense}</Card.Text>
            <Card.Text> {information} {due_date}</Card.Text>
        </Card.Body>
    </Card>
  );
}

export default Expense;
