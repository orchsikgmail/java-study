import React, { Component } from 'react';

class Contents extends Component {
    render(){
        console.log("render Contents");

        
        return(
            <article>
                <h2>{this.props.title}</h2>
                {this.props.desc}
            </article>
        );
    }
}

export default Contents;