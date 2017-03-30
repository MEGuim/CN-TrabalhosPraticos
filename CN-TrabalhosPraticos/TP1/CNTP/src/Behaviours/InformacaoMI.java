/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.FonteInformacaoMI;
import Agents.TorreControlo;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author PEDRO
 */
public class InformacaoMI extends OneShotBehaviour {
    
    private FonteInformacaoMI fi;
    private TorreControlo t;

    public InformacaoMI(FonteInformacaoMI fi) {
        this.fi = fi;
    }
    
    @Override
    public void action(){
        AID receiver = new AID();
       receiver.setLocalName("torre");
       long time =  System.currentTimeMillis();
       ACLMessage msg = new ACLMessage(ACLMessage.CONFIRM);
       msg.setContent(this.fi.getLocalName());
       msg.setConversationId(""+time);
       msg.addReceiver(receiver);
       this.fi.send(msg);  
       this.fi.takeDown();
    }
}
