import React,{useState} from "react";
import { useHistory } from "react-router";
import { useDispatch } from 'react-redux'

import {
  Button,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Form,
  Input,
  InputGroupAddon,
  InputGroupText,
  InputGroup,
  Container,
  Col,
} from "reactstrap";

// core components
import ExamplesNavbar from "components/Navbars/ExamplesNavbar.js";
import TransparentFooter from "components/Footers/TransparentFooter.js";
import axios from "axios";

function LoginPage(props) {
  const [firstFocus, setFirstFocus] = React.useState(false);
  const [lastFocus, setLastFocus] = React.useState(false);
  let history = useHistory();
  
  const [userId, setUserId] = useState('');
  const [userPwd, setUserPwd] = useState('');

  const onChangeId = (e) => {
    // e.target에는 이벤트가 발생한 input DOM에 대한 정보를 가지고 있다.
   // console.log(e.target);
    // 이벤트가 발생한 DOM의 값 가져오기
    //console.log(e.target.value);
    setUserId(e.target.value);
  }

  const onChangePwd = (e) => {
    // e.target에는 이벤트가 발생한 input DOM에 대한 정보를 가지고 있다.
    //console.log(e.target);
    // 이벤트가 발생한 DOM의 값 가져오기
    //console.log(e.target.value);
    setUserPwd(e.target.value);
  }
  //Login Api만들기
  function userlogin(dataTosubmit) {
    // 서버에서 받은 data를 request에 저장
   
    let form = new FormData();
    form.append("userId", userId);
    form.append("userPwd", userPwd);

    axios.post('/login/postLogin', form) .then(response => {  console.log(response.data)  })
     .catch(error => { console.log('error : ',error.response) });


     
    //return axios.post('login/postLogin',body);
    // dispatch(loginUser(body))
    //   		// 로그인되면 /(index페이지)로 이동
    //         .then(response => {
    //             if (response.payload.loginSuccess) {
    //                 props.history.push('/')
    //             } else {
    //                 alert('Error')
    //             }
    //         })
}

  // function loginUser(body) {
  //   // 서버에서 받은 data를 request에 저장
  //   const request = axios.get('/login/postLogin', body)
  //       .then(response => 
  //           response.data);
  //   return {
  //       type: "LOGIN_USER",
  //       payload: request
  //   }
  // }

  function guestlogin() {
    alert('게스트 로그인');
    localStorage.setItem("users", '게스트');
    history.push("/");
  }

  React.useEffect(() => {
    document.body.classList.add("login-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;
    return function cleanup() {
      document.body.classList.remove("login-page");
      document.body.classList.remove("sidebar-collapse");
    };
  }, []);
  return (
    <>
      {/* <ExamplesNavbar /> */}
      <div className="page-header clear-filter" filter-color="blue">
        <div
          className="page-header-image"
          style={{
            backgroundImage:
              "url(" + require("assets/img/login.jpg").default + ")",
          }}
        ></div>
        <div className="content">
          <Container>
            <Col className="ml-auto mr-auto" md="4">
              <Card className="card-login card-plain">
                <Form action="" className="form" method="">
                  {/* <CardHeader className="text-center">
                    <div className="logo-container">
                      <img
                        alt="..."
                        src={require("assets/img/now-logo.png").default}
                      ></img>
                    </div>
                  </CardHeader> */}
                  <CardBody>
                    <InputGroup
                      className={
                        "no-border input-lg" +
                        (firstFocus ? " input-group-focus" : "")
                      }
                    >
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons users_single-02"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="ID 입력"
                        type="text"
                        onChange={onChangeId}
                        onFocus={() => setFirstFocus(true)}
                        onBlur={() => setFirstFocus(false)}
                      ></Input>
                    </InputGroup>
                    <InputGroup
                      className={
                        "no-border input-lg" +
                        (lastFocus ? " input-group-focus" : "")
                      }
                    >
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="now-ui-icons ui-1_lock-circle-open"></i>
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        placeholder="PW 입력"
                        type="text"
                        onChange={onChangePwd}
                        onFocus={() => setLastFocus(true)}
                        onBlur={() => setLastFocus(false)}
                      ></Input>
                    </InputGroup>
                  </CardBody>
                  <CardFooter className="text-center">
                    <Button
                      block
                      className="btn-round"
                      color="info"
                      href="#pablo"
                      onClick={userlogin}
                      size="lg"
                    >
                      Login
                    </Button>
                    <Button
                      block
                      className="btn-round"
                      color="info"
                      href="#pablo"
                      onClick={guestlogin}
                      size="lg"
                    >
                      게스트접근
                    </Button>
                    {/* <div className="pull-left">
                      <h6>
                        <a
                          className="link"
                          href="#pablo"
                          onClick={(e) => e.preventDefault()}
                        >
                          Create Account
                        </a>
                      </h6>
                    </div>
                    <div className="pull-right">
                      <h6>
                        <a
                          className="link"
                          href="#pablo"
                          onClick={(e) => e.preventDefault()}
                        >
                          Need Help?
                        </a>
                      </h6>
                    </div> */}
                  </CardFooter>
                </Form>
              </Card>
            </Col>
          </Container>
        </div>
        {/* <TransparentFooter /> */}
      </div>
    </>
  );
}

export default LoginPage;
