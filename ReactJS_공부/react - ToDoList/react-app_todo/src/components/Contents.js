import React, {Component} from 'react';

class Contents extends Component{
   
    render(){

        return(
            <section>
                <h4>Content</h4>
                <p>{this.props.title} - {this.props.content}</p>
            </section>
        );
    }
}

export default Contents;