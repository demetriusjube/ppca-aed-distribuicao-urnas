"use strict";
(self["webpackChunkfrontend"] = self["webpackChunkfrontend"] || []).push([["main"],{

/***/ 158:
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AppRoutingModule": () => (/* binding */ AppRoutingModule)
/* harmony export */ });
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ 124);
/* harmony import */ var _map_map_component__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./map/map.component */ 2761);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ 2560);




const routes = [
    {
        path: `map`,
        component: _map_map_component__WEBPACK_IMPORTED_MODULE_0__.MapComponent,
    }
];
class AppRoutingModule {
}
AppRoutingModule.ɵfac = function AppRoutingModule_Factory(t) { return new (t || AppRoutingModule)(); };
AppRoutingModule.ɵmod = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineNgModule"]({ type: AppRoutingModule });
AppRoutingModule.ɵinj = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjector"]({ imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__.RouterModule.forRoot(routes), _angular_router__WEBPACK_IMPORTED_MODULE_2__.RouterModule] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsetNgModuleScope"](AppRoutingModule, { imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__.RouterModule], exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__.RouterModule] }); })();


/***/ }),

/***/ 5041:
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AppComponent": () => (/* binding */ AppComponent)
/* harmony export */ });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ 2560);
/* harmony import */ var _map_map_component__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./map/map.component */ 2761);


class AppComponent {
    constructor() {
        this.title = 'frontend';
    }
}
AppComponent.ɵfac = function AppComponent_Factory(t) { return new (t || AppComponent)(); };
AppComponent.ɵcmp = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineComponent"]({ type: AppComponent, selectors: [["app-root"]], decls: 1, vars: 0, template: function AppComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵelement"](0, "app-map");
    } }, dependencies: [_map_map_component__WEBPACK_IMPORTED_MODULE_0__.MapComponent], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJhcHAuY29tcG9uZW50LmNzcyJ9 */"] });


/***/ }),

/***/ 6747:
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AppModule": () => (/* binding */ AppModule)
/* harmony export */ });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/platform-browser */ 4497);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/common/http */ 8987);
/* harmony import */ var _marker_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./marker.service */ 9937);
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./app-routing.module */ 158);
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app.component */ 5041);
/* harmony import */ var _map_map_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./map/map.component */ 2761);
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ 4534);
/* harmony import */ var _solver_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./solver.service */ 1452);
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/forms */ 2508);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 2560);










class AppModule {
}
AppModule.ɵfac = function AppModule_Factory(t) { return new (t || AppModule)(); };
AppModule.ɵmod = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineNgModule"]({ type: AppModule, bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_2__.AppComponent] });
AppModule.ɵinj = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineInjector"]({ providers: [
        _marker_service__WEBPACK_IMPORTED_MODULE_0__.MarkerService,
        _solver_service__WEBPACK_IMPORTED_MODULE_4__.SolverService
    ], imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_6__.BrowserModule,
        _app_routing_module__WEBPACK_IMPORTED_MODULE_1__.AppRoutingModule,
        _angular_common_http__WEBPACK_IMPORTED_MODULE_7__.HttpClientModule,
        _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_8__.NgbModule,
        _angular_forms__WEBPACK_IMPORTED_MODULE_9__.FormsModule,
        _angular_forms__WEBPACK_IMPORTED_MODULE_9__.ReactiveFormsModule] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵsetNgModuleScope"](AppModule, { declarations: [_app_component__WEBPACK_IMPORTED_MODULE_2__.AppComponent,
        _map_map_component__WEBPACK_IMPORTED_MODULE_3__.MapComponent], imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_6__.BrowserModule,
        _app_routing_module__WEBPACK_IMPORTED_MODULE_1__.AppRoutingModule,
        _angular_common_http__WEBPACK_IMPORTED_MODULE_7__.HttpClientModule,
        _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_8__.NgbModule,
        _angular_forms__WEBPACK_IMPORTED_MODULE_9__.FormsModule,
        _angular_forms__WEBPACK_IMPORTED_MODULE_9__.ReactiveFormsModule] }); })();


