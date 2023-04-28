import React, { useEffect, useState} from 'react';
import { Form, Button } from 'react-bootstrap';
import { Calendar } from 'react-calendar';
import { TileContent } from 'react-calendar';
import ExpenseIncome from '../../components/ExpenseIncome'; 

//Monthly calendar and savings goal
function MonthlyCalendarView(props) {

const id=props.match.params.id;
const[user, setUser] = useState([]);
const[expenses, setExpense] = useState([]);
const[value, onChange] = useState(new Date());
//for the savings goal:
const [savingsGoal, setSavingsGoal] = useState(0);
const [showForm, setShowForm] = useState(false);
const [newSavingsGoal, setNewSavingsGoal]=useState(savingsGoal);

function handleSavingsGoalChange(event){
  setNewSavingsGoal(event.target.value);
}

//for savings goal:
function handleSavingsGoalSubmit(event){
  event.preventDefault();
  setSavingsGoal(newSavingsGoal);
  setShowForm(false);
  //not sure how to do this but maybe an idea
  //can't complete this because I don't have the backend code.
   fetch('http://localhost:8080/setMonthlyBalance/' + id + '/' + newSavingsGoal);
}

function handleFormClose(){
  setShowForm(false);
  setNewSavingsGoal(savingsGoal);
}


function tileContent({date}){
    var summary = "\n";
    var expense_or_income = 2;
    expenses.forEach((expense) => {
      if(expense.due_date === date.toISOString().split('T')[0]){
        if(expense.information != undefined){
          if(expense.income_or_expense == 0){
            expense_or_income = 0;
            return summary += expense.information + ", ";
          }
          if(expense.income_or_expense == 1){
            expense_or_income = 1;
            return summary += expense.information + ", ";
          }
        }
      }
    })
    console.log(expense_or_income);
    if(expense_or_income == 0){
      return <div class='tile-expense'>{summary}</div>
    }
    if(expense_or_income == 1){
      return <div class='tile-income'>{summary}</div>
    }
}


useEffect(()=>{
  fetch("http://localhost:8080/expense/" + id, {method:"GET"})
    .then(res => res.json())
    .then(res=> {setExpense(res);})
  fetch("http://localhost:8080/user/" + id, {method:"GET"})
  .then(res => res.json())
  .then(res=> {setUser(res); setSavingsGoal(res.savings_goal)})
},[])

  return (
    <div>
      <header class="loginheader">
        <div className="banklogo"/>
      </header>
      <form onSubmit={handleSavingsGoalSubmit}>
        <div>
          <p>Monthly Savings Goal: ${savingsGoal}</p>
          <Button onClick={() => setShowForm(true)}>Change Goal</Button>
          <br></br>
        </div>
      </form>
        {showForm && (
          <Form onSubmit={handleSavingsGoalSubmit}>
            <Form.Group controlId="formSavingsGoal">
              <Form.Label>Enter Monthly Savings Goal: $</Form.Label>
              <Form.Control type="number" value={newSavingsGoal} onChange={handleSavingsGoalChange} />
            </Form.Group>
            <Button variant="primary" type="submit">Save</Button>
            <Button variant="secondary" onClick={handleFormClose}>Cancel</Button>
          </Form>
        )}
      <Calendar onChange={onChange} value={value} tileContent={tileContent} tileClassName={'calendar-tile'}/>
    </div>
  );
}



export default MonthlyCalendarView;
