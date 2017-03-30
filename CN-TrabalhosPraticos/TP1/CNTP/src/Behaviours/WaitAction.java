/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.Airplane;
import Agents.TorreControlo;
import GUI.Principal;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ControllerException;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PEDRO
 */
public class WaitAction extends CyclicBehaviour{
    
    private Airplane a;
    private String ac;
    private TorreControlo tc;
    private Principal p;

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }
    
    

    public WaitAction(Airplane a) {
        this.a = a;
    }
    
    @Override
    public void action(){
        Boolean b = false;
        ACLMessage msg = this.a.receive();
        
        if(msg!=null){
            if(msg.getContent().equals("action1")){
                b = true;
                this.setAc("Descolas");
                System.out.println(this.a.getLocalName() + " recebi " + msg.getContent());
                //try {
                    /*List<String> rem = this.tc.getAirplanes();
                    for(String s: rem){
                    rem.remove(s);
                    }
                    //p.povoaAvioes();*/
                    //this.tc.getAirplanes().remove(a.getLocalName());
                    //this.tc.deleteAgent(this.a.getLocalName());
                System.out.println("Aviao " +  a.getLocalName() + " vai " +  this.getAc());
                //this.tc.removeAviao(b, this.a.getLocalName());
                this.a.takeDown();
                this.a.doDelete();
                
            }else if(msg.getContent().equals("action2")){
                this.setAc("Esperas");
                System.out.println(this.a.getLocalName() + " recebi " + msg.getContent());
            }else if(msg.getContent().equals("action3")){
                b = true;
                this.setAc("Cancelar");
                System.out.println(this.a.getLocalName() + "recebi" + msg.getContent());
                //this.tc.removeAviao(b, this.a.getLocalName());
                this.a.takeDown();
                this.a.doDelete();
                
            }
            
            
        }
    
    } 
}
