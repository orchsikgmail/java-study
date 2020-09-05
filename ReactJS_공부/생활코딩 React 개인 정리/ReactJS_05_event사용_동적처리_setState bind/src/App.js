import React, { Component} from 'react';
import './App.css';
import Subject from './components/Subject.js';
import TOC from './components/TOC.js';
import Contents from './components/Contents.js';

class App extends Component {
  constructor(props){
    super(props);
    this.state = { 
        mode : "html",
        welcome: { title:"welcome", desc: "Come one react world." },
        subject: { title:"web", desc:"world wid web!" }, 
        contents: [
          {id: 1, title:"HTML", desc:"HTML is HyperText Markup Language."},
          {id: 2, title:"JavaScript", desc:"JS is for interactive."},
          {id: 3, title:"CSS", desc:"CSS is for design"},
        ]
      }
  }


  render(){
    console.log("render App");

    var _title, _desc = null;
    if(this.state.mode === "welcome"){
        _title = this.state.welcome.title;
        _desc = this.state.welcome.desc;
      } else if(this.state.mode === "html") {
        _title = this.state.contents[0].title;
        _desc = this.state.contents[0].desc;
    }

    return (
      <div>
       {/*<Subject title={this.state.subject.title} desc={this.state.subject.desc}></Subject>*/}
      <header>
          <h1>
            <a href="/" onClick= { 
              function(e) { 
                 console.log(e);
                 // debugger;
                 e.preventDefault();
                 // this.state.mode="welcome";  // 함수에 .bind(this)로 componenet 미지정시 state를 못 찾는다.
                 this.setState(   // 위와 같이 mode변경시 React는 mode의 변경을 모른다. setState() 사용 필요하다.
                  { mode : "welcome" }
                  );
               }.bind(this)
            }>{this.state.subject.title}</a>
          </h1>
          {this.state.subject.desc}
      </header>
      <TOC data={this.state.contents}></TOC>
      <Contents title={_title} desc={_desc}></Contents>
      </div>
    );
  }
}


export default App;
