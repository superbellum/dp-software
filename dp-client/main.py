import base64
import os
from random import randint
import requests
from time import sleep

URL = 'http://localhost:8090/api/criminals'

counter = 1
err_counter = 0

for fingerprint_file_name in os.listdir('../data/fingerprints'):
    with open(f'../data/fingerprints/{fingerprint_file_name}', 'rb') as fingerprint_file:
        fingerprint_file_content = fingerprint_file.read()
        fingerprint_data_base64 = base64.b64encode(fingerprint_file_content).decode()

        body = {
            "criminal": {
                "address": {
                    "city": f'Blava{randint(1, 1787)}',
                    "country": f"Slovakia{randint(1, 1787)}",
                    "postCode": f"54321{randint(1, 1787)}",
                    "street": f"Prva{randint(1, 1787)}",
                    "streetNumber": f"{randint(1, 1787)}"
                },
                "email": f"{randint(1, 1787)}.{randint(1, 1787)}@gmail.com",
                "firstName": f"{randint(1, 1787)}",
                "lastName": f"{randint(1, 1787)}",
                "phoneNumber": f"{randint(1, 1787)}"
            },
            "modalities": [
                {
                    "data": f"{fingerprint_data_base64}",
                    "type": "FINGERPRINT"
                }
            ]
        }

        try:
            response = requests.post(URL, json=body)
            print(f'[{counter}]: {response.status_code}')
            counter += 1
        except Exception as exc:
            print(f'[{counter}]: {exc}')
            err_counter += 1

        sleep(0.1)

print(f'success/fail: {counter}/{err_counter}')
