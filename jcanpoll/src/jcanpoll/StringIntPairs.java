/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcanpoll;

import java.util.Comparator;

/**
 *
 * @author deh
 */

public class StringIntPairs implements Comparable<Canmsginfo>, Comparator<Canmsginfo>{
    public String s;
    public int n;
    public String description;

    public StringIntPairs(){
    }
    
    public StringIntPairs(String ss){
        this.s = ss;
    }

    public StringIntPairs (String ss, int nn, String dd){
        this.s = ss;
        this.n = nn;
        this.description = dd;
    }

    @Override
    public int compareTo(Canmsginfo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(Canmsginfo o1, Canmsginfo o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
