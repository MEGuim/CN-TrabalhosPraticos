/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.DefaultRevisionPI;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author PEDRO
 */
public class DefaultPI extends OneShotBehaviour {
    
    private DefaultRevisionPI revp;

    public DefaultPI(DefaultRevisionPI pi) {
        this.revp = pi;
    }

    @Override
    public void action(){
       AID receiver = new AID();
       receiver.setLocalName("torre");
       long time =  System.currentTimeMillis();
       ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
       msg.setContent(this.revp.getLocalName());
       msg.setConversationId(""+time);
       msg.addReceiver(receiver);
       this.revp.send(msg);  
       this.revp.takeDown();
    }
}
