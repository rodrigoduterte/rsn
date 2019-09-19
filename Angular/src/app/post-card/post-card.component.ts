import { Component, OnInit, Input } from '@angular/core';
import { PostBean } from 'src/PostBean';


@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent implements OnInit {

  constructor() { }

  @Input()
  post: PostBean;
  
  

  

  
  ngOnInit() {
  
  }

}
