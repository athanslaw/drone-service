
## Drones Service REST API


---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has: 
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task. 

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;

> Feel free to make assumptions for the design approach. 

---

### Requirements

While implementing your solution **please take care of the following requirements**: 

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---
### How to build

#### Requirements

- Java 8
- Java IDE (IntelliJ)
- MYSQL databse (Optional you can use in-memory databse)
- Postman(For testing ) 

### Steps by step for building and runing the project locally

- Clone the from the link git clone https://github.com/athanslaw/drone-service.git

Postman link: https://www.getpostman.com/collections/6728e771213a43a59ebd

- Open the cloned project in IntelliJ

- Allow maven to update all dependencies

- Maven Build the project and run

- Before running you can run the JUnit test cases to assert that everything is working correctly (I have included some of the JUnit tests)


---

### Testing the API
- Some of the assumption made for the purpose of this API design are:
- A medication can be loaded to multiple drones if the status is active / enable.
- Once a drone is in loaded state, new medications can no longer be added.
- The state can also be updated using the api in the postman collection for cases where you want to trigger the drone that is not yet fully loaded
- A periodic task runs every 10s but it doesn't update a database table. It only Displays the battery capacity on the log
- Medication image path is stored on the database.
- I used ENUM for drone model and state with the assumption that the order of the values will not change

TO DOs
- I did not implement security because of time
- I did not fully implement file upload for medication image

Open Postman
Import https://www.getpostman.com/collections/6728e771213a43a59ebd
These are the APIs
POST http://localhost:8082/drones (Register a drone)
GET http://localhost:8082/drones/model/MIDDLE_WEIGHT (view drones by model)
GET http://localhost:8082/drones/state/LOADED (View drones by state)

POST http://localhost:8082/medication (Save medication)
PUT http://localhost:8082/medication/enable/SQO122 (Activate medication for dispatch)
PUT http://localhost:8082/medication/disable/SQO122 (Deactivate medication so it is not considered during drone loading)
GET http://localhost:8082/medication (View saved medications)
GET http://localhost:8082/medication/active (View active medications)
GET http://localhost:8082/medication/inactive (View deactivated medications)

GET http://localhost:8082/dispatch/available-drones (View available drones for loading)
POST http://localhost:8082/dispatch/setup (Load drones with medications)
GET http://localhost:8082/dispatch/drone/SNO1234567862 (View drone content)
GET http://localhost:8082/dispatch/trigger/SNO1234567862 (Trigger drone for delivery)
GET http://localhost:8082/dispatch/medication/SQO122 (View drones having a particular medication)



Note: the ContentType is application/json



---

:scroll: **END** 


