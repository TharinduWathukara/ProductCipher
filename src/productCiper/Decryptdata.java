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
public class Decryptdata {
    
    protected ArrayList<String> encrypted = new ArrayList<String>();
    protected ArrayList<Integer> subs = new ArrayList<Integer>();
    protected ArrayList<Integer> permu = new ArrayList<Integer>();
    protected ArrayList<String> decryptpermutation = new ArrayList<String>();
    protected ArrayList<String> decrypted = new ArrayList<String>();
    
    
    public Decryptdata(ArrayList<String> encrypted, ArrayList<Integer> subs, ArrayList<Integer> permu){
        this.encrypted=encrypted;
        this.subs=subs;
        this.permu=permu;
        
        
        this.decryptpermutation=removepermutation(encrypted,permu);
        this.decrypted = removesubstitution(decryptpermutation,subs);
        
    }
    
    
/////////////////////////////////remove the substitution//////////////////////////////////////////////////////
    public ArrayList<String> removesubstitution(ArrayList<String> decryptpermutation, ArrayList<Integer> subs){
        ArrayList<String> decrypted = new ArrayList<String>();
//        System.out.println(subs);
        for (String line:decryptpermutation){
            String newLine="";
            for (int j=0; j<line.length();j++){
                int ascii = (int) line.charAt(j);
//                System.out.println(ascii);
                int newascii= getnewascii(j,subs,ascii);
//                System.out.println(newascii);
                char newchar = (char)newascii;
                newLine+=newchar;
            }
            decrypted.add(newLine);
        }
        System.out.println("After remove substitution.....");
        System.out.println(decrypted);
        System.out.println();
        return decrypted;
    }
    
    public int getnewascii(int i,ArrayList<Integer> subs, int ascii){
        int newascii;
        if(i%4==0){
            newascii=ascii-subs.get(0);
            int newasc=validateAscii(newascii);
            return newasc;
        }
        if(i%4==1){
            newascii=ascii-subs.get(1);
            int newasc=validateAscii(newascii);
            return newasc;
        }
        if(i%4==2){
            newascii=ascii-subs.get(2);
            int newasc=validateAscii(newascii);
            return newasc;
        }
        if(i%4==3){
            newascii=ascii-subs.get(3);
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
            int sm=ascii+126;
            ascii = sm-32;
            return validateAscii(ascii);
        }
    }
    
    
//////////////////////////////////////remove permutation//////////////////////////////////////
    public ArrayList<String> removepermutation(ArrayList<String> encrypted, ArrayList<Integer> permu){
        ArrayList<String> decryptpermutation = new ArrayList<String>();
        for (String line:encrypted){
            String newLine="";
            String[] split = line.split("(?<=\\G.{4})");
            for(String s : split){
                newLine+=changeChar(s,permu);
            }
            decryptpermutation.add(newLine);
        }
        System.out.println("After remove permutations.....");
        System.out.println(decryptpermutation);
        System.out.println();
        return decryptpermutation;
    }
    
    public String changeChar(String s,ArrayList<Integer> permu){
        String changes="";
        if(s.length()==4){
            List<String> ss = Arrays.asList(s.split(""));
            ArrayList<String> nss= new ArrayList <String>(ss);
            for(int i= 0; i<permu.size();i++){
                nss.set(permu.get(i)-1, ss.get(i));
            }
            for(String sr : nss){
                changes+=sr;
            }
        }else{
            changes=s;
        }
        return changes;
    }
    
    public ArrayList<String> getDecryptedData() {
        return decrypted;
    }
    
}
