import { UploadService } from './upload.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styles: []
})
export class UploadComponent implements OnInit {

  filePath: string;

  constructor(private service: UploadService) { }

  ngOnInit() {
  }

  getFileDetails (e) {
    this.filePath = e.target.files[0];
  }

  onUpload() {
    console.log('onUpload()...');
    const frmData = new FormData();
    frmData.append('multipartFile', this.filePath);
    this.service.upload(frmData).subscribe(data => {
        console.log ('Upload Response...');
      }
    );
  }

}
