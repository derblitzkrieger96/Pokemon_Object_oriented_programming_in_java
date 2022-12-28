/**
 * 
 */
package uniandes.cupi2.cupiPokemonGo.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/**
 * @author dd.dorado10
 *
 */
public class Tablero 
{
     private Casilla [][] matriz;
     private Casilla [][]  matrizReinicio;
     private int ContadorCasilla[][];
     private boolean juegoEnCurso;
     private boolean gameOver;
     private int tamano;
     private int xActual;
     private int yActual;
     private int numeroDemovimientos;
     private int numPokAtrapados;
     private int pokemonCaptActual;
     private int pokemonEnJuego;
     private int PrimerPokcapturado;
     private int SegundoPokCapturado;
     private int movDisponibles;
     private boolean gano;
     private File archivoReinicio;
     private boolean choco;
     
     private boolean capturo;
     private int xMasVisitada;
     private int yMasVisiada;
     
     public final static int SQUIRTLE=Casilla.OCUPADA_POR_SQUIRTLE;
     public final static int PICACHU=Casilla.OCUPADA_POR_PIKACHU;
     public final static int CHARMANDER=Casilla.OCUPADA_POR_CHARMANDER;
     
   
     public Tablero()
     {
    	 xActual=0;
    	 yActual=0;
    	 tamano=0;
    	 juegoEnCurso=false;
    	 numeroDemovimientos=0;
    	 numPokAtrapados=0;
    	 pokemonCaptActual=0;
    	 pokemonEnJuego=0;
    	 capturo=false;
    	 gano = false;
    	 gameOver=false;
    	 movDisponibles=0;
    	 xMasVisitada=0;
    	 yMasVisiada=0;
    	 choco=false;
    	 ContadorCasilla= new int [tamano][tamano];
     }
     public void cargarMatriz (File archivo) throws Exception
     {
    	 juegoEnCurso=false;
    	 Properties datos = new Properties();
    	 FileInputStream fis = new FileInputStream(archivo);
    	 try 
    	{
			datos.load(fis);
			fis.close();
			
			archivoReinicio = archivo;
			tamano = Integer.parseInt(datos.getProperty("mapa.tamanho")); 
			movDisponibles = Integer.parseInt(datos.getProperty("mapa.movimientos"));
			
			matriz = new Casilla [tamano][tamano];
			matrizReinicio = new Casilla [tamano][tamano];
			ContadorCasilla= new int[tamano][tamano];
			
			for (int i = 0; i < (matriz.length); i++) 
			{
				String fila= datos.getProperty("mapa.fila"+i); System.out.println("llego bien");
				System.out.println(fila);
				String [] posFila = fila.split("");
				for (int j = 0; j < matriz.length; j++) 
				{
					String ruta = "";  System.out.println(tamano+"llego 1  1  1");
					int estado = Integer.parseInt(posFila[j]);
					if(estado==Casilla.VACIA)
					{
						ruta = Casilla.RUTA_VACIA;
					}
					else if(estado==Casilla.OCUPADA_POR_OBSTACULO)
					{
						ruta = Casilla.RUTA_OCUPADA_POR_OBSTACULO;
					}
					else if(estado==Casilla.OCUPADA_POR_JUGADOR)
					{
						ruta = Casilla.RUTA_OCUPADA_POR_JUGADOR;
						xActual=i;
						yActual=j;System.out.println("xi= "+xActual+ " yi= "+yActual);
						xMasVisitada=i;
						yMasVisiada=j;
					}
						
					matriz[i][j]= new Casilla(estado, ruta); System.out.println("llego 3333");
					matriz[i][j].quitarPokebola(); System.out.println("llego 34444");
					matrizReinicio[i][j] = new Casilla(estado, ruta);
					matrizReinicio[i][j].quitarPokebola();
				}
				
			}
			//matriz[0][0]= new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
			asignarPokemon(archivo);
			System.out.println("222222222");
			
		} 
    	 catch (Exception e) 
		{
			throw new Exception("ocurrio un error");// TODO: handle exception
		}
     }
     
