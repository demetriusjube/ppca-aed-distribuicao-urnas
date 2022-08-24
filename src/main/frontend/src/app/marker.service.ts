import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as L from 'leaflet';
import * as _ from 'lodash';
import 'leaflet-routing-machine';
import { Customer, Depot, Location, Status, VehicleRoutingSolution } from './shared/model/distribuicao-urnas-model';

@Injectable({
  providedIn: 'root'
})
export class MarkerService {

  private colorMap = new Map<number, string>();
  private vehicleColorMap = new Map<number, string>();

  private letters = '0123456789ABCDEF';

  constructor(private http: HttpClient) { }

  public marcarSolucaoNoMapa(map: L.Map, statusSolucao: Status): void {
    if (this.existeSolucaoComLocalizacoes(statusSolucao)) {
      const solution = statusSolucao.solution;
      this.adicionaCentrosDeDistribuicao(solution, map);
      this.adicionaLocaisDeVotacao(solution, map);
      this.adicionaRotas(solution, map);
    }
  }
  public adicionaRotas(solution: VehicleRoutingSolution, map: L.Map) {
    solution.vehicleList.forEach(vehicle => {

      if (!_.isNil(vehicle.customerList) && vehicle.customerList.length > 0) {
        var visits: L.LatLng[] = [];
        visits.push(this.buildLatLongFromLocation(vehicle.depot.location));
        vehicle.customerList.forEach(customer => {
          visits.push(this.buildLatLongFromLocation(customer.location));
        });
        const vehicleColor = this.getVehicleColorById(vehicle.id);
        const lineOptions: L.Routing.LineOptions = {
          extendToWaypoints: true,
          missingRouteTolerance: 10,
          styles: [{
            color: vehicleColor,
            weight: 5
          }]
        }
        const rota = L.Routing.control({
          show: false,
          waypoints: visits,
          pointMarkerStyle: { color: vehicleColor },
          lineOptions: lineOptions,
          routeWhileDragging: false,
        });
        rota.addTo(map);
      }
    });
  }

  private buildLatLongFromLocation(location: Location) {
    return L.latLng(location.latitude, location.longitude);
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
    const popup: L.Content = `<h5>CD ${customer.id} - ${customer.name}</h5>
            <div>${customer.location.endereco}</div>`
    return popup;
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
    const color = this.getDepotColorById(id);
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

  public getVehicleColorById(id: number) {
    if (!this.vehicleColorMap.has(id)) {
      this.vehicleColorMap.set(id, this.getRandomColor());
    }
    return this.vehicleColorMap.get(id);
  }

  public getDepotColorById(id: number) {
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
