import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GetAllCriminalsResponse} from '../model/payload/get-all-criminals-response.model';
import {Observable} from 'rxjs';
import {GetModalitiesForCriminalResponse} from '../model/payload/get-modalities-for-criminal-response.model';
import {MessageResponse} from '../model/payload/message-response.model';
import {NewCriminal} from '../model/new-criminal.model';
import {AddModalityForCriminalRequest} from '../model/add-modality-for-criminal-request.model';

const httpOptions = {
  headers: new HttpHeaders({'content-type': 'application/json'})
};

@Injectable({providedIn: 'root'})
export class CriminalService {
  private static readonly API_URL = 'http://localhost:8090/api/criminals';

  private httpClient = inject(HttpClient);

  createCriminal(newCriminal: NewCriminal) {
    return this.httpClient.post(CriminalService.API_URL, {criminal: newCriminal, modalities: []}, httpOptions);
  }

  getAllCriminals(): Observable<GetAllCriminalsResponse> {
    return this.httpClient.get<GetAllCriminalsResponse>(CriminalService.API_URL, httpOptions);
  }

  getModalitiesForCriminal(criminalId: string): Observable<GetModalitiesForCriminalResponse> {
    return this.httpClient.get<GetModalitiesForCriminalResponse>(`${CriminalService.API_URL}/${criminalId}/modalities`, httpOptions);
  }

  addModalitiesToCriminal(criminalId: string, request: AddModalityForCriminalRequest): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${CriminalService.API_URL}/${criminalId}/modalities`, request, httpOptions);
  }

  deleteAllModalitiesForCriminal(criminalId: string): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(`${CriminalService.API_URL}/${criminalId}/modalities`);
  }

  deleteModalityForCriminal(criminalId: string, modalityId: string): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(`${CriminalService.API_URL}/${criminalId}/modalities/${modalityId}`);
  }

  deleteCriminalById(criminalId: string): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(`${CriminalService.API_URL}/${criminalId}`);
  }
}
