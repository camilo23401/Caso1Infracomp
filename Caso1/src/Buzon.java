import java.util.ArrayList;

public class Buzon {

	private int tamano;
	private String tipo;
	public ArrayList<Producto> almacenados;


	public Buzon(String pTipo, int pTamano)
	{
		this.tipo = pTipo;
		this.tamano = pTamano;
		this.almacenados = new ArrayList<Producto>();
	}

	public void almacenarProductoINT(Producto pProducto)
	{
		synchronized(this)
		{
			try
			{
				while(almacenados.size()==tamano)
				{
					System.out.println("Buzon intermedio lleno. Pasa a espera");
					wait();
				}
			}
			catch(Exception e)
			{

			}
		}
		synchronized(this)
		{
			System.out.println("Se agregó a buzon intermedio");
			almacenados.add(pProducto);
			notify();
			//falta sacar del buzón PROD y notificar a los productores (NotifyAll)
		}
	}

	public void almacenarProductoPROD(Producto pProducto)
	{
		try
		{
			while(almacenados.size()==tamano)
			{
				System.out.println("Buzon productores lleno. Pasa a espera");
				Thread.yield();
			}
		}
		catch(Exception e)
		{

		}
		synchronized(this)
		{
			System.out.println("Se agregó a buzon productores " + pProducto.getIdProducto() + pProducto.darTipo());
			almacenados.add(pProducto);
			notify(); //No se necesita notify() con yield
			//falta sacar del buzón PROD y notificar a los productores (NotifyAll)
		}
	}

	public Producto sacarProductoINT()
	{
		synchronized(this)
		{
			try
			{
				while(almacenados.size()==0)
				{
					System.out.println("Buzon vacío. Pasa a espera");
					wait();
				}
			}
			catch(Exception e)
			{

			}
		}
		synchronized(this)
		{
			Producto p = almacenados.remove(0);
			notify();
			return p;
			//Falta meter al buzon CONS y notificar a los consumidores (NotifyAll)
		}
	}

	public Producto sacarProductoCONS(String tipoProducto)
	{

		try
		{
			while(almacenados.size()==0 || !hayTipo(tipoProducto))
			{
				System.out.println("Buzon consumidores vacío o no hay tipo correspondiente. Pasa a espera");
				Thread.yield();
			}
		}
		catch(Exception e)
		{

		}
		synchronized(this)
		{
			Producto p = sacarProductoTipo(tipo);
			notify();
			return p;
			//Falta meter al buzon CONS y notificar a los consumidores (NotifyAll)
		}
	}



	public boolean hayTipo(String pTipo)
	{
		for(int i=0;i<almacenados.size();i++)
		{
			if(almacenados.get(i).darTipo()==pTipo)
				return true;
		}
		return false;
	}

	public Producto sacarProductoTipo (String tipo) {
		for(int i=0;i<almacenados.size();i++)
		{
			if(almacenados.get(i).darTipo()==tipo)
				return almacenados.remove(i);
		}
		return null;
	}

}
