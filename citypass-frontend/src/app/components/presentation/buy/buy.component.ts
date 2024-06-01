import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.scss']
})
export class BuyComponent {
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
    "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
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
        const daysDifference = Math.ceil(timeDifference / (1000 * 3600 * 24)) + 1; // Including both dates
        this.totalCost = daysDifference * this.dailyRate;
        this.showPaymentForm = true; // Prikaži formu za plaćanje
      }
    } else {
      this.buyForm.markAllAsTouched(); // Obeležava sva polja kao touched da bi prikazao poruke o greškama
    }
  }

  onPaymentSubmit() {
    if (this.paymentForm.valid) {
      // Prikazujemo poruku o uspešnom plaćanju
      this.paymentSuccess = true;
      this.showPassCard = true; // Prikaži karticu sa informacijama korisnika
    } else {
      this.paymentForm.markAllAsTouched();
    }
  }
}
