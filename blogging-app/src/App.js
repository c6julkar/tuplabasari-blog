import './App.css';
import React from 'react'
import { BrowserRouter as Router, Route } from 'react-router-dom'

import Blog from './Blog'

export default function App() {
    return (
        <Router>
        <div>
        <Route path="/blog/:id" component={Blog} /><Route path="/blogs" component={Blogs} />
        </div>
    </Router>
);
}


class Blogs extends React.Component {

    constructor(props) {
        super(props);
        this.state = {id: [],header:[],content: [], url:"http://localhost:8080/posts/", newHeader: "", newContent: ""};
        console.log(this.props.location);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.fetchPost();
    }

    fetchPost() {
        //this.state = {id: [],header:[],content: [], url:"http://localhost:8080/posts/", newHeader: "", newContent: ""};
        fetch(this.state.url) .then((resp) => resp.json()) // Transform the data into json
            .then((data) => {
                console.log(data)
                for (let i = 0; i < data.length; i++) {
                    this.setState({
                        id: [...this.state.id, data[i].id]
                    });
                    this.setState({
                        header: [...this.state.header, data[i].header]
                    });
                    this.setState({
                        content: [...this.state.content, data[i].content.slice(0,200) + "..."]
                    });
                }
            })
    }

    fetchLatestPost() {
        fetch(this.state.url) .then((resp) => resp.json()) // Transform the data into json
            .then((data) => {
                this.setState({
                    id: [...this.state.id, data[data.length - 1].id]
                });
                this.setState({
                    header: [...this.state.header, data[data.length - 1].header]
                });
                this.setState({
                    content: [...this.state.content, data[data.length - 1].content.slice(0,200) + "..."]
                });

            })
    }



    handleSubmit(event) {
        event.preventDefault();
        fetch(this.state.url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                header: this.state.newHeader,
                content : this.state.newContent
            })
        }).then(this.fetchLatestPost());
    }

    handleChange(event) {
        const name = event.target.name;
        this.setState({[name]: event.target.value});
    }

    deletePost(id, index) {
        fetch(this.state.url + '/' + id, {
            method: 'delete'
        });
        var array = this.state.id;
        array.splice(index, 1);
        this.setState({id: array });

        var header = this.state.header;
        header.splice(index, 1);
        this.setState({header: header });

        var content = this.state.content;
        content.splice(index, 1);
        this.setState({content: content });

    }

    render() {
        let element = [];
        for (let i = 0; i < this.state.id.length; i++) {
            let link = "/blog/" + this.state.id[i];
            element[i] = <tr><td><a href={link}>{this.state.header[i]}</a></td><td><a href={link}>{this.state.content[i]}</a></td>
                <td><button onClick={() => this.deletePost(this.state.id[i], i)} type="button" className="btn btn-danger">Delete post</button></td>
            </tr>;
        }
        return (
            <div>
            <form className="form-horizontal" onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label className="control-label col-sm-2" htmlFor="email">Title:</label>
                    <div className="col-sm-10">
                        <input name="newHeader" type="text" className="form-control" id="header" onChange={this.handleChange} value={this.state.newHeader} placeholder="Enter title"/>
                    </div>
                </div>
                <div className="form-group">
                    <label className="control-label col-sm-2" htmlFor="pwd">Content:</label>
                    <div className="col-sm-10">
                        <textarea name="newContent" value={this.state.newContent} onChange={this.handleChange} placeholder="Your thoughts here" className="form-control" rows="5" id="comment"></textarea>
                    </div>
                </div>
                <div className="form-group">
                    <div className="col-sm-offset-2 col-sm-10">
                        <button type="submit" className="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
        <div className="table-responsive">
                <table className="table">
                {element}
            </table>
            </div>
            </div>
        );
    }
}
