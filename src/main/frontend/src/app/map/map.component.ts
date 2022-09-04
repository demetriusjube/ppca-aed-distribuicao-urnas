import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { faClipboardList, faCrosshairs, faDownload, faInfoCircle, faListCheck, faPlay, faRefresh, faSignsPost, faStop, faTasks, faTruckPickup } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as L from 'leaflet';
import * as _ from 'lodash';
import { interval, Subscription } from 'rxjs';
import { MarkerService } from '../marker.service';
import { ErrorUtils } from '../shared/error-utils';
import { CentroDistribuicaoDTO, Depot, SimulacaoDTO, SimulacaoRequest, Status, Vehicle, VehicleRoutingSolution } from '../shared/model/distribuicao-urnas-model';
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
  public faListCheck = faListCheck;
  public faDownload = faDownload;
  public faRefresh = faRefresh;


  private map: L.Map;
  private layerGroup = L.layerGroup();
  private updateSubscription: Subscription;
  public solvingIsStarting = false;
  public isSolving = false;
  public simulationIsLoading = false;
  public statusSolucaoAtual: Status;
  public form: FormGroup;
  public formRotasSelecionadas: FormGroup;
  public formSimulacoes: FormGroup;
  public centrosDistribuicao: CentroDistribuicaoDTO[] = [];
  public veiculoItinerario: Vehicle;
  public simulacoes: SimulacaoDTO[];


  constructor(private markerService: MarkerService,
    private solverService: SolverService,
    private formBuilder: FormBuilder,
    private modalService: NgbModal) { }


  ngOnInit(): void {
    this.solverService.getCentrosDistribuicao().subscribe(centrosDistribuicao => {
      this.centrosDistribuicao = centrosDistribuicao;
      this.form = this.formBuilder.group({
        idCentroDistribuicao: this.getCentroDistribuicaoControl(),
        quantidadeCaminhoes38m3: this.formBuilder.control(0),
        quantidadeCaminhoes22m3: this.formBuilder.control(0),
        quantidadeCaminhoes13m3: this.formBuilder.control(0),
        quantidadeCaminhoes7_5m3: this.formBuilder.control(0),
        tempoDescarregamentoMinutos: this.formBuilder.control(30, Validators.required),
        tempoMaximoAtuacaoHoras: this.formBuilder.control(10, Validators.required),
        tipoOtimizacaoEnum: this.formBuilder.control('MENOR_DISTANCIA', Validators.required)
      });
      this.formRotasSelecionadas = this.formBuilder.group({
        rotasSelecionadas: this.formBuilder.array([])
      });
      this.solverService.getSimulacoes().subscribe(simulacoes => {
        this.formSimulacoes = this.formBuilder.group({
          idSimulacao: this.formBuilder.control(null, Validators.required)
        });
        this.simulacoes = simulacoes;
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
    this.marcarSolucaoNoMapa(this.map, this.statusSolucaoAtual.solution, this.statusSolucaoAtual.isSolving, veiculosSelecionados);

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

  public atualizarMapa() {
    this.solverService.status().subscribe(statusSolucao => {
      this.atualizarStatusSolucao(statusSolucao);
    })
  }

  private atualizarStatusSolucao(statusSolucao: Status) {
    this.marcarSolucaoNoMapa(this.map, statusSolucao.solution, statusSolucao.isSolving);
    this.statusSolucaoAtual = statusSolucao;
    this.updateSolvingStatus(this.statusSolucaoAtual.isSolving);
  }

  private marcarSolucaoNoMapa(map: L.Map, solution: VehicleRoutingSolution, isSolving: boolean, idsVehicles?: number[]): void {
    this.markerService.marcarSolucaoNoMapa(map, solution, isSolving, idsVehicles);
  }

  private marcarCentroDistribuicaoELocaisVotacao(idCentroDistribuicao: number): void {
    this.solverService.getCentroDistribuicaoELocaisVotacao(idCentroDistribuicao).subscribe(depotCustomers => {
      this.markerService.marcarCentroDistribuicaoELocaisVotacao(this.layerGroup, depotCustomers);
    });
  }

  private updateSolvingStatus(solvingStatus: boolean): void {
    this.isSolving = solvingStatus;
    if (!solvingStatus && this.updateSubscription) {
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
        this.updateSubscription = interval(30000).subscribe((val) => {
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
        this.carregarFormRotas(this.statusSolucaoAtual);
        this.markerService.mostrarItinerarios();
      },
      error: (err) => {
        ErrorUtils.displayError(err);
      }
    });

  }

  private carregarFormRotas(statusSolucaoAtual: Status) {
    const rotasSelecionadasArray = this.formRotasSelecionadas.get('rotasSelecionadas') as FormArray;
    rotasSelecionadasArray.clear();
    statusSolucaoAtual.solution.vehicleList.forEach(vehicle => {
      rotasSelecionadasArray.push(this.formBuilder.control(vehicle.id));
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

  public getTempoEmHorasEMinutos(timeInMilis: number): string {
    if (timeInMilis) {

    }
    return '0h';
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

  public getVehicleProgressBarClass(vehicle: Vehicle): string {
    let color = 'bg-primary';
    if (vehicle.totalDemand > vehicle.capacity) {
      color = 'bg-danger';
    }
    return `progress-bar ${color}`
  }
  public getVehicleProgressBarStyle(vehicle: Vehicle): string {
    return `width: ${(vehicle.totalDemand / vehicle.capacity) * 100}%;`;
  }

  public abrirItinerario(content, vehicle: Vehicle): void {
    this.veiculoItinerario = vehicle;
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
    }, (reason) => {
    });

  }

  public loadSimulacao(): void {
    const idSimulacaoSelecionada = this.formSimulacoes.get('idSimulacao').value;
    this.simulationIsLoading = true;
    this.solverService.getSolucaoSimulacao(idSimulacaoSelecionada).subscribe(status => {
      this.simulationIsLoading = false;
      this.atualizarStatusSolucao(status);
      this.carregarFormRotas(status);
      const centroDistribuicaoId = status.solution.depotList[0].id;
      this.marcarCentroDistribuicaoELocaisVotacao(centroDistribuicaoId);
    })

  }

}