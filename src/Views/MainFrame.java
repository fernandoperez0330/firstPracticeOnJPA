package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import Controller.ControllerMain;
//import java.io.sh


public class MainFrame extends JFrame{
    
    protected Dimension DimFrame = new Dimension(400,60);
    protected String title = "Sistema CRUD (Create, Update and Delete)";
    
    //cuando es el frame principal
    public MainFrame(){
        super();
    }
    
    //cuando es una frame con padre
    public MainFrame(MainFrame parent){
        super();
    }

    public void setFrameDimension(Dimension Dimen){
        this.setSize(Dimen);
    }
    
    public void setFrameTitle(String title){
        this.setTitle(title);
    }
    
    public JPanel getContent(){
        JPanel MainPanel = new JPanel();
        JButton btnAddEntities = new JButton("Agregar Entidad");
        JButton btnModifyEntities = new JButton("Modificar Entidad");
        
        btnAddEntities.addActionListener(new ControllerMain(this));
        btnModifyEntities.addActionListener(new ControllerMain(this));
        
        MainPanel.add(btnAddEntities);
        MainPanel.add(btnModifyEntities);
        return MainPanel;
    }
    
    public void showFrame(){
        this.getContentPane().add(this.getContent());
        this.setFrameTitle(this.title);
        this.setFrameDimension(this.DimFrame);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);        
    }
    
    public void hideFrame(){
        this.setVisible(false);
    }
    
}
