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
import java.util.Arrays;
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
    private String trevision;
    private String prevision;
    private String orevision;
    
    protected Principal p;

    public String getOrevision() {
        return orevision;
    }

    public String getPrevision() {
        return prevision;
    }

    public String getTrevision() {
        return trevision;
    }

    public void setTrevision(String trevision) {
        this.trevision = trevision;
    }

    public void setOrevision(String orevision) {
        this.orevision = orevision;
    }

    public void setPrevision(String prevision) {
        this.prevision = prevision;
    }
        
   
    
    @Override
    protected void setup(){
       p = new Principal(this);
       System.out.println("Torre de Controlo a iniciar...");
       p.setVisible(true);
       super.setup();
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
                   String t = content.split(":")[1];
                   System.out.println(t);
                   this.createNewAgentT(t);
                }catch(StaleProxyException ex){
                    Logger.getLogger(FonteInformacaoMI.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            else if(content.matches("informacao:p.+")){
                try{
                    String t1 = content.split(":")[1];
                    System.out.println(t1);
                    this.createNewAgentP(t1);
                }catch(StaleProxyException ex){
                    Logger.getLogger(FonteInformacaoPI.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            
            else if(content.matches("informacao:o.+")){
                try{
                    String t2 = content.split(":")[1];
                    System.out.println(t2);
                    this.createNewAgentO(t2);
                }catch(StaleProxyException ex){
                    Logger.getLogger(FonteInformacaoOI.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            else if(content.matches("revisao:t.+")){
                try{
                    String r = content.split(":")[1];
                    System.out.println(r);
                    this.createNewAgentRevT(r);
                }catch(StaleProxyException ex){
                    Logger.getLogger(DefaultRevisionMI.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            else if(content.matches("revisao:p.+")){
                try{
                    String r1 = content.split(":")[1];
                    System.out.println(r1);
                    this.createNewAgentRevP(r1);
                }catch(StaleProxyException ex){
                    Logger.getLogger(DefaultRevisionPI.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            else if(content.matches("revisao:o.+")){
                try{
                    String r2 = content.split(":")[1];
                    System.out.println(r2);
                    this.createNewAgentRevO(r2);
                }catch(StaleProxyException ex){
                    Logger.getLogger(DefaultRevisionOI.class.getName()).log(Level.SEVERE, null, ex); 
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
    
    public void revisionMI(String name){
        p.imprimeText("(TorreControlo) Asserting New Default for @mi");
        Query q41 = new Query("default_arrival",new Term[]{new Atom("t"),new Atom("mi"),new Atom("in_set"),new Atom(name)});
        q41.hasSolution();
        
        Query q42 = new Query("listing",new Term[] {new Atom("cbs")});
	q42.open();
        p.imprimeText("(TorreControlo) The belief set is:");
	p.imprimeText(q42.getSolution().toString());
	q42.close();
										
	Query q43 = new Query("listing",new Term[] {new Atom("active")});
        q43.open();
	p.imprimeText("(TorreControlo) The set of active processes is:");
	p.imprimeText(q43.getSolution().toString());
	q43.close();
										
	Query q44 = new Query("listing",new Term[] {new Atom("suspended")});
	q44.open();
	p.imprimeText("(TorreControlo) The set of suspended processes is:");
	p.imprimeText(q44.getSolution().toString());
	q44.close();
    }
    
    public void revisionPI(String name){
        p.imprimeText("(TorreControlo) Asserting New Default for @pi");
        Query q51 = new Query("default_arrival",new Term[]{new Atom("p"),new Atom("pi"),new Atom("in_set"),new Atom(name)});
        q51.hasSolution();
        
        Query q52 = new Query("listing",new Term[] {new Atom("cbs")});
	q52.open();
        p.imprimeText("(TorreControlo) The belief set is:");
	p.imprimeText(q52.getSolution().toString());
	q52.close();
										
	Query q53 = new Query("listing",new Term[] {new Atom("active")});
        q53.open();
	p.imprimeText("(TorreControlo) The set of active processes is:");
	p.imprimeText(q53.getSolution().toString());
	q53.close();
										
	Query q54 = new Query("listing",new Term[] {new Atom("suspended")});
	q54.open();
	p.imprimeText("(TorreControlo) The set of suspended processes is:");
	p.imprimeText(q54.getSolution().toString());
	q54.close();
    }
    
    public void revisionOI(String name){
        p.imprimeText("(TorreControlo) Asserting New Default for @oi");
        Query q61 = new Query("default_arrival",new Term[]{new Atom("o"),new Atom("oi"),new Atom("in_set"),new Atom(name)});
        q61.hasSolution();
        
        Query q62 = new Query("listing",new Term[] {new Atom("cbs")});
	q62.open();
        p.imprimeText("(TorreControlo) The belief set is:");
	p.imprimeText(q62.getSolution().toString());
	q62.close();
										
	Query q63 = new Query("listing",new Term[] {new Atom("active")});
        q63.open();
	p.imprimeText("(TorreControlo) The set of active processes is:");
	p.imprimeText(q63.getSolution().toString());
	q63.close();
										
	Query q64 = new Query("listing",new Term[] {new Atom("suspended")});
	q64.open();
	p.imprimeText("(TorreControlo) The set of suspended processes is:");
	p.imprimeText(q64.getSolution().toString());
	q64.close();
    }
    
    
    public void startFactArrivalPI(String name){
        p.imprimeText("(TorreControlo) Asserting Answer for @pi");
        Query q21 = new Query("answer_arrival",new Term[]{new Atom("p"),new Atom("pi"),new Atom("in_set"),new Atom(name)});
        q21.hasSolution();
        
        Query q22 = new Query("listing",new Term[]{new Atom("answer")});
        q22.open();
        p.imprimeText("(TorreControlo) The answer set is");
        p.imprimeText(q22.getSolution().toString());
        q22.close();
        
        Query q23 = new Query("listing",new Term[] {new Atom("cbs")});
	q23.open();
	p.imprimeText("(TorreControlo)The belief set is now:");
	p.imprimeText(q23.getSolution().toString());
	q23.close();
					
	Query q24 = new Query("listing",new Term[] {new Atom("active")});
	q24.open();
	p.imprimeText("(TorreControlo)The set of active processes is:");
	p.imprimeText(q24.getSolution().toString());
	q24.close();
					
	Query q25 = new Query("listing",new Term[] {new Atom("suspended")});
	q25.open();
	p.imprimeText("(TorreControlo)The set of suspended processes is:");
	p.imprimeText(q25.getSolution().toString());
	q25.close();
    }
    
    public void startFactArrivalOI(String name){
        p.imprimeText("(TorreControlo) Asserting Answer for @oi");
        Query q31 = new Query("answer_arrival",new Term[]{new Atom("o"),new Atom("oi"),new Atom("in_set"),new Atom(name)});
        q31.hasSolution();
        
        Query q32 = new Query("listing",new Term[]{new Atom("answer")});
        q32.open();
        p.imprimeText("(TorreControlo) The answer set is");
        p.imprimeText(q32.getSolution().toString());
        q32.close();
        
        Query q33 = new Query("listing",new Term[] {new Atom("cbs")});
	q33.open();
	p.imprimeText("(TorreControlo)The belief set is now:");
	p.imprimeText(q33.getSolution().toString());
	q33.close();
					
	Query q34 = new Query("listing",new Term[] {new Atom("active")});
	q34.open();
	p.imprimeText("(TorreControlo)The set of active processes is:");
	p.imprimeText(q34.getSolution().toString());
	q34.close();
					
	Query q35 = new Query("listing",new Term[] {new Atom("suspended")});
	q35.open();
	p.imprimeText("(TorreControlo)The set of suspended processes is:");
	p.imprimeText(q35.getSolution().toString());
	q35.close();
    }
    
    public void startFactArrivalMI(String name){
        p.imprimeText("(TorreControlo) Asserting Answer for @mi");
        Query q12 = new Query("answer_arrival",new Term[]{new Atom("t"),new Atom("mi"),new Atom("in_set"),new Atom(name)});
        q12.hasSolution();
        
        Query q13 = new Query("listing",new Term[]{new Atom("answer")});
        q13.open();
        p.imprimeText("(TorreControlo) The answer set is");
        p.imprimeText(q13.getSolution().toString());
        q13.close();
        
        Query q14 = new Query("listing",new Term[] {new Atom("cbs")});
	q14.open();
	p.imprimeText("(TorreControlo)The belief set is now:");
	p.imprimeText(q14.getSolution().toString());
	q14.close();
					
	Query q15 = new Query("listing",new Term[] {new Atom("active")});
	q15.open();
	p.imprimeText("(TorreControlo)The set of active processes is:");
	p.imprimeText(q15.getSolution().toString());
	q15.close();
					
	Query q16 = new Query("listing",new Term[] {new Atom("suspended")});
	q16.open();
	p.imprimeText("(TorreControlo)The set of suspended processes is:");
	p.imprimeText(q16.getSolution().toString());
	q16.close();
    }
    
    public void startProlog(){
       
        try{
            Term arg[] = {new Atom("question1")};
            
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
        AgentController ac = cc.createNewAgent(nome, FonteInformacaoMI.class.getName(), null);
        ac.start();
    }
    
    private void createNewAgentP(String nome) throws StaleProxyException{
        ContainerController cc = getContainerController();
        AgentController ac = cc.createNewAgent(nome, FonteInformacaoPI.class.getName(), null);
        ac.start();
    }
    
    private void createNewAgentO(String nome) throws StaleProxyException{
        ContainerController cc = getContainerController();
        AgentController ac = cc.createNewAgent(nome, FonteInformacaoOI.class.getName(), null);
        ac.start();
    }
    
    private void createNewAgentRevT(String nome) throws StaleProxyException{
        ContainerController cc = getContainerController();
        AgentController ac = cc.createNewAgent(nome, DefaultRevisionMI.class.getName(), null);
        ac.start();
    }
   
    private void createNewAgentRevO(String nome) throws StaleProxyException{
        ContainerController cc = getContainerController();
        AgentController ac = cc.createNewAgent(nome, DefaultRevisionOI.class.getName(), null);
        ac.start();
    }
    
    private void createNewAgentRevP(String nome) throws StaleProxyException{
        ContainerController cc = getContainerController();
        AgentController ac = cc.createNewAgent(nome, DefaultRevisionPI.class.getName(), null);
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