     public void asignarPokemon(File Pfile) throws Exception
     {
    	 int pos_x = (int) (Math.random()*tamano);   System.out.println("x= "+pos_x);
    	 int pos_y = (int) (Math.random()*tamano);   System.out.println("y= "+pos_y);
    	 
    	 int pokemon = ((int) (Math.random()*3))+3;   System.out.println("pokemon= "+pokemon);
    	 
    	 if (matriz[pos_x][pos_y].estaVacia())
    	 {
    		 if(pokemon==PICACHU)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
    			 pokemonEnJuego=PICACHU;
    		 }
    		 if(pokemon==CHARMANDER)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
                 pokemonEnJuego=CHARMANDER;   		 
    		 }
    		 if(pokemon==SQUIRTLE)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
                pokemonEnJuego=SQUIRTLE; 		 
    		 }
    			 
    	 }
    	 else
    	 {
    		 cargarMatriz(Pfile);
    	 }
    		 
    		 
     }
     
     public void asignarSdoPokemon() 
     {
    	 int pos_x = (int) (Math.random()*tamano);   System.out.println("x= "+pos_x);
    	 int pos_y = (int) (Math.random()*tamano);   System.out.println("y= "+pos_y);
    	 
    	 int pokemon = ((int) (Math.random()*3))+3;   System.out.println("pokemon= "+pokemon);
    	 
    	 if (matriz[pos_x][pos_y].estaVacia())
    	 {
    		 if(pokemonCaptActual==PICACHU)
    		 {
    			 if(pokemon==PICACHU)
    			 {
    				 asignarPokemon();
    			 }	 
    				 
    			 else
    			 {
    				 if(pokemon==CHARMANDER)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
            		    pokemonEnJuego=CHARMANDER;
            		 }
            		 if(pokemon==SQUIRTLE)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
            			 pokemonEnJuego=SQUIRTLE;
            		 }  
    				 
    			 }
    			 
    		 }
    		 if(pokemonCaptActual==CHARMANDER)
    		 {
    			 if(pokemon==CHARMANDER)
    			 {
    				 asignarPokemon();
    			 }	
    			 else
    			 {
    				 if(pokemon==PICACHU)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
            			 pokemonEnJuego=PICACHU;
            		 }
            		 if(pokemon==SQUIRTLE)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
            			 pokemonEnJuego=SQUIRTLE;
            		 }
    			 }
    		 }
    		 
    		 if(pokemonCaptActual==SQUIRTLE)
    		 {
    			 if(pokemon==SQUIRTLE)
    			 {
    				 asignarPokemon();
    			 }	
    			 else
    			 {
    				 if(pokemon==PICACHU)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
            			 pokemonEnJuego=PICACHU;
            		 }
            		 if(pokemon==CHARMANDER)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
            			 pokemonEnJuego=CHARMANDER;
            		 }
    			 }
    		 }
    		 
    		 System.out.println("el 2 pok quedo en "+pos_x+","+pos_y); 
    	 }
    	 else
    	 {
    		 asignarPokemon();
    	 }
    		 
    		 
     }
     
     //metodo que itera infinitamente en busca de otro pokemon
     public void asignarPokemon()
     {
    	 int pos_x = (int) (Math.random()*tamano);   System.out.println("x= "+pos_x);
    	 int pos_y = (int) (Math.random()*tamano);   System.out.println("y= "+pos_y);
    	 
    	 int pokemon = ((int) (Math.random()*3))+3;   System.out.println("pokemon= "+pokemon);
    	 
    	 if (matriz[pos_x][pos_y].estaVacia())
    	 {
    		 if(pokemonCaptActual==PICACHU)
    		 {
    			 if(pokemon==PICACHU)
    			 {
    				 asignarSdoPokemon();
    			 }	 
    				 
    			 else
    			 {
    				 if(pokemon==CHARMANDER)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
            		     pokemonEnJuego=CHARMANDER;
            		 }
            		 if(pokemon==SQUIRTLE)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
            		     pokemonEnJuego=SQUIRTLE;
            		 }  
    				 
    			 }
    			 
    		 }
    		 
    		 if(pokemonCaptActual==CHARMANDER)
    		 {
    			 if(pokemon==CHARMANDER)
    			 {
    				 asignarSdoPokemon();
    			 }	
    			 else
    			 {
    				 if(pokemon==PICACHU)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
            		 }
            		 if(pokemon==SQUIRTLE)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
            		 }
    			 }
    		 }
    		 
    		 if(pokemonCaptActual==SQUIRTLE)
    		 {
    			 if(pokemon==SQUIRTLE)
    			 {
    				 asignarSdoPokemon();
    			 }	
    			 else
    			 {
    				 if(pokemon==PICACHU)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
            		 }
            		 if(pokemon==CHARMANDER)
            		 {
            			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
            		 }
    			 }
    		 }
    		 System.out.println("el 2 pok quedo en "+pos_x+","+pos_y);	 
    	 }
    	 else
    	 {
    		 asignarSdoPokemon();
    	 }
    		 
    		 
     }
     
     public void asignarTerPokemon()
     {
    	 int pos_x = (int) (Math.random()*tamano);   System.out.println("x= "+pos_x);
    	 int pos_y = (int) (Math.random()*tamano);   System.out.println("y= "+pos_y);
    	 
    	 
    	 
    	 if (matriz[pos_x][pos_y].estaVacia())
    	 {
    		 if(pokemonCaptActual==PICACHU&&PrimerPokcapturado==CHARMANDER)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
    		 }
    		 if(pokemonCaptActual==PICACHU&&PrimerPokcapturado==SQUIRTLE)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
    		 }
    		 if(pokemonCaptActual==CHARMANDER&&PrimerPokcapturado==SQUIRTLE)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
    		 }
    		 if(pokemonCaptActual==CHARMANDER&&PrimerPokcapturado==PICACHU)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
    		 }
    		 if(pokemonCaptActual==SQUIRTLE&&PrimerPokcapturado==PICACHU)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
    		 }
    		 if(pokemonCaptActual==SQUIRTLE&&PrimerPokcapturado==CHARMANDER)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
    		 }
    		System.out.println("el 3 pok quedo en "+pos_x+","+pos_y);	 
    	 }
    	 else
    	 {
    		 asignarPokemonTer();
    	 }
    		 
    		 
    
     }
     public void asignarPokemonTer()
     {
    	 int pos_x = (int) (Math.random()*tamano);   System.out.println("x= "+pos_x);
    	 int pos_y = (int) (Math.random()*tamano);   System.out.println("y= "+pos_y);
    	 
    	 
    	 
    	 if (matriz[pos_x][pos_y].estaVacia())
    	 {
    		 if(pokemonCaptActual==PICACHU&&PrimerPokcapturado==CHARMANDER)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
    		 }
    		 if(pokemonCaptActual==PICACHU&&PrimerPokcapturado==SQUIRTLE)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
    		 }
    		 if(pokemonCaptActual==CHARMANDER&&PrimerPokcapturado==SQUIRTLE)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
    		 }
    		 if(pokemonCaptActual==CHARMANDER&&PrimerPokcapturado==PICACHU)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_SQUIRTLE, Casilla.RUTA_OCUPADA_POR_SQUIRTLE);
    		 }
    		 if(pokemonCaptActual==SQUIRTLE&&PrimerPokcapturado==PICACHU)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_CHARMANDER, Casilla.RUTA_OCUPADA_POR_CHARMANDER);
    		 }
    		 if(pokemonCaptActual==SQUIRTLE&&PrimerPokcapturado==CHARMANDER)
    		 {
    			 matriz[pos_x][pos_y] = new Casilla(Casilla.OCUPADA_POR_PIKACHU, Casilla.RUTA_OCUPADA_POR_PIKACHU);
    		 }
    		 System.out.println("el 3 pok quedo en "+pos_x+","+pos_y);	 
    	 }
    	 else
    	 {
    		 asignarTerPokemon();
    	 }
    		 
    		 
    
     }
    
     public void jugar(int x, int y, int xf, int yf) 
     {
    	 if(movDisponibles>0)
    	 {
    		 if (JuegoDentro(xf, yf))
        	 {
        		 
        		 if(matriz[xf][yf].darEstado()!=Casilla.OCUPADA_POR_OBSTACULO)
        		 {
        			 mover(x, y, xf, yf);
            		 juegoEnCurso=true;
            		 numeroDemovimientos+=1;
            		 xActual=xf;
            		 yActual=yf;
            		 if(numPokAtrapados>0&&numPokAtrapados<3)
            		 {
            			 if(numPokAtrapados==1&&capturo==true)
            			 {
            				 PrimerPokcapturado=pokemonCaptActual;
            				 if (pokemonCaptActual==PICACHU)
            				 {
            					 movDisponibles= movDisponibles+10;
            				 }
            				 if (pokemonCaptActual==CHARMANDER)
            				 {
            					 movDisponibles= movDisponibles+5;
            				 }
            				 if (pokemonCaptActual==SQUIRTLE)
            				 {
            					 movDisponibles= movDisponibles+7;
            				 }
            				 asignarSdoPokemon(); System.out.println("asignó 2do pokemon");
            			 }
            			 if(numPokAtrapados==2&&capturo==true)
            			 {
            				 SegundoPokCapturado=pokemonCaptActual;
            				 if (pokemonCaptActual==PICACHU)
            				 {
            					 movDisponibles= movDisponibles+10;
            				 }
            				 if (pokemonCaptActual==CHARMANDER)
            				 {
            					 movDisponibles= movDisponibles+5;
            				 }
            				 if (pokemonCaptActual==SQUIRTLE)
            				 {
            					 movDisponibles= movDisponibles+7;
            				 }
            				 asignarTerPokemon(); System.out.println("asigno 3er pokenon");
            			 }
            			 
            			 
            		 }
            		 if(numPokAtrapados==3)
            		 {
            			 gameOver=true;
            		 }
            		 if (numPokAtrapados==3&&movDisponibles>=0)
            		 {
            			 gameOver=false;
            			 gano=true;
            		 }
            		
        		 }
        		  if(matriz[xf][yf].darEstado()==Casilla.OCUPADA_POR_OBSTACULO)
        		 {
        			  movDisponibles--;
        			  choco=true;
        			 
        		 }
        		  
        	 }
        
    	 }
    	 else
    	 {
    		 gameOver=true;
    	 }
     }
     
     public void mover(int x, int y, int xf, int yf)
     {
    	 System.out.println("fin0");
    	 
    	 
    	 
    	 
    	 if(matriz[xf][yf].darEstado()==PICACHU&&matriz[x][y].tienePokebola()==false)
    	 {
    		 matriz[x][y].VaciarCasilla();;
        	 matriz[xf][yf].OcuparConJugador();
        	 matriz[xf][yf].asignarPokebola();
        	 numPokAtrapados+=1;
        	 pokemonCaptActual=PICACHU;
        	 capturo=true;
        	 System.out.println("op 4");
    	 }
    	 if(matriz[xf][yf].darEstado()==CHARMANDER&&matriz[x][y].tienePokebola()==false)
    	 {
    		 matriz[x][y].VaciarCasilla();;
        	 
        	 matriz[xf][yf].asignarPokebola(); 
        	 numPokAtrapados+=1;
        	 pokemonCaptActual = CHARMANDER;
        	 capturo=true;
        	 matriz[xf][yf].OcuparConJugador();
        	 System.out.println("op 5");
    	 }
    	 if(matriz[xf][yf].darEstado()==SQUIRTLE&&matriz[x][y].tienePokebola()==false)
    	 {
    		 matriz[x][y].VaciarCasilla();
        	 
        	 matriz[xf][yf].asignarPokebola();
        	 numPokAtrapados+=1;
        	 pokemonCaptActual=SQUIRTLE;
        	 capturo=true;
        	 matriz[xf][yf].OcuparConJugador();
        	 System.out.println("op 6");
    	 }
    	 if(matriz[xf][yf].darEstado()==PICACHU&&matriz[x][y].tienePokebola()==true)
    	 {
    		 matriz[x][y].OcuparConPokebola();
        	 
        	 matriz[xf][yf].asignarPokebola();
        	 numPokAtrapados+=1;
        	 pokemonCaptActual=PICACHU;
        	 capturo=true;
        	 matriz[xf][yf].OcuparConJugador();System.out.println("op 7");
    	 }
    	 if(matriz[xf][yf].darEstado()==CHARMANDER&&matriz[x][y].tienePokebola()==true)
    	 {
    		 matriz[x][y].OcuparConPokebola();
        	 matriz[xf][yf].OcuparConJugador();
        	 matriz[xf][yf].asignarPokebola(); 
        	 numPokAtrapados+=1;
        	 pokemonCaptActual=CHARMANDER;
        	 capturo=true;
        	 System.out.println("op 8");
    	 }
    	 if(matriz[xf][yf].darEstado()==SQUIRTLE&&matriz[x][y].tienePokebola()==true)
    	 {
    		 matriz[x][y].OcuparConPokebola();
        	 matriz[xf][yf].OcuparConJugador();
        	 matriz[xf][yf].asignarPokebola();
        	 
        	 //System.out.println("op 9");
        	 numPokAtrapados+=1;
        	 pokemonCaptActual=SQUIRTLE;
        	 capturo=true;
    	 }
    	 if(matriz[x][y].tienePokebola()==false&&(matriz[xf][yf].estaVacia()||matriz[xf][yf].darEstado()==Casilla.OCUPADA_CON_POKEBOLA)&&(matriz[xf][yf].darEstado()==PICACHU)==false||matriz[xf][yf].darEstado()==CHARMANDER||matriz[xf][yf].darEstado()==SQUIRTLE)
    	 {
    		 matriz[x][y].VaciarCasilla();
        	 matriz[xf][yf].OcuparConJugador();System.out.println("op 1");
        	 capturo=false;
    	 }
    	 if(matriz[x][y].tienePokebola()&&matriz[xf][yf].estaVacia())
    	 {
    		 matriz[x][y].OcuparConPokebola();
        	 matriz[xf][yf].OcuparConJugador();System.out.println("op 2");
        	 capturo=false;
    	 }
    	 if(matriz[x][y].tienePokebola()&&matriz[xf][yf].tienePokebola()&&(matriz[xf][yf].darEstado()==PICACHU)==false||matriz[xf][yf].darEstado()==CHARMANDER||matriz[xf][yf].darEstado()==SQUIRTLE)
    	 {
    		 matriz[x][y].OcuparConPokebola();
        	 matriz[xf][yf].OcuparConJugador();
        	 matriz[x][y].asignarPokebola();
        	 matriz[xf][yf].asignarPokebola();System.out.println("op 3");
        	 capturo=false;
    	 }
    	 if(matriz[xf][yf].darEstado()==Casilla.OCUPADA_POR_OBSTACULO)
    	 {
    		 numeroDemovimientos+=1;
    		 capturo=false;
    	 }
    	 
    	for (int i = 0; i < ContadorCasilla.length; i++)
    	{
			for (int j = 0; j < ContadorCasilla.length; j++)
			{
				if(xf==i&&yf==j)
					{
					ContadorCasilla [i][j]++; 
					}
			}
		}
    	casillamasvisitada();
    	
    	 
    	 System.out.println(xActual+","+yActual);
    	 System.out.println("fin1"); 
         movDisponibles-=1;
         choco=false;
     }
     public void casillamasvisitada()
     {
    	 int contador=0;
    	 int mayor=0;
    	 for (int i = 0; i < ContadorCasilla.length; i++) {
			for (int j = 0; j < ContadorCasilla.length; j++) {
				contador=ContadorCasilla[i][j];
				if(contador>mayor)
				{
					mayor=contador;
					xMasVisitada=i;
					yMasVisiada=j;
				}
			}
		}
     }
     
     public int CuantasCinco()
     {
    	 int cuantas=0;
    	 for (int i = 0; i < ContadorCasilla.length; i++) 
    	 {
			for (int j = 0; j < ContadorCasilla.length; j++) 
			{
				if(ContadorCasilla[i][j]==5)
					cuantas++;
			}
		}
    	 return cuantas;
     }
     public int darPrimerPokCap()
     {
    	 return PrimerPokcapturado;
     }
     public int darSdoPokCapt()
     {
    	 return SegundoPokCapturado;
     }
     public boolean JuegoDentro(int xf, int yf)
     {
    	 boolean si = false;
    	 if (xf>= 0&&xf<tamano&&yf>=0&&yf<tamano)
    	 {
    		 si=true;
    	 }
    	 return si;
     }
     
     
     public Casilla [][] darMatriz()
     {
    	 return matriz;
     }
     
     public int darPosxInicial()
     {
    	 return xActual;
     }
     public int darPosyInicial()
     {
    	 return yActual;
     }
     public boolean darJuegoEnCurso()
     {
    	 return juegoEnCurso;
     }
     public int darNumDeMov()
     {
    	 return numeroDemovimientos;
     }
     public boolean capturoPok()
     {
    	 return capturo;
     }
     public int darPokActual()
     {
    	 return pokemonCaptActual;
     }
     public boolean finalizoElJuego()
     {
    	 return gameOver;
     }
     public int darMovDisponibles()
     {
    	 return movDisponibles;
     }
     public int darNumPokCapturados()
     {
    	 return numPokAtrapados;
     }
     public boolean gano()
     {
    	 return gano;
     }
     public boolean darEstadoDelJuego()
     {
    	 return juegoEnCurso;
     }
     public void reiniciar() throws Exception
     {
    	 xActual=0;
    	 yActual=0;
    	 xMasVisitada=0;
    	 yMasVisiada=0;
    	 tamano=0;
    	 juegoEnCurso=false;
    	 numeroDemovimientos=0;
    	 numPokAtrapados=0;
    	 pokemonCaptActual=0;
    	 pokemonEnJuego=0;
    	 capturo=false;
    	 gano = false;
    	 gameOver=false;
    	 movDisponibles=0;
    	 choco=false;
    	 cargarMatriz(archivoReinicio);
    	 
     }
     public void reiniciarSiGano()
     {
    	 xActual=0;
    	 yActual=0;
    	 tamano=0;
    	 juegoEnCurso=false;
    	 numeroDemovimientos=0;
    	 numPokAtrapados=0;
    	 pokemonCaptActual=0;
    	 pokemonEnJuego=0;
    	 capturo=false;
    	 gano = false;
    	 gameOver=false;
    	 movDisponibles=0;
    	 //xMasVisitada=0;
    	 //yMasVisiada=0;
    	 choco=false;
     }
    
     public int darxMasVisitada()
     {
    	 return xMasVisitada;
     }
     public int darYMasVisitada()
     {
    	 return yMasVisiada;
    			 
     }
     public String opcion1()
     {
    	 return "Opción 1";
     }
     public String opcion2()
     {
    	 return "Opción 2";
     }
     public boolean choco()
     {
    	 return choco;
     }
}
