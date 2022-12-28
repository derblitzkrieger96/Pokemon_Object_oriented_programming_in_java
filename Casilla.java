package uniandes.cupi2.cupiPokemonGo.mundo;

public class Casilla 
{
    private int estado;
    private String ruta;
    private boolean tienePokebola;
    
    public final static int VACIA = 0;
    public final static int OCUPADA_POR_OBSTACULO = 1;
    public final static int OCUPADA_CON_POKEBOLA = 2;
    public final static int OCUPADA_POR_SQUIRTLE = 3;
    public final static int OCUPADA_POR_PIKACHU = 4;
    public final static int OCUPADA_POR_CHARMANDER = 5;
    public final static int OCUPADA_POR_JUGADOR = 6;
    
    
    public final static String RUTA_VACIA = "./data/imagenes/casillaVacia.png";
    public final static String RUTA_OCUPADA_POR_OBSTACULO = "./data/imagenes/casillaObstaculo.png";
    public final static String RUTA_OCUPADA_CON_POKEBOLA = "./data/imagenes/pokeball.png";
    public final static String RUTA_OCUPADA_POR_JUGADOR = "./data/imagenes/casillaJugador.png";
    public final static String RUTA_OCUPADA_POR_PIKACHU = "./data/imagenes/pikachu.png";
    public final static String RUTA_OCUPADA_POR_CHARMANDER = "./data/imagenes/charmander.png";
    public final static String RUTA_OCUPADA_POR_SQUIRTLE = "./data/imagenes/squirtle.png";    

    
	public  Casilla(int Pestado, String Pruta)
    {
    	estado = Pestado;
    	ruta = Pruta;
    	tienePokebola = false;
    }
    
	public boolean estaVacia()
	{
		boolean esVacia = false;
		if(estado==VACIA)
		{
			esVacia=true;
		}
		return esVacia;	
	}
	
	public int darEstado()
	{
		return estado;
	}
	
	public void asignarEstado(int PEstado)
	{
		estado = PEstado;
	}
	
	public void asignarRuta(String Pruta)
	{
		ruta = Pruta;
	}
	public String darRuta()
	{
		return ruta;
	}
	public void asignarPokebola()
	{
		tienePokebola=true;
	}
	public void quitarPokebola()
	{
		tienePokebola=false;
	}
	public boolean tienePokebola()
	{
		return tienePokebola;
	}
	public void VaciarCasilla()
	{
		estado=VACIA;
		ruta=RUTA_VACIA;
	}
	public void OcuparConPokebola()
	{
		estado=OCUPADA_CON_POKEBOLA;
		ruta=RUTA_OCUPADA_CON_POKEBOLA;
	}
	public void OcuparConJugador()
	{
		estado=OCUPADA_POR_JUGADOR;
		ruta=RUTA_OCUPADA_POR_JUGADOR;
	}
	public void OcuparConPikachu()
	{
		estado=OCUPADA_POR_PIKACHU;
		ruta=RUTA_OCUPADA_POR_PIKACHU;
	}
	public void OcuparConCharmander()
	{
		estado=OCUPADA_POR_CHARMANDER;
		ruta=RUTA_OCUPADA_POR_CHARMANDER;
	}
	public void OcuparConSquirtle()
	{
		estado= OCUPADA_POR_SQUIRTLE;
		ruta=RUTA_OCUPADA_POR_SQUIRTLE;
	}
	
	
}
