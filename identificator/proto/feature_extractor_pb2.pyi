from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class FeatureExtractionRequest(_message.Message):
    __slots__ = ("data",)
    DATA_FIELD_NUMBER: _ClassVar[int]
    data: str
    def __init__(self, data: _Optional[str] = ...) -> None: ...

class ExtractionResponse(_message.Message):
    __slots__ = ("keypointsSize", "encodedDescriptor")
    KEYPOINTSSIZE_FIELD_NUMBER: _ClassVar[int]
    ENCODEDDESCRIPTOR_FIELD_NUMBER: _ClassVar[int]
    keypointsSize: int
    encodedDescriptor: str
    def __init__(self, keypointsSize: _Optional[int] = ..., encodedDescriptor: _Optional[str] = ...) -> None: ...
