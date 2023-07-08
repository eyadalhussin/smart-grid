export class Schedule {
    constructor(
        public id: number,
        public deviceID: number,
        public smartHomeID: number,
        public powerConsumptionKWH: number,
        public scheduleState: string,
        public duration: number,
        public startTime: number,
        public endTime: number,
        public desiredPrice: number,
        public canExceedPrice: boolean,
        public shouldStopDevice: boolean
    ) { }
}