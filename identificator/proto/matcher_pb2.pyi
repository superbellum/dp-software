from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class MatchRequest(_message.Message):
    __slots__ = ("sampleTemplate", "realTemplate")
    SAMPLETEMPLATE_FIELD_NUMBER: _ClassVar[int]
    REALTEMPLATE_FIELD_NUMBER: _ClassVar[int]
    sampleTemplate: MatchTemplate
    realTemplate: MatchTemplate
    def __init__(self, sampleTemplate: _Optional[_Union[MatchTemplate, _Mapping]] = ..., realTemplate: _Optional[_Union[MatchTemplate, _Mapping]] = ...) -> None: ...

class MatchResponse(_message.Message):
    __slots__ = ("matchScore",)
    MATCHSCORE_FIELD_NUMBER: _ClassVar[int]
    matchScore: float
    def __init__(self, matchScore: _Optional[float] = ...) -> None: ...

class MatchTemplate(_message.Message):
    __slots__ = ("keypointsSize", "encodedDescriptor")
    KEYPOINTSSIZE_FIELD_NUMBER: _ClassVar[int]
    ENCODEDDESCRIPTOR_FIELD_NUMBER: _ClassVar[int]
    keypointsSize: int
    encodedDescriptor: str
    def __init__(self, keypointsSize: _Optional[int] = ..., encodedDescriptor: _Optional[str] = ...) -> None: ...
