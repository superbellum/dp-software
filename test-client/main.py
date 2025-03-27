import base64
import os, re
from random import randint
import requests
from time import sleep

data_filename = 'C:/Users/gughp/Desktop/data'

cities = [
    "New York",  # USA
    "Tokyo",  # Japan
    "London",  # UK
    "Shanghai",  # China
    "São Paulo",  # Brazil
    "Mumbai",  # India
    "Paris",  # France
    "Istanbul",  # Turkey
    "Mexico City",  # Mexico
    "Jakarta"  # Indonesia
]

countries = [
    "United States",
    "China",
    "India",
    "Brazil",
    "Germany",
    "United Kingdom",
    "France",
    "Japan",
    "Canada",
    "Australia"
]

streets = [
    "Broadway",  # New York, USA
    "Champs-Élysées",  # Paris, France
    "Wall Street",  # New York, USA
    "Abbey Road",  # London, UK
    "Fifth Avenue",  # New York, USA
    "Las Ramblas",  # Barcelona, Spain
    "Orchard Road",  # Singapore
    "Nevsky Prospect",  # St. Petersburg, Russia
    "Shibuya Street",  # Tokyo, Japan
    "Nanjing Road"  # Shanghai, China
]

first_names = [
    "James", "Olivia", "Liam", "Emma", "Noah",
    "Ava", "William", "Sophia", "Benjamin", "Mia"
]

last_names = [
    "Smith", "Johnson", "Williams", "Brown", "Jones",
    "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"
]

emails = [
    "not.a.robot@trustme.com",
    "404brainnotfound@oops.com",
    "icantremember@myemail.com",
    "whydoineedanemail@idk.com",
    "password123@hacked.com",
    "definitelynotaspy@cia.gov",
    "helpmeout@broke.com",
    "totallyreal@fakeemail.com",
    "lastplacewinner@loser.com",
    "sendmoney@now.com"
]


def enroll_new_criminal() -> str:
    json = {
        "criminal": {
            "address": {
                "city": f'{cities[randint(0, 9)]}',
                "country": f"{countries[randint(0, 9)]}",
                "postCode": f"{randint(10000, 99999)}",
                "street": f"{streets[randint(0, 9)]}",
                "streetNumber": f"{randint(1, 1787)}"
            },
            "email": f"{emails[randint(0, 9)]}",
            "firstName": f"{first_names[randint(0, 9)]}",
            "lastName": f"{last_names[randint(0, 9)]}",
            "phoneNumber": f"555 {randint(100, 999)} {randint(100, 999)}"
        },
        "modalities": []
    }

    try:
        response = requests.post('http://localhost:8090/api/criminals', json=json)
        print(f'Enrolling criminal: {response.status_code}')
        if response.status_code == 200:
            return response.json()["id"]
        else:
            return ""
    except Exception as exc:
        print(f'Enroll error: {exc}')
        return ""


def add_modality_to_criminal(criminal_id: str, data: str):
    json = {
        "modalities": [
            {
                "type": "FINGERPRINT",
                "data": data
            }
        ]
    }

    try:
        response = requests.post(f'http://localhost:8090/api/criminals/{criminal_id}/modalities', json=json)
        print(f'Adding modality to criminal ({criminal_id}); response code: {response.status_code}')
    except Exception as exc:
        print(f'Adding request error: {exc}')


counter = 0
f_counter = 0
criminal_id = ""

fingerprint_file_names = os.listdir(f'{data_filename}/fingerprints')
fingerprint_file_names = sorted(fingerprint_file_names, key=lambda x: int(re.match(r"(\d+)__", x).group(1)))

for fingerprint_file_name in fingerprint_file_names:
    if f_counter == 100:
        break

    counter += 1

    with open(f'{data_filename}/fingerprints/{fingerprint_file_name}', 'rb') as fingerprint_file:
        fingerprint_file_content = fingerprint_file.read()
        fingerprint_data_base64 = base64.b64encode(fingerprint_file_content).decode()

        if f_counter % 10 == 0:
            criminal_id = enroll_new_criminal()

        f_counter += 1

        if criminal_id != "":
            add_modality_to_criminal(criminal_id=criminal_id, data=fingerprint_data_base64)
