import React from 'react';
import NavTabs from './NavTabs';
import EmployeeDetails from './EmployeeDetails';


export default class Home extends React.Component {

  constructor(){
    super();
    this.state = {
      SessionId : ""
    }
  }

  componentDidMount(){
    this.setState({
      SessionId : this.props.location.state.SessionId
    })
  }

  render(){
    return (
      <div style={{display: 'flex'}}>
          <div style={{width:"25%"}}>
            <EmployeeDetails SessionId={this.props.location.state.SessionId}/>
          </div>
          <div style={{width:"75%"}}>
              <NavTabs SessionId={this.props.location.state.SessionId}
              history={this.props.history}/>
          </div>
      </div>
    );
  }
}