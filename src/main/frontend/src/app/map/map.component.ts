import { AfterViewInit, Component } from '@angular/core';
import * as L from 'leaflet';
import { interval, Observable, Subscription } from 'rxjs';
import { MarkerService } from '../marker.service';
import { Depot, Status } from '../shared/model/distribuicao-urnas-model';
import { SolverService } from '../solver.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
  private map: L.Map;
  private updateSubscription: Subscription;
  public isSolving = false;
  public statusSolucaoAtual: Status;

  constructor(private markerService: MarkerService,
    private solverService: SolverService) { }

  private initMap(): void {
    this.map = this.buildMap();
    const tiles = this.buildTiles();
    tiles.addTo(this.map);
    this.atualizarMapa();
  }


  private buildMap(): L.Map {
    return L.map('map', {
      center: [-15.782356113909074, -47.878821256477806],
      zoom: 10
    });
  }

  private buildTiles() {
    return L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 10,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });
  }

  private atualizarMapa() {
    this.solverService.status().subscribe(statusSolucao => {
      this.markerService.marcarSolucaoNoMapa(this.map, statusSolucao);
      this.statusSolucaoAtual = statusSolucao;
      this.updateSolvingStatus(this.statusSolucaoAtual.isSolving);
    })
  }

  private updateSolvingStatus(solvingStatus: boolean): void {
    this.isSolving = solvingStatus;
    if (!solvingStatus) {
      this.updateSubscription.unsubscribe();
    }
  }

  ngAfterViewInit(): void {
    this.initMap();
  }

  public startSolving(): void {
    this.solverService.startSolving();
    this.updateSubscription = interval(3000).subscribe((val) => {
      this.atualizarMapa();
    });
  }

  public stopSolving(): void {
    this.solverService.stopSolving();

  }

  public getDistanciaFormatada(): string {
    if (!_.isNil(this.statusSolucaoAtual) && !_.isNil(this.statusSolucaoAtual.solution) && !_.isNil(this.statusSolucaoAtual.solution.distanceMeters)) {
      const distanceInMeters = this.statusSolucaoAtual.solution.distanceMeters;
      return `${Math.floor(distanceInMeters / 1000)}km ${distanceInMeters % 1000}m`
    }
    return '0Km'
  }

  public getIdCrossHairDepot(depot: Depot): string {
    return 'crosshairs-' + depot.id;
  }

  public getDepotStyle(depot: Depot): string {
    return `background-color: ${this.markerService.getDepotColorById(depot.id)}; display: inline-block; width: 1rem; height: 1rem; text-align: center`
  }
}