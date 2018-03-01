/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productCiper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tharindu
 */
public class Encryptdata {
    
    protected ArrayList<String> readData = new ArrayList<String>();
    protected ArrayList<Integer> subs = new ArrayList<Integer>();
    protected ArrayList<Integer> permu = new ArrayList<Integer>();
    protected ArrayList<String> substituted = new ArrayList<String>();
    protected ArrayList<String> permutated = new ArrayList<String>();
    
    
    
    public Encryptdata(ArrayList<String> readData, ArrayList<Integer> subs, ArrayList<Integer> permu){
        this.readData=readData;
        this.subs=subs;
        this.permu=permu;
        
        this.substituted = createsubstitution(readData,subs);
        this.permutated = createpermutation(substituted,permu);
        
        
    }
    
//////////////////////////////////////substitution////////////////////////////////////////////////////////
    
    public ArrayList<String> createsubstitution(ArrayList<String> readData,ArrayList<Integer> subs){
        ArrayList<String> substituted = new ArrayList<String>();
        for (String line:readData){
            String newLine="";
            for (int j=0; j<line.length();j++){
                int ascii = (int) line.charAt(j);
                int newascii= getnewascii(j,subs,ascii);
                char newchar = (char)newascii;
                newLine+=newchar;
            }
            substituted.add(newLine);
        }
        System.out.println("After substitution......");
        System.out.println(substituted);
        System.out.println();
        return substituted;
    }
//    new ascii
    public int getnewascii(int i,ArrayList<Integer> subs, int ascii){
        int newascii;
        if(i%4==0){
            newascii=ascii+subs.get(0);
            int newasc=validateAscii(newascii);
            return newasc;
        }
        if(i%4==1){
            newascii=ascii+subs.get(1);
            int newasc=validateAscii(newascii);
            return newasc;
        }
        if(i%4==2){
            newascii=ascii+subs.get(2);
            int newasc=validateAscii(newascii);
            return newasc;
        }
        if(i%4==3){
            newascii=ascii+subs.get(3);
            int newasc=validateAscii(newascii);
            return newasc;
        }
        return 0;
    }
//    validate the ascii keys
    public int validateAscii(int ascii){
        if(ascii<=126 && 32<=ascii){
            return ascii;
        }else{
            int dif=ascii-126;
            ascii = 32+dif;
            return validateAscii(ascii);
        }
    }
    
////////////////////////////////////////////permutation/////////////////////////////////////////////////
    public ArrayList<String> createpermutation(ArrayList<String> substituted,ArrayList<Integer> permu){
        ArrayList<String> permutated = new ArrayList<String>();
        for (String line:substituted){
            String newLine="";
            String[] split = line.split("(?<=\\G.{4})");
            for(String s : split){
                newLine+=changeChar(s,permu);
            }
            permutated.add(newLine);
        }
        System.out.println("After permutated......");
        System.out.println(permutated);
        System.out.println();
        return permutated;
    }
    
    
    public String changeChar(String s,ArrayList<Integer> permu){
        String changes="";
        if(s.length()==4){
            List<String> ss = Arrays.asList(s.split(""));
            for(int i= 0; i<permu.size();i++){
                changes+=ss.get(permu.get(i)-1);
            }
            return changes;
        }else{
            changes=s;
            return changes;
        }
    }
    
//    get method for return encrypted data
    public ArrayList<String> getEncryptedData() {
        return permutated;
    }
    
}
