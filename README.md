Run with:

`podman build -f Dockerfile -t org.example.load-balancing-client .`

`podman run -p 8080:8080 --name=load-balancing-client -d localhost/org.example.load-balancing-client:latest`

Test with

`watch -n 1 curl localhost:8080/hi` or `watch -n 1 curl localhost:8080/hello` 