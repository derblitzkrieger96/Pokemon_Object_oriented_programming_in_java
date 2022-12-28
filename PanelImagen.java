/**
 * 
 */
package uniandes.cupi2.cupiPokemonGo.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * @author dd.dorado10
 *
 */
public class PanelImagen extends JPanel
{
    private String ruta;
    private JLabel image;
    private ImageIcon i;
    
	public PanelImagen ()
    {
    	setLayout(new GridLayout(1, 1));
    	setPreferredSize(new Dimension(816, 150));
    	
		
		ruta = new String("./data/imagenes/titulo.png");
    	image = new JLabel();
    	i = new ImageIcon(ruta);
    	image.setIcon(i);
    	add(image);
    }
}
