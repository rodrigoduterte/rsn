import { Injectable } from '@angular/core';
import { PostBean } from 'src/PostBean';


@Injectable({
  providedIn: 'root'
})
export class PopulateFeedService {

  //existingPosts: Array<PostBean> = []

  // fillFeed(jsonArrayOfPosts ){

  //   for(let i=0 ; i<25 ; i++){
  //    // this.existingPosts.push(jasonArray[i]); //need to define the input variable for the Recieved Json from DB 
  //   }
  // }
  

  existingPosts: Array<PostBean> = [
    new PostBean('1','Dylan' ),
    new PostBean('2','Dylan' ),
    new PostBean('3','Dylan' ),
    new PostBean('4','Dylan' )
  ]

  constructor() { }
}
