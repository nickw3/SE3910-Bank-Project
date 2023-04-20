import logo from './CommerceBankLogo.png';
import './App.css';
import {Container} from 'react-bootstrap';
import {Route} from 'react-router-dom';
import Header from './components/Header'
import LoginForm from './pages/user/LoginForm';
import MonthlyCalendarView from './pages/user/MonthlyCalendarView';
import BalanceAdjustmentView from './pages/user/BalanceAdjustmentView';
import LoanCalculatorView from './pages/user/LoanCalculatorView';

function App() {
  return (
    <div>
      <Container>
        <Route path="/" exact={true} component={LoginForm}/>
        <Route path="/testCalendar/:id" exact={true} component={MonthlyCalendarView}/>
        <Route path="/balanceAdjustment/:id" exact={true} component={BalanceAdjustmentView}/>
        <Route path="/loanCalculatorView/" exact={true} component={LoanCalculatorView}/>      
      </Container>    
    </div>
  );
}

export default App;
