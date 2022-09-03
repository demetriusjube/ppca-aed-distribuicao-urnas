/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.28.785 on 2022-09-03 11:38:09.

export interface CentroDistribuicaoDTO {
    id: number;
    nome: string;
    endereco: string;
    localizacao: number;
    totalDeUrnas: number;
}

export interface DistanciaDTO {
    id: number;
    menorTempoViagem: number;
    menorDistancia: number;
    origem: number;
    destino: number;
}

export interface ErrorResponse {
    httpStatus: number;
    exception: string;
    message: string;
    fieldErrors: FieldError[];
}

export interface FieldError {
    field: string;
    errorCode: string;
}

export interface LocalVotacaoDTO {
    id: number;
    numero: number;
    nome: string;
    endereco: string;
    quantidadeSecoes: number;
    zonaEleitoral: number;
    localizacao: number;
}

export interface ParametroCalculoDTO {
    id: number;
    valor: number;
    tipoParametro: TipoParametroEnum;
}

export interface PlanoRotaDTO {
    id: number;
}

export interface RestricaoSimulacaoDTO {
    id: number;
    valor: number;
    tipoRestricao: TipoRestricaoEnum;
    restricoes: number;
}

export interface SimulacaoDTO {
    id: number;
    descricao: string;
    dataHora: Date;
    tipoOtimizacao: TipoOtimizacaoEnum;
    centroDistribuicao: number;
}

export interface TREDTO {
    id: number;
    uf: string;
}

export interface VeiculoDTO {
    id: number;
    descricao: string;
    placa: string;
    capacidade: number;
}

export interface VeiculoSimulacaoDTO {
    id: number;
    urnasTransportadas: number;
    simulacao: number;
    veiculo: number;
    planoRota: number;
}

export interface ZonaEleitoralDTO {
    id: number;
    numero: number;
    nome: string;
    tre: number;
    centroDistribuicao: number;
}

export interface Customer extends Standstill {
    id: number;
    name: string;
    demand: number;
    last: boolean;
}

export interface Depot {
    id: number;
    name: string;
    location: Location;
}

export interface DepotCustomers {
    depot: Depot;
    customerList: Customer[];
}

export interface Location {
    id: number;
    nome: string;
    endereco: string;
    latitude: number;
    longitude: number;
    distanceMap: { [index: string]: RouteData };
}

export interface RouteData {
    distanceMeters: number;
    drivingTimeMilis: number;
}

export interface SimulacaoRequest {
    idCentroDistribuicao: number;
    quantidadeCaminhoes38m3: number;
    quantidadeCaminhoes22m3: number;
    quantidadeCaminhoes13m3: number;
    quantidadeCaminhoes7_5m3: number;
    tipoOtimizacaoEnum: TipoOtimizacaoEnum;
    tempoDescarregamentoMinutos: number;
    tempoMaximoAtuacaoHoras: number;
}

export interface Standstill {
    location: Location;
    nextVisit: Customer;
}

export interface Status {
    solution: VehicleRoutingSolution;
    scoreExplanation: string;
    isSolving: boolean;
}

export interface Vehicle extends Standstill {
    id: number;
    description: string;
    capacity: number;
    depot: Depot;
    tempoDescarregamentoMinutos: number;
    tempoMaximoAtuacaoHoras: number;
    tempoMaximoAtuacaoUltrapassado: boolean;
    tempoAtuacaoUltrapassadoMinutos: number;
    totalDistanceMeters: number;
    totalDemand: number;
    route: Location[];
    totalTimeMilis: number;
    futureVisits: Iterable<Customer>;
}

export interface VehicleRoutingSolution {
    name: string;
    depotList: Depot[];
    vehicleList: Vehicle[];
    customerList: Customer[];
    score: HardSoftLongScore;
    distanceMeters: number;
}

export interface Iterable<T> {
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
    solutionInitialized: boolean;
    feasible: boolean;
    initScore: number;
}

export interface Comparable<T> {
}

export type TipoOtimizacaoEnum = "MENOR_DISTANCIA" | "MENOR_TEMPO";

export type TipoParametroEnum = "VOLUME_KIT_VOTACAO" | "VOLUME_URNA_ANTES_2020" | "VELOCIDADE_MEDIA_VEICULO" | "VOLUME_URNA_APOS_2020";

export type TipoRestricaoEnum = "VALOR_MAXIMO_GASTO" | "TEMPO_MAXIMO_DISTRIBUICAO" | "QUANTIDADE_VEICULO_38_M3" | "QUANTIDADE_VEICULO_22_M3" | "QUANTIDADE_VEICULO_13_M3" | "QUANTIDADE_VEICULO_7_5_M3";
