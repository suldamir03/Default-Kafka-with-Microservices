A small microservice application where the communication between them is thanks to Kafka.

The project has a working kafka producer and consumer configuration.

Example GET request at url: http://localhost:8000/departments/2

sample response in Employee Service logs

Department event received in employee service => {"id":2, "name": "Marketing"}


POST request to http://localhost:8000/department

With body:

{
{ "id": 3,
"name": "Top"
}

sample response in Employee Service logs:

Department event received in employee service => { "id":3, "name": "Top"}