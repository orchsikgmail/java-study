import React, {Component} from 'react';

class UpdateContents extends Component {
    constructor(props){
        super(props);
        this.state = {
            title : this.props.data.title,
            content : this.props.data.content,
            id : this.props.data.id
        }
    }

    render(){
      

        return(
            <div>
                <h4>update</h4>
                <form action="/update-process" method="post"
                    onSubmit ={function(e){
                        e.preventDefault();
                        this.props.onClickBtn(
                            this.state.id,
                            this.state.title,
                            this.state.content,
                        );
                    }.bind(this)}>
                    <input type="hidden" value={this.state.id}></input>
                    <p>
                        <input type="text" name="title" placeholder='title'
                            value={this.state.title}
                            onChange = {function(e){
                                console.log(e.target.value);
                                this.setState({[e.target.name] : e.target.value});
                            }.bind(this)}
                        ></input>
                    </p>
                    <p>
                        <textarea name="content" placeholder='content'
                             value={this.state.content}
                             onChange = {function(e){
                                this.setState({[e.target.name] : e.target.value});
                            }.bind(this)}
                        ></textarea>
                    </p>
                    <p>
                        <button type="submit">submit</button>
                    </p>
                </form>
            </div>
        );
    }
}

export default UpdateContents;