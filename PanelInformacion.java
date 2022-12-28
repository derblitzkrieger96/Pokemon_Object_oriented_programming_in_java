/**
 * 
 */
package uniandes.cupi2.cupiPokemonGo.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.DimensionUIResource;

/**
 * @author dd.dorado10
 *
 */
public class PanelInformacion extends JPanel
{
    private Interfaz interfaz;
    private JPanel panelNumDeMov;
    private JPanel subpanel1;
    private JPanel panelPokCapt;
    private JPanel subpanel2;
	private JLabel NumeroDeMovimientos;
    private JLabel PokemonCapturados;
    private JLabel etiqueta1;
    private JLabel etiqueta2;
   
    

    
	public PanelInformacion(Interfaz in)
     {
		interfaz = in;
		 
		Font font = new Font("Calibri",3,15);
		Font font1 = new Font("Calibri",3,45);
		
		
				
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(170, 500));
		
	   TitledBorder ti = new TitledBorder("Información");
	   
		panelNumDeMov = new JPanel();
		panelNumDeMov.setLayout(new BorderLayout());
	    panelNumDeMov.setPreferredSize(new Dimension(200, 75));
	    subpanel1= new JPanel();
	    subpanel1.setLayout(new GridLayout(1, 3));
	    subpanel1.setPreferredSize(new Dimension(170, 75));
	    etiqueta1=new JLabel();
	    etiqueta1.setText("Número de movimientos");
	    etiqueta1.setFont(font);
	    etiqueta1.setForeground(Color.BLUE);
	    NumeroDeMovimientos =  new JLabel();
	    NumeroDeMovimientos.setText(Integer.toString(interfaz.darNumDeMov()));
	    NumeroDeMovimientos.setFont(font1);
	    NumeroDeMovimientos.setForeground(Color.BLUE);
	    panelNumDeMov.add(etiqueta1, BorderLayout.NORTH);
	    subpanel1.add(new JLabel(""));
	    subpanel1.add(NumeroDeMovimientos);
	    subpanel1.add(new JLabel(""));
	    
	    panelNumDeMov.add(subpanel1, BorderLayout.CENTER);
		
    	panelPokCapt = new JPanel();
	    panelPokCapt.setLayout(new BorderLayout());
	    panelPokCapt.setPreferredSize(new Dimension(200, 75));
	    subpanel2= new JPanel();
	    subpanel2.setLayout(new GridLayout(1, 3));
	    etiqueta2 = new JLabel();
	    etiqueta2.setFont(font);
	    Color VerdeOscuro = new Color(80,180,80);
	    etiqueta2.setForeground(VerdeOscuro);
	    etiqueta2.setText("Pokémon capturados");
	    PokemonCapturados = new JLabel();
	    PokemonCapturados.setText("0");
	    PokemonCapturados.setFont(font1);
	    PokemonCapturados.setForeground(VerdeOscuro);
	    panelPokCapt.add(etiqueta2, BorderLayout.NORTH);
	    subpanel2.add(new JLabel(""));
	    subpanel2.add(PokemonCapturados);
	    subpanel2.add(new JLabel(""));
	    
	    panelPokCapt.add(subpanel2, BorderLayout.CENTER);

	    
	    add(panelNumDeMov, BorderLayout.NORTH);
	    
	    add(panelPokCapt, BorderLayout.SOUTH);
	    setBorder(ti);
	    
	   
		
    	 
    	 
     }
	public void refrescarNumMov()
	{
		NumeroDeMovimientos.setText(Integer.toString(interfaz.darNumDeMov()));
	}
	public void refrescarPokAtrapados()
	{
		PokemonCapturados.setText(Integer.toString(interfaz.darNumPokCapturados()));
	}
}
