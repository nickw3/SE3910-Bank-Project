import React, { useEffect, useState} from 'react';
import { Form, Button } from 'react-bootstrap';
import { Calendar } from 'react-calendar';
import { TileContent } from 'react-calendar';
import ExpenseIncome from '../../components/ExpenseIncome'; 

function MonthlyCalendarView(props) {

const id=props.match.params.id;
const[user, setUser] = useState([]);
const[expenses, setExpense] = useState([]);
const[value, onChange] = useState(new Date());

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
  .then(res=> {setUser(res);})
},[])

  return (
    <div>
      <header class="loginheader">
          <div className="banklogo"/>
      </header>
      <Calendar onChange={onChange} value={value} tileContent={tileContent} tileClassName={'calendar-tile'}/>
    </div>
  );
}

export default MonthlyCalendarView;
