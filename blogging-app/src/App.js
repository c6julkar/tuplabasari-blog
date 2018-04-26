import './App.css';
import React from 'react'
import { BrowserRouter as Router, Route } from 'react-router-dom'

import Blogs from '../blogs'

export default function App() {
    return (
        <Router>
        <div>
        <Route path="/blogs/:id" component={blog} /><Route path="/" component={blogs} />
        </div>
    </Router>
);
}


class Blogs extends React.Component {

    constructor(props) {
        super(props);
        this.state = {id: [],header:[],content: [], url:"http://localhost:8080/posts/"};
        console.log(this.props.location);
    }

    componentDidMount() {
        this.fetchPost();
    }

    fetchPost() {
        fetch(this.state.url) .then((resp) => resp.json()) // Transform the data into json
            .then((data) => {
                //console.log(data)
                for (let i = 0; i < data.length; i++) {
                    this.setState({
                        id: [...this.state.id, data[i].id]
                    });
                    this.setState({
                        header: [...this.state.header, data[i].header]
                    });
                    this.setState({
                        content: [...this.state.content, data[i].content]
                    });
                }
            })
    }

    render() {
        let element = [];
        for (let i = 0; i < this.state.id.length; i++) {
            let link = "/" + this.state.id[i];
            element[i] = <tr><td><a href={link}>{this.state.header[i]}</a></td><td>{this.state.content[i]}</td></tr>;
        }
        return (
            <table>
                <tr><td>Header</td><td>Content</td></tr>
                {element}
            </table>
        );
    }
}

export default App;
