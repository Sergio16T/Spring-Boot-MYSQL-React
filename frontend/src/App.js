import React from 'react';
import './App.scss';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import Users from './pages/Users';
import AddUser from './pages/AddUser';
import UpdateUser from './pages/UpdateUser';
import SignUp from './pages/SignUp';
import SignIn from './pages/SignIn';

function App() {
  return (
	<Router>
		<div className="App">
			<Switch>
				<Route exact path ="/signup" component={SignUp}/>
				<Route exact path ="/signin" component={SignIn}/>
				<Route exact path="/" component={Users}/>
				<Route path="/adduser" component={AddUser}/>
				<Route path="/update-user/:id" component={UpdateUser}/>
			</Switch>
		</div>
	</Router>


  );
}

export default App;