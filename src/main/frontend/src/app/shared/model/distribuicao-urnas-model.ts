/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.28.785 on 2022-08-23 23:15:19.

export interface Customer {
    id: number;
    name: string;
    location: Location;
    demand: number;
}

export interface Depot {
    id: number;
    name: string;
    location: Location;
}

export interface Location {
    endereco: string;
    latitude: number;
    longitude: number;
    distanceMap: { [index: string]: number };
}

export interface Status {
    solution: VehicleRoutingSolution;
    scoreExplanation: string;
    isSolving: boolean;
}

export interface Vehicle {
    id: number;
    capacity: number;
    depot: Depot;
    customerList: Customer[];
    totalDistanceMeters: number;
    totalDemand: number;
    route: Location[];
}

export interface VehicleRoutingSolution {
    name: string;
    locationList: Location[];
    depotList: Depot[];
    vehicleList: Vehicle[];
    customerList: Customer[];
    score: HardSoftLongScore;
    bounds: Location[];
    distanceMeters: number;
}

export interface HardSoftLongScore extends AbstractScore<HardSoftLongScore> {
    hardScore: number;
    softScore: number;
}

export interface AbstractScore<Score_> extends Score<Score_>, Serializable {
}

export interface Serializable {
}

export interface Score<Score_> extends Comparable<Score_> {
    zero: boolean;
    initScore: number;
    feasible: boolean;
    solutionInitialized: boolean;
}

export interface Comparable<T> {
}
