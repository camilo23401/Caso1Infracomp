public class Productor extends Thread
{
	
	private String tipoProduce;
	
	private static Buzon buzonAsignado;
	
	private int maxProd;
	
	public Productor(String pTipoProduce, Buzon pBuzonAsignado, int pMaxProd)
	{
		this.tipoProduce = pTipoProduce;
		Productor.buzonAsignado = pBuzonAsignado;
		this.maxProd = pMaxProd;
	}

	public void run()
	{
		for(int i=0; i<maxProd; i++)
		{
			buzonAsignado.almacenarProductoPROD(new Producto(""+i, tipoProduce));
		}
		System.out.println("Acabé productor");
	}
}
