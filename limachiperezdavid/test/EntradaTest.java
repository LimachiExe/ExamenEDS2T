package limachiperezdavid.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import limachiperezdavid.Concierto;
import limachiperezdavid.Entrada;

class EntradaTest {

	@Test
	void testEntrada() {
		    
		    Concierto concierto = new Concierto(
		        "CocaColaFest",                           
		        LocalDate.of(2069, 7, 21),            
		        LocalTime.of(20, 0),                   
		        "Santiago Bernabeu",                    
		        500,                                   
		        100                                    
		    );
		   
		    Entrada entrada = new Entrada(concierto, "Florentino Perez", true, true);

		    double precioEsperado = concierto.getPrecioEntrada() 
		                          + concierto.getIncrementoVip() 
		                          + concierto.getIncrementoCamping();

		    assertEquals("Florentino Perez", entrada.getPropietario(), "El propietario no coincide");
		    assertEquals(precioEsperado, entrada.getPrecio(), 0.001, "El precio no es correcto");
		    assertTrue(entrada.toString().contains("CocaColaFest"), "El nombre del concierto no es aparece.");
		}

	}