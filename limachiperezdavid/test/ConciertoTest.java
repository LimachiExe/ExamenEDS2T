package limachiperezdavid.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import limachiperezdavid.Concierto;
import limachiperezdavid.Entrada;

class ConciertoTest {

	@Test
	void testConcierto() {
	    
	    Concierto concierto = new Concierto(
	        "FantaFest",                           
	        LocalDate.of(2025, 8, 15),            
	        LocalTime.of(19, 30),                  
	        "Camp Nou",                       
	        300,                                   
	        75.50                                 
	    );

	   
	    assertEquals("FantaTest", concierto.getNombre(), "El nombre del concierto no coincide");
	    assertEquals(LocalDate.of(2025, 8, 15), concierto.getFecha(), "La fecha del concierto no coincide");
	    assertEquals(LocalTime.of(19, 30), concierto.getHora(), "La hora del concierto no coincide");
	    assertEquals("Camp Nou", concierto.getLugar(), "El lugar del concierto no coincide.");
	    assertEquals(300, concierto.getAforo(), "El aforo del concierto no coincide.");
	    assertEquals(75.50, concierto.getPrecioEntrada(), 0.001, "El precio base del concierto no coincide");

	    
	    assertNotNull(concierto.getAsistentes(), "La lista de asistentes deberia estar con datos");
	    assertTrue(concierto.getAsistentes().isEmpty(), "La lista de asistentes deberia no tener datos");
	}

	@Test
	void testGetRecaudacion() {
	    
	    Concierto concierto = new Concierto(
	        "TravisFest",                          
	        LocalDate.of(2025, 7, 10),             
	        LocalTime.of(18, 0),                   
	        "Pisos Picados",                        
	        5,                                     
	        50.00                                 	    );

	    concierto.venderEntrada("Mbappe", false, false); 
	    concierto.venderEntrada("CR7", true, false);    
	    concierto.venderEntrada("Messi", false, true); 
	    concierto.venderEntrada("DanaYT", true, true);    

	    double precioBase = concierto.getPrecioEntrada();
	    double incrementoVip = concierto.getIncrementoVip();
	    double incrementoCamping = concierto.getIncrementoCamping();
	    double recaudacionEsperada = precioBase 
	                                + (precioBase + incrementoVip) 
	                                + (precioBase + incrementoCamping)
	                                + (precioBase + incrementoVip + incrementoCamping); 
	    
	    assertEquals(recaudacionEsperada, concierto.getRecaudacion(), 0.001, "La recaudación no esta bien");
	}
	
	@Test
	void testEsCompleto() {
	   
	    Concierto concierto = new Concierto(
	        "FiestaTomate",                         
	        LocalDate.of(2025, 6, 20),           
	        LocalTime.of(21, 0),                 
	        "Pleasent Park",                          
	        3,                                    
	        30.00                                 
	    );
	    
	    assertFalse(concierto.esCompleto(), "El concierto no debería estar completo al crearse.");

	    concierto.venderEntrada("Mbappe", false, false); 
	    concierto.venderEntrada("CR7", true, false);    
	    concierto.venderEntrada("Messi", false, true); 

	    assertTrue(concierto.esCompleto(), "El concierto debería estar completo tras vender todas las entradas.");
	    Entrada entradaAdicional = concierto.venderEntrada("DanaYT", true, true);
	    assertNull(entradaAdicional, "No se debería poder vender una entrada adicional cuando el concierto está completo.");
	}

	@Test
	void testVenderEntrada() {
	 
	    Concierto concierto = new Concierto(
	        "TylerFest",                           
	        LocalDate.of(2025, 5, 25),           
	        LocalTime.of(20, 0),               
	        "Mestalla",                         
	        2,                                 
	        40.00                                
	    );
	    Entrada entrada1 = concierto.venderEntrada("Mbappe", false, false);
	    assertNotNull(entrada1, "La primera entrada debería venderse bien");
	    assertEquals("Mbappe", entrada1.getPropietario(), "El propietario de la primera entrada no es el que deberia");

	    
	    Entrada entrada2 = concierto.venderEntrada("CR7", true, true);
	    assertNotNull(entrada2, "La primera entrada debería venderse bien");
	    assertEquals("CR7", entrada2.getPropietario(), "El propietario de la segunda entrada no es el que deberia");

	    
	    Entrada entrada3 = concierto.venderEntrada("Messi", false, false);
	    assertNull(entrada3, "No se debería haberse vendido la entrada, aforo superado");

	    
	    assertTrue(concierto.esCompleto(), "El concierto debería estar en aforo maximo tras la venta de entradas");
	}
}
