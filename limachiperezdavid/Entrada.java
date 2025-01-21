package limachiperezdavid;

public class Entrada {
	
	private Concierto concierto;
	private String propietario;	
	private boolean vip;
	private boolean camping;
	private double precio;
	
	/**
	 * @param concierto
	 * @param propietario
	 * @param vip
	 * @param camping
	 * @param precio
	 */
	
	public Entrada(Concierto concierto, String propietario, boolean vip, boolean camping) {
		this.concierto = concierto;
		this.propietario = propietario;
		this.vip = vip;
		this.camping = camping;
		this.precio = getPrecioActualizado();
	}
	
	public String getPropietario() {
		return propietario;
	}
	
	private double getPrecioActualizado() {
		double total = concierto.getPrecioEntrada();
		if(vip) total += concierto.getIncrementoVip();
		if(camping) total += concierto.getIncrementoCamping();
		return total;
	}
	
	public double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Entrada [concierto=" + concierto.getNombre() + ", propietario=" + propietario + ", vip=" + vip + ", camping=" + camping +
				", precio=" + precio + "]";
	}

}