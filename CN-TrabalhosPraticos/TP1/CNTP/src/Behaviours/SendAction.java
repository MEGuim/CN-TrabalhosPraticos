/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.TorreControlo;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.List;

/**
 *
 * @author PEDRO
 */
public class SendAction extends OneShotBehaviour {
    
    private TorreControlo t;
    private String s;

    public SendAction(TorreControlo t, String s) {
        this.t = t;
        this.s = s;
    }
    
    
    @Override
    public void action(){
        System.out.println("ESTOU A ENVIAR UMA MENSAGEM");
        AID receiver = new AID();
        List<String> aux = this.t.getAirplanes();
        String sol = aux.get(aux.size()-1);
        receiver.setLocalName(sol);
        long time =  System.currentTimeMillis();
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(s);
        msg.setConversationId(""+time);
        msg.addReceiver(receiver);
        this.t.send(msg);     
        
    }
    
}
