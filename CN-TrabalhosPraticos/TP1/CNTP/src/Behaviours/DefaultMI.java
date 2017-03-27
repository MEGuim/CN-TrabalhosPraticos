/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;


import Agents.DefaultRevisionMI;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author PEDRO
 */
public class DefaultMI extends OneShotBehaviour {
    
    private DefaultRevisionMI revm;

    public DefaultMI(DefaultRevisionMI mi) {
        this.revm = mi;
    }

    @Override
    public void action(){
       AID receiver = new AID();
       receiver.setLocalName("torre");
       long time =  System.currentTimeMillis();
       ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
       msg.setContent(this.revm.getLocalName());
       msg.setConversationId(""+time);
       msg.addReceiver(receiver);
       this.revm.send(msg);  
       this.revm.takeDown();
    }
}
