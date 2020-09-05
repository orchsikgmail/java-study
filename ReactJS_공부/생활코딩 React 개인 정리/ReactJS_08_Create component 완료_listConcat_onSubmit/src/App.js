import React, { Component} from 'react';
import './App.css';
import Subject from './components/Subject';
import TOC from './components/TOC';
import ReadContents from './components/ReadContents';
import CreateContents from './components/CreateContents';
import Control from './components/Control';

class App extends Component {
  constructor(props){
    super(props);

    this.max_id = 3; // state가 아닌 일반객체로 설정한이유? UI에 영향을 주지 않는 변수.
    this.state = { 
        mode : "create",
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

    var _title, _desc, _article = null;
    if(this.state.mode === "welcome"){
        _title = this.state.welcome.title;
        _desc = this.state.welcome.desc;
        _article = <ReadContents title={_title} desc={_desc}></ReadContents>
      } else if(this.state.mode === "read") {
        var i = 0;
        while(i < this.state.contents.length){
          var data = this.state.contents[i];
          if(data.id === this.state.selected_content_id) {
            _title = data.title;
            _desc = data.desc;
            _article = <ReadContents title={_title} desc={_desc}></ReadContents>
            break;
          }
          i = i + 1;
        }
    } else if(this.state.mode === 'create'){
      _article = <CreateContents onSubmit={function(_title, _desc) {
        this.max_id = this.max_id+1;
      
        // this.state.contents.push(
        //   {id: this.max_id, title:_title, desc:_desc}
        // );
        // 다음의 방법으로 하는 것이 퍼포먼스 성능에 좋다.
        // 왜? 다른 Component에서 shoudComponentUpdate()로 이전값과 새로운 값을 비교하여 개선된 방법으로 처리가능.
        // push() 를 사용한 경우 원본이 변경되어, 원본이 새로운값과 동일해지기때문에 shoudComponentUpdate() 사용불가.
        var _contents = this.state.contents.concat(
          {id: this.max_id, title:_title, desc:_desc}
        );
        this.setState({contents:_contents});
      }.bind(this)}></CreateContents>
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

      {_article}
      </div>
    );
  }
}


export default App;
