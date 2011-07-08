package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Views.*;

public class ControllerMain implements ActionListener{
        
        public Object component;
        public MainFrame MFrame;
        
        public ControllerMain(Object component){
            this.component = component;
        }
        
        public ControllerMain(MainFrame MFrame ){
            this.MFrame = MFrame;
        }
        
        @Override
	public void actionPerformed(ActionEvent ae) {
            String ActionCommand = ae.getActionCommand();
            
            if (ActionCommand.equals("Agregar Entidad")){
                new ManageEntities().showFrame();
            }else if (ActionCommand.equals("Modificar Entidad")){
                new ShowListEntities().showFrame();    
            }
        }
}
