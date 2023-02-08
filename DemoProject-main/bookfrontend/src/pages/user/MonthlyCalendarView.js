import React, { useEffect, useState} from 'react';
import { Form, Button } from 'react-bootstrap';
import { Calendar } from 'react-calendar';
import { TileContent } from 'react-calendar';

const changeValue=(e)=>{
  console.log(e);
}


function tileContent(expenses){
    return "Rent due";
}


 
function MonthlyCalendarView(props) {

  const id=props.match.params.id;
  const[user, setUser] = useState([]);
  const[expenses, setExpense] = useState([]);

  useEffect(()=>{
    fetch("http://localhost:8080/expense/" + id, {method:"GET"})
      .then(res => res.json())
      .then(res=> {setExpense(res);})
    fetch("http://localhost:8080/user/" + id, {method:"GET"})
    .then(res => res.json())
    .then(res=> {setUser(res);})
  },[])

  return (
    <div>
      <header class="loginheader">
          <div className="banklogo"/>
      </header>
      <Calendar onChange={(changeValue)} tileContent={tileContent(expenses)}/>
    </div>
  );
}

export default MonthlyCalendarView;
