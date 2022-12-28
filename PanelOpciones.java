/**
 * 
 */
package uniandes.cupi2.cupiPokemonGo.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @author dd.dorado10
 *
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    private Interfaz interfaz;
	
	private JButton cargar;
    private JButton reiniciar;
    private JButton casillaMasVisitada;
    private JButton opcion1;
    private JButton opcion2;
    
    public final static String CARGAR = "Cargar";
    public final static String REINICIAR = "Reiniciar";
    public final static String CASILLA_MAS_VISITADA = "Casilla mas visitada";
    public final static String OPCION_1 = "Opción 1";
    public final static String OPCION_2 = "Opción 2";
	
	public PanelOpciones(Interfaz in)
    {
    	interfaz = in;
    	
    	TitledBorder ti = new TitledBorder("Opciones");
		
		setLayout(new GridLayout(1, 5));
    	setPreferredSize(new Dimension(816, 50));
        
    	cargar = new JButton(CARGAR);
    	reiniciar = new JButton(REINICIAR);
    	casillaMasVisitada = new JButton(CASILLA_MAS_VISITADA);
    	opcion1 = new JButton(OPCION_1);
    	opcion2 = new JButton(OPCION_2);
    	
    	cargar.setActionCommand(CARGAR);
    	reiniciar.setActionCommand(REINICIAR);
    	casillaMasVisitada.setActionCommand(CASILLA_MAS_VISITADA);
    	opcion1.setActionCommand(OPCION_1);
    	opcion2.setActionCommand(OPCION_2);
    	
    	cargar.addActionListener(this);
    	reiniciar.addActionListener(this);
    	casillaMasVisitada.addActionListener(this);
    	opcion1.addActionListener(this);
    	opcion2.addActionListener(this);
    	
    	reiniciar.setEnabled(false);
		casillaMasVisitada.setEnabled(false);
    	
    	add(cargar);
    	add(reiniciar);
    	add(casillaMasVisitada);
    	add(opcion1);
    	add(opcion2);
    	setBorder(ti);
    }
	public void habilitarBotones()
	{
		reiniciar.setEnabled(true);
		casillaMasVisitada.setEnabled(true);
	}

	public void desabilitarCasillaMasVisitada()
	{
		casillaMasVisitada.setEnabled(false);
	}
	public void actionPerformed(ActionEvent ev) 
	{
		String eve= ev.getActionCommand();
		if (eve.equals(CARGAR))
		{
			interfaz.cargar();// TODO Auto-generated method stub
		}
		else if (eve.equals(REINICIAR))
		{
			interfaz.reiniciar();
		}
		else if (eve.equals(CASILLA_MAS_VISITADA))
		{
			interfaz.masVisitada();
		}
		else if(eve.equals(OPCION_1))
		{
			interfaz.opcion1();
		}
		else if(eve.equals(OPCION_2))
		{
			interfaz.opcion2();
		}
	}
}
