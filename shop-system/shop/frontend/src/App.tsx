import React from 'react';
import './App.css';
import { Switch, Route, NavLink } from "react-router-dom";
import HomeOverview from "./view/HomeOverview";
import ProductsOverview from "./view/ProductsOverview";
import {Col, Container, Row} from "react-bootstrap";
import ProductView from "./view/ProductView";

function App() {
  return (
  <Container fluid>
      <Row>
          <Col>
              <header>
                  HEADER
              </header>
          </Col>
      </Row>
      <Row>
          <Col sm={1}>
              <nav>
                  <NavLink to="/" activeClassName="active">
                      Home
                  </NavLink>
                  <NavLink to="/products" activeClassName="active">
                      Produkte
                  </NavLink>
              </nav>
          </Col>
          <Col sm={5}>
              <main>
                  <Switch>
                      {/* If the current URL is /about, this route is rendered while the rest are ignored */}
                      {/* Note how these two routes are ordered. The more specific path="/contact/:id" comes before path="/contact" so that route will render when viewing an individual contact */}
                      <Route path="/product/:id">
                          <ProductView />
                      </Route>
                      <Route path="/products">
                          <ProductsOverview />
                      </Route>
                      <Route path="/">
                          <HomeOverview />
                      </Route>
                  </Switch>

              </main>
          </Col>
      </Row>
  </Container>
  );
}

export default App;
