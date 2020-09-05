import React, { Component} from 'react';
import './App.css';
import Subject from './components/Subject.js';
import TOC from './components/TOC.js';
import Contents from './components/Contents.js';

class App extends Component {
  render(){
    return (
      <div>
      <Subject title="WEB" desc="world wide web!"></Subject>
      <TOC></TOC>
      <Contents title="HTML" desc="HTML is HyperText Markup Language."></Contents>
      </div>
    );
  }
}


export default App;
