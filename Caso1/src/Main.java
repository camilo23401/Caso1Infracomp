import java.io.FileReader;
import java.util.Properties;

public class Main {


	public static void main(String[] args)
	{

		try
		{
			FileReader reader = new FileReader("./docs/config.properties");
			Properties p = new Properties();
			p.load(reader);
			int numProdCons = Integer.parseInt(p.getProperty("numProdCons"));
			int numProductos = Integer.parseInt(p.getProperty("numProductos"));
			int buzonesProd = Integer.parseInt(p.getProperty("buzonesProd"));
			int buzonesCons = Integer.parseInt(p.getProperty("buzonesCons"));

			int cv = numProdCons*numProductos;
			final int MAX_PRODUCTOS = numProductos;
			
			Buzon buzint = new Buzon("INT", 1);
			Buzon buzprod = new Buzon("PROD",buzonesProd);
			Buzon buzcons = new Buzon("CONS", buzonesCons);

			Productor[] productores = new Productor[2];
			for(int i=0; i<productores.length; i++)
			{
				if(i%2==0)
				{
					productores[i] = new Productor("A",buzprod,MAX_PRODUCTOS);
				}
				else {
					productores[i] = new Productor("B",buzprod,MAX_PRODUCTOS);	
				}
			}

			Consumidor[] consumidores = new Consumidor[2];
			for(int i=0; i<consumidores.length; i++)
			{
				if(i%2==0)
				{
					consumidores[i] = new Consumidor("A",buzprod,MAX_PRODUCTOS);
				}
				else {
					consumidores[i] = new Consumidor("B",buzprod,MAX_PRODUCTOS);	
				}
			}

			Intermediario izq = new Intermediario(true,buzint,buzprod,cv);
			Intermediario der = new Intermediario(false,buzint,buzcons,cv);

			izq.start();
			der.start();

			for(int i=0; i<productores.length; i++)
			{
				productores[i].start();
			}

			for(int i=0; i<consumidores.length; i++)
			{
				consumidores[i].start();
			}

		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
}
