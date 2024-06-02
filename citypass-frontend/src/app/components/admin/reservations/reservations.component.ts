import { Component, OnInit } from '@angular/core';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})
export class ReservationsComponent implements OnInit {
  reservations: any = []

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {

    this.reservationService.getReservations().subscribe( data => {
      this.reservations = data;
       
    }, error => {
      console.error('Error fetching reservations', error);
    });
  }


}
