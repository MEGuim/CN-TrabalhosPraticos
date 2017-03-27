/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import Behaviours.DefaultPI;
import jade.core.Agent;

/**
 *
 * @author PEDRO
 */
public class DefaultRevisionPI extends Agent {
     @Override
    protected void setup(){
        System.out.println("Agente Default Revision @pi a começar...");
        super.setup();
        this.addBehaviour(new DefaultPI(this));
    }
    
    public void takeDown(){
        super.takeDown();
        System.out.println("Agente Default Revision @pi a terminar...");
    }
}
