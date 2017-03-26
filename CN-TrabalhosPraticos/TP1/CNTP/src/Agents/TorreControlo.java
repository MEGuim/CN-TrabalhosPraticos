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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;


/**
 *
 * @author PEDRO
 */
public class TorreControlo extends GuiAgent {
    
    private List<String> airplanes = new ArrayList<>();
    private String path;
    private List<String> answers = new ArrayList<String>();
    private List<String> answers_values = new ArrayList<String>();
    private List<String> all_variables = new ArrayList<String>();
    private List<String> current_values = new ArrayList<String>();
    
    
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
            if(content.matches("informacao:t.+")){
                try{
                    this.createNewAgentT(content);
                }catch(StaleProxyException ex){
                    Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            else if(content.matches("informacao:p.+")){
                try{
                    this.createNewAgentP(content);
                }catch(StaleProxyException ex){
                    Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            
            else if(content.matches("informacao:o.+")){
                try{
                    this.createNewAgentO(content);
                }catch(StaleProxyException ex){
                    Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            else{
            try {
                this.createNewAgent(content);
            } catch (StaleProxyException ex) {
                Logger.getLogger(Airplane.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public void startProlog(){
       
        try{
            Term arg[] = {new Atom("question1")};
            p.imprimeText("(TorreControlo) Initialize the Speculative Computation");
            Query q1 = new Query("consult",new Term[]{new Atom("C:/Users/PEDRO/Desktop/CN/TP1/git/tp1cn/CN-TrabalhosPraticos/CN-TrabalhosPraticos/TP1/CNTP/spc_componentv3.pl")});
            //System.out.println(q1.hasSolution());
            q1.hasSolution();
        
            p.imprimeText("(TorreContro) Loading the aircontrol case of study");
            Query q2 = new Query("load");
            //System.out.println(q2.hasSolution());
            q2.hasSolution();
           
            p.imprimeText("(TorreControlo) Initializing the belief set");
            Query q3 = new Query("init");
            q3.open();
            q3.getSolution();
            q3.close();
            
            //Variable CBS = new Variable("csb");
            Query q4 = new Query("listing",new Term[]{new Atom("cbs")});
            q4.open();
            p.imprimeText("(TorreControlo) The current belief set is:");
            //StringBuilder sb = new StringBuilder();
            //sb.append(q4.getSolution().entrySet());
            //String s = sb.toString();
            //System.out.println(s);
            //Term[] t = null;
            //for(Map.Entry<String,Term> sol: q4.getSolution().entrySet()){
                //System.out.println("Tenho cenas");
                //System.out.println(sol.getValue().name());
            //Map<String, Term>[] solution;    
            //solution=q4.allSolutions();
           
            //p.imprimeText(q4.getSolution().toString());
           	      
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<String,Term> sol:q4.getSolution().entrySet()){
                sb.append(sol.getKey());
                sb.append(sol.getValue());
                sb.append("\n");
            }
            String st;
            st = sb.toString();
            System.out.println(st);
            p.imprimeText(st);
            q4.close();


            Query q5 = new Query("query",new Compound("next",arg));
            Map<String,Term> s1 = q5.oneSolution();

            if(!s1.isEmpty()){
                p.imprimeText("(TorreControlo) Calculating the next action in the aircontrol process...");
                p.imprimeText("(TorreControlo) The alternative action is");
                p.imprimeText(s1.get("List").toString());

            }

            Query q6 = new Query("listing",new Term[]{new Atom("active")});
            q6.open();
            p.imprimeText("(TorreControlo) The set of active processes is");
            p.imprimeText(q6.getSolution().toString());
            q6.close();

            Query q7 = new Query("listing",new Term[]{new Atom("suspended")});
            q7.open();
            p.imprimeText("(TorreControlo) The set of suspended processes is");
            p.imprimeText(q7.getSolution().toString());
            q7.close(); 

        }catch(SecurityException e){
            e.printStackTrace();
        }
       
        
    }
    
    private void createNewAgentT(String nome) throws StaleProxyException{
        ContainerController cc = getContainerController();
        AgentController ac = cc.createNewAgent("fonteM", FonteInformacaoMI.class.getName(), null);
        ac.start();
    }
    
    private void createNewAgentP(String nome) throws StaleProxyException{
        ContainerController cc = getContainerController();
        AgentController ac = cc.createNewAgent("fonteP", FonteInformacaoPI.class.getName(), null);
        ac.start();
    }
    
    private void createNewAgentO(String nome) throws StaleProxyException{
        ContainerController cc = getContainerController();
        AgentController ac = cc.createNewAgent("fonteO", FonteInformacaoOI.class.getName(), null);
        ac.start();
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
