import React, { useEffect, useState } from 'react';
import { Form, Button, Table } from 'react-bootstrap';
import BankUser from '../../components/BankUser';
import ExpenseIncome from '../../components/ExpenseIncome';
import Header from '../../components/Header';

function BalanceAdjustmentView(props) {

  const id=props.location.state;
  console.log(props.id);
  const[user, setUser] = useState([]);
  const[expenses, setExpense] = useState([]);
  const[newExpense, setNewExpense] = useState({
    username:id,
    expense_id: '',
    planned:'',
    amount: '',
    income_or_expense: '',
    information: '',
    due_date: ''
  });


  useEffect(()=>{
    fetch("http://localhost:8080/expense/" + id, {method:"GET"})
      .then(res => res.json())
      .then(res=> {setExpense(res);})
    fetch("http://localhost:8080/user/" + id, {method:"GET"})
    .then(res => res.json())
    .then(res=> {setUser(res);})
  },[])

  const changeValue=(e)=>{
    console.log(e);
    setNewExpense({
     ...newExpense, [e.target.name]:e.target.value  
    });
    fetch("http://localhost:8080/expense/" + id, {method:"GET"})
      .then(res => res.json())
      .then(res=> {setExpense(res);})
  }

  const createNewExpense =(e)=>{
    e.preventDefault();
    fetch("http://localhost:8080/createExpense", {
      method:"PUT",
      headers:{
        "Content-Type" : "application/json"
      },
      body: JSON.stringify(newExpense)
    })
    .then(res=>{
        console.log(1,res);
        if(res.status === 200){
          refreshTable();
          return res.json();
        }else{
          return null;
        }
      });
  }

  const refreshTable =()=>{
    fetch("http://localhost:8080/expense/" + id, {method:"GET"})
      .then(res => res.json())
      .then(res=> {setExpense(res);}
    );
  }

  const deleteExpense =()=>{
    const deleteID=document.getElementById("deleteID").value;
    fetch("http://localhost:8080/deleteExpense/" + deleteID, {method:"GET"})
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

  //Ally's code for submitting initial balance
  //check if user is logged in for first time
  const isFirstTimeUser = user && user.total_balance === 0;
  //after button is pressed, receive user input and add initial balance
  const addInitialBalance = () => {

    const balance = prompt("Please enter initial balance: ");
    const newBalance = parseFloat(balance);

    if (isNaN(newBalance)){
      alert("Invalid input... please enter a valid starting balance.");
      return;
    }

    setUser({
      ...user,
      total_balance: newBalance
    });

    fetch("http://localhost:8080/userInitialBalance/" + id + "/" + newBalance, {
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    })
    .then(response => response.json())
    .then(data=>{
      const { username, name, password, total_balance, savings_goal } = data;
      console.log(`User ${username} has an initial balance of ${total_balance}`);
      setUser({
        ...user,
        total_balance: newBalance
      });
    })
  };

  return (
    <div>
      <header class="loginheader">
          <div className="banklogo"/>
      </header>
      <div>
        <Header id={id}/>
      </div>
      <Form>
        <Form.Group controlId="formBasicEmail" className="balanceform" class="accountbalance">
          <Form.Label>Account Balance {user.total_balance}</Form.Label>
          <br></br>
          {/*Ally's added code is below*/}
          {isFirstTimeUser && (
            <Button 
              variant="primary"
              onClick={addInitialBalance}
              className="ml-3"
            >Add Initial Balance</Button>
          )}
          {/*End ally's code*/}
        </Form.Group>
      </Form>
      <h1>Expenses and Incomes</h1>
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
      <Button type="button" variant="secondary" onClick={refreshTable}>
                Refresh  
      </Button>
      <h1>Create</h1>
      <div className="newexpenseform">
        <Form onSubmit={createNewExpense}>
            <Form.Group>
              <Form.Label>Planned</Form.Label>
              <Form.Control type="text" placeholder="Enter 1 if planned or 0 if unplanned" onChange = {changeValue} name="planned" value={newExpense.planned}/>
            </Form.Group>
              <Form.Label>Amount</Form.Label>
              <Form.Control type="text" placeholder="Enter amount" onChange = {changeValue} name="amount" value={newExpense.amount}/>
            <Form.Group>
              <Form.Label>Income or Expense</Form.Label>
              <Form.Control type="text" placeholder="Enter 1 for income or 0 for expense" onChange = {changeValue} name="income_or_expense" value={newExpense.income_or_expense}/>
            </Form.Group>
            <Form.Group>
              <Form.Label>Information</Form.Label>
              <Form.Control type="text" placeholder="Enter information" onChange = {changeValue} name="information" value={newExpense.information}/>
            </Form.Group>
            <Form.Group>
              <Form.Label>Due Date</Form.Label>
              <Form.Control type="text" placeholder="Enter due-date YYYY-MM-DD" onChange = {changeValue} name="due_date" value={newExpense.due_date}/>
            </Form.Group>
            <div className="expensesubmit">
              <Button type="submit" variant="secondary">
                Submit  
              </Button>
            </div>
          </Form>
        </div>

        <form>
            <br></br>
            <h3>Delete Expense</h3>
            <Form.Group>
              <Form.Label>Expense ID</Form.Label>
              <Form.Control type="text" placeholder="Enter expense id to be deleted" name="deleteID" id='deleteID'/>
            </Form.Group>

            <div className="expensesubmit">
              <Button type="button" variant="secondary" onClick={deleteExpense}>
                Delete  
              </Button>
            </div>
          </form>
    </div>
  );
}

export default BalanceAdjustmentView;
