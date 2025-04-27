from mocks.payloads import mock_criminal, mock_iris_modality, mock_identification_parameters, mock_verification_parameters


def get_enroll_without_modalities_request():
    return {
        "criminal": mock_criminal,
        "modalities": []
    }


def get_enroll_with_modalities_request():
    return {
        "criminal": mock_criminal,
        "modalities": [mock_iris_modality]
    }


def get_add_modalities_request():
    return {
        "modalities": [mock_iris_modality]
    }


def get_identification_request():
    return {
        "identificationParameters": mock_identification_parameters,
        "modality": mock_iris_modality
    }


def get_verification_request(criminal_id):
    return {
        "verificationParameters": mock_verification_parameters,
        "modality": mock_iris_modality,
        "criminalId": criminal_id
    }
