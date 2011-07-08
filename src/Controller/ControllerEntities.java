package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Views.ManageEntities;
import Views.ShowListEntities;

import Business.Entities;
import java.io.IOException;
import java.util.List;
import javax.management.Query;
import javax.swing.DefaultListModel;

public class ControllerEntities implements ActionListener{
        
        public Object component;
        public ManageEntities MEntities;
        public ShowListEntities SLEntities;
        public Entities ent;
        
        public ControllerEntities(Object component){
            this.component = component;
        }
        
        public ControllerEntities(ManageEntities MEntities ){
            this.MEntities = MEntities;
        }
        
        public ControllerEntities(ManageEntities MEntities, Entities ent,ShowListEntities SLEntities){
            this.MEntities = MEntities;
            this.SLEntities = SLEntities;
            this.ent = ent;
        }

        public ControllerEntities(ShowListEntities SLEntities ){
            this.SLEntities = SLEntities;
        }
        
    
        @Override
	public void actionPerformed(ActionEvent ae) {
            String ActionCommand = ae.getActionCommand();
            if (ActionCommand.equals("Agregar")){
                if (this.MEntities.inputNombre.getText().equals("")){
                    JOptionPane.showMessageDialog(this.MEntities, "El Nombre no puede quedar en blanco");
                }
                else if (this.MEntities.inputApellido.getText().equals("")){
                    JOptionPane.showMessageDialog(this.MEntities, "El Apellido no puede quedar en blanco");
                }
                else{ 
                    ent = new Entities();
                    ent.setNombre(this.MEntities.inputNombre.getText());
                    ent.setApellido(this.MEntities.inputApellido.getText());
                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU1");
                    EntityManager em = emf.createEntityManager();
                    em.getTransaction().begin();
                    em.persist(ent);
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(this.MEntities, "La entidad con el nombre de " + this.MEntities.inputNombre.getText() + " se ha agregado correctamente");
                    this.MEntities.setVisible(false);
                }
            }else if(ActionCommand.equals("Editar")){
                this.SLEntities.hideFrame();
                new ManageEntities((int) this.SLEntities.arrIdEntities.get(this.SLEntities.ListEntities.getSelectedIndex()),this.SLEntities).showFrame();
            }else if(ActionCommand.equals("Modificar")){
                if (this.MEntities.inputNombre.getText().equals("")){
                    JOptionPane.showMessageDialog(this.MEntities, "El Nombre no puede quedar en blanco");
                }
                else{
                   if (!this.MEntities.inputNombre.getText().equals(this.ent.getNombre())){
                       this.ent.setNombre(this.MEntities.inputNombre.getText());
                   }
                   EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU1");
                   EntityManager em = emf.createEntityManager();
                   em.getTransaction().begin();
                   em.merge(this.ent);
                   em.getTransaction().commit();
                   JOptionPane.showMessageDialog(this.MEntities, "La entidad con el nombre de " + this.ent.getNombre() + " se ha actualizado correctamente");
                   this.MEntities.hideFrame();
                   this.SLEntities.ListEntities.setModel(this.SLEntities.getOptionsListEntities(""));
                }
            }else if(ActionCommand.equals("Remover")){
                   EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU1");
                   EntityManager em = emf.createEntityManager();
                   int id = (int) this.SLEntities.arrIdEntities.get(this.SLEntities.ListEntities.getSelectedIndex());
                   em.getTransaction().begin();
                   Entities tempEnt = em.find(Entities.class,id);
                   em.remove(tempEnt);
                   em.getTransaction().commit();
                   em.close();
                   JOptionPane.showMessageDialog(this.MEntities, "La entidad con el nombre de " + tempEnt.getNombre() + " se ha elimnado correctamente");
                   this.MEntities.hideFrame();
            }else if(ActionCommand.equals("Buscar")){
                DefaultListModel ListEntities = this.SLEntities.getOptionsListEntities(this.SLEntities.inputBuscar.getText());
                this.SLEntities.ListEntities.setModel(ListEntities);
            }
        }
}
