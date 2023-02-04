import React from 'react';
import {Card} from 'react-bootstrap';
import { Link } from 'react-router-dom';
 
function BankUser(props) {

  const {username, name, password, total_balance, savings_goal} = props.bankuser;
  return (
    <Card>
        <Card.Body>
        </Card.Body>
    </Card>
  );
}

export default BankUser;
