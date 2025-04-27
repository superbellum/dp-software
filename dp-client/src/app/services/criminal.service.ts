import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MessageResponse} from '../model/payload/response/message-response';
import {AddModalitiesRequest} from '../model/payload/request/add-modalities-request';
import {GetCriminalModalitiesResponseType} from '../model/payload/response/get-criminal-modalities-response-type';
import {GetAllCriminalsResponse} from '../model/payload/response/get-all-criminals-response';
import {EnrollResponseType} from '../model/payload/response/enroll-response-type';
import {CriminalEnrollRequest} from '../model/payload/request/criminal-enroll-request';

const httpOptions = {
  headers: new HttpHeaders({'content-type': 'application/json'})
};

@Injectable({providedIn: 'root'})
export class CriminalService {
  private readonly API_URL = 'http://localhost:8090/api/criminals';

  private httpClient = inject(HttpClient);

  enroll(criminalEnrollRequest: CriminalEnrollRequest): Observable<EnrollResponseType> {
    return this.httpClient.post<EnrollResponseType>(this.API_URL, criminalEnrollRequest, httpOptions);
  }

  getAllCriminals(): Observable<GetAllCriminalsResponse> {
    return this.httpClient.get<GetAllCriminalsResponse>(this.API_URL, httpOptions);
  }

  getModalitiesForCriminal(criminalId: string): Observable<GetCriminalModalitiesResponseType> {
    return this.httpClient.get<GetCriminalModalitiesResponseType>(`${this.API_URL}/${criminalId}/modalities`, httpOptions);
  }

  addModalitiesToCriminal(criminalId: string, request: AddModalitiesRequest): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${this.API_URL}/${criminalId}/modalities`, request, httpOptions);
  }

  removeModalitiesOfCriminal(criminalId: string): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(`${this.API_URL}/${criminalId}/modalities`);
  }

  removeModalityOfCriminal(criminalId: string, modalityId: string): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(`${this.API_URL}/${criminalId}/modalities/${modalityId}`);
  }

  deleteCriminalById(criminalId: string): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(`${this.API_URL}/${criminalId}`);
  }

  deleteAllCriminals(): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(this.API_URL, httpOptions);
  }
}
