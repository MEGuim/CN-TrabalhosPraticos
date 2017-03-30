/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.FonteInformacaoOI;
import Agents.TorreControlo;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author PEDRO
 */
public class InformacaoOI extends OneShotBehaviour {
    
    private FonteInformacaoOI oi;
    private TorreControlo t;

    public InformacaoOI(FonteInformacaoOI oi) {
        this.oi = oi;
    }

    @Override
    public void action(){
       AID receiver = new AID();
       receiver.setLocalName("torre");
       long time =  System.currentTimeMillis();
       ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);
       msg.setContent(this.oi.getLocalName());
       msg.setConversationId(""+time);
       msg.addReceiver(receiver);
       this.oi.send(msg);  
       this.oi.takeDown();
    }
    
    
}
