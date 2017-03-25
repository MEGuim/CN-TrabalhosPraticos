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
            if(msg.getSender().equals("facts")){
                
            }
            else{
                System.out.println("Recebi uma mensagem de "+msg.getSender()+". Contéudo: "+msg.getContent());
                ACLMessage rsp = msg.createReply();
                if(msg.getPerformative() == ACLMessage.REQUEST){
                    rsp.setContent("Recebi o pedido");
                    rsp.setPerformative(ACLMessage.INFORM);
                }
                this.tc.send(rsp);
                this.tc.startProlog();
            }
        }
        block();
        
    }

    
    
}
