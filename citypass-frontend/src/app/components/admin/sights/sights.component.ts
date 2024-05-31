import { Component, OnInit } from '@angular/core';
import { SightsService } from 'src/app/services/sights.sevice';

@Component({
  selector: 'app-sights',
  templateUrl: './sights.component.html',
  styleUrls: ['./sights.component.scss']
})
export class SightsComponent implements OnInit{
  znamenitosti: any = []

  constructor(private sightsService: SightsService ) { }


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