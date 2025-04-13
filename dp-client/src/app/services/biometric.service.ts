import {HttpClient, HttpHeaders} from '@angular/common/http';
import {inject, Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {IdentificationResponse} from '../model/payload/response/identification-response';
import {IdentificationRequest} from '../model/payload/request/identification-request';
import {VerificationResponse} from '../model/payload/response/verification-response';
import {VerificationRequest} from '../model/payload/request/verification-request';

const httpOptions = {
  headers: new HttpHeaders({'content-type': 'application/json'})
};

@Injectable({providedIn: 'root'})
export class BiometricService {
  private readonly API_URL = 'http://localhost:8090/api/biometrics';

  private httpClient = inject(HttpClient);

  verify(request: VerificationRequest): Observable<VerificationResponse> {
    return this.httpClient.post<VerificationResponse>(`${(this.API_URL)}/verify`, request, httpOptions);
  }

  identify(request: IdentificationRequest): Observable<IdentificationResponse> {
    return this.httpClient.post<IdentificationResponse>(`${(this.API_URL)}/identify`, request, httpOptions);
  }
}
