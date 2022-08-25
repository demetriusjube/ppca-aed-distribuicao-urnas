import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CentroDistribuicaoDTO, SimulacaoRequest, Status } from './shared/model/distribuicao-urnas-model';

@Injectable({
  providedIn: 'root'
})
export class SolverService {

  constructor(private http: HttpClient) { }

  public status(): Observable<Status> {
    return this.http.get<Status>("/vrp/status");
  }

  public startSolving(simulacaoRequest: SimulacaoRequest): void {
    this.http.post("/vrp/solve", simulacaoRequest).subscribe(() => { })
  }

  public stopSolving(): void {
    this.http.post("/vrp/stopSolving", {}).subscribe(() => { })
  }

  public getCentrosDistribuicao(): Observable<CentroDistribuicaoDTO[]> {
    return this.http.get<CentroDistribuicaoDTO[]>("/vrp/centros-distribuicao", {});
  }

}
