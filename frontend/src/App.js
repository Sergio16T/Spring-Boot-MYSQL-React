import React from 'react';
import './App.scss';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import Users from './pages/Users'; 
import AddUser from './pages/AddUser'; 

function App() {
  return (
	<Router>
		<div className="App">
			<Switch>
				<Route exact path="/" component={Users}/>
				<Route path="/adduser" component={AddUser}/>
			</Switch>
		</div>
	</Router>


  );
}

export default App;