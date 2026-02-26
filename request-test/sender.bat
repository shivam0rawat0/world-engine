curl -v -X POST http://localhost:8080/engine ^
     -H "Content-Type: application/octet-stream" ^
     --data-binary "@data.bin"