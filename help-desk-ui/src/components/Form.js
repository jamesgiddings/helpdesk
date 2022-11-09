import React, { Component } from 'react'

class Form extends Component {
  state={
    id:"",
    title:""
  }
  render() {
    return (
      <div>
        <div className='card'> 
        <div className="card-header">HelpDesk Query
        <div className="card-body">
            <form>
            <div className="mb-4">
                <label htmlFor="name" className="form-label">
                Issue
                </label>
                <input
                type= "text"
                className="form-control" 
                name="name"
                
                />
              </div>
            </form>
          </div>
          </div>
          </div>
          </div>
     )
    }
  }

export default Form;