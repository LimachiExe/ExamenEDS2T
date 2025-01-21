package limachiperezdavid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Concierto {
	
	private String nombre;
	private LocalDate fecha;
	private LocalTime hora;
	private String lugar;
	private int aforo;
	private double precioEntrada;
	private double incrementoCamping = 20;
	private double incrementoVip = 50;
	
	private ArrayList<Entrada> asistentes;
	
	/**
	 * @param nombre
	 * @param fecha
	 * @param hora
	 * @param lugar
	 * @param aforo
	 * @param precioEntrada
	 */
	public Concierto(String nombre, LocalDate fecha, LocalTime hora, String lugar, int aforo, double precioEntrada) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.lugar = lugar;
		this.aforo = aforo;
		this.asistentes = new ArrayList<Entrada>();
		this.precioEntrada = precioEntrada;
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public ArrayList<Entrada> getAsistentes() {
		return asistentes;
	}
	
	
	public double getPrecioEntrada() {
		return precioEntrada;
	}
	
	public void setPrecioEntrada(double precio) {
		precioEntrada = precio;
	}
	
	public double getIncrementoCamping() {
		return incrementoCamping;
	}
	
	public void setIncrementoCamping(double precio) {
		incrementoCamping = precio;
	}
	
	public double getIncrementoVip() {
		return incrementoVip;
	}
	
	public void setIncrementoVip(double precio) {
		incrementoVip = precio;
	}
	
	public double getRecaudacion() {
		double total = 0;
		for(Entrada e : asistentes) {
			total += e.getPrecio();
		}
		return total;
	}
	
	public boolean esCompleto() {
		return asistentes.size() == aforo;
	}

	@Override
	public String toString() {
		return "Concierto [nombre= " + nombre + ", fecha=" + fecha + ", hora=" + hora.format(DateTimeFormatter.ofPattern("HH:mm")) +
				", lugar=" + lugar + ", asistentes=" + asistentes.size() + ", completo=" + esCompleto() +
				", recaudacion=" + getRecaudacion() + "]";
	}
	
	
	public Entrada venderEntrada(String propietario, boolean vip, boolean camping) {
		if(!esCompleto()) {
			Entrada e = new Entrada(this, propietario, vip, camping);
			asistentes.add(e);
			return e;
		}
		return null;
	}

	
	public static void main(String[] args) {
		// Introducir fecha concreta
		LocalDate fecha = LocalDate.parse("15/12/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		// Introducir hora concreta
		LocalTime hora = LocalTime.parse("17:30");
		
		Concierto c = new Concierto("JavaFest", fecha, hora, "Parque", 8, 10);
		c.venderEntrada("Juan", false, false);
		c.venderEntrada("Paco", false, true);
		c.venderEntrada("Pedro", true, false);
		c.venderEntrada("Carlos", true, true);
		System.out.println(c);
		System.out.println(c.asistentes);
		
		// Cambiar fecha a actual
		c.setFecha(LocalDate.now());
		// Cambiar hora a actual
		c.setHora(LocalTime.now());
		// Actualizar precio entrada
		c.setPrecioEntrada(40);
		// Actualizar importe Camping
		c.setIncrementoCamping(50);
		// Actualizar importe VIP
		c.setIncrementoVip(100);
		
		System.out.println();
		c.venderEntrada("Richi", false, false);
		c.venderEntrada("Mike", false, true);
		c.venderEntrada("Ted", true, false);
		c.venderEntrada("CJ", true, true);
		System.out.println(c);
		System.out.println(c.asistentes);
	}
}