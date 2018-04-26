import React from "react";

class Blog extends React.Component {

    constructor(props) {
        super(props);
        this.state = {id: this.props.id,header: this.props.header,content: this.props.content, url:"http://localhost:8080/posts/" + props.match.params.id};
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
            <div className="table-responsive">
            <table class="table">
                <tr><td>{this.state.header}</td></tr><tr><td>{this.state.content}</td></tr>
            </table>
            </div>
        );
    }
}

export default Blog