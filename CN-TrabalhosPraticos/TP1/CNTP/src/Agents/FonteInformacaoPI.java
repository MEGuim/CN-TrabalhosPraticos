/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import Behaviours.InformacaoPI;
import jade.core.Agent;

/**
 *
 * @author PEDRO
 */
public class FonteInformacaoPI extends Agent{
    @Override
    protected void setup(){
        System.out.println("Fonte de Informação @pi a começar...");
        super.setup();
        this.addBehaviour(new InformacaoPI(this));
    }
    
    @Override
    public void takeDown(){
        super.takeDown();
        super.doDelete();
        System.out.println("Fonte de Informação @pi a terminar...");
    }
    
}
