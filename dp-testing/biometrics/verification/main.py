# locust -f biometrics/verification

from locust import HttpUser, task, between
from mocks.requests import get_enroll_with_modalities_request, get_verification_request


class Verify(HttpUser):
    host = 'http://localhost:8090'
    wait_time = between(1, 3)

    def on_start(self):
        self.enroll_criminal()

    @task
    def verify(self):
        if hasattr(self, "criminal_id"):
            self.client.post(f'/api/biometrics/verify', json=get_verification_request(self.criminal_id))
        else:
            self.enroll_criminal()

    def enroll_criminal(self):
        with self.client.post("/api/criminals", json=get_enroll_with_modalities_request(),
                              catch_response=True) as response:
            if response.status_code == 200:
                self.criminal_id = response.json()["id"]
                response.success()
            else:
                response.failure(f"Failed to create criminal: {response.text}")
