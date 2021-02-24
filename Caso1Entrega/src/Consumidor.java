import java.util.ArrayList;

public class Consumidor extends Thread
{

	private String tipoConsume;

	private static Buzon buzonAsignado;

	private int maxCons;
	
	private ArrayList<Producto> productos;

	public Consumidor(String pTipoConsume, Buzon pBuzonAsignado, int pMaxCons)
	{
		this.tipoConsume = pTipoConsume;
		Consumidor.buzonAsignado = pBuzonAsignado;
		this.maxCons = pMaxCons;
		productos = new ArrayList<Producto>();
	}

	public void run()
	{
		for(int i=0; i<maxCons; i++)
		{
			productos.add(buzonAsignado.sacarProductoCONS(tipoConsume));
		}
		System.out.println("Acabé consumidor, tengo " + productos.size());
	}
}
