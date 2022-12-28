/**
 * 
 */
package uniandes.cupi2.cupiPokemonGo.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author dd.dorado10
 *
 */
public class PanelControles extends JPanel implements ActionListener
{
    private int posxActual;
    private int posyActual;
    
    private int posxFinal;
    private int posyFinal;
	
	private Interfaz interfaz;
	
	private JPanel subpanel; 
    private JButton noroeste;
    private JButton norte;
    private JButton noreste;
    private JButton oeste;
    private JButton este;
    private JButton suroeste;
    private JButton sur;
    private JButton sureste;
    
    private JPanel panel1;
    private JPanel panel2;
    
    public final static String NOROESTE = "noroeste";
    public final static String NORTE = "norte";
    public final static String NORESTE = "noreste";
    public final static String OESTE = "oeste";
    public final static String ESTE = "este";
    public final static String SUROESTE = "suroeste";
    public final static String SUR = "sur";
    public final static String SURESTE = "sureste";
    
	
	public PanelControles(Interfaz in)
     {
    	
		
		interfaz = in;
		
		setLayout(new BorderLayout());
    	 setPreferredSize(new Dimension(170, 500));
    	 
    	 subpanel = new JPanel();
    	 subpanel.setLayout(new GridLayout(3, 5));
    	 subpanel.setPreferredSize(new Dimension(150,150));
    	 
    	    String ruta0= "./data/imagenes/noroeste.png";
			String ruta1= "./data/imagenes/norte.png";
			String ruta2= "./data/imagenes/noreste.png";
			String ruta3= "./data/imagenes/oeste.png";
			String ruta4= "centro";
			String ruta5= "./data/imagenes/este.png";
			String ruta6= "./data/imagenes/suroeste.png";
			String ruta7= "./data/imagenes/sur.png";
			String ruta8= "./data/imagenes/sureste.png";
			
			ImageIcon ic0 = new ImageIcon(ruta0);
			ImageIcon ic1 = new ImageIcon(ruta1);
			ImageIcon ic2 = new ImageIcon(ruta2);
			ImageIcon ic3 = new ImageIcon(ruta3);
			ImageIcon ic4 = new ImageIcon(ruta4);
			ImageIcon ic5 = new ImageIcon(ruta5);
			ImageIcon ic6 = new ImageIcon(ruta6);
			ImageIcon ic7 = new ImageIcon(ruta7);
			ImageIcon ic8 = new ImageIcon(ruta8);
					
    	 noroeste = new JButton(ic0);
    	 norte = new JButton(ic1);
    	 noreste = new JButton(ic2);
    	 oeste = new JButton(ic3);
    	 este = new JButton(ic5);
    	 suroeste = new JButton(ic6);
    	 sur = new JButton(ic7);
    	 sureste = new JButton(ic8);
    	 
    	 
    	 
    	 noroeste.setActionCommand(NOROESTE);
    	 norte.setActionCommand(NORTE);
    	 noreste.setActionCommand(NORESTE);
    	 oeste.setActionCommand(OESTE);
    	 este.setActionCommand(ESTE); 
    	 suroeste.setActionCommand(SUROESTE);
    	 sur.setActionCommand(SUR); 
    	 sureste.setActionCommand(SURESTE); 

    	 noroeste.addActionListener(this);
    	 norte.addActionListener(this);
    	 noreste.addActionListener(this);
    	 oeste.addActionListener(this);
    	 este.addActionListener(this);
    	 suroeste.addActionListener(this);
    	 sur.addActionListener(this); 
    	 sureste.addActionListener(this);
    	 
    	 noroeste.setEnabled(false);
		 norte.setEnabled(false);
		 noreste.setEnabled(false);
		 oeste.setEnabled(false);
		 este.setEnabled(false);
		 suroeste.setEnabled(false);
		 sur.setEnabled(false);
		 sureste.setEnabled(false);
    	 
    	 panel1 = new JPanel();
    	 panel1.setLayout(new GridLayout());
    	 panel1.setPreferredSize(new Dimension(170,185));
    	 
    	 panel2 = new JPanel();
    	 panel2.setLayout(new GridLayout());
    	 panel2.setPreferredSize(new Dimension(170,185));
    	 
    	 subpanel.add(new JLabel(""));
    	 subpanel.add(noroeste);
    	 subpanel.add(norte);
    	 subpanel.add(noreste);
    	 subpanel.add(new JLabel(""));
    	 subpanel.add(new JLabel(""));
    	 subpanel.add(oeste);
    	 subpanel.add(new JLabel(""));
    	 subpanel.add(este);
    	 subpanel.add(new JLabel(""));
    	 subpanel.add(new JLabel(""));
    	 subpanel.add(suroeste);
    	 subpanel.add(sur);
    	 subpanel.add(sureste);
    	 subpanel.add(new JLabel(""));
    			 
    	 add(subpanel, BorderLayout.CENTER);
    	 add(panel1, BorderLayout.NORTH);
    	 add(panel2, BorderLayout.SOUTH);
    	 
    	 posxActual = 0;
     	posyActual = 0;
     	posyFinal=0;
     	posxFinal=0;
     }
     public void habilitarBotones()
     {
    	 //if (interfaz.darJuegoEnCurso()==true)
    	 {
    		 noroeste.setEnabled(true);
    		 norte.setEnabled(true);
    		 noreste.setEnabled(true);
    		 oeste.setEnabled(true);
    		 este.setEnabled(true);
    		 suroeste.setEnabled(true);
    		 sur.setEnabled(true);
    		 sureste.setEnabled(true);
    	 }
     }
     
