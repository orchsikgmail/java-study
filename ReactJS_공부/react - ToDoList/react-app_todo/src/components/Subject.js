import React, {Component} from 'react';

class Subject extends Component{
    render(){
        var aStyle = {
            textDecoration : 'none',
            color : 'black',
            margin : '0px'
        }

        return(
            <header>
                <h1><a style={aStyle} href='/'
                    onClick ={function(e){
                        e.preventDefault();
                        this.props.onClickSubject();
                    }.bind(this)}
                >TO DO LIST</a></h1>
                <h5>오늘은 {this.props.date} 입니다.</h5>
                <br></br>
            </header> 
        );
    }
}

export default Subject;