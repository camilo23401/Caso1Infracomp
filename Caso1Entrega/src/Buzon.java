import java.util.ArrayList;

public class Buzon {

	private int tamano;
	private String tipo;
	public ArrayList<Producto> almacenados;
	private Object prod;


	public Buzon(String pTipo, int pTamano)
	{
		this.tipo = pTipo;
		this.tamano = pTamano;
		this.almacenados = new ArrayList<Producto>();
		this.prod = new Object();
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
			System.out.println("Se agrego a buzon intermedio");
			almacenados.add(pProducto);
			notify();
		}
	}
	
	public void almacenarProductoCONS(Producto pProducto)
	{
		synchronized(prod)
		{
			try
			{
				while(almacenados.size()==tamano)
				{
					System.out.println("Buzon consumidores lleno. Pasa a espera");
					wait();
				}
			}
			catch(Exception e)
			{

			}
		}
		synchronized(almacenados)
		{
			System.out.println("Se agrego a buzon de consumidores");
			almacenados.add(pProducto);
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
			System.out.println("Se agrego a buzon productores " + pProducto.getIdProducto() + pProducto.darTipo());
			almacenados.add(pProducto);
			notify();
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
					System.out.println("Buzon vacio. Pasa a espera");
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
		}
	}

	public Producto sacarProductoCONS(String tipoProducto)
	{
		while(almacenados.size()==0 || !hayTipo(tipoProducto))
		{
			System.out.println("Buzon consumidores vacio o no hay tipo correspondiente. Pasa a espera");
			Thread.yield();
		}
		Producto p;
		synchronized(almacenados)
		{
			p = sacarProductoTipo(tipoProducto);
		}
		synchronized(prod)
		{
			prod.notifyAll();	
		}
		return p;
		
	}



	public boolean hayTipo(String pTipo)
	{
		if(!almacenados.isEmpty())
		{
			for(int i=0;i<almacenados.size();i++)
			{
				if(almacenados.get(i)!=null)
				{
					if(almacenados.get(i).darTipo()==pTipo)
						return true;
				}
			}
		}
		return false;
	}

	public Producto sacarProductoTipo (String tipo) {
		System.out.println("Intentando consumir producto");
		for(int i=0;i<almacenados.size();i++)
		{
			if(almacenados.get(i).darTipo()==tipo) {
				System.out.println("Consumiendo producto " + almacenados.get(i).getIdProducto() +" "+  almacenados.get(i).darTipo());
				return almacenados.remove(i);				
			}
		}
		return null;
	}

}
