Run with:

`podman build -f Dockerfile -t org.example.load-balancer .`

Create bridge network if it does not exist:

`podman network create --driver bridge andrea_bridge_default`

`podman run --replace -e SPRING_PROFILES_ACTIVE=container --net andrea_bridge_default -p 8080:8080 -p 18080:18080 --name=load-balancer -d localhost/org.example.load-balancer:latest`

Test with

`watch -n 1 curl localhost:8080/hi` or `watch -n 1 curl localhost:8080/hello` 