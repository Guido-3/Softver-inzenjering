import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-daily-passes',
  templateUrl: './daily-passes.component.html',
  styleUrls: ['./daily-passes.component.scss']
})
export class DailyPassesComponent {

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