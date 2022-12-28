/**
 * 
 */
package uniandes.cupi2.cupiPokemonGo.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.cupi2.cupiPokemonGo.mundo.Casilla;

/**
 * @author dd.dorado10
 *
 */
public class PanelTablero  extends JPanel
{
    private int tamano;
    private JLabel[][] matriz;
    private Interfaz interfaz;
    
    
	public PanelTablero(Interfaz in)
    {
    	interfaz = in;
    	tamano=12;
    	matriz= new JLabel [tamano][tamano];
    	
		setLayout(new GridLayout(tamano, tamano));
		setPreferredSize(new Dimension(500, 500));
		
		for (int i = 0; i < matriz.length; i++) 
		{
			for (int j = 0; j < matriz.length; j++) 
			{
				ImageIcon ic = new ImageIcon();
				matriz[i][j]= new JLabel();
				matriz[i][j].setIcon(ic);
				add(matriz[i][j]);
			}
		}
    }
	
	public void refrescarTablero(Casilla [][] matriz1 )
	{
		removeAll();
		tamano = matriz1.length;
		matriz = new JLabel [tamano][tamano];
		setLayout(new GridLayout(tamano, tamano));
		setPreferredSize(new Dimension(500, 500));
		
		for (int i = 0; i < matriz.length; i++) 
		{
			for (int j = 0; j < matriz1.length; j++) 
			{
				matriz[i][j]= new JLabel();
				String ruta=matriz1[i][j].darRuta();
				ImageIcon ic1 = new ImageIcon(ruta);
				matriz[i][j].setIcon(ic1);
				matriz[i][j].setBackground(Color.GRAY);;
                add(matriz[i][j]);
			}
		}
		repaint();
		revalidate();
	}
	public void deshabilitarTablero()
	{
		
		for (int i = 0; i < matriz.length; i++) 
		{
			for (int j = 0; j < matriz.length; j++) {

				matriz[i][j].setBackground(Color.GRAY);
				matriz[i][j].setEnabled(!isVisible());;;
			}
		}
		repaint();
		revalidate();
	}
	
}
