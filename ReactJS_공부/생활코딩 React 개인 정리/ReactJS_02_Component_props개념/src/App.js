import React, { Component} from 'react';
import './App.css';

class Subject extends Component {
  render(){
    return (
      <header>
        <h1>{this.props.title}</h1>
        {this.props.desc}
      </header>
    );
  }
}

class TOC extends Component {
  render(){
    return(
      <nav>
        <ul>
          <li><a href="1.html">HTML</a></li>
          <li><a href="2.html">CSS</a></li>
          <li><a href="3.html">JavaScript</a></li>
        </ul>        
      </nav>
    );
  }
}

class Contents extends Component{
  render(){
    return(
      <article>
            <h2>{this.props.title}</h2>
            {this.props.desc}
        </article>
    );
  }
}

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
