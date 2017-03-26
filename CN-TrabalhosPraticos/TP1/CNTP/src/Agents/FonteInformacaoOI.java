/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import jade.core.Agent;

/**
 *
 * @author PEDRO
 */
public class FonteInformacaoOI extends Agent {
    @Override
    protected void setup(){
        System.out.println("Fonte de Informação @oi a começar...");
        super.setup();
        //this.addBehaviour(new InformacaoMI(this));
    }
    
    @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println("Fonte de Informação @oi a terminar...");
    }
    
}
