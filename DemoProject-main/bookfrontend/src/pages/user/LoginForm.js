import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { propTypes } from 'react-bootstrap/esm/Image';
 
function LoginForm(props) {

  const[bankuser, setUser] = useState({
    username:'',
    password:'',
  });

  const id=props.match.params.id;

  const changeValue=(e)=>{
    console.log(e);
    setUser({
     ...bankuser, [e.target.name]:e.target.value  
    });
    console.log(e.target.name + " name "  );
    console.log(e.target.value + " value " );
  }

  const login =(e)=>{
    e.preventDefault();
    fetch("http://localhost:8080/userLogin", {
      method:"PUT",
      headers:{
        "Content-Type" : "application/json"
      },
      body: JSON.stringify(bankuser)
    })
    .then(res=>{
        console.log(1,res);
        if(res.status === 200){
          return res.json();
        }else{
          return null;
        }
      })
    .then(res=>{
      console.log(res)
      if(res!==null){
        props.history.push('/balanceAdjustmentView');
      }else{
        alert('fails');
      }
    
    });

  }

  return (
    <div>
       
      <Form onSubmit = {login}>
        <Form.Group controlId="formBasicEmail">
          <Form.Label>Username</Form.Label>
          <Form.Control type="text" placeholder="Enter username" onChange = {changeValue} name="username" value={bankuser.username}/>
        </Form.Group>

        <Form.Group controlId="formBasicEmail">
          <Form.Label>Password</Form.Label>
          <Form.Control type="text" placeholder="Enter password" onChange = {changeValue} name="password" value={bankuser.password}/>
        </Form.Group>

        <Button variant="primary" type="submit">
          Submit  
        </Button>
      </Form>
    </div>
  );
}

export default LoginForm;
