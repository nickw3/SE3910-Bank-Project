import logo from './CommerceBankLogo.png';
import './App.css';
import {Container} from 'react-bootstrap';
import {Route} from 'react-router-dom';
import Header from './components/Header'
import SaveForm from './pages/user/SaveForm';
import Home from './pages/book/Home';
import LoginForm from './pages/user/LoginForm';
import JoinForm from './pages/user/JoinForm';
import UpdateForm from './pages/user/UpdateForm';
import Detail from './pages/book/Detail';
import MonthlyCalendarView from './pages/user/MonthlyCalendarView';
import BalanceAdjustmentView from './pages/user/BalanceAdjustmentView'


function App() {
  return (
    <div>
      <Container>
        <Route path="/" exact={true} component={LoginForm}/>
        <Route path="/save" exact={true} component={SaveForm}/>
        <Route path="/book/:id" exact={true} component={Detail}/>
        <Route path="/login" exact={true} component={LoginForm}/>
        <Route path="/join" exact={true} component={JoinForm}/>
        <Route path="/update/:id" exact={true} component={UpdateForm}/>
        <Route path="/testCalendar" exact={true} component={MonthlyCalendarView}/>
        <Route path="/balanceAdjustment/:id" exact={true} component={BalanceAdjustmentView}/>
      </Container>    
    </div>
  );
}

export default App;
