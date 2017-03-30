/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import Behaviours.InformacaoOI;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 *
 * @author PEDRO
 */
public class FonteInformacaoOI extends Agent {
    @Override
    protected void setup(){
        
        super.setup();
        /*DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setName(getLocalName());
        sd.setType("airplanes");
        dfd.addServices(sd);*/
        
        //try{
          //DFService.register(this, dfd);
          System.out.println("Fonte de Informação @oi a começar...");
        //}catch(FIPAException fe){
         //  fe.printStackTrace();
        //}
        this.addBehaviour(new InformacaoOI(this));
    }
    
    @Override
    public void takeDown(){
        super.takeDown();
        /*try{
            DFService.deregister(this);
            System.out.println("Agente [" + this.getLocalName() + "] a terminar");
        }catch(Exception e){
           e.printStackTrace();
        }*/
        System.out.println("Fonte de Informação @oi a terminar...");
        
    }
    
}
