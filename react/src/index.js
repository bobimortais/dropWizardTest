import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

class BillRow extends React.Component
 {
  render() 
  {
    return (
      <tr>
        <td>{this.props.bill.id}</td>
        <td>{this.props.bill.companyId}</td>
        <td>{this.props.bill.type}</td>
        <td>{this.props.bill.description}</td>
        <td>{this.props.bill.value}</td>
      </tr>
    );
  }
}

class BillTable extends React.Component 
{  
  constructor(props) 
  {
    super(props);
  
    this.state = 
    {
      posts: []
    };

    this.handleList();
  }
  render() 
  {
    return (
      <table className="table table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Company</th>
            <th>Type</th>
            <th>Description</th>
            <th>Value</th>
          </tr>
        </thead>
        <tbody>{this.state.posts}</tbody>
      </table>
    );
  }

  async handleList()
  {
    var post = [];
    axios.get("http://localhost:8090/getAllBills")
      .then( res => {
                    var posts = [];
                    posts = res.data;

                    posts.forEach((bill) => 
                    {
                      console.log(bill);
                      post.push(<BillRow bill={bill} />);
                    });

                    this.setState({
                      posts: post
                    });
          });

  }
}


class SearchBar extends React.Component {
  constructor(props) 
  {
    super(props);
    this.handleFilterTextInputChange = this.handleFilterTextInputChange.bind(this);
  }
  
  handleFilterTextInputChange(e) 
  {
    this.props.onFilterTextInput(e.target.value);
  }

  render() 
  {
    return (
      <form>
        <input
          className="form-control"
          type="text"
          placeholder="Search..."
          value={this.props.filterText}
          onChange={this.handleFilterTextInputChange}
        />
      </form>
    );
  }
}

class FilterableBillTable extends React.Component {
  constructor(props) 
  {
    super(props);
  
    this.state = {
      filterText: ''
    };
    
    this.handleFilterTextInput = this.handleFilterTextInput.bind(this);
  }

  handleFilterTextInput(filterText)
  {
    this.setState({
      filterText: filterText
    });
  }
  
  render() 
  {
    return (
      <div>
        <h1>Bills List</h1>
        <SearchBar
          filterText={this.state.filterText}
          onFilterTextInput={this.handleFilterTextInput}
        />
        <BillTable
          filterText={this.state.filterText}
        />
      </div>
    );
  }
}

ReactDOM.render(
  <FilterableBillTable/>,
  document.getElementById('container')
);
