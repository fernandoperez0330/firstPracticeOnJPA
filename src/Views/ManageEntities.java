package Views;
import Business.Entities;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import Controller.ControllerEntities;
import java.awt.FlowLayout;

public class ManageEntities extends MainFrame{
    
    public String valueMainBtn;
    
    public JPanel EntityPanel;
    public JLabel lblNombre;
    public JTextField inputNombre = new JTextField();
    public JLabel lblApellido;
    public JTextField inputApellido = new JTextField();
    JButton btnProcesar;
    
    //cuando es para agregar nuevo
    public ManageEntities(){
        super();
        this.title = "Crear Nueva Entidad";
        this.valueMainBtn = "Agregar";
        this.DimFrame = new Dimension(250,200);
        btnProcesar = new JButton(this.valueMainBtn);;
        btnProcesar.addActionListener(new ControllerEntities(this));
    }
    
    public ManageEntities(int id,ShowListEntities SLEntities){
        super();
        this.title = "Modificar Entidad";
        this.valueMainBtn = "Modificar";
        Entities ent = new Entities(id);
        
        inputNombre.setText(ent.getNombre());
        inputApellido.setText(ent.getApellido());
        this.DimFrame = new Dimension(250,200);
        btnProcesar = new JButton(this.valueMainBtn);;
        btnProcesar.addActionListener(new ControllerEntities(this,ent,SLEntities));
    }
    
    @Override
    public JPanel getContent(){
        EntityPanel = new JPanel();
        
        lblNombre = new JLabel("Nombre.:");
        lblApellido = new JLabel("Apellido.:");
       
        inputNombre.setPreferredSize(new Dimension(100,30));
        inputApellido.setPreferredSize(new Dimension(100,30));
        EntityPanel.setLayout(new FlowLayout());
        JPanel panelNombre = new JPanel();
        JPanel panelApellido = new JPanel();
        
        
        panelNombre.add(lblNombre);
        panelNombre.add(inputNombre);
        
        panelApellido.add(lblApellido);
        panelApellido.add(inputApellido);
                       
        EntityPanel.add(panelNombre);
        EntityPanel.add(panelApellido);
        
        EntityPanel.add(btnProcesar);
        return EntityPanel;
    }
    
}
