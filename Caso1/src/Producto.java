
public class Producto {
	
	private String idProducto;
	
	private String tipo;
	
	public Producto(String pIdProducto, String pTipo)
	{
		this.idProducto = pIdProducto;
		this.tipo = pTipo;
	}
	
	public String darTipo()
	{
		return tipo;
	}

	public String getIdProducto() {
		return idProducto;
	}
	

}
