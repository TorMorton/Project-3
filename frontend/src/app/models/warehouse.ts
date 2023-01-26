export class Warehouse {

    #warehouseName: string;
    #location: string;
    #capacity: number = 100;
    #currentTotal: number;


    constructor (name: string, location: string, currentTotal: number) {
        this.#warehouseName = name;
        this.#location = location;
        this.#capacity = 100;
        this.#currentTotal = currentTotal;
    }

    get warehouseName(): string {
        return this.#warehouseName;
    }

    set warehouseName(name: string) {
        this.#warehouseName = name;
    }

    get location(): string {
        return this.#location;
    }

    set location(location: string) {
        this.#location = location;
    }

    get capacity(): number {
        return this.#capacity;
    }

    get currentTotal(): number {
        return this.#currentTotal;
    }

    set currentTotal(currentTotal: number) {
        this.#currentTotal = currentTotal;
    }
}
