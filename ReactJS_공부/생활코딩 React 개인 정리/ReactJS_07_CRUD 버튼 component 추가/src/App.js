import React, { Component} from 'react';
import './App.css';
import Subject from './components/Subject.js';
import TOC from './components/TOC.js';
import Contents from './components/Contents.js';
import Control from './components/Control.js';

class App extends Component {
  constructor(props){
    super(props);
    this.state = { 
        mode : "welcome",
        selected_content_id : 2,
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
      } else if(this.state.mode === "read") {
        var i = 0;
        while(i < this.state.contents.length){
          var data = this.state.contents[i];
          if(data.id === this.state.selected_content_id) {
            _title = data.title;
            _desc = data.desc;
            break;
          }
          i = i + 1;
        }
    }
    
    return (
      <div>
       <Subject
        title={this.state.subject.title} desc={this.state.subject.desc}
        onChangePage = {function() {  // onChangePage() 라는 ReactJS 함수 존재한다.
            this.setState({ mode : "welcome" });
          }.bind(this)
        }>
       </Subject>

      <TOC data={this.state.contents}
        onChangePage={function(id){
            this.setState({ mode:"read", selected_content_id:Number(id) });
        }.bind(this)}
      ></TOC>

        <Control onChangeMode={function(_mode) {
          this.setState(
            {mode:_mode}
            )
        }.bind(this)}></Control>

      <Contents title={_title} desc={_desc}></Contents>
      </div>
    );
  }
}


export default App;