/***/ }),

/***/ 2761:
/*!**************************************!*\
  !*** ./src/app/map/map.component.ts ***!
  \**************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "MapComponent": () => (/* binding */ MapComponent)
/* harmony export */ });
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ 2508);
/* harmony import */ var leaflet__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! leaflet */ 5836);
/* harmony import */ var leaflet__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(leaflet__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! lodash */ 2938);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(lodash__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! rxjs */ 8653);
/* harmony import */ var _shared_error_utils__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../shared/error-utils */ 3516);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 2560);
/* harmony import */ var _marker_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../marker.service */ 9937);
/* harmony import */ var _solver_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../solver.service */ 1452);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/common */ 4666);










function MapComponent_div_6_option_10_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "option", 43);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
} if (rf & 2) {
    const centroDistribuicao_r6 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵpropertyInterpolate"]("value", centroDistribuicao_r6.id);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate1"](" ", centroDistribuicao_r6.nome, " ");
} }
function MapComponent_div_6_button_40_Template(rf, ctx) { if (rf & 1) {
    const _r8 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "button", 44);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵlistener"]("click", function MapComponent_div_6_button_40_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵrestoreView"](_r8); const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](2); return _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵresetView"](ctx_r7.startSolving()); });
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](1, "i", 45);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](2, " Resolver ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
} }
function MapComponent_div_6_button_41_Template(rf, ctx) { if (rf & 1) {
    const _r10 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "button", 46);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵlistener"]("click", function MapComponent_div_6_button_41_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵrestoreView"](_r10); const ctx_r9 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](2); return _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵresetView"](ctx_r9.stopSolving()); });
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](1, "i", 47);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](2, " Parar resolu\u00E7\u00E3o ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
} }
function MapComponent_div_6_table_63_tr_7_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "tr")(1, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](2, "i", 53);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const depot_r12 = ctx.$implicit;
    const ctx_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵstyleMap"](ctx_r11.getDepotStyle(depot_r12));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("id", ctx_r11.getIdCrossHairDepot(depot_r12));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](depot_r12.name);
} }
function MapComponent_div_6_table_63_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "table", 48)(1, "thead")(2, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](3, "th", 49);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](4, "th", 50);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](5, "Nome");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](6, "tbody", 51);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](7, MapComponent_div_6_table_63_tr_7_Template, 5, 4, "tr", 52);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const ctx_r4 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](7);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngForOf", ctx_r4.statusSolucaoAtual.solution.depotList);
} }
function MapComponent_div_6_table_67_tr_12_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "tr")(1, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](2, "i", 53);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](5, "td")(6, "div", 57)(7, "div", 58);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](8);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](9, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](10);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const vehicle_r14 = ctx.$implicit;
    const ctx_r13 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵstyleMap"](ctx_r13.getVehicleStyle(vehicle_r14));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("id", ctx_r13.getIdCrossHairVehicle(vehicle_r14));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate1"]("Ve\u00EDculo ", vehicle_r14.id, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("title", ctx_r13.getVehicleTitle(vehicle_r14));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵstyleMap"](ctx_r13.getVehicleProgressBarStyle(vehicle_r14));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate2"](" ", vehicle_r14.totalDemand, "/", vehicle_r14.capacity, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](ctx_r13.getDistanciaEmMetros(vehicle_r14.totalDistanceMeters));
} }
function MapComponent_div_6_table_67_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "table", 48)(1, "thead")(2, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](3, "th", 49);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](4, "th", 54);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](5, "Nome");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](6, "th", 54);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](7, " Carga ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](8, "i", 55);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](9, "th", 54);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](10, "Dist\u00E2ncia");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](11, "tbody", 56);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](12, MapComponent_div_6_table_67_tr_12_Template, 11, 10, "tr", 52);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](12);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngForOf", ctx_r5.statusSolucaoAtual.solution.vehicleList);
} }
function MapComponent_div_6_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "div", 14)(1, "fieldset")(2, "legend");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](3, "Dados para simula\u00E7\u00E3o");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](4, "div", 3)(5, "div", 15)(6, "div", 16)(7, "label", 17);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](8, "Centro de Distribui\u00E7\u00E3o");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](9, "select", 18);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](10, MapComponent_div_6_option_10_Template, 2, 2, "option", 19);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](11, "div", 15)(12, "div", 20)(13, "label", 21);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](14, "Ve\u00EDculos 38m3");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](15, "input", 22);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](16, "div", 20)(17, "label", 23);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](18, "Ve\u00EDculos 22m3");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](19, "input", 24);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](20, "div", 15)(21, "div", 20)(22, "label", 25);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](23, "Ve\u00EDculos 13m3");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](24, "input", 26);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](25, "div", 20)(26, "label", 27);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](27, "Ve\u00EDculos 7,5m3");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](28, "input", 28);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](29, "div", 15)(30, "div", 16)(31, "label", 29);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](32, "Tipo de otimiza\u00E7\u00E3o");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](33, "select", 30)(34, "option", 31);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](35, " Menor dist\u00E2ncia ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](36, "option", 32);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](37, " Menor tempo ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()()()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](38, "div", 15)(39, "div", 33);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](40, MapComponent_div_6_button_40_Template, 3, 0, "button", 34);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](41, MapComponent_div_6_button_41_Template, 3, 0, "button", 35);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](42, "div", 36)(43, "h5");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](44, " Resumo da solu\u00E7\u00E3o ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](45, "a", 37);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](46, "i", 38);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](47, "table", 39)(48, "tr")(49, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](50, "Score:");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](51, "td")(52, "span", 40);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](53);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](54, "tr")(55, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](56, "Dist\u00E2ncia total percorrida:");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](57, "td")(58, "span", 41);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](59);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](60, "div", 36)(61, "h5");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](62, "Centro de Distribui\u00E7\u00E3o");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](63, MapComponent_div_6_table_63_Template, 8, 1, "table", 42);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](64, "div", 36)(65, "h5");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](66, "Ve\u00EDculos");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](67, MapComponent_div_6_table_67_Template, 13, 1, "table", 42);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("formGroup", ctx_r0.form);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](10);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngForOf", ctx_r0.centrosDistribuicao);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](30);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", !ctx_r0.isSolving);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx_r0.isSolving);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](12);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](ctx_r0.statusSolucaoAtual == null ? null : ctx_r0.statusSolucaoAtual.solution == null ? null : ctx_r0.statusSolucaoAtual.solution.score);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](ctx_r0.getDistanciaFormatada());
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx_r0.statusSolucaoAtual == null ? null : ctx_r0.statusSolucaoAtual.solution == null ? null : ctx_r0.statusSolucaoAtual.solution.depotList);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx_r0.statusSolucaoAtual == null ? null : ctx_r0.statusSolucaoAtual.solution == null ? null : ctx_r0.statusSolucaoAtual.solution.vehicleList);
} }
class MapComponent {
    constructor(markerService, solverService, formBuilder) {
        this.markerService = markerService;
        this.solverService = solverService;
        this.formBuilder = formBuilder;
        this.isSolving = false;
        this.centrosDistribuicao = [];
    }
    ngOnInit() {
        this.solverService.getCentrosDistribuicao().subscribe(centrosDistribuicao => {
            this.centrosDistribuicao = centrosDistribuicao;
            this.form = this.formBuilder.group({
                idCentroDistribuicao: this.formBuilder.control(null, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required),
                quantidadeCaminhoes38m3: this.formBuilder.control(null),
                quantidadeCaminhoes22m3: this.formBuilder.control(null),
                quantidadeCaminhoes13m3: this.formBuilder.control(null),
                quantidadeCaminhoes7_5m3: this.formBuilder.control(null),
                tipoOtimizacaoEnum: this.formBuilder.control(null, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.Validators.required)
            });
        });
    }
    initMap() {
        this.map = this.buildMap();
        const tiles = this.buildTiles();
        tiles.addTo(this.map);
        // this.atualizarMapa();
    }
    buildMap() {
        return leaflet__WEBPACK_IMPORTED_MODULE_0__.map('map', {
            center: [-15.782356113909074, -47.878821256477806],
            zoom: 10
        });
    }
    buildTiles() {
        return leaflet__WEBPACK_IMPORTED_MODULE_0__.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 18,
            minZoom: 10,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        });
    }
    atualizarMapa() {
        this.solverService.status().subscribe(statusSolucao => {
            this.markerService.marcarSolucaoNoMapa(this.map, statusSolucao);
            this.statusSolucaoAtual = statusSolucao;
            this.updateSolvingStatus(this.statusSolucaoAtual.isSolving);
        });
    }
    updateSolvingStatus(solvingStatus) {
        this.isSolving = solvingStatus;
        if (!solvingStatus) {
            this.updateSubscription.unsubscribe();
        }
    }
    ngAfterViewInit() {
        this.initMap();
    }
    startSolving() {
        this.form.updateValueAndValidity();
        if (!this.form.valid) {
            _shared_error_utils__WEBPACK_IMPORTED_MODULE_2__.ErrorUtils.displayError("Os dados estão inválidos para a simulação!");
            return;
        }
        const simulacaoRequest = this.form.value;
        this.updateSolvingStatus(true);
        this.solverService.startSolving(simulacaoRequest).subscribe({
            next: () => {
                this.updateSubscription = (0,rxjs__WEBPACK_IMPORTED_MODULE_7__.interval)(12000).subscribe((val) => {
                    this.atualizarMapa();
                });
            },
            error: (err) => {
                _shared_error_utils__WEBPACK_IMPORTED_MODULE_2__.ErrorUtils.displayError(err);
            }
        });
    }
    stopSolving() {
        this.updateSolvingStatus(false);
        this.solverService.stopSolving().subscribe({
            next: () => {
            },
            error: (err) => {
                _shared_error_utils__WEBPACK_IMPORTED_MODULE_2__.ErrorUtils.displayError(err);
            }
        });
    }
    getDistanciaFormatada() {
        if (!lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(this.statusSolucaoAtual) && !lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(this.statusSolucaoAtual.solution) && !lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(this.statusSolucaoAtual.solution.distanceMeters)) {
            const distanceInMeters = this.statusSolucaoAtual.solution.distanceMeters;
            return this.getDistanciaEmMetros(distanceInMeters);
        }
        return '0Km';
    }
    getDistanciaEmMetros(distanceInMeters) {
        if (distanceInMeters) {
            return `${Math.floor(distanceInMeters / 1000)}km ${distanceInMeters % 1000}m`;
        }
        return '0Km';
    }
    getIdCrossHairDepot(depot) {
        return 'crosshairs-depot-' + depot.id;
    }
    getIdCrossHairVehicle(vehicle) {
        return 'crosshairs-vehicle-' + vehicle.id;
    }
    getDepotStyle(depot) {
        return `background-color: ${this.markerService.getDepotColorById(depot.id)}; display: inline-block; width: 1rem; height: 1rem; text-align: center`;
    }
    getVehicleStyle(vehicle) {
        return `background-color: ${this.markerService.getVehicleColorById(vehicle.id)}; display: inline-block; width: 1rem; height: 1rem; text-align: center`;
    }
    getVehicleTitle(vehicle) {
        return `Carga: ${vehicle.totalDemand}<br/>Capacity: ${vehicle.capacity}`;
    }
    getVehicleProgressBarStyle(vehicle) {
        return `width: ${(vehicle.totalDemand / vehicle.capacity) * 100}%`;
    }
}
MapComponent.ɵfac = function MapComponent_Factory(t) { return new (t || MapComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdirectiveInject"](_marker_service__WEBPACK_IMPORTED_MODULE_3__.MarkerService), _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdirectiveInject"](_solver_service__WEBPACK_IMPORTED_MODULE_4__.SolverService), _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_6__.FormBuilder)); };
MapComponent.ɵcmp = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineComponent"]({ type: MapComponent, selectors: [["app-map"]], decls: 19, vars: 1, consts: [[1, "sticky-top", "d-flex", "justify-content-center", "align-items-center"], ["id", "notificationPanel", 2, "position", "absolute", "top", ".5rem"], [1, "container-fluid"], [1, "row"], [1, "col-7", "col-lg-8", "col-xl-9"], ["id", "map", 2, "width", "100%", "height", "100vh"], ["class", "col-5 col-lg-4 col-xl-3", "style", "height: 100vh; overflow-y: scroll;", 3, "formGroup", 4, "ngIf"], ["id", "scoreDialog", "tabindex", "-1", "role", "dialog", 1, "modal", "fade"], ["role", "dialog", 1, "modal-dialog", "modal-lg"], [1, "modal-content"], [1, "modal-header"], ["type", "button", "data-dismiss", "modal", 1, "close"], [1, "modal-body"], ["id", "scoreInfo"], [1, "col-5", "col-lg-4", "col-xl-3", 2, "height", "100vh", "overflow-y", "scroll", 3, "formGroup"], [1, "row", "pt-2", "row-cols-1"], [1, "col-12"], ["for", "idCentroDistribuicao"], ["formControlName", "idCentroDistribuicao", 1, "form-control"], [3, "value", 4, "ngFor", "ngForOf"], [1, "col-6"], ["for", "quantidadeCaminhoes38m3"], ["type", "number", "formControlName", "quantidadeCaminhoes38m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes22m3"], ["type", "number", "formControlName", "quantidadeCaminhoes22m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes13m3"], ["type", "number", "formControlName", "quantidadeCaminhoes13m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes7_5m3"], ["type", "number", "formControlName", "quantidadeCaminhoes7_5m3", "min", "0", 1, "form-control"], ["for", "tipoOtimizacaoEnum"], ["formControlName", "tipoOtimizacaoEnum", 1, "form-control"], ["value", "MENOR_DISTANCIA", "selected", ""], ["value", "MENOR_TEMPO"], [1, "col", "mb-3"], ["id", "solveButton", "type", "button", "class", "btn btn-success", 3, "click", 4, "ngIf"], ["id", "stopSolvingButton", "type", "button", "class", "btn btn-danger", 3, "click", 4, "ngIf"], [1, "col"], ["href", "#", "data-toggle", "modal", "data-target", "#scoreDialog", 1, "float-right"], [1, "fas", "fa-info-circle"], [1, "table"], ["id", "score"], ["id", "distance"], ["class", "table-sm w-100", 4, "ngIf"], [3, "value"], ["id", "solveButton", "type", "button", 1, "btn", "btn-success", 3, "click"], [1, "fas", "fa-play"], ["id", "stopSolvingButton", "type", "button", 1, "btn", "btn-danger", 3, "click"], [1, "fas", "fa-stop"], [1, "table-sm", "w-100"], [1, "col-1"], [1, "col-11"], ["id", "depots"], [4, "ngFor", "ngForOf"], [1, "fas", "fa-crosshairs", 3, "id"], [1, "col-3"], ["data-toggle", "tooltip", "data-placement", "top", "data-html", "true", "title", "Carga dos ve\u00EDculos mostrada na forma <em>Carga total / Capacidade</em>.", 1, "fas", "fa-info-circle"], ["id", "vehicles"], ["data-toggle", "tooltip-load", "data-placement", "left", "data-html", "true", 1, "progress", 3, "title"], ["role", "progressbar", 1, "progress-bar"]], template: function MapComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](2, "div", 2)(3, "div", 3)(4, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](5, "div", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](6, MapComponent_div_6_Template, 68, 8, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](7, "div", 7)(8, "div", 8)(9, "div", 9)(10, "div", 10)(11, "h5");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](12, "Score explanation");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](13, "button", 11)(14, "span");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](15, "\u00D7");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](16, "div", 12)(17, "pre", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](18, "        ");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()()()();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx.form);
    } }, dependencies: [_angular_common__WEBPACK_IMPORTED_MODULE_8__.NgForOf, _angular_common__WEBPACK_IMPORTED_MODULE_8__.NgIf, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.NgSelectOption, _angular_forms__WEBPACK_IMPORTED_MODULE_6__["ɵNgSelectMultipleOption"], _angular_forms__WEBPACK_IMPORTED_MODULE_6__.DefaultValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.NumberValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.SelectControlValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.NgControlStatus, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.NgControlStatusGroup, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.MinValidator, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.FormGroupDirective, _angular_forms__WEBPACK_IMPORTED_MODULE_6__.FormControlName], styles: [".map-container[_ngcontent-%COMP%] {\r\n    position: absolute;\r\n    top: 0;\r\n    left: 0;\r\n    right: 0;\r\n    bottom: 0;\r\n    margin: 30px;\r\n}\r\n\r\n.map-frame[_ngcontent-%COMP%] {\r\n    border: 2px solid black;\r\n    height: 100%;\r\n}\r\n\r\n#map[_ngcontent-%COMP%] {\r\n    height: 100%;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1hcC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0lBQ0ksa0JBQWtCO0lBQ2xCLE1BQU07SUFDTixPQUFPO0lBQ1AsUUFBUTtJQUNSLFNBQVM7SUFDVCxZQUFZO0FBQ2hCOztBQUVBO0lBQ0ksdUJBQXVCO0lBQ3ZCLFlBQVk7QUFDaEI7O0FBRUE7SUFDSSxZQUFZO0FBQ2hCIiwiZmlsZSI6Im1hcC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLm1hcC1jb250YWluZXIge1xyXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgdG9wOiAwO1xyXG4gICAgbGVmdDogMDtcclxuICAgIHJpZ2h0OiAwO1xyXG4gICAgYm90dG9tOiAwO1xyXG4gICAgbWFyZ2luOiAzMHB4O1xyXG59XHJcblxyXG4ubWFwLWZyYW1lIHtcclxuICAgIGJvcmRlcjogMnB4IHNvbGlkIGJsYWNrO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG59XHJcblxyXG4jbWFwIHtcclxuICAgIGhlaWdodDogMTAwJTtcclxufSJdfQ== */"] });


