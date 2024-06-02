import { Component, OnInit } from '@angular/core';
import { SightsService } from 'src/app/services/sights.service';

@Component({
  selector: 'app-daily-passes',
  templateUrl: './daily-passes.component.html',
  styleUrls: ['./daily-passes.component.scss']
})
export class DailyPassesComponent {
  znamenitosti: any[] = [];

  constructor(private sightsService: SightsService) { }

  ngOnInit(): void {
    console.log('ngOnInit called');
    this.sightsService.getSights().subscribe(
      data => {
        console.log('Data received:', data);
        this.znamenitosti = data;
      },
      error => {
        console.error('Error fetching sights data', error);
      }
    );
  }

  
  
  icons = [
    { class: 'fa-solid fa-building-columns', text: 'Museums' },
    { class: 'class="fa-solid fa-wifi', text: 'WiFi' },
    { class: 'fa-solid fa-fish', text: 'Aquarium' },
    { class: 'fa-solid fa-cable-car', text: 'Cable Car' },
    { class: 'fa-solid fa-bus-simple', text: 'Public transport' },
    { class: 'fa-solid fa-archway', text: 'Fortress' }
  ];
}
/**
 * import { Component } from '@angular/core';
import { DailyService } from 'src/app/services/daily.service';

@Component({
  selector: 'app-daily-passes',
  templateUrl: './daily-passes.component.html',
  styleUrls: ['./daily-passes.component.scss']
})
export class DailyPassesComponent {
  daily_passes: any = []

  constructor(private dailyService: DailyService ) {}

  ngOnInit(): void {
    this.daily_passes = this.dailyService.getDailyPass().subscribe(data => {
      this.daily_passes = data; 
    })
}
}
 */