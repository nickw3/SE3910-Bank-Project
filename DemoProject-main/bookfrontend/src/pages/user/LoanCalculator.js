import React, { useState } from "react";

function LoanCalculator() {
  const [totalAmount, setTotalAmount] = useState("");
  const [loanTerm, setLoanTerm] = useState("");
  const [interestRate, setInterestRate] = useState("");
  const [monthlyPayment, setMonthlyPayment] = useState("");
  const [totalInterest, setTotalInterest] = useState("");
  const [showResults, setShowResults] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    // Calculation based on the formula: M = P * (r*(1+r)^n) / ((1+r)^n - 1)
    const monthlyRate = interestRate / 100 / 12;
    const loanAmount = parseFloat(totalAmount);
    const termMonths = parseFloat(loanTerm);
    const monthlyPayment =
      (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, termMonths)) /
      (Math.pow(1 + monthlyRate, termMonths) - 1);
    const totalInterest = monthlyPayment * termMonths - loanAmount;

    setMonthlyPayment(monthlyPayment.toFixed(2));
    setTotalInterest(totalInterest.toFixed(2));
    setShowResults(true);
  };

  return (
    <div>
      <h1>Loan Calculator</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Total Amount:
          <input
            type="text"
            value={totalAmount}
            onChange={(e) => setTotalAmount(e.target.value)}
          />
        </label>
        <br />
        <label>
          Loan Term (months):
          <input
            type="text"
            value={loanTerm}
            onChange={(e) => setLoanTerm(e.target.value)}
          />
        </label>
        <br />
        <label>
          Interest Rate (%):
          <input
            type="text"
            value={interestRate}
            onChange={(e) => setInterestRate(e.target.value)}
          />
        </label>
        <br />
        <button type="submit">Calculate</button>
      </form>
      {showResults && (
        <div>
          <h2>Results</h2>
          <p>Total Amount: {totalAmount}</p>
          <p>Loan Term (months): {loanTerm}</p>
          <p>Interest Rate (%): {interestRate}</p>
          <p>Monthly Payment: ${monthlyPayment}</p>
          <p>Total Interest: ${totalInterest}</p>
        </div>
      )}
    </div>
  );
}

export default LoanCalculator;