import {Component, OnInit} from '@angular/core';

import {FoodPlaceService} from '../../../services/food-place.service';

@Component({
  selector: 'app-admin',
  templateUrl: './dining-places-list.component.html',
  styleUrls: ['./dining-places-list.component.css']
})
export class DiningPlacesListComponent implements OnInit {
  public foodPlaces;
  public filteredFoodPlaces: any[];
  title = 'All Dining Places';

  // tslint:disable-next-line:variable-name
  private _listFilter: string;
  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredFoodPlaces = this.listFilter ? this.performFilter(this.listFilter) : this.foodPlaces;
  }

  constructor(private foodPlaceService: FoodPlaceService) {
  }

  ngOnInit(): void {
    this.getFoodPlaces();
  }

  getFoodPlaces() {
    this.foodPlaceService.getAllFoodPlaces().subscribe(
      data => {
        this.foodPlaces = data;
        this.filteredFoodPlaces = this.foodPlaces;
      },
      error => console.error(error),
      () => console.log('Food Places Loaded')
    );
  }

  private performFilter(filterBy: string): any[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.foodPlaces.filter((account: any) => account.name.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }
}
