# locust -f criminals/enroll/main.py

from locust import HttpUser, task, between
from mocks.requests import get_enroll_without_modalities_request


class Enroll(HttpUser):
    host = 'http://localhost:8090'
    wait_time = between(1, 3)

    @task
    def enroll_without_modalities(self):
        self.client.post('/api/criminals', json=get_enroll_without_modalities_request())
