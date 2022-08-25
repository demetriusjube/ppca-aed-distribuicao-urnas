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
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ 2508);
/* harmony import */ var leaflet__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! leaflet */ 5836);
/* harmony import */ var leaflet__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(leaflet__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! lodash */ 2938);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(lodash__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! rxjs */ 8653);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/core */ 2560);
/* harmony import */ var _marker_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../marker.service */ 9937);
/* harmony import */ var _solver_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../solver.service */ 1452);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/common */ 4666);









function MapComponent_div_6_option_4_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "option", 40);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
} if (rf & 2) {
    const centroDistribuicao_r6 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵpropertyInterpolate"]("value", centroDistribuicao_r6.id);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtextInterpolate1"](" ", centroDistribuicao_r6.nome, " ");
} }
function MapComponent_div_6_button_29_Template(rf, ctx) { if (rf & 1) {
    const _r8 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "button", 41);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵlistener"]("click", function MapComponent_div_6_button_29_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵrestoreView"](_r8); const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵnextContext"](2); return _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵresetView"](ctx_r7.startSolving()); });
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](1, "i", 42);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](2, " Resolver ");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
} }
function MapComponent_div_6_button_30_Template(rf, ctx) { if (rf & 1) {
    const _r10 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "button", 43);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵlistener"]("click", function MapComponent_div_6_button_30_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵrestoreView"](_r10); const ctx_r9 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵnextContext"](2); return _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵresetView"](ctx_r9.stopSolving()); });
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](1, "i", 44);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](2, " Parar resolu\u00E7\u00E3o ");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
} }
function MapComponent_div_6_table_52_tr_7_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "tr")(1, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](2, "i", 50);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const depot_r12 = ctx.$implicit;
    const ctx_r11 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵstyleMap"](ctx_r11.getDepotStyle(depot_r12));
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("id", ctx_r11.getIdCrossHairDepot(depot_r12));
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtextInterpolate"](depot_r12.name);
} }
function MapComponent_div_6_table_52_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "table", 45)(1, "thead")(2, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](3, "th", 46);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](4, "th", 47);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](5, "Nome");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](6, "tbody", 48);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtemplate"](7, MapComponent_div_6_table_52_tr_7_Template, 5, 4, "tr", 49);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const ctx_r4 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](7);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("ngForOf", ctx_r4.statusSolucaoAtual.solution.depotList);
} }
function MapComponent_div_6_table_56_tr_12_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "tr")(1, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](2, "i", 50);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](5, "td")(6, "div", 54)(7, "div", 55);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](8);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](9, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](10);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const vehicle_r14 = ctx.$implicit;
    const ctx_r13 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵstyleMap"](ctx_r13.getVehicleStyle(vehicle_r14));
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("id", ctx_r13.getIdCrossHairVehicle(vehicle_r14));
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtextInterpolate1"]("Ve\u00EDculo ", vehicle_r14.id, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("title", ctx_r13.getVehicleTitle(vehicle_r14));
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵstyleMap"](ctx_r13.getVehicleProgressBarStyle(vehicle_r14));
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtextInterpolate2"](" ", vehicle_r14.totalDemand, "/", vehicle_r14.capacity, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtextInterpolate"](ctx_r13.getDistanciaEmMetros(vehicle_r14.totalDistanceMeters));
} }
function MapComponent_div_6_table_56_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "table", 45)(1, "thead")(2, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](3, "th", 46);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](4, "th", 51);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](5, "Nome");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](6, "th", 51);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](7, " Carga ");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](8, "i", 52);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](9, "th", 51);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](10, "Dist\u00E2ncia");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](11, "tbody", 53);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtemplate"](12, MapComponent_div_6_table_56_tr_12_Template, 11, 10, "tr", 49);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](12);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("ngForOf", ctx_r5.statusSolucaoAtual.solution.vehicleList);
} }
function MapComponent_div_6_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "div", 14)(1, "div", 15)(2, "div", 16)(3, "select", 17);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtemplate"](4, MapComponent_div_6_option_4_Template, 2, 2, "option", 18);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](5, "div", 16)(6, "label", 19);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](7, "Ve\u00EDculos 38m3");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](8, "input", 20);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](9, "div", 16)(10, "label", 21);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](11, "Ve\u00EDculos 22m3");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](12, "input", 22);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](13, "div", 16)(14, "label", 23);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](15, "Ve\u00EDculos 13m3");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](16, "input", 24);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](17, "div", 16)(18, "label", 25);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](19, "Ve\u00EDculos 7,5m3");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](20, "input", 26);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](21, "div", 16)(22, "select", 27)(23, "option", 28);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](24, " Menor dist\u00E2ncia ");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](25, "option", 29);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](26, " Menor tempo ");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](27, "div", 16)(28, "div", 30);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtemplate"](29, MapComponent_div_6_button_29_Template, 3, 0, "button", 31);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtemplate"](30, MapComponent_div_6_button_30_Template, 3, 0, "button", 32);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](31, "div", 33)(32, "h5");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](33, " Resumo da solu\u00E7\u00E3o ");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](34, "a", 34);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](35, "i", 35);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](36, "table", 36)(37, "tr")(38, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](39, "Score:");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](40, "td")(41, "span", 37);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](42);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](43, "tr")(44, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](45, "Dist\u00E2ncia total percorrida:");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](46, "td")(47, "span", 38);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](48);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](49, "div", 33)(50, "h5");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](51, "Centro de Distribui\u00E7\u00E3o");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtemplate"](52, MapComponent_div_6_table_52_Template, 8, 1, "table", 39);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](53, "div", 33)(54, "h5");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](55, "Ve\u00EDculos");
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtemplate"](56, MapComponent_div_6_table_56_Template, 13, 1, "table", 39);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()()();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("formGroup", ctx_r0.form);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("ngForOf", ctx_r0.centrosDistribuicao);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](25);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("ngIf", !ctx_r0.isSolving);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("ngIf", ctx_r0.isSolving);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](12);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtextInterpolate"](ctx_r0.statusSolucaoAtual == null ? null : ctx_r0.statusSolucaoAtual.solution == null ? null : ctx_r0.statusSolucaoAtual.solution.score);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtextInterpolate"](ctx_r0.getDistanciaFormatada());
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("ngIf", ctx_r0.statusSolucaoAtual == null ? null : ctx_r0.statusSolucaoAtual.solution == null ? null : ctx_r0.statusSolucaoAtual.solution.depotList);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("ngIf", ctx_r0.statusSolucaoAtual == null ? null : ctx_r0.statusSolucaoAtual.solution == null ? null : ctx_r0.statusSolucaoAtual.solution.vehicleList);
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
                idCentroDistribuicao: this.formBuilder.control(null, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.Validators.required),
                quantidadeCaminhoes38m3: this.formBuilder.control(null),
                quantidadeCaminhoes22m3: this.formBuilder.control(null),
                quantidadeCaminhoes13m3: this.formBuilder.control(null),
                quantidadeCaminhoes7_5m3: this.formBuilder.control(null),
                tipoOtimizacaoEnum: this.formBuilder.control(null)
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
        const simulacaoRequest = this.form.value;
        this.solverService.startSolving(simulacaoRequest);
        this.updateSubscription = (0,rxjs__WEBPACK_IMPORTED_MODULE_6__.interval)(12000).subscribe((val) => {
            this.atualizarMapa();
        });
    }
    stopSolving() {
        this.solverService.stopSolving();
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
MapComponent.ɵfac = function MapComponent_Factory(t) { return new (t || MapComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdirectiveInject"](_marker_service__WEBPACK_IMPORTED_MODULE_2__.MarkerService), _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdirectiveInject"](_solver_service__WEBPACK_IMPORTED_MODULE_3__.SolverService), _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_5__.FormBuilder)); };
MapComponent.ɵcmp = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵdefineComponent"]({ type: MapComponent, selectors: [["app-map"]], decls: 19, vars: 1, consts: [[1, "sticky-top", "d-flex", "justify-content-center", "align-items-center"], ["id", "notificationPanel", 2, "position", "absolute", "top", ".5rem"], [1, "container-fluid"], [1, "row"], [1, "col-7", "col-lg-8", "col-xl-9"], ["id", "map", 2, "width", "100%", "height", "100vh"], [3, "formGroup", 4, "ngIf"], ["id", "scoreDialog", "tabindex", "-1", "role", "dialog", 1, "modal", "fade"], ["role", "dialog", 1, "modal-dialog", "modal-lg"], [1, "modal-content"], [1, "modal-header"], ["type", "button", "data-dismiss", "modal", 1, "close"], [1, "modal-body"], ["id", "scoreInfo"], [3, "formGroup"], [1, "col-5", "col-lg-4", "col-xl-3", 2, "height", "100vh", "overflow-y", "scroll"], [1, "row", "pt-2", "row-cols-1"], ["formControlName", "idCentroDistribuicao", 1, "form-control"], [3, "value", 4, "ngFor", "ngForOf"], ["for", "quantidadeCaminhoes38m3"], ["type", "number", "formControlName", "quantidadeCaminhoes38m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes22m3"], ["type", "number", "formControlName", "quantidadeCaminhoes22m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes13m3"], ["type", "number", "formControlName", "quantidadeCaminhoes13m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes7_5m3"], ["type", "number", "formControlName", "quantidadeCaminhoes7_5m3", "min", "0", 1, "form-control"], ["formControlName", "tipoOtimizacaoEnum", 1, "form-control"], ["value", "MENOR_DISTANCIA"], ["value", "MENOR_TEMPO"], [1, "col", "mb-3"], ["id", "solveButton", "type", "button", "class", "btn btn-success", 3, "click", 4, "ngIf"], ["id", "stopSolvingButton", "type", "button", "class", "btn btn-danger", 3, "click", 4, "ngIf"], [1, "col"], ["href", "#", "data-toggle", "modal", "data-target", "#scoreDialog", 1, "float-right"], [1, "fas", "fa-info-circle"], [1, "table"], ["id", "score"], ["id", "distance"], ["class", "table-sm w-100", 4, "ngIf"], [3, "value"], ["id", "solveButton", "type", "button", 1, "btn", "btn-success", 3, "click"], [1, "fas", "fa-play"], ["id", "stopSolvingButton", "type", "button", 1, "btn", "btn-danger", 3, "click"], [1, "fas", "fa-stop"], [1, "table-sm", "w-100"], [1, "col-1"], [1, "col-11"], ["id", "depots"], [4, "ngFor", "ngForOf"], [1, "fas", "fa-crosshairs", 3, "id"], [1, "col-3"], ["data-toggle", "tooltip", "data-placement", "top", "data-html", "true", "title", "Carga dos ve\u00EDculos mostrada na forma <em>Carga total / Capacidade</em>.", 1, "fas", "fa-info-circle"], ["id", "vehicles"], ["data-toggle", "tooltip-load", "data-placement", "left", "data-html", "true", 1, "progress", 3, "title"], ["role", "progressbar", 1, "progress-bar"]], template: function MapComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](2, "div", 2)(3, "div", 3)(4, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelement"](5, "div", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtemplate"](6, MapComponent_div_6_Template, 57, 8, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()();
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](7, "div", 7)(8, "div", 8)(9, "div", 9)(10, "div", 10)(11, "h5");
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](12, "Score explanation");
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](13, "button", 11)(14, "span");
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](15, "\u00D7");
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()();
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementStart"](16, "div", 12)(17, "pre", 13);
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵtext"](18, "        ");
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵelementEnd"]()()()()();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_4__["ɵɵproperty"]("ngIf", ctx.form);
    } }, dependencies: [_angular_common__WEBPACK_IMPORTED_MODULE_7__.NgForOf, _angular_common__WEBPACK_IMPORTED_MODULE_7__.NgIf, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.NgSelectOption, _angular_forms__WEBPACK_IMPORTED_MODULE_5__["ɵNgSelectMultipleOption"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__.DefaultValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.NumberValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.SelectControlValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.NgControlStatus, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.NgControlStatusGroup, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.MinValidator, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.FormGroupDirective, _angular_forms__WEBPACK_IMPORTED_MODULE_5__.FormControlName], styles: [".map-container[_ngcontent-%COMP%] {\r\n    position: absolute;\r\n    top: 0;\r\n    left: 0;\r\n    right: 0;\r\n    bottom: 0;\r\n    margin: 30px;\r\n}\r\n\r\n.map-frame[_ngcontent-%COMP%] {\r\n    border: 2px solid black;\r\n    height: 100%;\r\n}\r\n\r\n#map[_ngcontent-%COMP%] {\r\n    height: 100%;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1hcC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0lBQ0ksa0JBQWtCO0lBQ2xCLE1BQU07SUFDTixPQUFPO0lBQ1AsUUFBUTtJQUNSLFNBQVM7SUFDVCxZQUFZO0FBQ2hCOztBQUVBO0lBQ0ksdUJBQXVCO0lBQ3ZCLFlBQVk7QUFDaEI7O0FBRUE7SUFDSSxZQUFZO0FBQ2hCIiwiZmlsZSI6Im1hcC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLm1hcC1jb250YWluZXIge1xyXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgdG9wOiAwO1xyXG4gICAgbGVmdDogMDtcclxuICAgIHJpZ2h0OiAwO1xyXG4gICAgYm90dG9tOiAwO1xyXG4gICAgbWFyZ2luOiAzMHB4O1xyXG59XHJcblxyXG4ubWFwLWZyYW1lIHtcclxuICAgIGJvcmRlcjogMnB4IHNvbGlkIGJsYWNrO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG59XHJcblxyXG4jbWFwIHtcclxuICAgIGhlaWdodDogMTAwJTtcclxufSJdfQ== */"] });


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
        const popup = `<h5>CD ${customer.id} - ${customer.name}</h5>
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
        this.http.post("/vrp/solve", simulacaoRequest).subscribe(() => { });
    }
    stopSolving() {
        this.http.post("/vrp/stopSolving", {}).subscribe(() => { });
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