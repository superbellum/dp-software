import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GetAllCriminalsResponse} from '../model/payload/get-all-criminals-response.model';
import {Observable} from 'rxjs';
import {GetModalitiesForCriminalResponse} from '../model/payload/get-modalities-for-criminal-response.model';

const httpOptions = {
  headers: new HttpHeaders({'content-type': 'application/json'})
};

@Injectable({providedIn: 'root'})
export class CriminalService {
  private static readonly API_URL = 'http://localhost:8090/api/criminals';

  private httpClient = inject(HttpClient);

  getAllCriminals(): Observable<GetAllCriminalsResponse> {
    return this.httpClient.get<GetAllCriminalsResponse>(CriminalService.API_URL, httpOptions);
  }

  getModalitiesForCriminal(criminalId: string): Observable<GetModalitiesForCriminalResponse> {
    return this.httpClient.get<GetModalitiesForCriminalResponse>(`${CriminalService.API_URL}/${criminalId}/modalities`, httpOptions);
  }

  deleteAllModalitiesForCriminal(criminalId: string) {
    return this.httpClient.delete(`${CriminalService.API_URL}/${criminalId}/modalities`);
  }
}
