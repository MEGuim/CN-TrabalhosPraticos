/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import Behaviours.SendAction;
import Behaviours.TorreEspera;
import GUI.Principal;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
    
    public List<String> airplanes = new ArrayList<>();
    private String trevision;
    private String prevision;
    private String orevision;
    private String action;
    private Path caminho = Paths.get("C:/Users/Alexandre Mirra/Documents/NetBeansProjects/CN-TrabalhosPraticos/CN-TrabalhosPraticos/TP1/CNTP/MyListing.txt");
    private ContainerController container;
    private AgentController agentcontroller;
    private String fontet;
    private String fonteo;
    private String fontep;
    private String defaulto;
    private String defaultt;
    private String defaultp;
    //private Boolean apaga = false;

    public String getDefaulto() {
        return defaulto;
    }

    public String getDefaultp() {
        return defaultp;
    }

    public String getDefaultt() {
        return defaultt;
    }

    public void setDefaulto(String defaulto) {
        this.defaulto = defaulto;
    }

    public void setDefaultp(String defaultp) {
        this.defaultp = defaultp;
    }

    public void setDefaultt(String defaultt) {
        this.defaultt = defaultt;
    }

    public String getFonteo() {
        return fonteo;
    }

    public String getFontep() {
        return fontep;
    }

    public String getFontet() {
        return fontet;
    }

    public void setFonteo(String fonteo) {
        this.fonteo = fonteo;
    }

    public void setFontep(String fontep) {
        this.fontep = fontep;
    }

    public void setFontet(String fontet) {
        this.fontet = fontet;
    }
    
    
    
    
    
    
    public List<String> getAirplanes() {
        List<String> res = new ArrayList<>();
        for(String s: airplanes){
            res.add(s);
        }
        return res;
    }
    
    
    
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
    
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
       Query q1 = new Query("consult",new Term[]{new Atom("C:/Users/Alexandre Mirra/Documents/NetBeansProjects/CN-TrabalhosPraticos/CN-TrabalhosPraticos/TP1/CNTP/spc_componentv3.pl")});
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
    
    public void revisionMI(String name) throws IOException, InterruptedException{
        p.imprimeText("(TorreControlo) Asserting New Default for @mi");
        Query q41 = new Query("default_arrival",new Term[]{new Atom("t"),new Atom("mi"),new Atom("in_set"),new Atom(name)});
        q41.hasSolution();
        
        Query q42 = new Query("list_items",new Term[] {new Atom("cbs")});
	q42.hasSolution();
        p.imprimeText("(TorreControlo) The belief set is:");
        Stream<String> lines21 = Files.lines(caminho);
        lines21.forEach(s -> p.imprimeText(s));
										
	Query q43 = new Query("list_items",new Term[] {new Atom("active")});
        q43.hasSolution();
	p.imprimeText("(TorreControlo) The set of active processes is:");
	String linhas7 = new String(Files.readAllBytes(caminho));
        System.out.println(linhas7);
        char c = linhas7.charAt(linhas7.indexOf("action")+6);
        System.out.println(c);
        String st = "action"+c;
        System.out.println(st);
        this.setAction(st);
        System.out.println(this.getAction());
        p.imprimeText(linhas7);
										
	Query q44 = new Query("list_items",new Term[] {new Atom("suspended")});
	q44.hasSolution();
	p.imprimeText("(TorreControlo) The set of suspended processes is:");
        Stream<String> lines23 = Files.lines(caminho);
        lines23.forEach(s -> p.imprimeText(s));
        
        p.imprimeText("(TorreControlo) Action is");
        p.imprimeText(this.getAction());
        /*
        if(this.getAction().equals("action1")){
            List<String> so = this.getAirplanes();
            if(!so.isEmpty()){
                for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action1").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }else if(this.getAction().equals("action3")){
                 for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action3").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }
            
            
        }*/
    }
    
    public void revisionPI(String name) throws IOException, InterruptedException{
        p.imprimeText("(TorreControlo) Asserting New Default for @pi");
        Query q51 = new Query("default_arrival",new Term[]{new Atom("p"),new Atom("pi"),new Atom("in_set"),new Atom(name)});
        q51.hasSolution();
        
        Query q52 = new Query("list_items",new Term[] {new Atom("cbs")});
	q52.hasSolution();
        p.imprimeText("(TorreControlo) The belief set is:");
        Stream<String> lines18 = Files.lines(caminho);
        lines18.forEach(s -> p.imprimeText(s));
										
	Query q53 = new Query("list_items",new Term[] {new Atom("active")});
        q53.hasSolution();
	p.imprimeText("(TorreControlo) The set of active processes is:");
	String linhas6 = new String(Files.readAllBytes(caminho));
        System.out.println(linhas6);
        char c = linhas6.charAt(linhas6.indexOf("action")+6);
        System.out.println(c);
        String st = "action"+c;
        System.out.println(st);
        this.setAction(st);
        System.out.println(this.getAction());
        p.imprimeText(linhas6);
										
	Query q54 = new Query("list_items",new Term[] {new Atom("suspended")});
	q54.hasSolution();
	p.imprimeText("(TorreControlo) The set of suspended processes is:");
        Stream<String> lines20 = Files.lines(caminho);
        lines20.forEach(s -> p.imprimeText(s));
        p.imprimeText("(TorreControlo) Action is");
        p.imprimeText(this.getAction());
        
        /*
        if(this.getAction().equals("action1")){
            List<String> so = this.getAirplanes();
            if(!so.isEmpty()){
                for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action1").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }else if(this.getAction().equals("action3")){
                 for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action3").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }
            
            
        }*/
        
    }
    
    public void revisionOI(String name) throws IOException, InterruptedException{
        p.imprimeText("(TorreControlo) Asserting New Default for @oi");
        Query q61 = new Query("default_arrival",new Term[]{new Atom("o"),new Atom("oi"),new Atom("in_set"),new Atom(name)});
        q61.hasSolution();
        
        Query q62 = new Query("list_items",new Term[] {new Atom("cbs")});
	q62.hasSolution();
        p.imprimeText("(TorreControlo) The belief set is:");
        Stream<String> lines16 = Files.lines(caminho);
        lines16.forEach(s -> p.imprimeText(s));
										
	Query q63 = new Query("list_items",new Term[] {new Atom("active")});
        q63.hasSolution();
	p.imprimeText("(TorreControlo) The set of active processes is:");
	String linhas5 = new String(Files.readAllBytes(caminho));
        System.out.println(linhas5);
        char c = linhas5.charAt(linhas5.indexOf("action")+6);
        System.out.println(c);
        String st = "action"+c;
        System.out.println(st);
        this.setAction(st);
        System.out.println(this.getAction());
        p.imprimeText(linhas5);
										
	Query q64 = new Query("list_items",new Term[] {new Atom("suspended")});
	q64.hasSolution();
	p.imprimeText("(TorreControlo) The set of suspended processes is:");
        Stream<String> lines18 = Files.lines(caminho);
        lines18.forEach(s -> p.imprimeText(s));
        p.imprimeText("(TorreControlo) Action is");
        p.imprimeText(this.getAction());
        /*
        if(this.getAction().equals("action1")){
            List<String> so = this.getAirplanes();
            if(!so.isEmpty()){
                for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action1").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }else if(this.getAction().equals("action3")){
                 for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action3").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }
            
            
        }*/
        
    }
    
    
    public void startFactArrivalPI(String name) throws IOException, InterruptedException{
        p.imprimeText("(TorreControlo) Asserting Answer for @pi");
        Query q21 = new Query("answer_arrival",new Term[]{new Atom("p"),new Atom("pi"),new Atom("in_set"),new Atom(name)});
        q21.hasSolution();
        
        Query q22 = new Query("list_items",new Term[]{new Atom("answer")});
        q22.hasSolution();
        p.imprimeText("(TorreControlo) The answer set is");
        Stream<String> lines15 = Files.lines(caminho);
        lines15.forEach(s -> p.imprimeText(s));
        
        Query q23 = new Query("list_items",new Term[] {new Atom("cbs")});
	q23.hasSolution();
	p.imprimeText("(TorreControlo)The belief set is now:");
        Stream<String> lines14 = Files.lines(caminho);
        lines14.forEach(s -> p.imprimeText(s));
					
	Query q24 = new Query("list_items",new Term[] {new Atom("active")});
	q24.hasSolution();
	p.imprimeText("(TorreControlo)The set of active processes is:");
	String linhas4 = new String(Files.readAllBytes(caminho));
        System.out.println(linhas4);
        char c = linhas4.charAt(linhas4.indexOf("action")+6);
        System.out.println(c);
        String st = "action"+c;
        System.out.println(st);
        this.setAction(st);
        System.out.println(this.getAction());
        p.imprimeText(linhas4);
					
	Query q25 = new Query("list_items",new Term[] {new Atom("suspended")});
	q25.hasSolution();
	p.imprimeText("(TorreControlo)The set of suspended processes is:");
        Stream<String> lines12 = Files.lines(caminho);
        lines12.forEach(s -> p.imprimeText(s));
        p.imprimeText("(TorreControlo) Action is");
        p.imprimeText(this.getAction());
        /*
        if(this.getAction().equals("action1")){
            List<String> so = this.getAirplanes();
            if(!so.isEmpty()){
                for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action1").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }else if(this.getAction().equals("action3")){
                 for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action3").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }
            
            
        }*/
        
    }
    
    public void startFactArrivalOI(String name) throws IOException, InterruptedException{
        p.imprimeText("(TorreControlo) Asserting Answer for @oi");
        Query q31 = new Query("answer_arrival",new Term[]{new Atom("o"),new Atom("oi"),new Atom("in_set"),new Atom(name)});
        q31.hasSolution();
        
        Query q32 = new Query("list_items",new Term[]{new Atom("answer")});
        q32.hasSolution();
        p.imprimeText("(TorreControlo) The answer set is");
        Stream<String> lines11 = Files.lines(caminho);
        lines11.forEach(s -> p.imprimeText(s));
        
        Query q33 = new Query("list_items",new Term[] {new Atom("cbs")});
	q33.hasSolution();
	p.imprimeText("(TorreControlo)The belief set is now:");
        Stream<String> lines9 = Files.lines(caminho);
        lines9.forEach(s -> p.imprimeText(s));
					
	Query q34 = new Query("list_items",new Term[] {new Atom("active")});
	q34.hasSolution();
	p.imprimeText("(TorreControlo)The set of active processes is:");
	String linhas3 = new String(Files.readAllBytes(caminho));
        System.out.println(linhas3);
        char c = linhas3.charAt(linhas3.indexOf("action")+6);
        System.out.println(c);
        String st = "action"+c;
        System.out.println(st);
        this.setAction(st);
        System.out.println(this.getAction());
        p.imprimeText(linhas3);
					
	Query q35 = new Query("list_items",new Term[] {new Atom("suspended")});
	q35.hasSolution();
	p.imprimeText("(TorreControlo)The set of suspended processes is:");
        Stream<String> lines8 = Files.lines(caminho);
        lines8.forEach(s -> p.imprimeText(s));
        p.imprimeText("(TorreControlo) Action is");
        p.imprimeText(this.getAction());
        
        
       
        /*
        if(this.getAction().equals("action1")){
            List<String> so = this.getAirplanes();
            if(!so.isEmpty()){
                for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action1").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }else if(this.getAction().equals("action3")){
                 for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action3").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }
            
            
        }*/
        
    }
   
    public void startFactArrivalMI(String name) throws IOException, InterruptedException{
        p.imprimeText("(TorreControlo) Asserting Answer for @mi");
        Query q12 = new Query("answer_arrival",new Term[]{new Atom("t"),new Atom("mi"),new Atom("in_set"),new Atom(name)});
        q12.hasSolution();
        
        Query q13 = new Query("list_items",new Term[]{new Atom("answer")});
        q13.hasSolution();
        p.imprimeText("(TorreControlo) The answer set is");
        Stream<String> lines7 = Files.lines(caminho);
        lines7.forEach(s -> p.imprimeText(s));
        
        Query q14 = new Query("list_items",new Term[] {new Atom("cbs")});
	q14.hasSolution();
	p.imprimeText("(TorreControlo)The belief set is now:");
        Stream<String> lines6 = Files.lines(caminho);
        lines6.forEach(s -> p.imprimeText(s));
					
	Query q15 = new Query("list_items",new Term[] {new Atom("active")});
	q15.hasSolution();
	p.imprimeText("(TorreControlo)The set of active processes is:");
	String linhas2 = new String(Files.readAllBytes(caminho));
        System.out.println(linhas2);
        char c = linhas2.charAt(linhas2.indexOf("action")+6);
        System.out.println(c);
        String st = "action"+c;
        System.out.println(st);
        this.setAction(st);
        System.out.println(this.getAction());
        p.imprimeText(linhas2);
					
	Query q16 = new Query("list_items",new Term[] {new Atom("suspended")});
	q16.hasSolution();
	p.imprimeText("(TorreControlo)The set of suspended processes is:");
        Stream<String> lines4 = Files.lines(caminho);
        lines4.forEach(s -> p.imprimeText(s));
        p.imprimeText("(TorreControlo) Action is");
        p.imprimeText(this.getAction());
        /*
        if(this.getAction().equals("action1")){
            List<String> so = this.getAirplanes();
            if(!so.isEmpty()){
                for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action1").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }else if(this.getAction().equals("action3")){
                 for(int i = 0; i < so.size();i++){
                    new SendAction(this,"action3").action();
                    so.remove(i);
                    p.povoaAvioes();
                    sleep(1000);
                }
            }
            
            
        }*/
        
    }
    
    
    
    public void startProlog(){
       
        try{


            Term arg[] = {new Atom("question1")};
            
            
            Query q4 = new Query("list_items",new Term[]{new Atom("cbs")});
            q4.hasSolution();
            Stream<String> lines = Files.lines(caminho);
            lines.forEach(s -> p.imprimeText(s));
            
            
            Query q5 = new Query("query",new Compound("next",arg));
            Map<String,Term> s1 = q5.oneSolution();

            if(!s1.isEmpty()){
                p.imprimeText("(TorreControlo) Calculating the next action in the aircontrol process...");
                p.imprimeText("(TorreControlo) The alternative action is");
                p.imprimeText(s1.get("List").toString());

            }

            Query q6 = new Query("list_items",new Term[]{new Atom("active")});
            q6.hasSolution();
            String linhas = new String(Files.readAllBytes(caminho));
            System.out.println(linhas);
            char c = linhas.charAt(linhas.indexOf("action")+6);
            System.out.println(c);
            String st = "action"+c;
            System.out.println(st);
            this.setAction(st);
            System.out.println(this.getAction());
            p.imprimeText(linhas);
            
            
            Query q7 = new Query("list_items",new Term[]{new Atom("suspended")});
            q7.hasSolution();
            Stream<String> lines3 = Files.lines(caminho);
            lines3.forEach(s -> p.imprimeText(s));
            
            p.imprimeText("(TorreControlo) Action is");
            p.imprimeText(this.getAction());
            
            /*if((this.getAction().equals("action1"))||(this.getAction().equals("action3"))){
                List<String> rem = this.getAirplanes();
                for(String s: rem){
                    rem.remove(s);
                }
                p.povoaAvioes();
            }*/

        }catch(SecurityException e){
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(TorreControlo.class.getName()).log(Level.SEVERE, null, ex);
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
    /*
    public void deleteAgent(String nome) throws ControllerException{
            ContainerController cc = getContainerController();
            AgentController ac = cc.getAgent(nome);
            ac.kill();
        }
    
    */
    private void createNewAgent(String nome) throws StaleProxyException{
        
        if(!airplanes.contains(nome)){
            airplanes.add(nome);
            p.povoaAvioes();
            ContainerController cc = getContainerController();
            AgentController ac = cc.createNewAgent(nome, Airplane.class.getName(), null);
            ac.start();
            p.avisaPositivo(nome);
            System.out.println(airplanes.size());
            airplanes.
        }
       
        else{
            p.avisa();
        }
        
         
    }
    
    public void removeAviao (Boolean a,String nome){
        if (a==true)
        {
         this.airplanes.remove(nome);
          
        }
        
    }
    
    

    
    
}
