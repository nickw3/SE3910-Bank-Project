import React, { useState } from 'react';
import Header from '../../components/Header';

function LoanCalculator() {
  const [loanAmount, setLoanAmount] = useState(100000);
  const [interestRate, setInterestRate] = useState(5);
  const [loanTerm, setLoanTerm] = useState(30);
  const [monthlyPayment, setMonthlyPayment] = useState(0);

  function handleLoanAmountChange(e) {
    setLoanAmount(e.target.value);
  }

  function handleInterestRateChange(e) {
    setInterestRate(e.target.value);
  }

  function handleLoanTermChange(e) {
    setLoanTerm(e.target.value);
  }

  function calculateMonthlyPayment() {
    // calculate monthly payment here based on loanAmount, interestRate, and loanTerm
    const monthlyInterestRate = interestRate / 12 / 100;
    const totalPayments = loanTerm * 12;
    const numerator = monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalPayments);
    const denominator = Math.pow(1 + monthlyInterestRate, totalPayments) - 1;
    const monthlyPayment = loanAmount * (numerator / denominator);
    setMonthlyPayment(monthlyPayment.toFixed(2));
  }

  return (
    <div>
      <header class="loginheader">
        <div className="banklogo"/>
      </header>
      <Header/>
      <label>Loan Amount:</label>
      <input type="number" value={loanAmount} onChange={handleLoanAmountChange} />
      <br />
      <label>Interest Rate:</label>
      <input type="number" value={interestRate} onChange={handleInterestRateChange} />
      <br />
      <label>Loan Term (years):</label>
      <input type="number" value={loanTerm} onChange={handleLoanTermChange} />
      <br />
      <button onClick={calculateMonthlyPayment}>Calculate</button>
      <br />
      <label>Monthly Payment:</label>
      <span>{monthlyPayment}</span>
    </div>
  );
}

export default LoanCalculator;