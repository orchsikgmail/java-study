import React, { Component } from 'react';

class ReadContents extends Component {
    render(){
        console.log("render ReadContents");
        
        return(
            <article>
                <h2>{this.props.title}</h2>
                {this.props.desc}
            </article>
        );
    }
}

export default ReadContents;