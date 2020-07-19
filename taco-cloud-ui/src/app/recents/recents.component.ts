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
  recentTacos: any;

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
    //从服务器端获取最近端taco
    //使用注入的http模版针对URL地址发送HTTP GET请求,并期望得到一个包含设计taco的列表
    //返回数据会被放到recentTacos的模型属性中,视图(recents.component.html)会将模型数据展示为HTML形式
    this.httpClient.get('http://localhost:8080/design/recent') // <1>
      .subscribe(data => this.recentTacos = data);
  }
}
