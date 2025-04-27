# locust -f biometrics/identification/main.py

from locust import HttpUser, task, between
from mocks.requests import get_enroll_with_modalities_request, get_identification_request


class Identify(HttpUser):
    host = 'http://localhost:8090'
    wait_time = between(1, 3)

    def on_start(self):
        self.enroll_criminal()

    @task
    def identify(self):
        self.client.post(f'/api/biometrics/identify', json=get_identification_request())

    def enroll_criminal(self):
        self.client.post("/api/criminals", json=get_enroll_with_modalities_request(), catch_response=True)
