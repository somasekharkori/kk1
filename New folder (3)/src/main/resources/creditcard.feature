Feature: Credit Card CRUD Operation
  Scenario: client makes call to GET credit card details
    Given Get URI
    When the client calls 
    Then the client receives status code of 200
    And the client receives credit card details
    
  Scenario: client makes call to POST credit card details
    Given Post URI
    And set credit card details
    When the client post credit card details
    Then recieves status code of 200
    
  Scenario: client makes call to Update card details
    Given Put URI
    And Set credit card details to modify
    When the client send put request
    Then the client receives 1
    
  Scenario: client makes call to delete record
    Given Delete URI
    When the client call delete uri
    Then client recieves status code 200