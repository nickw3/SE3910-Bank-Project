import React, { useEffect, useState} from 'react';
import ExpenseIncome from '../../components/ExpenseIncome';
 
function Home() {

  const[expenses, setExpense] = useState([]);

  useEffect(()=>{
      fetch("http://localhost:8080/expense", {method:"GET"})
      .then(res => res.json())
      .then(res=> {setExpense(res);})
      
  },[])

  return (
    <div>
          {expenses.map(expense=> <ExpenseIncome key={expense.expense_id} expense = {expense}></ExpenseIncome> )}            
    </div>
  );
}

export default Home;
