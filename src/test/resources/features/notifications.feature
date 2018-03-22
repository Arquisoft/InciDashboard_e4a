Feature: Dangerous values
	If values of certain properties will be dangerous, the system will 
	notice workers to take actions
Scenario Outline: Dangerous values
    Given a incidence <incidence>
    Then the system will notice with message <message>
    Examples:
      | incidence    | message  						 |
      | Prueba1    	 | La hemos cagao       			 |
      | Prueba2    	 | Se ha roto    					 |
      | Prueba3    	 | Pa cuenca a reparar      		 |
      | Prueba4    	 | La hemos cagao x2      			 |
      | Prueba5    	 | Notificacion de incidencia 5      |
      | Prueba6    	 | Notificacion de incidencia 6      |