     public void deshabilitarBotones()
     {
    	 noroeste.setEnabled(false);
		 norte.setEnabled(false);
		 noreste.setEnabled(false);
		 oeste.setEnabled(false);
		 este.setEnabled(false);
		 suroeste.setEnabled(false);
		 sur.setEnabled(false);
		 sureste.setEnabled(false);
     }

	public void actionPerformed(ActionEvent e) 
	{
		String eve = e.getActionCommand();// TODO Auto-generated method stub
		//if(interfaz.darJuegoEnCurso()==true)
		{
			if (eve.equals(NOROESTE))
			{
				posxActual = interfaz.darXInicial();
		     	posyActual = interfaz.darYInicial();
				posxFinal=posxActual-1;
				posyFinal=posyActual-1;
				interfaz.jugar(posxActual, posyActual, posxFinal, posyFinal);System.out.println(posxFinal+","+posyFinal+",  "+posxActual+posyActual);
			}
			if (eve.equals(NORTE))
			{
				posxActual = interfaz.darXInicial();
		     	posyActual = interfaz.darYInicial();
				posxFinal=posxActual-1;
				posyFinal=posyActual;
				interfaz.jugar(posxActual, posyActual, posxFinal, posyFinal);System.out.println(posxFinal+","+posyFinal+",  "+posxActual+posyActual);
			}
			if (eve.equals(NORESTE))
			{
				posxActual = interfaz.darXInicial();
		     	posyActual = interfaz.darYInicial();
				posxFinal=posxActual-1;
				posyFinal=posyActual+1;
				interfaz.jugar(posxActual, posyActual, posxFinal, posyFinal);System.out.println(posxFinal+","+posyFinal+",  "+posxActual+posyActual);
			}
			if (eve.equals(OESTE))
			{
				posxActual = interfaz.darXInicial();
		     	posyActual = interfaz.darYInicial();
				posxFinal=posxActual;
				posyFinal=posyActual-1;
				interfaz.jugar(posxActual, posyActual, posxFinal, posyFinal);System.out.println(posxFinal+","+posyFinal+",  "+posxActual+posyActual);
			}
			if (eve.equals(ESTE))
			{
				posxActual = interfaz.darXInicial();
		     	posyActual = interfaz.darYInicial();
				posxFinal=posxActual;
				posyFinal=posyActual+1;
				interfaz.jugar(posxActual, posyActual, posxFinal, posyFinal);System.out.println(posxFinal+","+posyFinal+",  "+posxActual+posyActual);
			}
			if (eve.equals(SUROESTE))
			{
				posxActual = interfaz.darXInicial();
		     	posyActual = interfaz.darYInicial();
				posxFinal=posxActual+1;
				posyFinal=posyActual-1;
				interfaz.jugar(posxActual, posyActual, posxFinal, posyFinal);System.out.println(posxFinal+","+posyFinal+",  "+posxActual+posyActual);
			}
			if (eve.equals(SUR))
			{
				posxActual = interfaz.darXInicial();
		     	posyActual = interfaz.darYInicial();
				posxFinal=posxActual+1;
				posyFinal=posyActual;
				interfaz.jugar(posxActual, posyActual, posxFinal, posyFinal);System.out.println(posxFinal+","+posyFinal+",  "+posxActual+posyActual);
			}
			if (eve.equals(SURESTE))
			{
				posxActual = interfaz.darXInicial();
		     	posyActual = interfaz.darYInicial();
				posxFinal=posxActual+1;
				posyFinal=posyActual+1;
				interfaz.jugar(posxActual, posyActual, posxFinal, posyFinal);System.out.println(posxFinal+","+posyFinal+",  "+posxActual+posyActual);
			}
		}
		
	}
}
