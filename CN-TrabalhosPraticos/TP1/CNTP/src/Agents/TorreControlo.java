/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import Behaviours.TorreEspera;
import GUI.Principal;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author PEDRO
 */
public class TorreControlo extends GuiAgent {
    
    private ArrayList<String> airplanes = new ArrayList<String>();
    
    protected Principal p;
    
    @Override
    protected void setup(){
       p = new Principal(this);
       System.out.println("Torre de Controlo a iniciar...");
       p.setVisible(true);
       super.setup();
       this.addBehaviour(new TorreEspera(this));
    }
    
     @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName()+" a morrer...");
    }
    
    @Override
    protected void onGuiEvent(GuiEvent ev){
        int comand = ev.getType();
        
        if(comand ==1){
            String content = (String)ev.getSource();
            try {
                this.createNewAgent(content);
            } catch (StaleProxyException ex) {
                Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<String> daAviao(ArrayList<String> ai){
        ArrayList<String> ap = new ArrayList<>();
        
        for(String s: airplanes){
            ap.add(s);
        }
        return ap;
    }
    
    private void createNewAgent(String nome) throws StaleProxyException{
        
        if(!airplanes.contains(nome)){
            airplanes.add(nome);
            p.povoaAvioes();
            ContainerController cc = getContainerController();
            AgentController ac = cc.createNewAgent(nome, Airplane.class.getName(), null);
            ac.start();
            p.avisaPositivo(nome);
        }
       
        else{
            p.avisa();
        }
    }

    
    

    
    
}
