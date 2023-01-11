
export default class ProductService {

    private readonly url: string;

    public constructor() {
        this.url = "http://localhost:8080/shop";
    }

    public getAll(setData: (p:ProductItem[]) => void): void {
        fetch(this.url+"/products", {
            method: 'GET',
        })
        .then(response => {
            return response.json();
        })
        .then(json => {
            setData(json);
        })
        .catch(error => {
            console.error("error:", error);
        });
    }

    public getOne(id:string|undefined, setData: (p:ProductItem) => void): void {
        fetch(this.url+"/product/"+id, {
            method: 'GET',
        })
        .then(response => {
            return response.json();
        })
        .then(json => {
            setData(json);
        })
        .catch(error => {
            console.error("error:", error);
        });
    }

    public buy(id:string|undefined, setData: (p:ProductItem) => void, setLoading: (p:boolean) => void): void {
        setLoading(true);
        fetch(this.url+"/buy/"+id, {
            method: 'GET',
        })
            .then(response => {
                return response.json();
            })
            .then(json => {
                setData(json);
                setLoading(false);
            })
            .catch(error => {
                console.error("error:", error);
            });
    }
}