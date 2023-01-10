Feature: Login to HRM Application 
 
Background: 
   Given User launched myntra webpage 
  
   @ValidCredentials
   Scenario: Login with valid credentials
      
    When User enters username as "Admin" and password as "admin123"
    Then User should be able to login sucessfully and new page open
     
