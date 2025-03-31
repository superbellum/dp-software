from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class IdentificationRequest(_message.Message):
    __slots__ = ("modalityType", "data", "identificationParameters")
    MODALITYTYPE_FIELD_NUMBER: _ClassVar[int]
    DATA_FIELD_NUMBER: _ClassVar[int]
    IDENTIFICATIONPARAMETERS_FIELD_NUMBER: _ClassVar[int]
    modalityType: str
    data: str
    identificationParameters: IdentificationParameters
    def __init__(self, modalityType: _Optional[str] = ..., data: _Optional[str] = ..., identificationParameters: _Optional[_Union[IdentificationParameters, _Mapping]] = ...) -> None: ...

class VerificationRequest(_message.Message):
    __slots__ = ("modalityType", "data", "criminalId", "verificationParameters")
    MODALITYTYPE_FIELD_NUMBER: _ClassVar[int]
    DATA_FIELD_NUMBER: _ClassVar[int]
    CRIMINALID_FIELD_NUMBER: _ClassVar[int]
    VERIFICATIONPARAMETERS_FIELD_NUMBER: _ClassVar[int]
    modalityType: str
    data: str
    criminalId: str
    verificationParameters: VerificationParameters
    def __init__(self, modalityType: _Optional[str] = ..., data: _Optional[str] = ..., criminalId: _Optional[str] = ..., verificationParameters: _Optional[_Union[VerificationParameters, _Mapping]] = ...) -> None: ...

class IdentificationResponse(_message.Message):
    __slots__ = ("hitlist",)
    HITLIST_FIELD_NUMBER: _ClassVar[int]
    hitlist: _containers.RepeatedCompositeFieldContainer[Candidate]
    def __init__(self, hitlist: _Optional[_Iterable[_Union[Candidate, _Mapping]]] = ...) -> None: ...

class VerificationResponse(_message.Message):
    __slots__ = ("verified", "matchScore")
    VERIFIED_FIELD_NUMBER: _ClassVar[int]
    MATCHSCORE_FIELD_NUMBER: _ClassVar[int]
    verified: bool
    matchScore: float
    def __init__(self, verified: bool = ..., matchScore: _Optional[float] = ...) -> None: ...

class IdentificationParameters(_message.Message):
    __slots__ = ("matchScoreThreshold", "candidateCount")
    MATCHSCORETHRESHOLD_FIELD_NUMBER: _ClassVar[int]
    CANDIDATECOUNT_FIELD_NUMBER: _ClassVar[int]
    matchScoreThreshold: float
    candidateCount: int
    def __init__(self, matchScoreThreshold: _Optional[float] = ..., candidateCount: _Optional[int] = ...) -> None: ...

class VerificationParameters(_message.Message):
    __slots__ = ("matchScoreThreshold",)
    MATCHSCORETHRESHOLD_FIELD_NUMBER: _ClassVar[int]
    matchScoreThreshold: float
    def __init__(self, matchScoreThreshold: _Optional[float] = ...) -> None: ...

class Candidate(_message.Message):
    __slots__ = ("criminal", "matchScore")
    CRIMINAL_FIELD_NUMBER: _ClassVar[int]
    MATCHSCORE_FIELD_NUMBER: _ClassVar[int]
    criminal: Criminal
    matchScore: float
    def __init__(self, criminal: _Optional[_Union[Criminal, _Mapping]] = ..., matchScore: _Optional[float] = ...) -> None: ...

class Criminal(_message.Message):
    __slots__ = ("id", "firstName", "lastName", "phoneNumber", "email", "address")
    ID_FIELD_NUMBER: _ClassVar[int]
    FIRSTNAME_FIELD_NUMBER: _ClassVar[int]
    LASTNAME_FIELD_NUMBER: _ClassVar[int]
    PHONENUMBER_FIELD_NUMBER: _ClassVar[int]
    EMAIL_FIELD_NUMBER: _ClassVar[int]
    ADDRESS_FIELD_NUMBER: _ClassVar[int]
    id: str
    firstName: str
    lastName: str
    phoneNumber: str
    email: str
    address: Address
    def __init__(self, id: _Optional[str] = ..., firstName: _Optional[str] = ..., lastName: _Optional[str] = ..., phoneNumber: _Optional[str] = ..., email: _Optional[str] = ..., address: _Optional[_Union[Address, _Mapping]] = ...) -> None: ...

class Address(_message.Message):
    __slots__ = ("country", "city", "street", "streetNumber", "postCode")
    COUNTRY_FIELD_NUMBER: _ClassVar[int]
    CITY_FIELD_NUMBER: _ClassVar[int]
    STREET_FIELD_NUMBER: _ClassVar[int]
    STREETNUMBER_FIELD_NUMBER: _ClassVar[int]
    POSTCODE_FIELD_NUMBER: _ClassVar[int]
    country: str
    city: str
    street: str
    streetNumber: str
    postCode: str
    def __init__(self, country: _Optional[str] = ..., city: _Optional[str] = ..., street: _Optional[str] = ..., streetNumber: _Optional[str] = ..., postCode: _Optional[str] = ...) -> None: ...
