# Store_datasystemer_oblig3_gruppe10_maven
Repository for Oblig 3 in the Uit Course Store datasystemer

### To run the application via docker, use command:
docker run -d -p 8080:8080 --name coronaapi mko095/coronaapi:latest
after this is run api will be available at: http://localhost:8080/api/department/

###This must be run before the app is run to ensure proper function
the app can be found at https://github.com/Mkogithub/Oblig3_app



###to run docker image with swagger docs, use command: 
docker run -d -p 8080:8080 --name coronaapidocs mko095/coronaapiwithdocs:latest
after this is run, api docs will be available at:
http://localhost:8080/swagger-ui.html#/
#### This version will not work with the app
