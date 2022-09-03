import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-defaulticon-compatibility';
import * as _ from 'lodash';
import 'leaflet-routing-machine';
import { Customer, Depot, DepotCustomers, Location, Status, VehicleRoutingSolution } from './shared/model/distribuicao-urnas-model';

@Injectable({
  providedIn: 'root'
})
export class MarkerService {

  private colorMap = new Map<number, string>();
  private vehicleColorMap = new Map<number, string>();
  private routeControls: L.Control[] = [];

  private letters = '0123456789ABCDEF';

  constructor(private http: HttpClient) { }

  public mostrarItinerarios(): void {
    if (this.routeControls && this.routeControls.length > 0) {
      this.routeControls.forEach(routeControl => {
        const options = routeControl.options as L.Routing.RoutingControlOptions;
        options.show = true;
      });
    }
  }

  public marcarCentroDistribuicaoELocaisVotacao(layerGroup: L.LayerGroup, depotCustomer: DepotCustomers): void {
    if (depotCustomer) {
      layerGroup.clearLayers();
      this.adicionaCentroDeDistribuicao(depotCustomer.depot, layerGroup);
      this.adicionaLocaisDeVotacao(depotCustomer.customerList, layerGroup);
    }
  }

  public marcarSolucaoNoMapa(map: L.Map, solution: VehicleRoutingSolution, isSolving: boolean, idsVehicles?: number[]): void {
    if (this.existeSolucaoComLocalizacoes(solution)) {
      // this.adicionaCentrosDeDistribuicao(solution, map);
      // this.adicionaLocaisDeVotacao(solution.customerList, map);
      this.adicionaRotas(solution, map, isSolving, idsVehicles);
    }
  }

  public adicionaRotas(solution: VehicleRoutingSolution, map: L.Map, isSolving: boolean, idsVehicles?: number[]) {
    this.routeControls.forEach(routeControl => {
      map.removeControl(routeControl);
    });
    solution.vehicleList.forEach(vehicle => {
      if ((_.isNil(idsVehicles) || idsVehicles.length === 0) || (!_.isNil(idsVehicles) && idsVehicles.indexOf(vehicle.id) != -1)) {
        if (!_.isNil(vehicle.customerList) && vehicle.customerList.length > 0) {
          var visits: L.Routing.Waypoint[] = [];
          vehicle.route.forEach(location => {
            visits.push(this.buildWaypointLocation(location));
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
          const format: L.Routing.FormatterOptions = {
            distanceTemplate: "",
            language: 'pt-BR'
          }

          const formatterFunction: L.Routing.Formatter = new L.Routing.Formatter(format);

          const rota = L.Routing.control({
            show: true,
            waypoints: visits,
            pointMarkerStyle: { color: vehicleColor },
            lineOptions: lineOptions,
            routeWhileDragging: false

          });


          this.routeControls.push(rota);
          rota.addTo(map);
        }
      }
    });
  }

  private buildWaypointLocation(location: Location) {
    const waypoint: L.Routing.Waypoint = {
      latLng: L.latLng(location.latitude, location.longitude),
      name: location.nome + `(${location.endereco})`
    }
    return waypoint;
  }

  private adicionaLocaisDeVotacao(customerList: Customer[], layerGroup: L.LayerGroup) {
    customerList.forEach(customer => {
      const latLong: L.LatLngExpression = [customer.location.latitude, customer.location.longitude];
      const options: L.MarkerOptions = {
        title: customer.name + ' - ' + customer.id
      };
      const popup: L.Content = this.montaPopupLocalVotacao(customer);
      const marcador = L.circleMarker(latLong, options).bindPopup(popup);
      marcador.addTo(layerGroup);
    });
  }

  private montaPopupLocalVotacao(customer: Customer): L.Content {
    const popup: L.Content = `<h5>LV ${customer.id} - ${customer.name}</h5>
            <div>${customer.location.endereco}</div>`
    return popup;
  }

  // private adicionaCentrosDeDistribuicao(solution: VehicleRoutingSolution, map: L.Map) {
  //   solution.depotList.forEach(depot => {
  //     this.adicionaCentroDeDistribuicao(depot, map);
  //   });
  // }

  private adicionaCentroDeDistribuicao(depot: Depot, layerGroup: L.LayerGroup) {
    const latLong: L.LatLngExpression = [depot.location.latitude, depot.location.longitude];
    const options: L.MarkerOptions = {
      title: depot.name + ' - ' + depot.id
    };
    const popup: L.Content = this.montaPopupCentroDistribuicao(depot);
    const marcador = L.marker(latLong, options).bindPopup(popup);
    marcador.addTo(layerGroup);
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

  private existeSolucaoComLocalizacoes(solution: VehicleRoutingSolution) {
    return !_.isNil(solution) && !_.isNil(solution.locationList) && solution.locationList.length > 0;
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
