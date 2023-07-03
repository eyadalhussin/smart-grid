export class Powerplant{
    constructor(
        public currentPowerGeneration: number,
        public fuelType: string,
        public id: number,
        public name: string,
        public numberOfGenerators: number
      ) {}
}