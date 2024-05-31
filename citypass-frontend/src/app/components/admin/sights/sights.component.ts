import { Component } from '@angular/core';
import { SightsService } from 'src/app/services/sights.sevice';

@Component({
  selector: 'app-sights',
  templateUrl: './sights.component.html',
  styleUrls: ['./sights.component.scss']
})
export class SightsComponent {

    znamenitosti: any = []

  constructor(private sightsService: SightsService ) {}

  ngOnInit(): void {
    this.znamenitosti = this.sightsService.getSights().subscribe(data => {
      this.znamenitosti = data; 
    })
}
}
