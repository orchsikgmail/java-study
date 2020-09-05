import React, { Component } from 'react';

class UpdateContents extends Component {
    constructor(props){
        super(props);
        this.state = {
            id : this.props.data.id, 
            title : this.props.data.title, // props를 form에서 바로 수정불가. state로 변경해야 한다.
            desc : this.props.data.desc,
        }
        this.inputFormHandler = this.inputFormHandler.bind(this)  // 해당 함수 bind 일괄적용.
    }


    inputFormHandler(e){
        var value = e.target.value;
        // console.log(value); // 변경시 바로바로 적용됨을 확인할 수 있다.
        this.setState({ [e.target.name] : value });  // 변경된 데이터로 state를 변경해준다.
        // this.setState({ title : value });  // 이 처럼 하드코딩하는 경우 동적처리 불가능.
    }

  
    render(){
        console.log("render UpdateContents");

        return(
            <article>
                <h2>update</h2>
                <form action="/update-process" method="post"
                    onSubmit = {function(e) {
                        e.preventDefault();
                        this.props.onSubmit(
                            this.state.id,
                            this.state.title,
                            this.state.desc
                        );
                    }.bind(this)} 
                >
                    <p>
                        <input type="hidden" name="id" value={this.state.id}></input>
                    </p>
                    <p>
                        <input type="text" name="title" value={this.state.title} placeholder="title"
                            onChange={this.inputFormHandler}></input>
                    </p>
                    <p>
                        <textarea name="desc" value={this.state.desc} placeholder="description"
                            onChange={this.inputFormHandler}></textarea>
                    </p>
                    <p>
                        <input type="submit"></input>
                    </p>
                </form>
            </article>
        );
    }
}

export default UpdateContents;