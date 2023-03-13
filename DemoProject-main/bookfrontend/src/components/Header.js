import React from 'react';
import {Container, Nav, Navbar } from 'react-bootstrap';
import {Link} from 'react-router-dom';
import Dropdown from 'react-bootstrap/Dropdown';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';


 
function App() {
  return (
<>
  <Navbar bg="dark" variant="dark" className="mx-auto">
    <Container>
      <Dropdown as={ButtonGroup}>
        <Link to="/Home">
        <Button variant="success">Home</Button>
        </Link>
        <Dropdown.Toggle split variant="success" id="dropdown-split-basic">
          </Dropdown.Toggle>

          <Dropdown.Menu variant="dark">
            <Dropdown.Item href="/JoinForm">Join</Dropdown.Item>
            <Dropdown.Item href="/LoginForm">Login</Dropdown.Item>
            <Dropdown.Item href="/Detail">Detail</Dropdown.Item>
          </Dropdown.Menu>
      </Dropdown>

    <Nav className="me-auto">
      <Link to ="/MonthlyCalendarView" className = "nav-link">Calendar</Link>
      <Link to ="/BalanceAdjustmentView" className = "nav-link">Balance</Link>
      <Link to ="/MonthlyCalendarView" className = "nav-link">Calendar</Link>
      <Link to ="/SaveForm" className = "nav-link">Save</Link>
      <Link to ="/UpdateForm" className = "nav-link">Update</Link>

    </Nav>
    </Container>
  </Navbar>
  <br />
 
 
</>
  );
}


export default App;
