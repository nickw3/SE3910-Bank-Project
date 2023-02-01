import React from 'react';
import { Form, Button } from 'react-bootstrap';

const changeValue=(e)=>{
  console.log(e);
}

 
function LoginForm() {
  return (
    <div>
    <Form onSubmit = {changeValue}>
  <Form.Group controlId="formBasicEmail">
    <Form.Label>Username</Form.Label>
    <Form.Control type="text" placeholder="Enter Username" onChange = {changeValue} name="username" />
  </Form.Group>

  <Form.Group controlId="formBasicEmail">
    <Form.Label>Password</Form.Label>
    <Form.Control type="text" placeholder="Enter Password" onChange = {changeValue} name="password"/>
  </Form.Group>

  <Button variant="primary" type="submit">
    Submit  
  </Button>
</Form>
  </div>
  );
}

export default LoginForm;
