import React from 'react';
import {Card} from 'react-bootstrap';
import { Link } from 'react-router-dom';
 
function BankUser(props) {

  const {username, name, password, total_balance, savings_goal} = props.bankuser;
  return (
    <Card>
        <Card.Body>
            <Card.Title> {username} : {name} : </Card.Title>
            <Card.Text> {password} {total_balance} {savings_goal}</Card.Text>
        </Card.Body>
    </Card>
  );
}

export default BankUser;
