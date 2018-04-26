import React from "react";

class Blogs extends React.Component {

    constructor(props) {
        super(props);
        this.state = {id: this.props.id,header: this.props.header,content: this.props.content, url:"http://localhost:8080/posts/" + props.match.params.name};
    }

    componentDidMount() {
        this.fetchPost();
    }

    fetchPost() {
        fetch(this.state.url) .then((resp) => resp.json()) // Transform the data into json
            .then((data) => {
                console.log(data)
                this.setState({'id':data['id']});
                this.setState({'header':data['header']});
                this.setState({'content':data['content']});
            })
    }

    render() {
        return (
            <table>
                <tr><td>#</td><td>Header</td><td>Content</td></tr>
                <tr><td>{this.state.id}</td><td>{this.state.header}</td><td>{this.state.content}</td></tr>
            </table>
        );
    }
}