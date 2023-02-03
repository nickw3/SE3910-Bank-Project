import React from 'react';
import { Form, Button } from 'react-bootstrap';

const changeValue=(e)=>{
  console.log(e);
}

 
function TestLogin() {
  return (
    <div>
    <Form onSubmit = {changeValue}>
    </Form>
    </div>
  );
}

export default TestLogin;
