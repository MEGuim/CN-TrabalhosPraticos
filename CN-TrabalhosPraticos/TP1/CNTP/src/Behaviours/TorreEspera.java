/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.TorreControlo;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

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
                    //System.out.println(msg.getSender().getLocalName());
                    this.tc.startFactArrivalMI(msg.getSender().getLocalName());
                }
                else if(msg.getSender().getLocalName().matches("o.+")){
                    System.out.println(msg.getContent());
                    this.tc.startFactArrivalOI(msg.getSender().getLocalName());
                    
                }
                else if(msg.getSender().getLocalName().matches("p.+")){
                    System.out.println(msg.getContent());
                    this.tc.startFactArrivalPI(msg.getSender().getLocalName());
                    
                }
                        
            }
            else if(msg.getPerformative()== ACLMessage.INFORM){
                if(msg.getSender().getLocalName().matches("t.+")){
                    System.out.println(msg.getContent());
                    this.tc.revisionMI(msg.getSender().getLocalName());
                }
                else if(msg.getSender().getLocalName().matches("o.+")){
                    System.out.println(msg.getContent());
                    this.tc.revisionOI(msg.getSender().getLocalName());
                }
                else if(msg.getSender().getLocalName().matches("p.+")){
                    System.out.println(msg.getContent());
                    this.tc.revisionPI(msg.getSender().getLocalName());
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
