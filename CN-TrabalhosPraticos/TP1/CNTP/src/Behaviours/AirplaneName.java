/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.Airplane;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author PEDRO
 */
public class AirplaneName extends OneShotBehaviour{
    
    private Airplane a;
    
    public AirplaneName(Airplane ai){
        this.a = ai;
    }
    
    @Override
    public void action(){
       AID receiver = new AID();
       receiver.setLocalName("torre");
       long time =  System.currentTimeMillis();
       ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
       msg.setContent("Sou o avião " + this.a.getLocalName() + " e pretendo descolar");
       msg.setConversationId(""+time);
       msg.addReceiver(receiver);
       this.a.send(msg);     
    }
    
    
}