/***/ }),

/***/ 9937:
/*!***********************************!*\
  !*** ./src/app/marker.service.ts ***!
  \***********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "MarkerService": () => (/* binding */ MarkerService)
/* harmony export */ });
/* harmony import */ var leaflet__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! leaflet */ 5836);
/* harmony import */ var leaflet__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(leaflet__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! lodash */ 2938);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(lodash__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var leaflet_routing_machine__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! leaflet-routing-machine */ 8513);
/* harmony import */ var leaflet_routing_machine__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(leaflet_routing_machine__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ 2560);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ 8987);





class MarkerService {
    constructor(http) {
        this.http = http;
        this.colorMap = new Map();
        this.vehicleColorMap = new Map();
        this.letters = '0123456789ABCDEF';
    }
    marcarSolucaoNoMapa(map, statusSolucao) {
        if (this.existeSolucaoComLocalizacoes(statusSolucao)) {
            const solution = statusSolucao.solution;
            this.adicionaCentrosDeDistribuicao(solution, map);
            this.adicionaLocaisDeVotacao(solution, map);
            this.adicionaRotas(solution, map);
        }
    }
    adicionaRotas(solution, map) {
        solution.vehicleList.forEach(vehicle => {
            if (!lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(vehicle.customerList) && vehicle.customerList.length > 0) {
                var visits = [];
                vehicle.route.forEach(location => {
                    visits.push(this.buildLatLongFromLocation(location));
                });
                const vehicleColor = this.getVehicleColorById(vehicle.id);
                const lineOptions = {
                    extendToWaypoints: true,
                    missingRouteTolerance: 10,
                    styles: [{
                            color: vehicleColor,
                            weight: 5
                        }]
                };
                const rota = leaflet__WEBPACK_IMPORTED_MODULE_0__.Routing.control({
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
    buildLatLongFromLocation(location) {
        return leaflet__WEBPACK_IMPORTED_MODULE_0__.latLng(location.latitude, location.longitude);
    }
    adicionaLocaisDeVotacao(solution, map) {
        solution.customerList.forEach(customer => {
            const latLong = [customer.location.latitude, customer.location.longitude];
            const options = {
                title: customer.name + ' - ' + customer.id
            };
            const popup = this.montaPopupLocalVotacao(customer);
            const marcador = leaflet__WEBPACK_IMPORTED_MODULE_0__.circleMarker(latLong, options).bindPopup(popup);
            marcador.addTo(map);
        });
    }
    montaPopupLocalVotacao(customer) {
        const popup = `<h5>LV ${customer.id} - ${customer.name}</h5>
            <div>${customer.location.endereco}</div>`;
        return popup;
    }
    adicionaCentrosDeDistribuicao(solution, map) {
        solution.depotList.forEach(depot => {
            const latLong = [depot.location.latitude, depot.location.longitude];
            const options = {
                title: depot.name + ' - ' + depot.id
            };
            const popup = this.montaPopupCentroDistribuicao(depot);
            const marcador = leaflet__WEBPACK_IMPORTED_MODULE_0__.marker(latLong, options).bindPopup(popup);
            marcador.addTo(map);
        });
    }
    montaPopupCentroDistribuicao(depot) {
        const id = depot.id;
        const name = depot.name;
        const endereco = depot.location.endereco;
        return this.montaPopup(id, name, endereco);
    }
    montaPopup(id, name, endereco) {
        const color = this.getDepotColorById(id);
        const popup = `<h5>CD ${id} - ${name}</h5>
            <div>${endereco}</div>
            <ul class="list-unstyled">
            <li><span style="background-color: ${color}; display: inline-block; width: 12px; height: 12px; text-align: center">
            </span> ${color}</li>
            </ul>`;
        return popup;
    }
    existeSolucaoComLocalizacoes(statusSolucao) {
        return !lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(statusSolucao) && !lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(statusSolucao.solution) && !lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(statusSolucao.solution.locationList) && statusSolucao.solution.locationList.length > 0;
    }
    getVehicleColorById(id) {
        if (!this.vehicleColorMap.has(id)) {
            this.vehicleColorMap.set(id, this.getRandomColor());
        }
        return this.vehicleColorMap.get(id);
    }
    getDepotColorById(id) {
        if (!this.colorMap.has(id)) {
            this.colorMap.set(id, this.getRandomColor());
        }
        return this.colorMap.get(id);
    }
    getRandomColor() {
        let color = '#'; // <-----------
        for (var i = 0; i < 6; i++) {
            color += this.letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
}
MarkerService.ɵfac = function MarkerService_Factory(t) { return new (t || MarkerService)(_angular_core__WEBPACK_IMPORTED_MODULE_3__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_4__.HttpClient)); };
MarkerService.ɵprov = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_3__["ɵɵdefineInjectable"]({ token: MarkerService, factory: MarkerService.ɵfac, providedIn: 'root' });


/***/ }),

/***/ 3516:
/*!***************************************!*\
  !*** ./src/app/shared/error-utils.ts ***!
  \***************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ErrorUtils": () => (/* binding */ ErrorUtils)
/* harmony export */ });
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! lodash */ 2938);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(lodash__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ 5474);
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ 9346);
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ 2313);
/* harmony import */ var sweetalert2__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! sweetalert2 */ 598);
/* harmony import */ var sweetalert2__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(sweetalert2__WEBPACK_IMPORTED_MODULE_1__);




