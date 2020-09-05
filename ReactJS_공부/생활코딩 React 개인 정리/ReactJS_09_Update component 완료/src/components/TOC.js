import React, { Component } from 'react';

class TOC extends Component {
    render(){
      console.log("render TOC");

      var list = [];

      var data = this.props.data;
      var i=0;
      while(i < data.length) {
        list.push(
          <li key={data[i].id}>
            <a 
              href={"/content/"+data[i].id} 
              data-id={data[i].id} 
              onClick={function(e){
                  e.preventDefault();
                  this.props.onChangePage(e.target.dataset.id);  // TOC객체가 App객체의 onChangePage() 함수를 호출한다.
              }.bind(this)}
            >{data[i].title}</a>
          </li>
          );
        i=i+1;
      }

      return(
        <nav>
          <ul>
            {list}
          </ul>        
        </nav>
      );
    }
  }

export default TOC;