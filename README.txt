Coding is done with H2 db. Not kept any relations B/w the tables manually saved the parent key in child.
In Future planning to explore in No sql db.

1) Created Test Data from resourse/json file
2) APIs exposed to (These APIs will support any kind of Booking system like Movie,Vehicles Train,Bus,Flight)
   --> Find all available flights  //
   --> Find details row level seat available based on flight-id
   --> Lock a desired seats from rows
   --> Book locked or selected seats
   --> Un lock seats


Note: Currently locking/unlock seats done in Hashmap we can use any caching technique with TTL.

PostMan collections:
https://api.postman.com/collections/1089508-789a77f9-79ae-485e-ae9a-b8360f64339f?access_key=PMAT-01GPTQSA73ZQ22FZ05GW2YMKJW

Swagger--> http://localhost:8080/swagger-ui/#/
