import { Component } from '@angular/core';
import { SightsService } from 'src/app/services/sights.sevice';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  noOfClicks = 0;
  angularConcepts = ['Components', 'Routing', 'Services', 'Guards', 'Interceptors'];
}

/*
import { Component, OnInit } from '@angular/core';
import { SightsService } from 'src/app/services/sights.sevice';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent  implements OnInit {
  znamenitosti: any = []

  constructor(private sightsService: SightsService ) {}

  ngOnInit(): void {
    this.znamenitosti = this.sightsService.getSights().subscribe(data => {
      this.znamenitosti = data; 
    })
  /*
  noOfClicks = 0;
  angularConcepts = ['Components', 'Routing', 'Services', 'Guards', 'Interceptors'];

}
}
*/