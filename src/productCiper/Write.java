/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productCiper;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tharindu
 */
public class Write {
    
    private String path;
    private String data;
    
    public Write(String path,ArrayList<String> data) throws IOException{
        this.path=path;
        
        FileWriter fileWriter = new FileWriter(path);
        
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(String s:data){
            bufferedWriter.write(s);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}