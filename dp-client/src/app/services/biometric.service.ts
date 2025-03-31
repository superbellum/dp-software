import {HttpClient, HttpHeaders} from '@angular/common/http';
import {inject, Injectable} from '@angular/core';
import {VerificationRequest} from '../model/payload/verification-request.model';
import {Observable} from 'rxjs';
import {VerificationResponse} from '../model/payload/verification-response.model';
import {IdentificationRequest} from '../model/payload/identification-request.model';
import {IdentificationResponse} from '../model/payload/identification-response.model';

const httpOptions = {
  headers: new HttpHeaders({'content-type': 'application/json'})
};

@Injectable({providedIn: 'root'})
export class BiometricService {
  private static readonly API_URL = 'http://localhost:8090/api/biometrics';

  private httpClient = inject(HttpClient);

  verify(request: VerificationRequest): Observable<VerificationResponse> {
    return this.httpClient.post<VerificationResponse>(`${BiometricService.API_URL}/verify`, request, httpOptions);
  }

  identify(request: IdentificationRequest): Observable<IdentificationResponse> {
    return this.httpClient.post<IdentificationResponse>(`${BiometricService.API_URL}/identify`, request, httpOptions);
  }
}
