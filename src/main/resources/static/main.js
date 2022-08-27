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
/* harmony import */ var _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @fortawesome/angular-fontawesome */ 9200);
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
        _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_8__.NgbTooltipModule,
        _angular_forms__WEBPACK_IMPORTED_MODULE_9__.FormsModule,
        _angular_forms__WEBPACK_IMPORTED_MODULE_9__.ReactiveFormsModule,
        _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_10__.FontAwesomeModule] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵsetNgModuleScope"](AppModule, { declarations: [_app_component__WEBPACK_IMPORTED_MODULE_2__.AppComponent,
        _map_map_component__WEBPACK_IMPORTED_MODULE_3__.MapComponent], imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_6__.BrowserModule,
        _app_routing_module__WEBPACK_IMPORTED_MODULE_1__.AppRoutingModule,
        _angular_common_http__WEBPACK_IMPORTED_MODULE_7__.HttpClientModule,
        _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_8__.NgbModule,
        _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_8__.NgbTooltipModule,
        _angular_forms__WEBPACK_IMPORTED_MODULE_9__.FormsModule,
        _angular_forms__WEBPACK_IMPORTED_MODULE_9__.ReactiveFormsModule,
        _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_10__.FontAwesomeModule] }); })();


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
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/forms */ 2508);
/* harmony import */ var _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @fortawesome/free-solid-svg-icons */ 655);
/* harmony import */ var leaflet__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! leaflet */ 5836);
/* harmony import */ var leaflet__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(leaflet__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! lodash */ 2938);
/* harmony import */ var lodash__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(lodash__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! rxjs */ 8653);
/* harmony import */ var _shared_error_utils__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../shared/error-utils */ 3516);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/core */ 2560);
/* harmony import */ var _marker_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../marker.service */ 9937);
/* harmony import */ var _solver_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../solver.service */ 1452);
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/common */ 4666);
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ 4534);
/* harmony import */ var _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @fortawesome/angular-fontawesome */ 9200);













