# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# NO CHECKED-IN PROTOBUF GENCODE
# source: identificator.proto
# Protobuf Python Version: 5.29.0
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import runtime_version as _runtime_version
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
_runtime_version.ValidateProtobufRuntimeVersion(
    _runtime_version.Domain.PUBLIC,
    5,
    29,
    0,
    '',
    'identificator.proto'
)
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x13identificator.proto\x12&sk.stuba.fei.api.msus.dp.identificator\"\xa2\x01\n\x15IdentificationRequest\x12\x14\n\x0cmodalityType\x18\x01 \x01(\t\x12\x0f\n\x07rawData\x18\x02 \x01(\t\x12\x62\n\x18identificationParameters\x18\x03 \x01(\x0b\x32@.sk.stuba.fei.api.msus.dp.identificator.IdentificationParameters\"\xb0\x01\n\x13VerificationRequest\x12\x14\n\x0cmodalityType\x18\x01 \x01(\t\x12\x0f\n\x07rawData\x18\x02 \x01(\t\x12\x12\n\ncriminalId\x18\x03 \x01(\t\x12^\n\x16verificationParameters\x18\x04 \x01(\x0b\x32>.sk.stuba.fei.api.msus.dp.identificator.VerificationParameters\"\\\n\x16IdentificationResponse\x12\x42\n\x07hitlist\x18\x01 \x03(\x0b\x32\x31.sk.stuba.fei.api.msus.dp.identificator.Candidate\"<\n\x14VerificationResponse\x12\x10\n\x08verified\x18\x01 \x01(\x08\x12\x12\n\nmatchScore\x18\x02 \x01(\x02\"O\n\x18IdentificationParameters\x12\x1b\n\x13matchScoreThreshold\x18\x01 \x01(\x02\x12\x16\n\x0e\x63\x61ndidateCount\x18\x02 \x01(\x03\"5\n\x16VerificationParameters\x12\x1b\n\x13matchScoreThreshold\x18\x01 \x01(\x02\"c\n\tCandidate\x12\x42\n\x08\x63riminal\x18\x01 \x01(\x0b\x32\x30.sk.stuba.fei.api.msus.dp.identificator.Criminal\x12\x12\n\nmatchScore\x18\x02 \x01(\x02\"\xa1\x01\n\x08\x43riminal\x12\n\n\x02id\x18\x01 \x01(\t\x12\x11\n\tfirstName\x18\x02 \x01(\t\x12\x10\n\x08lastName\x18\x03 \x01(\t\x12\x13\n\x0bphoneNumber\x18\x04 \x01(\t\x12\r\n\x05\x65mail\x18\x05 \x01(\t\x12@\n\x07\x61\x64\x64ress\x18\x06 \x01(\x0b\x32/.sk.stuba.fei.api.msus.dp.identificator.Address\"`\n\x07\x41\x64\x64ress\x12\x0f\n\x07\x63ountry\x18\x01 \x01(\t\x12\x0c\n\x04\x63ity\x18\x02 \x01(\t\x12\x0e\n\x06street\x18\x03 \x01(\t\x12\x14\n\x0cstreetNumber\x18\x04 \x01(\t\x12\x10\n\x08postCode\x18\x05 \x01(\t2\xa5\x02\n\rIdentificator\x12\x8b\x01\n\x08Identify\x12=.sk.stuba.fei.api.msus.dp.identificator.IdentificationRequest\x1a>.sk.stuba.fei.api.msus.dp.identificator.IdentificationResponse\"\x00\x12\x85\x01\n\x06Verify\x12;.sk.stuba.fei.api.msus.dp.identificator.VerificationRequest\x1a<.sk.stuba.fei.api.msus.dp.identificator.VerificationResponse\"\x00\x42\x02P\x01\x62\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'identificator_pb2', _globals)
if not _descriptor._USE_C_DESCRIPTORS:
  _globals['DESCRIPTOR']._loaded_options = None
  _globals['DESCRIPTOR']._serialized_options = b'P\001'
  _globals['_IDENTIFICATIONREQUEST']._serialized_start=64
  _globals['_IDENTIFICATIONREQUEST']._serialized_end=226
  _globals['_VERIFICATIONREQUEST']._serialized_start=229
  _globals['_VERIFICATIONREQUEST']._serialized_end=405
  _globals['_IDENTIFICATIONRESPONSE']._serialized_start=407
  _globals['_IDENTIFICATIONRESPONSE']._serialized_end=499
  _globals['_VERIFICATIONRESPONSE']._serialized_start=501
  _globals['_VERIFICATIONRESPONSE']._serialized_end=561
  _globals['_IDENTIFICATIONPARAMETERS']._serialized_start=563
  _globals['_IDENTIFICATIONPARAMETERS']._serialized_end=642
  _globals['_VERIFICATIONPARAMETERS']._serialized_start=644
  _globals['_VERIFICATIONPARAMETERS']._serialized_end=697
  _globals['_CANDIDATE']._serialized_start=699
  _globals['_CANDIDATE']._serialized_end=798
  _globals['_CRIMINAL']._serialized_start=801
  _globals['_CRIMINAL']._serialized_end=962
  _globals['_ADDRESS']._serialized_start=964
  _globals['_ADDRESS']._serialized_end=1060
  _globals['_IDENTIFICATOR']._serialized_start=1063
  _globals['_IDENTIFICATOR']._serialized_end=1356
# @@protoc_insertion_point(module_scope)
