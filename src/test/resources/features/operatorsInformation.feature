Feature: Information to operators
	The system will inform to operators about incidences that they have asigned
Scenario Outline: Dangerous values
    Given a operator with email <email> 
    When he wants control their incidences  
    Then the system will give his <numero> incidences
    Examples:
      | email    	 		| numero  	|
      | ivan@gmail.com    	| 2			|
      | antonio@gmail.com   | 4  		|
      | hugo@gmail.com    	| 3	 		|
      | mirza@gmail.com    	| 1      	|