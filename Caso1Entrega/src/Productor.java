public class Productor extends Thread
{
	private int id;
	
	private String tipoProduce;
	
	private static Buzon buzonAsignado;
	
	private int maxProd;
	
	public Productor(int id, String pTipoProduce, Buzon pBuzonAsignado, int pMaxProd)
	{
		this.id=id;
		this.tipoProduce = pTipoProduce;
		Productor.buzonAsignado = pBuzonAsignado;
		this.maxProd = pMaxProd;
	}

	public void run()
	{
		for(int i=0; i<maxProd; i++)
		{
			buzonAsignado.almacenarProductoPROD(new Producto(id+" - "+i, tipoProduce));
		}
		System.out.println("Acabo productor");
	}
}
