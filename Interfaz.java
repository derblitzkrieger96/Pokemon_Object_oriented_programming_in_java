/**
 * 
 */
package uniandes.cupi2.cupiPokemonGo.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.cupiPokemonGo.mundo.Casilla;
import uniandes.cupi2.cupiPokemonGo.mundo.Tablero;

/**
 * @author dd.dorado10
 *
 */
public class Interfaz extends JFrame
{
    private PanelImagen panelImagen;
    private PanelTablero panelTablero;
    private PanelInformacion panelInformacion;
    private PanelOpciones panelOpciones;
    
    private Tablero mundo;
    private PanelControles panelControles;
    private File file;
    private boolean juegoEnCurso;
    
	public Interfaz()
    {
    	mundo = new Tablero();
    	juegoEnCurso=false;
		
		setLayout(new BorderLayout());
    	setSize(new Dimension(816, 700));
    	setLocation(250, 10);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setResizable(false);
    	
    	panelImagen = new PanelImagen();
    	panelTablero = new PanelTablero(this);
    	panelInformacion = new PanelInformacion(this);
    	panelOpciones = new PanelOpciones(this);
    	panelControles = new PanelControles(this);
    	add(panelImagen, BorderLayout.NORTH);
    	add(panelTablero, BorderLayout.CENTER);
    	add(panelInformacion, BorderLayout.WEST);add(panelOpciones, BorderLayout.SOUTH);
    	add(panelControles, BorderLayout.EAST);
    	
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Interfaz i = new Interfaz();
		i.setVisible(true);// TODO Auto-generated method stub

	}
	
	public void cargar()
	{
		JFileChooser fc= new JFileChooser("./data");
		int respuesta = fc.showOpenDialog(this);
		if(respuesta==JFileChooser.APPROVE_OPTION)
		{
			try 
			{
				file = fc.getSelectedFile();
				mundo.cargarMatriz(file);
				panelTablero.refrescarTablero(mundo.darMatriz());
				panelInformacion.refrescarNumMov();
				panelControles.habilitarBotones();
				panelOpciones.habilitarBotones();
			} catch (Exception e) 
			{
				JOptionPane.showMessageDialog(this, "error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				// TODO: handle exception
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Debe seleccionar un archivo para poder jugar", "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
    
	public File darFile()
	{
		return file;
	}
	public void jugar(int x, int y, int xf, int yf)
	{
		mundo.jugar(x, y, xf, yf);
		if(mundo.choco())
		{
			JOptionPane.showMessageDialog(this, "El jugador ha chocado en uno de los obstaculos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);// TODO
		}
			
		if(mundo.finalizoElJuego()==false)
		{
			if(mundo.gano())
			{
				panelTablero.refrescarTablero(mundo.darMatriz());
				
				JOptionPane.showMessageDialog(this, "FELICITACIONES, USTED HA GANADO", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				panelControles.deshabilitarBotones();
                panelOpciones.desabilitarCasillaMasVisitada();
                mundo.reiniciarSiGano();
                panelInformacion.refrescarNumMov();
				panelInformacion.refrescarPokAtrapados();
				panelTablero.deshabilitarTablero();
                
			}
			else 
			{
				panelTablero.refrescarTablero(mundo.darMatriz());
				panelInformacion.refrescarNumMov();
				panelInformacion.refrescarPokAtrapados();
		        if(mundo.capturoPok())
				{
					int pokemonCapturado = mundo.darPokActual();
					String po = "";
					String ruta ="";
					String ln = System.getProperty("line.separator");
					String mov="";
					if(pokemonCapturado==Tablero.CHARMANDER)
					{
						po = "CHARMANDER";
						ruta = Casilla.RUTA_OCUPADA_POR_CHARMANDER;
						mov = "¡USTED HA RECIBIDO 5 MOVIMIENTOS!";
					}
					if(pokemonCapturado==Tablero.PICACHU)
					{
						po= "PICHACHU";
						ruta = Casilla.RUTA_OCUPADA_POR_PIKACHU;
						mov = "¡USTED HA RECIBIDO 10 MOVIMIENTOS!";
					}
					if(pokemonCapturado==Tablero.SQUIRTLE)
					{
						po = "SQUIRTLE";
						ruta = Casilla.RUTA_OCUPADA_POR_SQUIRTLE;
						mov = "¡USTED HA RECIBIDO 7 MOVIMIENTOS!";
					}
		        	JOptionPane.showMessageDialog(this, "Felicidades capturaste a "+po+ln+mov, "Mensaje" ,JOptionPane.INFORMATION_MESSAGE,  new ImageIcon(ruta) );
				}
			}
		}
		if(mundo.finalizoElJuego())
		{
			panelControles.deshabilitarBotones();
            panelOpciones.desabilitarCasillaMasVisitada();
            mundo.reiniciarSiGano();
            panelInformacion.refrescarNumMov();
			panelInformacion.refrescarPokAtrapados();
			panelTablero.deshabilitarTablero();
			JOptionPane.showMessageDialog(this, "Usted no tien mas movimientos, ¡USTED HA PERDIDO!", "Juego Finalizado", JOptionPane.INFORMATION_MESSAGE);
			panelTablero.deshabilitarTablero();
		}
		
	}
	public boolean darJuegoEnCurso()
	{
		return mundo.darJuegoEnCurso();
	}
	public int darXInicial()
	{
		return mundo.darPosxInicial();
	}
	public int darYInicial()
	{
		return mundo.darPosyInicial();
	}
	public int darNumDeMov()
	{
		return mundo.darMovDisponibles();
	}public int darNumPokCapturados()
	{
		return mundo.darNumPokCapturados();
	}
	public boolean darEstadoDelJuego()
	{
		return mundo.darEstadoDelJuego();
	}
	public void masVisitada()
	{
		int x= mundo.darxMasVisitada();
		int y=mundo.darYMasVisitada();
		JOptionPane.showMessageDialog(this, "La casilla mas Visitada es "+"("+x+","+y+")", "Casilla mas visiatda", JOptionPane.INFORMATION_MESSAGE);
	}
	public void reiniciar()
	{
		try {
			mundo.reiniciar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelTablero.refrescarTablero(mundo.darMatriz());
		panelInformacion.refrescarNumMov();
		panelInformacion.refrescarPokAtrapados();
		panelControles.habilitarBotones();
		panelOpciones.habilitarBotones();
	}
	public void opcion1()
	{
		JOptionPane.showMessageDialog(this, mundo.opcion1(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}
	public void opcion2()
	{
		JOptionPane.showMessageDialog(this, mundo.opcion2(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}
}
