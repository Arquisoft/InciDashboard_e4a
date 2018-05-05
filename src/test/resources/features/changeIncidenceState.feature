Feature: Change incidence state
	The system will inform to operators about incidences that they have asigned
Scenario: Dangerous values
    Given a operator with the email "ivan@gmail.com" 
    When he wants to change the state of a incidence with the name "Prueba6"  
    Then he changes the state "closed" 