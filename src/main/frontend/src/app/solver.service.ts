import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Status } from './shared/model/distribuicao-urnas-model';

@Injectable({
  providedIn: 'root'
})
export class SolverService {

  constructor(private http: HttpClient) { }

  public status(): Observable<Status> {
    return this.http.get<Status>("/vrp/status");
  }

  public startSolving(): void {
    this.http.post("/vrp/solve", {}).subscribe(() => { })
  }

  public stopSolving(): void {
    this.http.post("/vrp/stopSolving", {}).subscribe(() => { })
  }

}