class ErrorUtils {
    static displayError(error, defaultMessage) {
        this.displayErrorAsObservable(error, defaultMessage).subscribe({
            next: () => {
            },
            error: () => {
            }
        });
    }
    static isJson(value) {
        return (value &&
            value.headers &&
            /\bapplication\/json\b/.test(value.headers.get('Content-Type') || ''));
    }
    static rethrowError(errorResponse) {
        if (ErrorUtils.isJson(errorResponse)) {
            return (0,rxjs__WEBPACK_IMPORTED_MODULE_2__.throwError)(() => errorResponse.error);
        }
        else {
            return (0,rxjs__WEBPACK_IMPORTED_MODULE_2__.throwError)(() => {
                const erro = {
                    message: errorResponse.status === 404
                        ? 'Recurso não encontrado'
                        : errorResponse.status === 0
                            ? 'Sessão expirada'
                            : 'Erro ' + errorResponse.status,
                    status: errorResponse.status,
                };
                return erro;
            });
        }
    }
    static displayErrorAsObservable(error, defaultMessage) {
        const err = ErrorUtils.isJson(error)
            ? error.error
            : error;
        let title = !err.message || err.message === 'No message available'
            ? defaultMessage || 'Erro'
            : err.message;
        let text = err.text && lodash__WEBPACK_IMPORTED_MODULE_0__.isString(err.text) ? `<p>${err.text}</p>` : `<p>${err}</p>`;
        return (0,rxjs__WEBPACK_IMPORTED_MODULE_3__.from)(sweetalert2__WEBPACK_IMPORTED_MODULE_1___default().fire({
            title: title,
            html: text,
            icon: 'error',
            confirmButtonText: 'OK',
        })).pipe((0,rxjs_operators__WEBPACK_IMPORTED_MODULE_4__.finalize)(() => {
            if (err.status === 0) {
                window.location.href = window.location.href;
            }
        }));
    }
}


