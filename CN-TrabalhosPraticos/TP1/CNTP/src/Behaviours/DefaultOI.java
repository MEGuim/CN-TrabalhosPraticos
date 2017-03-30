/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;


import Agents.DefaultRevisionOI;
import Agents.TorreControlo;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author PEDRO
 */
public class DefaultOI extends OneShotBehaviour{
    
    private DefaultRevisionOI revo;
    private TorreControlo t;
    
    public DefaultOI(DefaultRevisionOI oi) {
        this.revo = oi;
    }

    @Override
    public void action(){
       AID receiver = new AID();
       receiver.setLocalName("torre");
       long time =  System.currentTimeMillis();
       ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
       msg.setContent(this.revo.getLocalName());
       msg.setConversationId(""+time);
       msg.addReceiver(receiver);
       this.revo.send(msg);  
       this.revo.takeDown();
    }
}
