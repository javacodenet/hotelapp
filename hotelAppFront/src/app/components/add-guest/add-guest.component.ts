import {Component, OnInit} from '@angular/core';
import {Guest} from '../../models/guest';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {UploadFileService} from '../../shared/upload-file/upload-file.service';
import {AddGuestService} from './add-guest.service';

@Component({
  selector: 'app-add-guest',
  templateUrl: './add-guest.component.html',
  styleUrls: ['./add-guest.component.css']
})
export class AddGuestComponent implements OnInit {
  newGuest: Guest = new Guest();
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = {percentage: 0};

  constructor(private uploadService: UploadFileService,
              private addGuestService: AddGuestService) {
  }

  ngOnInit() {
  }

  addGuest() {
    this.addGuestService.addGuest(this.newGuest).subscribe(
      res => {
        this.upload(res);
        this.newGuest = new Guest();
      },
      error => {
        console.log(error);
      });
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload(id: string) {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.uploadFile(this.currentFileUpload, id).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    })
  }
}
