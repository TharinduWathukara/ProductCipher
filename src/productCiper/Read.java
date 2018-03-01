
package productCiper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tharindu
 */
public class Read{
    
    private String path = "";
    private String line = "";
    private ArrayList<String> data = new ArrayList<String>();

    public Read(String path) throws FileNotFoundException, IOException{
        this.path=path;
        FileReader fileReader = new FileReader(path);
        
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            data.add(line);
        }   
        bufferedReader.close();
    }
    
    public ArrayList<String> getData(){
        return data;
    }
    
}
