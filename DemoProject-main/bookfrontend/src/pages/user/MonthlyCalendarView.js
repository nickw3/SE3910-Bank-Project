import React from 'react';
import { Form, Button } from 'react-bootstrap';
import { Calendar } from 'react-calendar';
import { TileContent } from 'react-calendar';

const changeValue=(e)=>{
  console.log(e);
}

function tileContent(){
    return "Rent due";
}

 
function MonthlyCalendarView() {
  return (
    <div>
      <header class="loginheader">
          <div className="banklogo"/>
      </header>
      <Calendar onChange={(changeValue)} tileContent={tileContent}/>
    </div>
  );
}

export default MonthlyCalendarView;
