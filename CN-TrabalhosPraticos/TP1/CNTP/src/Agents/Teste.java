/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author meguim
 */
public class Teste {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("/home/meguim/Transferências/MyListing.txt");
            
            Stream<String> lines3 = Files.lines(path);
            String linhas = new String(Files.readAllBytes(path));
            System.out.println(linhas);
            char c = linhas.charAt(linhas.indexOf("action") + 6);
            
            System.out.println("Action" + c);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
