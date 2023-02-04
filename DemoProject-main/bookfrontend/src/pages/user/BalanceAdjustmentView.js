import React, { useEffect, useState} from 'react';
import { Form, Button, Table } from 'react-bootstrap';
import BankUser from '../../components/BankUser';
import ExpenseIncome from '../../components/ExpenseIncome';

function BalanceAdjustmentView(props) {

  const id=props.match.params.id;
  var rows = 0;

  console.log(id);

  const[user, setUser] = useState([]);
  const[expenses, setExpense] = useState([]);

  function renderExpense(expense, index) {
    return (
      <tr key={index}>
        <td>{expense.username}</td>
        <td>{expense.expense_id}</td>
        <td>{expense.planned}</td>
        <td>{expense.ammount}</td>
        <td>{expense.income_or_expense}</td>
        <td>{expense.information}</td>
        <td>{expense.due_date}</td>
      </tr>
    )
  }

  useEffect(()=>{
    fetch("http://localhost:8080/expense/" + id, {method:"GET"})
      .then(res => res.json())
      .then(res=> {setExpense(res);})
    fetch("http://localhost:8080/user/" + id, {method:"GET"})
    .then(res => res.json())
    .then(res=> {setUser(res);})
  },[])
  
  return (
    <div>
      <header class="loginheader">
          <div className="banklogo"/>
      </header>
      <Form>
        <Form.Group controlId="formBasicEmail" className="balanceform" class="accountbalance">
          <Form.Label>Account Balance {user.total_balance}</Form.Label>
        </Form.Group>
      </Form>
      <Table>
        <thead>
          <tr>
            <th>username</th>
            <th>expense_id</th>
            <th>planned</th>
            <th>ammount</th>
            <th>income_or_expense</th>
            <th>information</th>
            <th>due_date</th>
          </tr>
        </thead>
        <tbody>
          {expenses.map(expense => <ExpenseIncome key={expense.expense_id} expense = {expense}/>)}
        </tbody>
      </Table>
    </div>
  );
}

export default BalanceAdjustmentView;
