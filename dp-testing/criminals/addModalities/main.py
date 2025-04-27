# locust -f criminals/addModalities/main.py

from locust import HttpUser, task, between
from mocks.requests import get_enroll_without_modalities_request, get_add_modalities_request


class AddModalities(HttpUser):
    host = 'http://localhost:8090'
    wait_time = between(1, 3)

    def on_start(self):
        self.enroll_criminal()

    @task
    def add_modalities(self):
        if hasattr(self, "criminal_id"):
            self.client.post(f'/api/criminals/{self.criminal_id}/modalities', json=get_add_modalities_request())
        else:
            self.enroll_criminal()

    def enroll_criminal(self):
        with self.client.post("/api/criminals", json=get_enroll_without_modalities_request(),
                              catch_response=True) as response:
            if response.status_code == 200:
                self.criminal_id = response.json()["id"]
                response.success()
            else:
                response.failure(f"Failed to create criminal: {response.text}")
