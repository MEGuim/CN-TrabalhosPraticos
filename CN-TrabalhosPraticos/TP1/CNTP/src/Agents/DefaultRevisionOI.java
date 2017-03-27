/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import Behaviours.DefaultOI;
import jade.core.Agent;

/**
 *
 * @author PEDRO
 */
public class DefaultRevisionOI extends Agent {
     @Override
    protected void setup(){
        System.out.println("Agente Default Revision @oi a começar...");
        super.setup();
        this.addBehaviour(new DefaultOI(this));
    }
    
    public void takeDown(){
        super.takeDown();
        System.out.println("Agente Default Revision @oi a terminar...");
    }
}