function MapComponent_fieldset_7_option_9_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "option", 47);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
} if (rf & 2) {
    const centroDistribuicao_r9 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵpropertyInterpolate"]("value", centroDistribuicao_r9.id);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate2"](" ", centroDistribuicao_r9.nome, " - (", centroDistribuicao_r9.totalDeUrnas, ") ");
} }
function MapComponent_fieldset_7_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "fieldset", 29)(1, "legend");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](2, "Dados para simula\u00E7\u00E3o");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](3, "div", 3)(4, "div", 8)(5, "div", 30)(6, "label", 31);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](7, "Centro de Distribui\u00E7\u00E3o");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](8, "select", 32);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](9, MapComponent_fieldset_7_option_9_Template, 2, 3, "option", 33);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](10, "div", 8)(11, "div", 34)(12, "label", 35);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](13, "Ve\u00EDculos 38m3 (730)");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](14, "input", 36);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](15, "div", 34)(16, "label", 37);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](17, "Ve\u00EDculos 22m3 (422)");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](18, "input", 38);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](19, "div", 8)(20, "div", 34)(21, "label", 39);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](22, "Ve\u00EDculos 13m3 (249)");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](23, "input", 40);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](24, "div", 34)(25, "label", 41);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](26, "Ve\u00EDculos 7,5m3 (144)");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](27, "input", 42);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](28, "div", 8)(29, "div", 30)(30, "label", 43);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](31, "Tipo de otimiza\u00E7\u00E3o");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](32, "select", 44)(33, "option", 45);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](34, " Menor dist\u00E2ncia ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](35, "option", 46);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](36, " Menor tempo ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()()()()();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("formGroup", ctx_r0.form);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](9);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngForOf", ctx_r0.centrosDistribuicao);
} }
function MapComponent_button_10_Template(rf, ctx) { if (rf & 1) {
    const _r11 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "button", 48);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵlistener"]("click", function MapComponent_button_10_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵrestoreView"](_r11); const ctx_r10 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](); return _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵresetView"](ctx_r10.startSolving()); });
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](1, "fa-icon", 15);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](2, " Resolver ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("icon", ctx_r1.faPlay);
} }
function MapComponent_button_11_Template(rf, ctx) { if (rf & 1) {
    const _r13 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "button", 49);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵlistener"]("click", function MapComponent_button_11_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵrestoreView"](_r13); const ctx_r12 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](); return _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵresetView"](ctx_r12.stopSolving()); });
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](1, "fa-icon", 15);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](2, " Parar resolu\u00E7\u00E3o ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("icon", ctx_r2.faStop);
} }
function MapComponent_div_12_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](1, "div", 50);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](2, "span");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](3, "Preparando para calcular rotas...");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} }
function MapComponent_table_34_tr_7_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "tr")(1, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](2, "fa-icon", 56);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const depot_r15 = ctx.$implicit;
    const ctx_r14 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵstyleMap"](ctx_r14.getDepotStyle(depot_r15));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("icon", ctx_r14.faCrosshairs)("id", ctx_r14.getIdCrossHairDepot(depot_r15));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](depot_r15.name);
} }
function MapComponent_table_34_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "table", 51)(1, "thead")(2, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](3, "th", 52);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](4, "th", 53);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](5, "Nome");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](6, "tbody", 54);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](7, MapComponent_table_34_tr_7_Template, 5, 5, "tr", 55);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const ctx_r4 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](7);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngForOf", ctx_r4.statusSolucaoAtual.solution.depotList);
} }
function MapComponent_div_35_table_3_th_3_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](0, "th", 52);
} }
function MapComponent_div_35_table_3_th_4_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](0, "th", 52);
} }
function MapComponent_div_35_table_3_tr_14_td_1_Template(rf, ctx) { if (rf & 1) {
    const _r25 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "td")(1, "input", 65);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵlistener"]("change", function MapComponent_div_35_table_3_tr_14_td_1_Template_input_change_1_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵrestoreView"](_r25); const ctx_r24 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](4); return _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵresetView"](ctx_r24.onRotasSelecionadasChange($event)); });
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const vehicle_r20 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]().$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("value", vehicle_r20.id);
} }
function MapComponent_div_35_table_3_tr_14_td_2_Template(rf, ctx) { if (rf & 1) {
    const _r28 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "td")(1, "fa-icon", 66);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵlistener"]("click", function MapComponent_div_35_table_3_tr_14_td_2_Template_fa_icon_click_1_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵrestoreView"](_r28); const vehicle_r20 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]().$implicit; const ctx_r27 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](3); const _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵreference"](49); return _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵresetView"](ctx_r27.abrirItinerario(_r6, vehicle_r20)); });
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const ctx_r23 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("icon", ctx_r23.faSignsPost);
} }
function MapComponent_div_35_table_3_tr_14_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](1, MapComponent_div_35_table_3_tr_14_td_1_Template, 2, 1, "td", 12);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](2, MapComponent_div_35_table_3_tr_14_td_2_Template, 2, 1, "td", 12);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](4, "fa-icon", 56);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](5, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](7, "td")(8, "div", 63)(9, "div", 64);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](10);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](11, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](12);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const vehicle_r20 = ctx.$implicit;
    const ctx_r19 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx_r19.showRouteCheckbox());
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx_r19.showRouteCheckbox());
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵstyleMap"](ctx_r19.getVehicleStyle(vehicle_r20));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("icon", ctx_r19.faTruckPickup)("id", ctx_r19.getIdCrossHairVehicle(vehicle_r20));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate1"]("Ve\u00EDculo ", vehicle_r20.id, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("title", ctx_r19.getVehicleTitle(vehicle_r20));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵstyleMap"](ctx_r19.getVehicleProgressBarStyle(vehicle_r20));
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate2"](" ", vehicle_r20.totalDemand, "/", vehicle_r20.capacity, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](ctx_r19.getDistanciaEmMetros(vehicle_r20.totalDistanceMeters));
} }
function MapComponent_div_35_table_3_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "table", 51)(1, "thead")(2, "tr");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](3, MapComponent_div_35_table_3_th_3_Template, 1, 0, "th", 58);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](4, MapComponent_div_35_table_3_th_4_Template, 1, 0, "th", 58);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](5, "th", 52);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](6, "th", 59);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](7, "Nome");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](8, "th", 60);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](9, " Carga ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](10, "fa-icon", 61);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](11, "th", 59);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](12, "Dist\u00E2ncia");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](13, "tbody", 62);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](14, MapComponent_div_35_table_3_tr_14_Template, 13, 13, "tr", 55);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const ctx_r16 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx_r16.showRouteCheckbox());
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx_r16.showRouteCheckbox());
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("icon", ctx_r16.faInfoCircle);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngForOf", ctx_r16.statusSolucaoAtual.solution.vehicleList);
} }
function MapComponent_div_35_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "div", 57)(1, "h5");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](2, "Ve\u00EDculos");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](3, MapComponent_div_35_table_3_Template, 15, 4, "table", 19);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("formGroup", ctx_r5.formRotasSelecionadas);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](3);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx_r5.statusSolucaoAtual == null ? null : ctx_r5.statusSolucaoAtual.solution == null ? null : ctx_r5.statusSolucaoAtual.solution.vehicleList);
} }
function MapComponent_ng_template_48_tr_16_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "tr")(1, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](3, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](5, "td");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const customer_r32 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](customer_r32.id);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](customer_r32.name);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](customer_r32.location.endereco);
} }
function MapComponent_ng_template_48_Template(rf, ctx) { if (rf & 1) {
    const _r34 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "div", 24)(1, "h4", 67);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](3, "button", 68);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵlistener"]("click", function MapComponent_ng_template_48_Template_button_click_3_listener() { const restoredCtx = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵrestoreView"](_r34); const modal_r30 = restoredCtx.$implicit; return _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵresetView"](modal_r30.dismiss("Cross click")); });
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](4, "div", 26)(5, "h5");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](7, "table")(8, "thead")(9, "th");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](10, " N\u00FAmero ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](11, "th");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](12, " Nome ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](13, "th");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](14, " Endere\u00E7o ");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](15, "tbody");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](16, MapComponent_ng_template_48_tr_16_Template, 7, 3, "tr", 55);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](17, "div", 69)(18, "button", 70);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵlistener"]("click", function MapComponent_ng_template_48_Template_button_click_18_listener() { const restoredCtx = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵrestoreView"](_r34); const modal_r30 = restoredCtx.$implicit; return _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵresetView"](modal_r30.close("Save click")); });
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](19, "Save");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
} if (rf & 2) {
    const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate1"]("Itiner\u00E1rio - Ve\u00EDculo ", ctx_r7.veiculoItinerario.id, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate1"]("Sa\u00EDda : ", ctx_r7.veiculoItinerario.depot.name, "");
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](10);
    _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngForOf", ctx_r7.veiculoItinerario.customerList);
} }
class MapComponent {
    constructor(markerService, solverService, formBuilder) {
        this.markerService = markerService;
        this.solverService = solverService;
        this.formBuilder = formBuilder;
        this.faInfoCircle = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_6__.faInfoCircle;
        this.faStop = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_6__.faStop;
        this.faPlay = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_6__.faPlay;
        this.faCrosshairs = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_6__.faCrosshairs;
        this.faTruckPickup = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_6__.faTruckPickup;
        this.faSignsPost = _fortawesome_free_solid_svg_icons__WEBPACK_IMPORTED_MODULE_6__.faSignsPost;
        this.layerGroup = leaflet__WEBPACK_IMPORTED_MODULE_0__.layerGroup();
        this.solvingIsStarting = false;
        this.isSolving = false;
        this.centrosDistribuicao = [];
    }
    ngOnInit() {
        this.solverService.getCentrosDistribuicao().subscribe(centrosDistribuicao => {
            this.centrosDistribuicao = centrosDistribuicao;
            this.form = this.formBuilder.group({
                idCentroDistribuicao: this.getCentroDistribuicaoControl(),
                quantidadeCaminhoes38m3: this.formBuilder.control(0),
                quantidadeCaminhoes22m3: this.formBuilder.control(0),
                quantidadeCaminhoes13m3: this.formBuilder.control(0),
                quantidadeCaminhoes7_5m3: this.formBuilder.control(0),
                tipoOtimizacaoEnum: this.formBuilder.control('MENOR_DISTANCIA', _angular_forms__WEBPACK_IMPORTED_MODULE_7__.Validators.required)
            });
            this.formRotasSelecionadas = this.formBuilder.group({
                rotasSelecionadas: this.formBuilder.array([])
            });
        });
    }
    onRotasSelecionadasChange(event) {
        const rotasSelecionadas = this.formRotasSelecionadas.controls['rotasSelecionadas'];
        const idVeiculoSelecionado = +event.target.value;
        if (event.target.checked) {
            rotasSelecionadas.push(this.formBuilder.control(idVeiculoSelecionado));
        }
        else {
            const index = rotasSelecionadas.controls
                .findIndex(x => x.value === idVeiculoSelecionado);
            rotasSelecionadas.removeAt(index);
        }
        this.atualizarRotasSelecionadas();
    }
    atualizarRotasSelecionadas() {
        const veiculosSelecionados = this.formRotasSelecionadas.value.rotasSelecionadas;
        this.marcarSolucaoNoMapa(this.map, this.statusSolucaoAtual, veiculosSelecionados);
    }
    getCentroDistribuicaoControl() {
        const centroDistribuicaoControl = this.formBuilder.control(null, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.Validators.required);
        centroDistribuicaoControl.valueChanges.subscribe(idCentroDistribuicao => {
            this.marcarCentroDistribuicaoELocaisVotacao(idCentroDistribuicao);
        });
        return centroDistribuicaoControl;
    }
    initMap() {
        this.map = this.buildMap();
        const tiles = this.buildTiles();
        tiles.addTo(this.map);
        this.layerGroup.addTo(this.map);
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
            this.marcarSolucaoNoMapa(this.map, statusSolucao);
            this.statusSolucaoAtual = statusSolucao;
            this.updateSolvingStatus(this.statusSolucaoAtual.isSolving);
        });
    }
    marcarSolucaoNoMapa(map, status, idsVehicles) {
        this.markerService.marcarSolucaoNoMapa(this.map, status, idsVehicles);
    }
    marcarCentroDistribuicaoELocaisVotacao(idCentroDistribuicao) {
        this.solverService.getCentroDistribuicaoELocaisVotacao(idCentroDistribuicao).subscribe(depotCustomers => {
            this.markerService.marcarCentroDistribuicaoELocaisVotacao(this.layerGroup, depotCustomers);
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
        this.solvingIsStarting = true;
        this.solverService.startSolving(simulacaoRequest).subscribe({
            next: () => {
                this.solvingIsStarting = false;
                this.atualizarMapa();
                this.updateSubscription = (0,rxjs__WEBPACK_IMPORTED_MODULE_8__.interval)(10000).subscribe((val) => {
                    this.atualizarMapa();
                });
            },
            error: (err) => {
                _shared_error_utils__WEBPACK_IMPORTED_MODULE_2__.ErrorUtils.displayError(err);
            }
        });
    }
    showRouteCheckbox() {
        return this.isSolving === false && this.statusSolucaoAtual && this.statusSolucaoAtual.solution && this.statusSolucaoAtual.solution.vehicleList && this.statusSolucaoAtual.solution.vehicleList.length > 0;
    }
    stopSolving() {
        this.solverService.stopSolving().subscribe({
            next: () => {
                this.updateSolvingStatus(false);
                const rotasSelecionadasArray = this.formRotasSelecionadas.get('rotasSelecionadas');
                rotasSelecionadasArray.clear();
                this.statusSolucaoAtual.solution.vehicleList.forEach(vehicle => {
                    rotasSelecionadasArray.push(this.formBuilder.control(vehicle.id));
                });
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
    abrirItinerario(content, vehicle) {
    }
}
MapComponent.ɵfac = function MapComponent_Factory(t) { return new (t || MapComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdirectiveInject"](_marker_service__WEBPACK_IMPORTED_MODULE_3__.MarkerService), _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdirectiveInject"](_solver_service__WEBPACK_IMPORTED_MODULE_4__.SolverService), _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdirectiveInject"](_angular_forms__WEBPACK_IMPORTED_MODULE_7__.FormBuilder)); };
MapComponent.ɵcmp = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵdefineComponent"]({ type: MapComponent, selectors: [["app-map"]], decls: 50, vars: 9, consts: [[1, "sticky-top", "d-flex", "justify-content-center", "align-items-center"], ["id", "notificationPanel", 2, "position", "absolute", "top", ".5rem"], [1, "container-fluid"], [1, "row"], [1, "col-6", "col-lg-7", "col-xl-8"], ["id", "map", 2, "width", "100%", "height", "100vh"], [1, "col-6", "col-lg-5", "col-xl-4", 2, "height", "100vh", "overflow-y", "scroll"], [3, "formGroup", 4, "ngIf"], [1, "row", "pt-2", "row-cols-1"], [1, "col", "mb-3"], ["id", "solveButton", "type", "button", "class", "btn btn-success", 3, "click", 4, "ngIf"], ["id", "stopSolvingButton", "type", "button", "class", "btn btn-danger", 3, "click", 4, "ngIf"], [4, "ngIf"], [1, "col"], ["href", "#", "data-toggle", "modal", "data-target", "#scoreDialog", 1, "float-right"], [3, "icon"], [1, "table"], ["id", "score"], ["id", "distance"], ["class", "table-sm w-100", 4, "ngIf"], ["class", "col", 3, "formGroup", 4, "ngIf"], ["id", "scoreDialog", "tabindex", "-1", "role", "dialog", 1, "modal", "fade"], ["role", "dialog", 1, "modal-dialog", "modal-lg"], [1, "modal-content"], [1, "modal-header"], ["type", "button", "data-dismiss", "modal", 1, "close"], [1, "modal-body"], ["id", "scoreInfo"], ["content", ""], [3, "formGroup"], [1, "col-12"], ["for", "idCentroDistribuicao"], ["formControlName", "idCentroDistribuicao", 1, "form-control"], [3, "value", 4, "ngFor", "ngForOf"], [1, "col-6"], ["for", "quantidadeCaminhoes38m3"], ["type", "number", "formControlName", "quantidadeCaminhoes38m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes22m3"], ["type", "number", "formControlName", "quantidadeCaminhoes22m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes13m3"], ["type", "number", "formControlName", "quantidadeCaminhoes13m3", "min", "0", 1, "form-control"], ["for", "quantidadeCaminhoes7_5m3"], ["type", "number", "formControlName", "quantidadeCaminhoes7_5m3", "min", "0", 1, "form-control"], ["for", "tipoOtimizacaoEnum"], ["formControlName", "tipoOtimizacaoEnum", 1, "form-control"], ["value", "MENOR_DISTANCIA", "selected", ""], ["value", "MENOR_TEMPO"], [3, "value"], ["id", "solveButton", "type", "button", 1, "btn", "btn-success", 3, "click"], ["id", "stopSolvingButton", "type", "button", 1, "btn", "btn-danger", 3, "click"], ["role", "status", 1, "spinner-border", "text-primary"], [1, "table-sm", "w-100"], [1, "col-1"], [1, "col-11"], ["id", "depots"], [4, "ngFor", "ngForOf"], [3, "icon", "id"], [1, "col", 3, "formGroup"], ["class", "col-1", 4, "ngIf"], [1, "col-2"], [1, "col-3"], ["placement", "top", "ngbTooltip", "Carga dos ve\u00EDculos mostrada na forma Carga total / Capacidade.", 3, "icon"], ["id", "vehicles"], ["data-toggle", "tooltip-load", "data-placement", "left", "data-html", "true", 1, "progress", 3, "title"], ["role", "progressbar", 1, "progress-bar"], ["type", "checkbox", "formArrayName", "rotasSelecionadas", "checked", "", 3, "value", "change"], [3, "icon", "click"], ["id", "modal-basic-title", 1, "modal-title"], ["type", "button", "aria-label", "Close", 1, "btn-close", 3, "click"], [1, "modal-footer"], ["type", "button", 1, "btn", "btn-outline-dark", 3, "click"]], template: function MapComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](2, "div", 2)(3, "div", 3)(4, "div", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](5, "div", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](6, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](7, MapComponent_fieldset_7_Template, 37, 2, "fieldset", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](8, "div", 8)(9, "div", 9);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](10, MapComponent_button_10_Template, 3, 1, "button", 10);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](11, MapComponent_button_11_Template, 3, 1, "button", 11);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](12, MapComponent_div_12_Template, 4, 0, "div", 12);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](13, "div", 13)(14, "h5");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](15, " Resumo da solu\u00E7\u00E3o ");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](16, "a", 14);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelement"](17, "fa-icon", 15);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](18, "table", 16)(19, "tr")(20, "td");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](21, "Score:");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](22, "td")(23, "span", 17);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](24);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](25, "tr")(26, "td");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](27, "Dist\u00E2ncia total percorrida:");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](28, "td")(29, "span", 18);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](30);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()()()();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](31, "div", 13)(32, "h5");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](33, "Centro de Distribui\u00E7\u00E3o");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](34, MapComponent_table_34_Template, 8, 1, "table", 19);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](35, MapComponent_div_35_Template, 4, 2, "div", 20);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()()();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](36, "div", 21)(37, "div", 22)(38, "div", 23)(39, "div", 24)(40, "h5");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](41, "Score explanation");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](42, "button", 25)(43, "span");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](44, "\u00D7");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementStart"](45, "div", 26)(46, "pre", 27);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtext"](47, "        ");
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵelementEnd"]()()()()();
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplate"](48, MapComponent_ng_template_48_Template, 20, 3, "ng-template", null, 28, _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtemplateRefExtractor"]);
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](7);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx.form);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](3);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", !ctx.isSolving);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx.isSolving && !ctx.solvingIsStarting);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx.solvingIsStarting);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("icon", ctx.faInfoCircle);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](7);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](ctx.statusSolucaoAtual == null ? null : ctx.statusSolucaoAtual.solution == null ? null : ctx.statusSolucaoAtual.solution.score);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](6);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵtextInterpolate"](ctx.getDistanciaFormatada());
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx.statusSolucaoAtual == null ? null : ctx.statusSolucaoAtual.solution == null ? null : ctx.statusSolucaoAtual.solution.depotList);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_5__["ɵɵproperty"]("ngIf", ctx.formRotasSelecionadas);
    } }, dependencies: [_angular_common__WEBPACK_IMPORTED_MODULE_9__.NgForOf, _angular_common__WEBPACK_IMPORTED_MODULE_9__.NgIf, _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_10__.NgbTooltip, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.NgSelectOption, _angular_forms__WEBPACK_IMPORTED_MODULE_7__["ɵNgSelectMultipleOption"], _angular_forms__WEBPACK_IMPORTED_MODULE_7__.DefaultValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.NumberValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.SelectControlValueAccessor, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.NgControlStatus, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.NgControlStatusGroup, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.MinValidator, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.FormGroupDirective, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.FormControlName, _angular_forms__WEBPACK_IMPORTED_MODULE_7__.FormArrayName, _fortawesome_angular_fontawesome__WEBPACK_IMPORTED_MODULE_11__.FaIconComponent], styles: [".map-container[_ngcontent-%COMP%] {\r\n    position: absolute;\r\n    top: 0;\r\n    left: 0;\r\n    right: 0;\r\n    bottom: 0;\r\n    margin: 30px;\r\n}\r\n\r\n.map-frame[_ngcontent-%COMP%] {\r\n    border: 2px solid black;\r\n    height: 100%;\r\n}\r\n\r\n#map[_ngcontent-%COMP%] {\r\n    height: 100%;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1hcC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0lBQ0ksa0JBQWtCO0lBQ2xCLE1BQU07SUFDTixPQUFPO0lBQ1AsUUFBUTtJQUNSLFNBQVM7SUFDVCxZQUFZO0FBQ2hCOztBQUVBO0lBQ0ksdUJBQXVCO0lBQ3ZCLFlBQVk7QUFDaEI7O0FBRUE7SUFDSSxZQUFZO0FBQ2hCIiwiZmlsZSI6Im1hcC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLm1hcC1jb250YWluZXIge1xyXG4gICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgdG9wOiAwO1xyXG4gICAgbGVmdDogMDtcclxuICAgIHJpZ2h0OiAwO1xyXG4gICAgYm90dG9tOiAwO1xyXG4gICAgbWFyZ2luOiAzMHB4O1xyXG59XHJcblxyXG4ubWFwLWZyYW1lIHtcclxuICAgIGJvcmRlcjogMnB4IHNvbGlkIGJsYWNrO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG59XHJcblxyXG4jbWFwIHtcclxuICAgIGhlaWdodDogMTAwJTtcclxufSJdfQ== */"] });


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
        this.routeControls = [];
        this.letters = '0123456789ABCDEF';
    }
    marcarCentroDistribuicaoELocaisVotacao(layerGroup, depotCustomer) {
        if (depotCustomer) {
            layerGroup.clearLayers();
            this.adicionaCentroDeDistribuicao(depotCustomer.depot, layerGroup);
            this.adicionaLocaisDeVotacao(depotCustomer.customerList, layerGroup);
        }
    }
    marcarSolucaoNoMapa(map, statusSolucao, idsVehicles) {
        if (this.existeSolucaoComLocalizacoes(statusSolucao)) {
            const solution = statusSolucao.solution;
            // this.adicionaCentrosDeDistribuicao(solution, map);
            // this.adicionaLocaisDeVotacao(solution.customerList, map);
            this.adicionaRotas(solution, map, idsVehicles);
        }
    }
    adicionaRotas(solution, map, idsVehicles) {
        this.routeControls.forEach(routeControl => {
            map.removeControl(routeControl);
        });
        solution.vehicleList.forEach(vehicle => {
            if ((lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(idsVehicles) || idsVehicles.length === 0) || (!lodash__WEBPACK_IMPORTED_MODULE_1__.isNil(idsVehicles) && idsVehicles.indexOf(vehicle.id) != -1)) {
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
                    this.routeControls.push(rota);
                    rota.addTo(map);
                }
            }
        });
    }
    buildLatLongFromLocation(location) {
        return leaflet__WEBPACK_IMPORTED_MODULE_0__.latLng(location.latitude, location.longitude);
    }
    adicionaLocaisDeVotacao(customerList, layerGroup) {
        customerList.forEach(customer => {
            const latLong = [customer.location.latitude, customer.location.longitude];
            const options = {
                title: customer.name + ' - ' + customer.id
            };
            const popup = this.montaPopupLocalVotacao(customer);
            const marcador = leaflet__WEBPACK_IMPORTED_MODULE_0__.circleMarker(latLong, options).bindPopup(popup);
            marcador.addTo(layerGroup);
        });
    }
    montaPopupLocalVotacao(customer) {
        const popup = `<h5>LV ${customer.id} - ${customer.name}</h5>
            <div>${customer.location.endereco}</div>`;
        return popup;
    }
    // private adicionaCentrosDeDistribuicao(solution: VehicleRoutingSolution, map: L.Map) {
    //   solution.depotList.forEach(depot => {
    //     this.adicionaCentroDeDistribuicao(depot, map);
    //   });
    // }
    adicionaCentroDeDistribuicao(depot, layerGroup) {
        const latLong = [depot.location.latitude, depot.location.longitude];
        const options = {
            title: depot.name + ' - ' + depot.id
        };
        const popup = this.montaPopupCentroDistribuicao(depot);
        const marcador = leaflet__WEBPACK_IMPORTED_MODULE_0__.marker(latLong, options).bindPopup(popup);
        marcador.addTo(layerGroup);
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
    getCentroDistribuicaoELocaisVotacao(idCentroDistribuicao) {
        return this.http.get(`/vrp/depot-customers/${idCentroDistribuicao}`, {});
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