import { Component, OnInit , AfterViewInit } from '@angular/core';
import * as L from 'leaflet';
import { SightsService } from 'src/app/services/sights.service';
import Swiper from 'swiper';
import { Navigation, Pagination,Autoplay } from 'swiper/modules';





@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit,AfterViewInit{

  znamenitosti: any = []; 
  slides = [
    { img: 'assets/images/slika1.png' },
    { img: 'assets/images/slika2.png' },
    { img: 'assets/images/slika11.jpg' },
    { img: 'assets/images/slika12.png' },
    { img: 'assets/images/slika13.jpg' },
    { img: 'assets/images/slika5.png'}
  ];   

  stats = [
    { value: 0, target: 12000, label: 'UNESCO protected area' },
    { value: 0, target: 1749, label: 'Mount Lovćen highest point' },
    { value: 0, target: 5000, label: 'Annual rainfall in Crkvice' },
    { value: 0, target: 105, label: 'Bay of Kotor shoreline length' },
    { value: 0, target: 392, label: 'Endemic plant species in Montenegro' },
  ];

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

    //mapa

    this.initMap();

    this.stats.forEach((stat) => {
      this.animateCount(stat);
    });


  }

  ngAfterViewInit(): void {
    this.initSwiper();
  }


  private initMap(): void {

    const bounds = L.latLngBounds([
      [42.4200, 18.7600], // Jugozapadna tačka
      [42.4300, 18.7800]  // Sjeveroistočna tačka
    ]);

    const map = L.map('map', {
     center: [42.4241, 18.7710], // Koordinate za Kotor
      zoom: 15,
      minZoom: 15,                // Minimalni zoom nivo
      maxZoom: 18,                // Maksimalni zoom nivo
      maxBounds: bounds,          // Postavi granice
      maxBoundsViscosity: 1.0,    // Postavi viskoznost granica
      dragging: true,             // Omogući povlačenje
      scrollWheelZoom: false,     // Onemogući zoomiranje pomoću skrola
      doubleClickZoom: false,     // Onemogući zoomiranje dvostrukim klikom
      boxZoom: false,             // Onemogući zoomiranje okvirom
      touchZoom: false            // Onemogući zoomiranje dodirom
    });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);

    // Dodaj interaktivnost
    map.on('click', function(e) {
      const { lat, lng } = e.latlng;
      L.popup()
        .setLatLng(e.latlng)
        .setContent(`Coordinates: ${lat}, ${lng}`)
        .openOn(map);
    });
  }

  private initSwiper(): void {
    new Swiper('.swiper-container', {
      modules: [Navigation, Pagination, Autoplay],
      slidesPerView: 3,
      spaceBetween: 0,
      pagination: {
        el: '.swiper-pagination',
        clickable: true
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },
      loop: true,
      autoplay: {
        delay: 3000, // vreme između automatskog prelaza slika u milisekundama
        disableOnInteraction: false
      },
      breakpoints: {
        650: {
            slidesPerView: 3, // Show 3 slides on screens larger than 1024px
            spaceBetween: 0,
        },
        0: {
            slidesPerView: 1, // Show 2 slides on screens up to 768px wide
            spaceBetween: 0,
        },
        370: {
            slidesPerView: 2, // Show 1 slide on screens up to 576px wide
            spaceBetween: 0,
        }
    }
    });
  }

  animateCount(stat: any) {
    const duration = 1000; // Duration of animation in milliseconds
    const frameDuration = 1000 / 30; // 60 frames per second
    const totalFrames = duration / frameDuration;
    const increment = stat.target / totalFrames;
    
    const updateCount = () => {
      stat.value += increment;
      if (stat.value < stat.target) {
        requestAnimationFrame(updateCount);
      } else {
        stat.value = stat.target;
      }
    };

    updateCount();
  }



}