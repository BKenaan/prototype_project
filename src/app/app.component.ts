import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Explicitly import from @angular/router

@Component({
  selector: 'app-root',
  standalone: true, // Ensure this is standalone
  imports: [RouterModule], // Ensure RouterModule is listed here
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Hello, prototype_project';
}
