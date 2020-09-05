import React, { Component} from 'react';
import './App.css';
import Subject from './components/Subject.js';
import TOC from './components/TOC.js';
import Contents from './components/Contents.js';

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
      
      {/* <header>
          <h1>
            <a href="/" onClick= { 
              function(e) { 
                 e.preventDefault();
                 // this.state.mode="welcome";  // 해당 함수에 .bind(this)로 componenet 저징해야만 state를 찾는다.
                 this.setState(   // 위와 같이 mode변경시 React는 mode의 변경을 모른다. setState() 사용 필요하다.
                  { mode : "welcome" }
                  );
               }.bind(this)
            }>{this.state.subject.title}</a>
          </h1>
          {this.state.subject.desc}
      </header> */}
      
      <TOC data={this.state.contents}
        onChangePage={function(id){
            this.setState({ mode:"read", selected_content_id:Number(id) });
        }.bind(this)}
      ></TOC>
      <Contents title={_title} desc={_desc}></Contents>
      </div>
    );
  }
}


export default App;
