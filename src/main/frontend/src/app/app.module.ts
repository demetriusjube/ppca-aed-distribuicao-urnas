import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MarkerService } from './marker.service';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MapComponent } from './map/map.component';
import { NgbModalModule, NgbModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { SolverService } from './solver.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgxHumanizeDurationModule, NgxHumanizerOptions } from 'ngx-humanize-duration';

const defaults: NgxHumanizerOptions = {
  language: "pt",
};
@NgModule({
  declarations: [
    AppComponent,
    MapComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    NgbTooltipModule,
    NgbModalModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    NgxHumanizeDurationModule.forRoot(defaults)

  ],
  providers: [
    MarkerService,
    SolverService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
