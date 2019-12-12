import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FoodPlaceService} from '../../../services/food-place.service';

@Component({
  selector: 'app-view-food-place',
  templateUrl: './view-food-place.component.html',
  styleUrls: ['./view-food-place.component.css']
})
export class ViewFoodPlaceComponent implements OnInit {
  public foodPlace;

  constructor(private foodPlaceService: FoodPlaceService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getFoodPlaceByID(this.route.snapshot.params.id);
  }
  getFoodPlaceByID(id: number) {
    this.foodPlaceService.getFoodPlace(id).subscribe(
      data => {this.foodPlace = data;
      },
      error => console.error(error),
      () => console.log('Food Place Loaded')
    );
  }
  deleteFoodPlace() {
    this.foodPlaceService.deleteFoodPlace(this.foodPlace.foodPlaceID).subscribe(data => {this.foodPlace = data;
      },
      error => console.error(error),
      () => console.log('Food Place Deleted '));
  }

}

