/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.TorreControlo;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PEDRO
 */
public class TorreEspera extends CyclicBehaviour {
    
    private TorreControlo tc;
    
    public TorreEspera(TorreControlo t){
        this.tc = t;
    }
    
    @Override
    public void action(){
        ACLMessage msg = this.tc.receive();
        
        if(msg != null){
            if(msg.getPerformative() == ACLMessage.CONFIRM){
                if(msg.getSender().getLocalName().matches("t.+")){
                    System.out.println(msg.getContent());
                    try {
                        //System.out.println(msg.getSender().getLocalName());
                        this.tc.startFactArrivalMI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(msg.getSender().getLocalName().matches("o.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.startFactArrivalOI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else if(msg.getSender().getLocalName().matches("p.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.startFactArrivalPI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                        
            }
            else if(msg.getPerformative()== ACLMessage.INFORM){
                if(msg.getSender().getLocalName().matches("t.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.revisionMI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(msg.getSender().getLocalName().matches("o.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.revisionOI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(msg.getSender().getLocalName().matches("p.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.revisionPI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else{
                System.out.println("Recebi uma mensagem de "+msg.getSender()+". Contéudo: "+msg.getContent());
                ACLMessage rsp = msg.createReply();
                if(msg.getPerformative() == ACLMessage.REQUEST){
                    rsp.setContent("Recebi o pedido");
                    rsp.setPerformative(ACLMessage.INFORM);
                    this.tc.send(rsp);
                    this.tc.startProlog();
                }
                //this.tc.send(rsp);
                //this.tc.startProlog();
            }
        }
        block();
        
    }

    
    
}
