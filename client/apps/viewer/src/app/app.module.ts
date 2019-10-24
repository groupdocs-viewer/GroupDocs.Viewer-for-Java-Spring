import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ViewerModule} from "@groupdocs.examples.angular/viewer";
import { CustomViewerComponent } from './custom-viewer/custom-viewer.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [AppComponent, CustomViewerComponent],
  imports: [BrowserModule,ViewerModule.forRoot("http://localhost:8080"),FontAwesomeModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
