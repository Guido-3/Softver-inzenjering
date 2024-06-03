import { Component, OnInit  } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.scss']
})
export class BuyComponent implements OnInit {
  buyForm: FormGroup;
  paymentForm: FormGroup;
  totalCost: number | null = null;
  dailyRate: number | null = null;
  showPaymentForm: boolean = false;
  paymentSuccess: boolean = false; // Dodata promenljiva za kontrolu prikaza poruke o uspešnom plaćanju
  showPassCard: boolean = false; // Dodata promenljiva za prikazivanje kartice
  countries: string[] = [
    "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria",
    "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
    "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia",
    "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo (Congo-Brazzaville)", "Costa Rica",
    "Croatia", "Cuba", "Cyprus", "Czechia (Czech Republic)", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador",
    "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini (fmr. Swaziland)", "Ethiopia", "Fiji", "Finland", "France",
    "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau",
    "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland",
    "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait",
    "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
    "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico",
    "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (formerly Burma)", "Namibia", "Nauru",
    "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman",
    "Pakistan", "Palau", "Palestine State", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal",
    "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe",
    "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
    "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria",
    "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
    "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu",
    "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe", "Crna Gora"
  ];

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.buyForm = this.fb.group({
      name: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]], // Dodato polje za email
      countryname: ['', Validators.required],
      dateFrom: ['', [Validators.required, this.dateValidator]],
      dateTo: ['', [Validators.required, this.dateValidator]]
    }, { validator: this.dateRangeValidator });

    this.paymentForm = this.fb.group({
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      cardName: ['', Validators.required],
      expiryDate: ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/)]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3}$/)]]
    });

    this.getDailyRate();
  }
  ngOnInit(): void {}

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Dodajemo 1 jer je mesec 0-indeksiran
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
  getDailyRate() {
    this.http.get<number>('http://localhost:8080/citypass-api/daily-pass/price-per-day/1').subscribe(rate => {
      this.dailyRate = rate;
    });
  }

  dateValidator(control: any) {
    const currentDate = new Date();
    const selectedDate = new Date(control.value);
    currentDate.setHours(0, 0, 0, 0); // Set current date to midnight to only compare dates
    if (selectedDate < currentDate) {
      return { dateInvalid: true };
    }
    return null;
  }

  dateRangeValidator(group: FormGroup) {
    const dateFrom = group.controls['dateFrom'].value;
    const dateTo = group.controls['dateTo'].value;
    if (dateFrom && dateTo && new Date(dateFrom) > new Date(dateTo)) {
      return { dateRangeInvalid: true };
    }
    return null;
  }

  onSubmit() {
    if (this.buyForm.valid) {
      const { dateFrom, dateTo } = this.buyForm.value;
      if (dateFrom && dateTo && this.dailyRate !== null) {
        const startDate = new Date(dateFrom);
        const endDate = new Date(dateTo);
        const timeDifference = endDate.getTime() - startDate.getTime();
        const daysDifference = Math.ceil(timeDifference / (1000 * 3600 * 24)) + 1; 
        this.totalCost = daysDifference * this.dailyRate;
        this.showPaymentForm = true; 
      }
    } else {
      this.buyForm.markAllAsTouched(); 
    }
  }
  onPaymentSubmit() {
    if (this.paymentForm.valid) {
      this.paymentSuccess = true;
      this.showPassCard = true;

      const korisnikData = {
        ime: this.buyForm.get('name')?.value,
        prezime: this.buyForm.get('lastname')?.value,
        email: this.buyForm.get('email')?.value,
        drzava_ime: this.buyForm.get('countryname')?.value,
        br_telefona: '123',
        admin: 'Gvido'
      };

      const turistaData = {
        ime: this.buyForm.get('name')?.value,
        prezime: this.buyForm.get('lastname')?.value,
        drzava_ime: this.buyForm.get('countryname')?.value
      };

      const dailyPassId = 1; // ID daily pass-a

      const turistaDailyPassData = {
        turistaId: null, // Biće postavljeno nakon kreiranja turista
        dailyPassId: dailyPassId,
        datumOd: this.buyForm.get('dateFrom')?.value,
        datumDo: this.buyForm.get('dateTo')?.value,
        ukupnaCijena: this.totalCost,
        kupovinaId: null 
      };

      // Prvo kreiramo korisnika
      this.http.post('http://localhost:8080/citypass-api/korisnik', korisnikData).subscribe((response: any) => {
        console.log('Korisnik saved successfully', response);
        const korisnikId = response.id;

        console.log("Novi korisnik: ", korisnikData);
        // Kreiramo kupovinu sa dobijenim korisnikId
        const kupovinaData = {
          korisnik_id: korisnikId,
          datum: this.formatDate(new Date()),
          // id: null
        };
        console.log("Nova kupovina: ", kupovinaData);

        this.http.post('http://localhost:8080/citypass-api/kupovina', kupovinaData).subscribe((kupovinaresponse: any) => {
          console.log('Kupovina saved successfully', kupovinaresponse);
          //const kupovinaId = kupovinaresponse.id;
          const kupovinaId = kupovinaresponse.id;
          turistaDailyPassData.kupovinaId = kupovinaId;
        }, error => {
          console.error('Error saving kupovina', error);
        });

        // Zatim kreiramo turista i vežemo ga za daily pass
        this.http.post('http://localhost:8080/citypass-api/turista', turistaData).subscribe((turista: any) => {
          console.log('Turista saved successfully', turista);
          // Nakon što je turista kreiran, koristimo njegov ID za kreiranje turistaDailyPass
          turistaDailyPassData.turistaId = turista.id;
          

          this.http.post('http://localhost:8080/citypass-api/turista-daily-pass', turistaDailyPassData).subscribe(response => {
            console.log('TuristaDailyPass saved successfully', response);
          }, error => {
            console.error('Error saving turistaDailyPass', error);
          });

        }, error => {
          console.error('Error saving turista', error);
        });

      }, error => {
        console.error('Error saving korisnik', error);
      });

    } else {
      this.paymentForm.markAllAsTouched();
    }
  }
}