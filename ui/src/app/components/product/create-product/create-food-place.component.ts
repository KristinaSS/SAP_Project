import {Component, OnInit} from '@angular/core';
import {FoodPlaceService} from '../../../services/food-place.service';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-food-place',
  templateUrl: './create-food-place.component.html',
  styleUrls: ['./create-food-place.component.css']
})
export class CreateFoodPlaceComponent implements OnInit {
  title: 'Create Dining Place';
  foodPlaceForm: FormGroup;
  validMessage: string = '';

  constructor(private foodPlaceService: FoodPlaceService, private router: Router) {
  }

  ngOnInit() {
    this.foodPlaceForm = new FormGroup({
      name: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      telephone: new FormControl('', Validators.minLength(0)),
      linkToWebsite: new FormControl('', Validators.minLength(0))
    });
  }

  submitRegistration() {
    if (this.foodPlaceForm.valid) {
      this.validMessage = 'Your food place registration has been submitted. Thank you!';
      this.foodPlaceService.createFoodPlace(this.foodPlaceForm.value).subscribe(
        data => {
          this.foodPlaceForm.reset();
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      );
      this.router.navigate(['product-list/product']);
    } else {
      this.validMessage = 'Please fill out this form before submitting';
    }
  }
}
