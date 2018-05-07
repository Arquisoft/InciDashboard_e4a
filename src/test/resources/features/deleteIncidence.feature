Feature: Change incidence state
	The system delete one incidence and there are one less.
Scenario: Dangerous values
    Given a incidence with name "Prueba11"
    When it deletes the incidence 
    Then there are one less 