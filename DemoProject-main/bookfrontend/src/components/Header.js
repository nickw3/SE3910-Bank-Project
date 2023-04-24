import React from 'react';
import {Container, Nav, Navbar } from 'react-bootstrap';
import {Link} from 'react-router-dom';
import Dropdown from 'react-bootstrap/Dropdown';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';


 
function App(props) {
  const id = props.id;
  return (
<>
  <Navbar bg="dark" variant="dark" className="mx-auto">
    <Container>

    <Nav className="me-auto">
      <Link to ={{
        pathname: "/CalendarView/",
        state: id
      }} className = "nav-link">Calendar</Link>
      <Link to ={{
        pathname: "/BalanceView/",
        state: id
      }} className = "nav-link">Balance</Link>
      <Link to ={{
        pathname: "/LoanCalculator/",
        state: id
      }} className = "nav-link">Loan Calculator</Link>
    </Nav>
    </Container>
  </Navbar>
  <br />
 
 
</>
  );
}


export default App;