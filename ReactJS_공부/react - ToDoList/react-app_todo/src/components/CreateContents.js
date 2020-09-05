import React, {Component} from 'react';

class CreateContents extends Component {
    render(){
        return(
            <div>
                <h4>create</h4>
                <form action="/create-process" method="post"
                    onSubmit ={function(e){
                        e.preventDefault();
                        this.props.onClickBtn(
                            e.target.title.value,
                            e.target.content.value
                        );
                    }.bind(this)}>
                    <p>
                        <input type="text" name="title" placeholder='title'></input>
                    </p>
                    <p>
                        <textarea name="content" placeholder='content'></textarea>
                    </p>
                    <p>
                        <button type="submit">submit</button>
                    </p>
                </form>
            </div>
        );
    }
}

export default CreateContents;