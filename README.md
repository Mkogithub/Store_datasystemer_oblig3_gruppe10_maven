# Store_datasystemer_oblig3_gruppe10_maven
Repository for Oblig 3 in the Uit Course Store datasystemer

Now also home to the updated app, which is found under zippedapp/oblig4

### To run the application via docker, use command:
docker run -d -p 8080:8080 --name coronaapi mko095/coronaapi:latest
after this is run api will be available at: http://localhost:8080/api/department/

### This must be run before the app is run to ensure proper function:
    #### Base Url in java/com/example/oblig3_0_3/util/Constants.kt
    must be changed to the host computers ipv4 address :8080/api/ so the client can connect to the api



### to run docker image with swagger docs, use command: 
docker run -d -p 8080:8080 --name coronaapidocs mko095/coronaapiwithdocs:latest
after this is run, api docs will be available at:
http://localhost:8080/swagger-ui.html#/
#### This version will not work with the app
