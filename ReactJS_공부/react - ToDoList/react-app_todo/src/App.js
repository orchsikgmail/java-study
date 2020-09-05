import React, {Component} from 'react';
import './App.css';
import Subject from './components/Subject';
import TOC from './components/TOC';
import Contents from './components/Contents';
import Control from './components/Control';
import CreateContents from './components/CreateContents';
import UpdateContents from './components/UpdateContents';

class App extends Component{
  constructor(props){
    super(props);

    this.state = {
      mode : 'read',
      checkedID : '0',
      contents :[
        {id:'0', title:'Welcome', content:'make your to do list'},
        {id:'1', title:'React Study', content:'make todoList by create-react-app'},
        {id:'2', title:'JS Study', content:'study JS API version 6'},
        {id:'3', title:'SQL Study', content:'make a file by myself for SQL sequences'}
      ]

    }
  }

  getContent(){
    var _title, _content = null;
    if(this.state.mode === 'read'){
      var i = 0;
      while(i < this.state.contents.length){
        var content = this.state.contents[i];
        
        if(this.state.checkedID === content.id){
        _title = content.title;
        _content = content.content;
        return <Contents title={_title} content={_content}></Contents>
       }
      
        i=i+1;
      }
    } else if(this.state.mode === 'create'){
      return <CreateContents onClickBtn={function(title, content){
        var newData = {id: String(this.state.contents.length), title:title, content:content}
        var _contents = Array.from(this.state.contents);
        _contents.push(newData);
        this.setState({contents:_contents, mode:'read', checkedID:String(this.state.contents.length)});
      }.bind(this)}></CreateContents>
    } else if(this.state.mode === 'update'){
       return <UpdateContents 
            data={this.state.contents[this.state.checkedID]}
            onClickBtn = {function(id,title,content) {
              var i =0;
              while(i < this.state.contents.length){
                if(this.state.contents[i].id === id){
                  var updateData = {id:id, title:title, content:content};
                  var _contents = Array.from(this.state.contents);
                  _contents[id] = updateData;
                  this.setState({contents:_contents, mode:'read', checkedID:id});
                }
                i = i+1;
              }
            }.bind(this)}
          ></UpdateContents>
    } else if(this.state.mode === 'delete'){
      if(window.confirm('really?')) {
        var idx = 0;
        while(idx < this.state.contents.length){
          if(this.state.contents[idx].id === this.state.checkedID){
            var _contents = Array.from(this.state.contents);
            _contents.splice(idx,1);
            this.setState({contents: _contents, mode:'read'});
            break;
          }
          
          idx=idx+1;
        }
      };
    }



  }

  render(){
    var today = new Date();
    var date = today.getFullYear()+'-'+ (today.getMonth()+1) +'-'+today.getDate();
   
    return(
      <div>
      <Subject date={date}
        onClickSubject={function(){
          this.setState({mode : 'read', checkedID: '0'});
        }.bind(this)}
      ></Subject>

      <TOC contents={this.state.contents}
        onClickTitle = {function(clickedId){
          this.setState({checkedID:clickedId, mode:'read'});
        }.bind(this)}
      ></TOC>

      <Control onClickControl={function(mode){
        this.setState({mode:mode});
      }.bind(this)}></Control>

      

      {this.getContent()}
      </div>
    );
  }
}


export default App;
