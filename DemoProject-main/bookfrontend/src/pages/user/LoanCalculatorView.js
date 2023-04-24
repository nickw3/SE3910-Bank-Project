import React, { useEffect, useState } from 'react';
import { Form, Button, Table } from 'react-bootstrap';
import BankUser from '../../components/BankUser';
import ExpenseIncome from '../../components/ExpenseIncome';
import Header from '../../components/Header';

function LoanCalculatorView(props) {

  const id = props.match.params.id;

  const[expenses, setExpense] = useState([]);
  const[user, setUser] = useState([]);

  const [inputValues, setInputValues] = useState({
    amount: '',
    term: '',
    interest: '',
  });

  const [results, setResults] = useState({
    monthlyPayment: '',
    totalPayment: '',
    totalInterest: '',
    suggestion: '',
  });

  const changeValue=(e)=>{
    setInputValues({
     ...inputValues, [e.target.name]:e.target.value  
    });
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(inputValues);
    loanCalculator(inputValues);
  }

  useEffect(()=>{
    fetch("http://localhost:8080/expense/" + id, {method:"GET"})
      .then(res => res.json())
      .then(res=> {setExpense(res);})
    fetch("http://localhost:8080/user/" + id, {method:"GET"})
    .then(res => res.json())
    .then(res=> {setUser(res);})
  },[])

  const loanCalculator = ({ amount, term, interest }) => {
    const userAmount = Number(amount);
    const calculatedInterest = Number(interest) / 100;
    const calculatedPayments = Number(term); 
    const x = Math.pow(1 + calculatedInterest, calculatedPayments);
    const monthly = (userAmount * x * calculatedInterest) / (x-1);

    if (isFinite(monthly)) {
        const monthlyPaymentCalculated = monthly.toFixed(2);
        const totalPaymentCalculated = (monthly * calculatedPayments).toFixed(2);
        const totalInterestCalculated = (monthly * calculatedPayments - userAmount).toFixed(2);

        var suggestion;
        if(user.savings_goal < monthlyPaymentCalculated){
          suggestion = "This loan will exceed your monthly savings goal of " + user.savings_goal;
        }
        if(user.account_balance < monthlyPaymentCalculated){
          suggestion = "This loan will exceed your total balance of " + user.account_balance;
        }
        else{
          suggestion = "This loan is within your budget";
        }

        setResults({
            monthlyPayment: monthlyPaymentCalculated,
            totalPayment: totalPaymentCalculated,
            totalInterest: totalInterestCalculated,
            suggestion: suggestion,

        });
    }
    return;
  };

  return (
    <div>
      <header class="loginheader">
          <div className="banklogo"/>
      </header>
      <div>
        <Header id={id}/>
      </div>
      <div className="newexpenseform">
        <Form onSubmit={handleSubmit}>
            <Form.Group>
              <Form.Label>Loan Amount</Form.Label>
              <Form.Control type="text" placeholder="Enter loan amount" onChange = {changeValue} name="amount" value={inputValues.amount}/>
            </Form.Group>
              <Form.Label>Loan Term</Form.Label>
              <Form.Control type="text" placeholder="Enter loan term in months" onChange = {changeValue} name="term" value={inputValues.years}/>
            <Form.Group>
              <Form.Label>Loan Interest</Form.Label>
              <Form.Control type="text" placeholder="Enter loan interest" onChange = {changeValue} name="interest" value={inputValues.interest}/>
            </Form.Group>
            <div className="expensesubmit">
              <Button type="submit" variant="secondary">
                Submit  
              </Button>
            </div>
            <Form.Group>
              <Form.Label>Monthly Payment</Form.Label>
              <Form.Control type="text" name="monthlyPayment" value={results.monthlyPayment} disabled/>
            </Form.Group>
            <Form.Group>
              <Form.Label>Total Payment</Form.Label>
              <Form.Control type="text" name="totalPayment" value={results.totalPayment} disabled/>
            </Form.Group>
            <Form.Group>
              <Form.Label>Total Interest</Form.Label>
              <Form.Control type="text" name="totalInterest" value={results.totalInterest} disabled/>
            </Form.Group>
            <Form.Group>
              <Form.Label>Suggestion</Form.Label>
              <Form.Control type="text" name="suggestion" value={results.suggestion} disabled/>
            </Form.Group>
          </Form>
        </div>
    </div>
  );
}

export default LoanCalculatorView;
