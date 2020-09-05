import React, {Component} from 'react';

class Control extends Component{
    render(){

        return(
            <section>
                <input type="button" value="create"
                    onClick ={function(){
                        this.props.onClickControl('create');
                    }.bind(this)}
                ></input>
               
                <input type="button" value="update"
                    onClick ={function(){
                        this.props.onClickControl('update');
                    }.bind(this)}
                ></input>
               
                <input type="button" value="delete"
                    onClick ={function(){
                        this.props.onClickControl('delete');
                    }.bind(this)}
                ></input>
            </section> 
        );
    }
}

export default Control;