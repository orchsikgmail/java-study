import React, { Component} from 'react';
import './App.css';
import Subject from './components/Subject.js';
import TOC from './components/TOC.js';
import Contents from './components/Contents.js';

class App extends Component {
  constructor(props){
    super(props);
    this.state = { 
        subject: { title:"web", desc:"world wid web!" }, 
        contents: [
          {id: 1, title:"HTML", desc:"HTML is HyperText Markup Language."},
          {id: 2, title:"JavaScript", desc:"JS is for interactive."},
          {id: 3, title:"CSS", desc:"CSS is for design"},
        ]
      }
  }

  render(){
    return (
      <div>
      <Subject title={this.state.subject.title} desc={this.state.subject.desc}></Subject>
      <TOC data={this.state.contents}></TOC>
      <Contents title="HTML" desc="HTML is HyperText Markup Language."></Contents>
      </div>
    );
  }
}


export default App;
