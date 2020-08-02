import {Component, OnInit, Injectable} from "@angular/core";
import {Http} from '@angular/http';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'recent-tacos',
  templateUrl: 'recents.component.html',
  styleUrls: ['./recents.component.css']
})

@Injectable()
export class RecentTacosComponent implements OnInit {
  recentTacos: any
  // recentTacos: any;

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
    //从服务器端获取最近端taco
    //使用注入的http模版针对URL地址发送HTTP GET请求,并期望得到一个包含设计taco的列表
    //返回数据会被放到recentTacos的模型属性中,视图(recents.component.html)会将模型数据展示为HTML形式
    this.httpClient.get<TacoResponse>('http://localhost:8080/design/recent') // <1>
      .subscribe(data => this.recentTacos = data._embedded.tacos);
  }
}


export class Ingredient {
  name: string;
  type: string;
  _links: {
    self: {
      href: string
    }
  };
}

export class Taco {
  _links: {
    self: {
      href: string
    }
  };
  createdAt: string;
  ingredients: {
    _embedded: Ingredient[];
  };
  name: string;
}

export class TacoResponse {
  _embedded: {
    tacos: Taco[]
  };
  _links: {
    recents: {
      href: string
    }
  };
}
