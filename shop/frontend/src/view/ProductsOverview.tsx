import React, {useMemo} from 'react';
import {Table} from "react-bootstrap";
import ProductService from "../services/ProductService";
import {useEffect, useState} from "react";

export default function ProductsOverview() {
    const service = useMemo(() => new ProductService(), []);
    const [overviewData, setOverviewData] = useState<ProductItem[]>([]);

    useEffect(() => {
        service.getAll(setOverviewData);
    }, [service]);

    return (
        <div className="productOverview">
            <Table striped bordered size="sm">
                <thead>
                <tr>
                    <th>Name</th>
                    <th></th>
                    <th>Preis</th>
                </tr>
                </thead>
                <tbody>
                {
                    overviewData.map(p =>
                        <tr key={p.id}>
                            <td><a href={`/product/${p.id}`}>{p.name}</a></td>
                            <td>{p.image}</td>
                            <td className="blockContent">{p.price.number}&nbsp;{p.price.currency.currencyCode}</td>
                        </tr>
                    )
                }
                </tbody>
            </Table>
        </div>
    );
}