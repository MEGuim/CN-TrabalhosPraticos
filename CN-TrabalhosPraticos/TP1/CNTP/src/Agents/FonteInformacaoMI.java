/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import Behaviours.InformacaoMI;
import jade.core.Agent;
import java.util.ArrayList;

/**
 *
 * @author PEDRO
 */
public class FonteInformacaoMI extends Agent{
    
    //private String s;
   
    
    
    @Override
    protected void setup(){
        System.out.println("Fonte de Informação @mi a começar...");
        super.setup();
        this.addBehaviour(new InformacaoMI(this));
    }
    
    @Override
    public void takeDown(){
        super.takeDown();
        super.doDelete();
        System.out.println("Fonte de Informação @mi a terminar...");
    }
    
}
