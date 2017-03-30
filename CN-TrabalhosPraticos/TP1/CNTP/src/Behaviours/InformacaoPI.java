/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.FonteInformacaoPI;
import Agents.TorreControlo;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author PEDRO
 */
public class InformacaoPI extends OneShotBehaviour{
    
    private FonteInformacaoPI pi;
    private TorreControlo t;

    public InformacaoPI(FonteInformacaoPI pi) {
        this.pi = pi;
    }
    
    @Override
    public void action(){
       AID receiver = new AID();
       receiver.setLocalName("torre");
       long time =  System.currentTimeMillis();
       ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);
       msg.setContent(this.pi.getLocalName());
       msg.setConversationId(""+time);
       msg.addReceiver(receiver);
       this.pi.send(msg);  
       this.pi.takeDown();
    }
}
