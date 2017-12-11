-install JDK 1.8+
-instal Maven 3+
-install local Red Hat CDK from https://access.redhat.com/documentation/en-us/red_hat_container_development_kit/
Please install completely including fuse integration services, jboss ide, openshift, virtualbox etc.


-install kompose to convert docker-compose file to kubernetes configs:
```curl -L https://github.com/kubernetes/kompose/releases/download/v1.5.0/kompose-darwin-amd64 -o kompose```

```
minishift start
minishift login (developer/developer)
cd camel-data-service
mvn fabric8:deploy
```


-2 services and pods should be deployed i.e. mongodb and data-service
-You can got to openshift console https://192.168.99.100:8443/console/ and browse through pod logs and terminal e.g. to access mongodb etc.

-Create test data in mongo by going to Applications>Pods><mongodb pod>>Terminal and
```
mongo
use test
db.test.insert({'item': 1});
```
  
-Now expose the data-service to your localhost so that you can test
```
oc port-forward -p <data-service-pod> 8082:8082
curl -H "Content-Type: application/json" -X POST -d '{"item": "1"}' http://localhost:8082/data/test
curl -H "Content-Type: application/json" -X POST -d '{"item": "1"}' http://localhost:8082/data/test
```

application.properties allows you to change the mongodb db name.

