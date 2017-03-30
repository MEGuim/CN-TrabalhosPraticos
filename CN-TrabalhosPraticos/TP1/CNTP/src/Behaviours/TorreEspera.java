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
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(msg.getSender().getLocalName().matches("o.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.startFactArrivalOI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else if(msg.getSender().getLocalName().matches("p.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.startFactArrivalPI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
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
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(msg.getSender().getLocalName().matches("o.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.revisionOI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(msg.getSender().getLocalName().matches("p.+")){
                    System.out.println(msg.getContent());
                    try {
                        this.tc.revisionPI(msg.getSender().getLocalName());
                    } catch (IOException ex) {
                        Logger.getLogger(TorreEspera.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
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
                    if(this.tc.getAction().equals("action1")){
                    String s1 = this.tc.getAction();
                    if(!this.tc.getAirplanes().isEmpty()){
                        for(int i=0;i<=this.tc.getAirplanes().size();i++){
                            System.out.println(i);
                            System.out.println(this.tc.getAirplanes().size());
                            new SendAction(this.tc,s1).action();
                        }
                    }
                        
                    }
                    else if(this.tc.getAction().equals("action2")){
                        String s2 = this.tc.getAction();
                        new SendAction(this.tc,s2).action();
                    }
                    else if(this.tc.getAction().equals("action3")){
                        String s3 = this.tc.getAction();
                        new SendAction(this.tc,s3).action();
                    }
                }
                //this.tc.send(rsp);
                //this.tc.startProlog();
            }
        }
        block();
        
    }

    
    
}