/***/ }),

/***/ 1452:
/*!***********************************!*\
  !*** ./src/app/solver.service.ts ***!
  \***********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "SolverService": () => (/* binding */ SolverService)
/* harmony export */ });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ 2560);
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ 8987);


class SolverService {
    constructor(http) {
        this.http = http;
    }
    status() {
        return this.http.get("/vrp/status");
    }
    startSolving(simulacaoRequest) {
        return this.http.post("/vrp/solve", simulacaoRequest);
    }
    stopSolving() {
        return this.http.post("/vrp/stopSolving", {});
    }
    getCentrosDistribuicao() {
        return this.http.get("/vrp/centros-distribuicao", {});
    }
}
SolverService.ɵfac = function SolverService_Factory(t) { return new (t || SolverService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_1__.HttpClient)); };
SolverService.ɵprov = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: SolverService, factory: SolverService.ɵfac, providedIn: 'root' });


/***/ }),

/***/ 2340:
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "environment": () => (/* binding */ environment)
/* harmony export */ });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ 4431:
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser */ 4497);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ 2560);
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./app/app.module */ 6747);
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./environments/environment */ 2340);




if (_environments_environment__WEBPACK_IMPORTED_MODULE_1__.environment.production) {
    (0,_angular_core__WEBPACK_IMPORTED_MODULE_2__.enableProdMode)();
}
_angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__.platformBrowser().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_0__.AppModule)
    .catch(err => console.error(err));


/***/ })

},
/******/ __webpack_require__ => { // webpackRuntimeModules
/******/ var __webpack_exec__ = (moduleId) => (__webpack_require__(__webpack_require__.s = moduleId))
/******/ __webpack_require__.O(0, ["vendor"], () => (__webpack_exec__(4431)));
/******/ var __webpack_exports__ = __webpack_require__.O();
/******/ }
]);
//# sourceMappingURL=main.js.map