import { Component, OnInit } from '@angular/core';
import { SightsService } from 'src/app/services/sights.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  znamenitosti: any = [];

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
}