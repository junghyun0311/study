import React from 'react';
import { Redirect, Route } from 'react-router-dom';

function PrivateRoute ({ component: Component,render, ...rest }) {
    console.log(localStorage.getItem('users'));
    return (
        <Route
            {...rest}
            render = {props => 
                localStorage.getItem('users')?(
                    // <Component {...props} />
                    render(props)
                ) : ( 
                    <Redirect to={{
                                    pathname: '/login-page', 
                                    state: {from: props.location}
                                  }}
                    />
                )
            }
        />
    )
}

export default PrivateRoute;