# dp-software

**Swagger-UI URL:** 
```bash
http://localhost:8090/swagger-ui/
```

**run to regenerate files for protobufs:**
```bash
python -m grpc_tools.protoc -I../proto --python_out=. --pyi_out=. --grpc_python_out=. matcher.proto
```

**start docker:**
```bash
docker-compose up --pull never|always
```
