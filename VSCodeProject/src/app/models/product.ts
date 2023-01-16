export class Product {
    // properties
    #manufacturer: string;
    #model: string;
    #cost: number;


    // constructor
    constructor (manufacturer: string, model: string, price: number) {
        this.#manufacturer = manufacturer;
        this.#model = model;
        this.#cost = price;
    }

    // getters and setters
    get manufacturer(): string {
        return this.#manufacturer;
    }

    set manufacturer(manufacturer: string) {
        this.#manufacturer = manufacturer;
    }

    get model(): string {
        return this.#model;
    }

    set model(model: string) {
        this.#model = model;
    }

    get cost(): number {
        return this.#cost;
    }

    set cost(price: number) {
        this.#cost = price;
    }
}