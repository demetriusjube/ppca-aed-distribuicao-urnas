import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CentroDistribuicaoDTO, DepotCustomers, SimulacaoDTO, SimulacaoRequest, Status, VehicleRoutingSolution } from './shared/model/distribuicao-urnas-model';

@Injectable({
  providedIn: 'root'
})
export class SolverService {

  constructor(private http: HttpClient) { }

  public status(): Observable<Status> {
    return this.http.get<Status>("/vrp/status");
  }

  public startSolving(simulacaoRequest: SimulacaoRequest): Observable<void> {
    return this.http.post<void>("/vrp/solve", simulacaoRequest);
  }

  public stopSolving(): Observable<void> {
    return this.http.post<void>("/vrp/stopSolving", {});
  }

  public getCentrosDistribuicao(): Observable<CentroDistribuicaoDTO[]> {
    return this.http.get<CentroDistribuicaoDTO[]>("/vrp/centros-distribuicao", {});
  }

  public getSimulacoes(): Observable<SimulacaoDTO[]> {
    return this.http.get<SimulacaoDTO[]>("/vrp/simulacoes", {});
  }

  public getSolucaoSimulacao(idSimulacao: number): Observable<Status> {
    return this.http.get<Status>(`/vrp/simulacao/${idSimulacao}`, {});
  }


  public getCentroDistribuicaoELocaisVotacao(idCentroDistribuicao: number): Observable<DepotCustomers> {
    return this.http.get<DepotCustomers>(`/vrp/depot-customers/${idCentroDistribuicao}`, {});
  }

}
