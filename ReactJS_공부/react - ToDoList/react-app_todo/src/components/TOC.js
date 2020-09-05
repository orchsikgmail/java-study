import React, {Component} from 'react';

class TOC extends Component{
  
    getTitles(){
        var aStyle = {
            textDecoration : 'none',
            color : 'black',
            margin : '0px'
        }

        var titles = [];
        var contents = this.props.contents;

        var i = 1;
        while(i<contents.length){
            var title = 
                <li key={contents[i].id}>
                    <a style={aStyle} href='/' data-id={contents[i].id}
                        onClick = {function(e){
                            e.preventDefault();
                            this.props.onClickTitle(e.target.dataset.id);
                        }.bind(this)}
                    >{contents[i].title}</a></li>;
            titles.push(title);
            i = i + 1;
        }

        return titles;
    }


    render() {
        
        var olStyle = {
            padding: '0px'
        }

        return(
            <nav>
                <h4>Today Goal~</h4>
                <ol style={olStyle}>
                    {this.getTitles()}
                </ol>
                <br></br>
            </nav>
        );
    }
}

export default TOC;