import React, {useMemo} from 'react';
import { useParams } from "react-router-dom";
import ProductService from "../services/ProductService";
import {Col, Row} from "react-bootstrap";
import {useEffect, useState} from "react";

export default function PlayerOverview() {
    const service = useMemo(() => new ProductService(), []);
    const params: {id:string} = useParams();
    const [product, setProduct] = useState<ProductItem>({});

    useEffect(() => {
        service.getOne(params.id, setProduct);
    }, [service]);

    return (
        <div className="productDetail" data-id={params.id}>
            <Row>
                <Col>Name:</Col>
                <Col>{product.name}</Col>
            </Row>
            <Row>
                <Col>Beschreibung:</Col>
                <Col>{product.description}</Col>
            </Row>
        </div>
    );
}