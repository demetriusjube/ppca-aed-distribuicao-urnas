import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as L from 'leaflet';
import * as _ from 'lodash';
import { interval, Subscription } from 'rxjs';
import { MarkerService } from '../marker.service';
import { CentroDistribuicaoDTO, Depot, SimulacaoRequest, Status, Vehicle } from '../shared/model/distribuicao-urnas-model';
import { SolverService } from '../solver.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit {
  private map: L.Map;
  private updateSubscription: Subscription;
  public isSolving = false;
  public statusSolucaoAtual: Status;
  public form: FormGroup;
  public centrosDistribuicao: CentroDistribuicaoDTO[] = [];

  constructor(private markerService: MarkerService,
    private solverService: SolverService,
    private formBuilder: FormBuilder) { }


  ngOnInit(): void {
    this.solverService.getCentrosDistribuicao().subscribe(centrosDistribuicao => {
      this.centrosDistribuicao = centrosDistribuicao;
      this.form = this.formBuilder.group({
        idCentroDistribuicao: this.formBuilder.control(null, Validators.required),
        quantidadeCaminhoes38m3: this.formBuilder.control(null),
        quantidadeCaminhoes22m3: this.formBuilder.control(null),
        quantidadeCaminhoes13m3: this.formBuilder.control(null),
        quantidadeCaminhoes7_5m3: this.formBuilder.control(null),
        tipoOtimizacaoEnum: this.formBuilder.control(null)
      })
    })
  }

  private initMap(): void {
    this.map = this.buildMap();
    const tiles = this.buildTiles();
    tiles.addTo(this.map);
    // this.atualizarMapa();
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
    const simulacaoRequest = this.form.value as SimulacaoRequest;
    this.solverService.startSolving(simulacaoRequest);
    this.updateSubscription = interval(12000).subscribe((val) => {
      this.atualizarMapa();
    });
  }

  public stopSolving(): void {
    this.solverService.stopSolving();

  }

  public getDistanciaFormatada(): string {
    if (!_.isNil(this.statusSolucaoAtual) && !_.isNil(this.statusSolucaoAtual.solution) && !_.isNil(this.statusSolucaoAtual.solution.distanceMeters)) {
      const distanceInMeters = this.statusSolucaoAtual.solution.distanceMeters;
      return this.getDistanciaEmMetros(distanceInMeters)
    }
    return '0Km'
  }

  public getDistanciaEmMetros(distanceInMeters: number): string {
    if (distanceInMeters) {
      return `${Math.floor(distanceInMeters / 1000)}km ${distanceInMeters % 1000}m`;
    }
    return '0Km';
  }

  public getIdCrossHairDepot(depot: Depot): string {
    return 'crosshairs-depot-' + depot.id;
  }

  public getIdCrossHairVehicle(vehicle: Vehicle): string {
    return 'crosshairs-vehicle-' + vehicle.id;
  }


  public getDepotStyle(depot: Depot): string {
    return `background-color: ${this.markerService.getDepotColorById(depot.id)}; display: inline-block; width: 1rem; height: 1rem; text-align: center`
  }

  public getVehicleStyle(vehicle: Vehicle): string {
    return `background-color: ${this.markerService.getVehicleColorById(vehicle.id)}; display: inline-block; width: 1rem; height: 1rem; text-align: center`
  }

  public getVehicleTitle(vehicle: Vehicle): string {
    return `Carga: ${vehicle.totalDemand}<br/>Capacity: ${vehicle.capacity}`;
  }

  public getVehicleProgressBarStyle(vehicle: Vehicle): string {
    return `width: ${(vehicle.totalDemand / vehicle.capacity) * 100}%`;
  }

}