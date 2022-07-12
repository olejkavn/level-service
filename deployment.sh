mvn clean install
docker build -t pet-project/level-service .
docker tag pet-project/level-service registry.digitalocean.com/pet-project/level-service
docker push registry.digitalocean.com/pet-project/level-service
kubectl delete deploy level-service
kubectl delete service level-service
kubectl create -f kube-manifests/deployment.yaml
kubectl get pods

