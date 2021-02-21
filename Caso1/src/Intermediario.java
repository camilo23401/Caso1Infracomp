
public class Intermediario extends Thread
{

	private boolean prodint;
	private static Buzon buzonIntermedio;
	private static int MAX_PRODUCTOS;
	//private Buzon buzonProductores;
	private Buzon buzonPOC;
	
	public Intermediario(boolean pProdint, Buzon pBuzonIntermedio, Buzon pBuzonPOC, int maxProductos)
	{
		this.prodint = pProdint;
		Intermediario.buzonIntermedio = pBuzonIntermedio;
		Intermediario.MAX_PRODUCTOS = maxProductos;
		this.buzonPOC = pBuzonPOC;
	}
	
	public void run()
	{
		int contador = 0; //Revisar
		System.out.println("Inicio intermediario " + prodint);
		while (contador < MAX_PRODUCTOS) {	
			Producto prod;
			if(prodint)
			{
				prod = buzonPOC.sacarProductoINT();//sacar producto buzon productores
				System.out.println("Intermediario " + prodint + " saca producto " + prod.getIdProducto() + prod.darTipo());
				buzonIntermedio.almacenarProductoINT(prod); //meter producto buzon intermediario
				System.out.println("Intermediario " + prodint + " mete producto " + prod.getIdProducto() + prod.darTipo());
			}
			else {
				prod = buzonIntermedio.sacarProductoINT(); //sacar producto del buzon intermediario
				System.out.println("Intermediario " + prodint + " saca producto " + prod.getIdProducto() + prod.darTipo());
				buzonPOC.almacenarProductoINT(prod);//meter producto en buzon consumidores
				System.out.println("Intermediario " + prodint + " mete producto " + prod.getIdProducto() + prod.darTipo());
			}
			contador++;
			System.out.println("Intermedio "+ prodint + contador);
		}
		System.out.println("Acabé intermediario "+prodint);
		
	}
}
