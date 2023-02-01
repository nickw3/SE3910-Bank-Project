import logo from './logo.svg';
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

function App() {
  return (
    <div>
      <Header/>
      <Container>
        <Route path="/" exact={true} component={Home}/>
        <Route path="/save" exact={true} component={SaveForm}/>
        <Route path="/book/:id" exact={true} component={Detail}/>
        <Route path="/login" exact={true} component={LoginForm}/>
        <Route path="/join" exact={true} component={JoinForm}/>
        <Route path="/update/:id" exact={true} component={UpdateForm}/>
      </Container>    
    </div>
  );
}

export default App;
