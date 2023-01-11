import React, {useMemo, useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import ProductService from "../services/ProductService";
import {Col, Row, Button} from "react-bootstrap";

export default function ProductOverview() {
    const service = useMemo(() => new ProductService(), []);
    const {id} = useParams();
    const [isLoading, setLoading] = useState(false);
    const [product, setProduct] = useState<ProductItem>({
        availability: null,
        description: "",
        id: "",
        image: "",
        name: "",
        price: undefined,
        stock: null
    });
    const handleClick = () => setLoading(true);

    useEffect(() => {
        if (isLoading) {
            service.buy(id, setProduct, setLoading)
        }
    }, [isLoading]);

    useEffect(() => {
        service.getOne(id, setProduct);
    }, [service]);

    // @ts-ignore
    return (
        <div className="productDetail" data-id={id}>
            <Row>
                <Col sm>Name:</Col>
                <Col sm>{product.name}</Col>
            </Row>
            <Row>
                <Col sm>Beschreibung:</Col>
                <Col sm>{product.description}</Col>
            </Row>
            <Row>
                <Col sm>Anzahl:</Col>
                <Col sm>{product.stock}</Col>
            </Row>
            <Row>
                <Col sm={5} />
                <Col sm={7}>
                    <Button
                        variant="warning"
                        disabled={isLoading || product.stock == 0}
                        onClick={!isLoading ? handleClick : undefined}
                    >
                        {isLoading ? 'Bitte warten…' : 'Kaufen'}
                    </Button>
                </Col>
            </Row>
        </div>
    );
}