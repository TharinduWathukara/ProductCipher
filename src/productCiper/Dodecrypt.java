/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productCiper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
/**
 *
 * @author tharindu
 */
public class Dodecrypt {
    protected String filepath;
    protected String key;

    /**
     * @param args the command line arguments
     */
    public Dodecrypt(String path,String key) throws FileNotFoundException,IOException{
        // TODO code application logic here
        
        this.filepath = path;
        this.key = key;
        
        //create arryLists for substitution and permutation sequences
        ArrayList<Integer> subs = new ArrayList<Integer>();
        ArrayList<Integer> permu = new ArrayList<Integer>();
        
        //create arraLists for store read data
        ArrayList<String> readData = new ArrayList<String>();
        
        //create substitution sequence
        subs = createSubstitutionArray(key);
        
        //create permutation sequence
        permu = createPermutationArray(key, subs);

//        read the string from text
        Read read = new Read(filepath);
        readData = read.getData();
        System.out.println(readData);
        System.out.println();
        System.out.println();
        
//        create decryption
        Decryptdata decryptdata = new Decryptdata(readData,subs,permu);
        ArrayList<String> decrypted = decryptdata.getDecryptedData();

//       write data in text file
        Write write = new Write(filepath,decrypted);

    }
    
    public ArrayList<Integer> createSubstitutionArray(String key){
        ArrayList<Integer> subs = new ArrayList<Integer>();
        
        for (int i=0; i<key.length();i++){
            int ascii = (int) key.charAt(i);
            subs.add(ascii);
        }
        return subs;
    }
    
    public ArrayList<Integer> createPermutationArray(String key, ArrayList<Integer> subs){
        ArrayList<Integer> permu = new ArrayList<Integer>();
        ArrayList<Integer> subs2 = new ArrayList<Integer>(subs);
        subs2.sort(null);
        for(int i : subs){
            permu.add(subs2.indexOf(i)+1);
        }
        return permu;
    }
    
    
    
}
