/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import Behaviours.DefaultMI;
import jade.core.Agent;

/**
 *
 * @author PEDRO
 */
public class DefaultRevisionMI extends Agent{
    
    @Override
    protected void setup(){
        System.out.println("Agente Default Revision @mi a começar...");
        super.setup();
        this.addBehaviour(new DefaultMI(this));
    }
    
    public void takeDown(){
        super.takeDown();
        super.doDelete();
        System.out.println("Agente Default Revision @mi a terminar...");
    }
    
}
