import React from 'react';
import './App.css';
import { Routes, Route, Link} from "react-router-dom";
import HomeOverview from "./view/HomeOverview";
import ProductsOverview from "./view/ProductsOverview";
import {Col, Container, Row} from "react-bootstrap";
import ProductView from "./view/ProductView";

function App() {
  return (
  <Container fluid>
      <Row>
          <Col>
              <header>&nbsp;</header>
          </Col>
      </Row>
      <Row>
          <Col sm={1}>
              <nav>
                  <Link to="/">
                      Home
                  </Link>
                  <Link to="/products">
                      Produkte
                  </Link>
              </nav>
          </Col>
          <Col sm={5}>
              <main>
                  <Routes>
                      {/* If the current URL is /about, this route is rendered while the rest are ignored */}
                      {/* Note how these two routes are ordered. The more specific path="/contact/:id" comes before path="/contact" so that route will render when viewing an individual contact */}
                      <Route path="/product/:id" element={<ProductView />} />
                      <Route path="/products" element={<ProductsOverview />} />
                      <Route path="/" element={<HomeOverview />} />
                  </Routes>
              </main>
          </Col>
      </Row>
  </Container>
  );
}

export default App;
