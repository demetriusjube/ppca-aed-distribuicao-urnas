import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { faClipboardList, faCrosshairs, faInfoCircle, faPlay, faSignsPost, faStop, faTruckPickup } from '@fortawesome/free-solid-svg-icons';
import * as L from 'leaflet';
import * as _ from 'lodash';
import { interval, Subscription } from 'rxjs';
import { MarkerService } from '../marker.service';
import { ErrorUtils } from '../shared/error-utils';
import { CentroDistribuicaoDTO, Depot, SimulacaoRequest, Status, Vehicle } from '../shared/model/distribuicao-urnas-model';
import { SolverService } from '../solver.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewInit {

  public faInfoCircle = faInfoCircle;
  public faStop = faStop;
  public faPlay = faPlay;
  public faCrosshairs = faCrosshairs;
  public faTruckPickup = faTruckPickup;
  public faSignsPost = faSignsPost;


  private map: L.Map;
  private layerGroup = L.layerGroup();
  private updateSubscription: Subscription;
  public solvingIsStarting = false;
  public isSolving = false;
  public statusSolucaoAtual: Status;
  public form: FormGroup;
  public formRotasSelecionadas: FormGroup;
  public centrosDistribuicao: CentroDistribuicaoDTO[] = [];
  public veiculoItinerario: Vehicle;

  constructor(private markerService: MarkerService,
    private solverService: SolverService,
    private formBuilder: FormBuilder) { }


  ngOnInit(): void {
    this.solverService.getCentrosDistribuicao().subscribe(centrosDistribuicao => {
      this.centrosDistribuicao = centrosDistribuicao;
      this.form = this.formBuilder.group({
        idCentroDistribuicao: this.getCentroDistribuicaoControl(),
        quantidadeCaminhoes38m3: this.formBuilder.control(0),
        quantidadeCaminhoes22m3: this.formBuilder.control(0),
        quantidadeCaminhoes13m3: this.formBuilder.control(0),
        quantidadeCaminhoes7_5m3: this.formBuilder.control(0),
        tipoOtimizacaoEnum: this.formBuilder.control('MENOR_DISTANCIA', Validators.required)
      })
      this.formRotasSelecionadas = this.formBuilder.group({
        rotasSelecionadas: this.formBuilder.array([])
      })
    })
  }

  public onRotasSelecionadasChange(event: any) {
    const rotasSelecionadas = (this.formRotasSelecionadas.controls['rotasSelecionadas'] as FormArray);
    const idVeiculoSelecionado = +event.target.value;
    if (event.target.checked) {
      rotasSelecionadas.push(this.formBuilder.control(idVeiculoSelecionado));
    } else {
      const index = rotasSelecionadas.controls
        .findIndex(x => x.value === idVeiculoSelecionado);
      rotasSelecionadas.removeAt(index);
    }
    this.atualizarRotasSelecionadas();
  }
  private atualizarRotasSelecionadas() {
    const veiculosSelecionados = this.formRotasSelecionadas.value.rotasSelecionadas as number[];
    this.marcarSolucaoNoMapa(this.map, this.statusSolucaoAtual, veiculosSelecionados);

  }

  private getCentroDistribuicaoControl() {
    const centroDistribuicaoControl = this.formBuilder.control(null, Validators.required);
    centroDistribuicaoControl.valueChanges.subscribe(idCentroDistribuicao => {
      this.marcarCentroDistribuicaoELocaisVotacao(idCentroDistribuicao);
    });
    return centroDistribuicaoControl;
  }

  private initMap(): void {
    this.map = this.buildMap();
    const tiles = this.buildTiles();
    tiles.addTo(this.map);
    this.layerGroup.addTo(this.map);
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
      this.marcarSolucaoNoMapa(this.map, statusSolucao);
      this.statusSolucaoAtual = statusSolucao;
      this.updateSolvingStatus(this.statusSolucaoAtual.isSolving);
    })
  }

  private marcarSolucaoNoMapa(map: L.Map, status: Status, idsVehicles?: number[]): void {
    this.markerService.marcarSolucaoNoMapa(this.map, status, idsVehicles);
  }

  private marcarCentroDistribuicaoELocaisVotacao(idCentroDistribuicao: number): void {
    this.solverService.getCentroDistribuicaoELocaisVotacao(idCentroDistribuicao).subscribe(depotCustomers => {
      this.markerService.marcarCentroDistribuicaoELocaisVotacao(this.layerGroup, depotCustomers);
    });
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
    this.form.updateValueAndValidity();
    if (!this.form.valid) {
      ErrorUtils.displayError("Os dados estão inválidos para a simulação!");
      return;
    }

    const simulacaoRequest = this.form.value as SimulacaoRequest;
    this.updateSolvingStatus(true);
    this.solvingIsStarting = true;
    this.solverService.startSolving(simulacaoRequest).subscribe({
      next: () => {
        this.solvingIsStarting = false;
        this.atualizarMapa();
        this.updateSubscription = interval(10000).subscribe((val) => {
          this.atualizarMapa();
        });
      },
      error: (err) => {
        ErrorUtils.displayError(err);
      }
    });
  }

  public showRouteCheckbox() {
    return this.isSolving === false && this.statusSolucaoAtual && this.statusSolucaoAtual.solution && this.statusSolucaoAtual.solution.vehicleList && this.statusSolucaoAtual.solution.vehicleList.length > 0;
  }

  public stopSolving(): void {
    this.solverService.stopSolving().subscribe({
      next: () => {
        this.updateSolvingStatus(false);
        const rotasSelecionadasArray = this.formRotasSelecionadas.get('rotasSelecionadas') as FormArray;
        rotasSelecionadasArray.clear();
        this.statusSolucaoAtual.solution.vehicleList.forEach(vehicle => {
          rotasSelecionadasArray.push(this.formBuilder.control(vehicle.id))
        })
      },
      error: (err) => {
        ErrorUtils.displayError(err);
      }
    });

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

  public abrirItinerario(content, vehicle): void {

  }

}