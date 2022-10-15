# congestionTaxCalculator
Home Assignment from VCC : Congestion Tax Calculator. Restfull webservice calculates tolls of vehicle travelling each day. 


Description : 
Congestion Tax Calculator is a rest webservice which calculates toll prices for vehicles travelling with respective to time.
There is a list of vehicles which are considered as toll free vehichles, no toll tax is charged for those vehicles.
Also, there is a list of days for which no vehicle is charged.
Maximum toll a vehicle can pay each day is 60Kr.
If in an hour a vehicle is charged two times or more than one time then maximum charge must be considered.

Project Structure : 
Project is created in spring boot and testing is done using Junit.
There are in total 5 packages in source package
  1.service : This package contains two service classes where whole business logic and validation is done.
  2.dto : These are data transfer objects created to fetch data from YAML file and to fetch request body.
  3.domain : This package consist of all model objects
  4.controller : This package has rest controller which calls services created in service package
  5.config : classes created in this package consist of configuration properties which are required and configured at the time of server start up and some constants
  
  TEST Package :
  1. controller : Test cases related to controller classes are written. Also please refer to Postman_AWS_Test_Case.docx file.
  2. service : Unit test cases of service classes.
  
  Application is also deployed on AWS so we can test using postman.
