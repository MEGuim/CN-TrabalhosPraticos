/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Behaviours;

import Agents.Airplane;
import Agents.TorreControlo;
import GUI.Principal;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


/**
 *
 * @author PEDRO
 */
public class WaitAction extends CyclicBehaviour{
    
    private Airplane a;
    private String ac;
    private TorreControlo tc;
    private Principal p;

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }
    
    

    public WaitAction(Airplane a) {
        this.a = a;
    }
    
    
    /*public void removeAviao (String nome){
         TorreControlo tc = new TorreControlo();
         System.out.println(tc.getAirplanes());
         for(int i=0;i<tc.airplanes.size();i++){
             System.out.println(tc.airplanes.size());
             System.out.println(tc.airplanes.get(0));
             if(tc.airplanes.get(0).equals(nome)){
                 tc.airplanes.remove(nome);
             }
             
         }
         
         System.out.println(tc.getAirplanes()); 
    }*/
    
    @Override
    public void action(){
        boolean b = false;
        ACLMessage msg = this.a.receive();
        
        if(msg!=null){
            if(msg.getContent().equals("action1")){
                b = true;
                this.setAc("Descolar");
                System.out.println(this.a.getLocalName() + " recebi " + msg.getContent());
                //try {
                    /*List<String> rem = this.tc.getAirplanes();
                    for(String s: rem){
                    rem.remove(s);
                    }
                    //p.povoaAvioes();*/
                    //this.tc.getAirplanes().remove(a.getLocalName());
                    //this.tc.deleteAgent(this.a.getLocalName());
                System.out.println("Aviao " +  a.getLocalName() + " vai " +  this.getAc());
                //String s = this.a.getLocalName();
                //this.tc.removeAviao(b, this.a.getLocalName());
                //this.a.removeAviao(s);
                this.a.takeDown();
                this.a.doDelete();
                //this.a.removeAviao(s);
                
            }else if(msg.getContent().equals("action2")){
                this.setAc("Esperar");
                System.out.println(this.a.getLocalName() + " recebi " + msg.getContent());
                System.out.println("Aviao " +  a.getLocalName() + " vai " +  this.getAc());
            }else if(msg.getContent().equals("action3")){
                b = true;
                this.setAc("Cancelado");
                System.out.println(this.a.getLocalName() + "recebi" + msg.getContent());
                //this.tc.removeAviao(b, this.a.getLocalName());
                System.out.println("Aviao " +  a.getLocalName() + " vai ser " +  this.getAc());
                this.a.takeDown();
                this.a.doDelete();
                
            }
            
            
        }
    
    } 
}
