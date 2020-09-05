import React, { Component } from 'react';

class Subject extends Component {
    render(){
      console.log("render Subject");
      return (
        <header>
          <h1><a href="/">{this.props.title}</a></h1>
          {this.props.desc}
        </header>
      );
    }
  }

  export default Subject;