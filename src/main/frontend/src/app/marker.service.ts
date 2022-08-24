import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as L from 'leaflet';
import * as _ from 'lodash';
import { Customer, Depot, Status, VehicleRoutingSolution } from './shared/model/distribuicao-urnas-model';

@Injectable({
  providedIn: 'root'
})
export class MarkerService {

  private colorMap = new Map<number, string>();

  private letters = '0123456789ABCDEF';

  constructor(private http: HttpClient) { }

  public marcarSolucaoNoMapa(map: L.Map): void {
    this.http.get<Status>("/vrp/status").subscribe(statusSolucao => {
      if (this.existeSolucaoComLocalizacoes(statusSolucao)) {
        const solution = statusSolucao.solution;
        this.adicionaCentrosDeDistribuicao(solution, map);
        this.adicionaLocaisDeVotacao(solution, map);
      }
    })
  }

  private adicionaLocaisDeVotacao(solution: VehicleRoutingSolution, map: L.Map) {
    solution.customerList.forEach(customer => {
      const latLong: L.LatLngExpression = [customer.location.latitude, customer.location.longitude];
      const options: L.MarkerOptions = {
        title: customer.name + ' - ' + customer.id
      };
      const popup: L.Content = this.montaPopupLocalVotacao(customer);
      const marcador = L.circleMarker(latLong, options).bindPopup(popup);
      marcador.addTo(map);
    });
  }

  private montaPopupLocalVotacao(customer: Customer): L.Content {
    return this.montaPopup(customer.id, customer.name, customer.location.endereco);;
  }

  private adicionaCentrosDeDistribuicao(solution: VehicleRoutingSolution, map: L.Map) {
    solution.depotList.forEach(depot => {
      const latLong: L.LatLngExpression = [depot.location.latitude, depot.location.longitude];
      const options: L.MarkerOptions = {
        title: depot.name + ' - ' + depot.id
      };
      const popup: L.Content = this.montaPopupCentroDistribuicao(depot);
      const marcador = L.marker(latLong, options).bindPopup(popup);
      marcador.addTo(map);
    });
  }

  private montaPopupCentroDistribuicao(depot: Depot) {
    const id = depot.id;
    const name = depot.name;
    const endereco = depot.location.endereco;
    return this.montaPopup(id, name, endereco);
  }

  private montaPopup(id: number, name: string, endereco: string) {
    const color = this.getColorById(id);
    const popup: L.Content = `<h5>CD ${id} - ${name}</h5>
            <div>${endereco}</div>
            <ul class="list-unstyled">
            <li><span style="background-color: ${color}; display: inline-block; width: 12px; height: 12px; text-align: center">
            </span> ${color}</li>
            </ul>`;
    return popup;
  }

  private existeSolucaoComLocalizacoes(statusSolucao: Status) {
    return !_.isNil(statusSolucao) && !_.isNil(statusSolucao.solution) && !_.isNil(statusSolucao.solution.locationList) && statusSolucao.solution.locationList.length > 0;
  }

  private getColorById(id: number) {
    if (!this.colorMap.has(id)) {
      this.colorMap.set(id, this.getRandomColor());
    }
    return this.colorMap.get(id);
  }

  private getRandomColor(): string {
    let color = '#'; // <-----------
    for (var i = 0; i < 6; i++) {
      color += this.letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }
}
