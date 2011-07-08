package Views;

import Business.Entities;
import Controller.ControllerEntities;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ShowListEntities extends MainFrame{
        
    public JButton btnModEntities;
    public JList ListEntities;
    public List<Object> arrIdEntities = new ArrayList<Object>();
    public JButton btnBuscarEntities;
    public JTextField inputBuscar = new JTextField();
    public JButton btnRemEntities = new JButton();
    public JPanel panelManage = new JPanel();
    
    
    public JLabel lblMain = new JLabel();
        public ShowListEntities(){
            super();
            this.title = "Modificar Entidades";
            this.DimFrame = new Dimension(232,254);
        }
        
        
        public DefaultListModel getOptionsListEntities(String valueq){
            Query q;
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU1");
            EntityManager em = emf.createEntityManager();
            if (!valueq.equals("")) q = em.createQuery("select e from Entities e where e.nombre = (:nombre)").setParameter("nombre", valueq); 
            else q = em.createQuery("SELECT e FROM Entities e"); 
            List<Entities> RowsEntities = q.getResultList();
            DefaultListModel model = new DefaultListModel();
            for(Entities ent: RowsEntities){
                model.addElement(ent.getNombre());
                arrIdEntities.add(ent.getId());
            }
            return model;
        }

        @Override
        public JPanel getContent(){
            JPanel EntityPanel = new JPanel();
            
            ListEntities = new JList(this.getOptionsListEntities(""));
            lblMain = new JLabel("Seleccione el registro a modificar:");
            btnBuscarEntities = new JButton("Buscar");
            btnModEntities = new JButton("Editar");
            btnRemEntities = new JButton("Remover");
            
            inputBuscar.setPreferredSize(new Dimension(100,30));
            ListEntities.setPreferredSize(new Dimension(150,100));
            
            ListEntities.setAutoscrolls(true);
            
            
            btnModEntities.setEnabled(false);
            btnRemEntities.setEnabled(false);
            
            btnModEntities.addActionListener(new ControllerEntities(this));
            btnRemEntities.addActionListener(new ControllerEntities(this));
            btnBuscarEntities.addActionListener(new ControllerEntities(this));
            
            ListEntities.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    btnModEntities.setEnabled(true);
                    btnRemEntities.setEnabled(true);
                }
             });

            panelManage.add(btnModEntities);
            panelManage.add(btnRemEntities);
            
            EntityPanel.add(lblMain);
            EntityPanel.add(inputBuscar);
            EntityPanel.add(btnBuscarEntities);
            EntityPanel.add(ListEntities);
            
            EntityPanel.add(panelManage);
            
            
            return EntityPanel;
        }
        
}
