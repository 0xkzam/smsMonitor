

package com.kuz.tmp.control;

import com.kuz.tmp.model.Message;
import com.kuz.tmp.view.MainUI;
import java.util.List;

/**
 * Main Controller class
 * 
 * @author Kasun Amarasena
 */
public class MainController implements MainUIListener{
    
    public MainController(){}

    MainController(MainUI ui) {
        
    }
   

    @Override
    public void deleteAction(MainUIEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> filterAction(MainUIEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> newButtonAction(MainUIEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> oldButtonAction(MainUIEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> resetAction(MainUIEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
