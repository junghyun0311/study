/*eslint-disable*/
import React from "react";

// reactstrap components
import { Container } from "reactstrap";

// core components

function DefaultFooter() {
  return (
    <>
      <footer className="footer footer-default">
        <Container>
          <nav>
            <ul>
              {/* <li>
                <a
                  href="https://www.creative-tim.com?ref=nukr-default-footer"
                  target="_blank"
                >
                  Creative Tim
                </a>
              </li>
              <li>
                <a
                  href="http://presentation.creative-tim.com?ref=nukr-default-footer"
                  target="_blank"
                >
                  About Us
                </a>
              </li> */}
              <li>
                <a
                  href="http://naver.com"
                  target="_blank"
                >
                  Blog
                </a>
              </li>
            </ul>
          </nav>
          <div className="copyright" id="copyright">
            © {new Date().getFullYear()}, 개발 진행중 
            {/* <a
              href="https://www.creative-tim.com?ref=nukr-default-footer"
              target="_blank"
            > */}
               -Junghyun
            {/* </a> */}
          </div>
        </Container>
      </footer>
    </>
  );
}

export default DefaultFooter;
