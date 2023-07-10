export class History{
    constructor(
        public id: number,
        public generatedPower: number,
        public consumedPower: number,
        public price: number,
        public start: string,
        public end: string,
        public chargedPower: number
      ) {}
}