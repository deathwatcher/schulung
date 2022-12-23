
export default class ProductService {

    private readonly url: string;

    public constructor() {
        this.url = "http://shop-backend:8080";
    }

    public getAll(setData: (p:any) => void): void {
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

    public getOne(id:string, setData: (p:any) => void): void {
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

}