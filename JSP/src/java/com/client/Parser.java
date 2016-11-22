/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author afp
 */
public class Parser {
    static public List<Map<String,String>> catalogParser(List<String> listString) {
        List<Map<String,String>> listCatalog = new ArrayList<>();
        int length = listString.size()/20;
        for(int i = 0; i < length; i++) {
            Map<String,String> map = new HashMap<>();
            
            for(int j = 0; j < 10; j++){
                System.out.println((2*j)+(10*i));
                System.out.println((10*i)+1+(j*2));
                map.put(listString.get((2*j)+(20*i)),listString.get((20*i)+1+(j*2)));
            }
            
            
            listCatalog.add(map);
        }
        return listCatalog;
    }
    
    static public List<Map<String,String>> salesParser(List<String> listString) {
        List<Map<String,String>> listCatalog = new ArrayList<>();
        int length = listString.size()/22;
        for(int i = 0; i < length; i++) {
            Map<String,String> map = new HashMap<>();
            
            for(int j = 0; j < 11; j++){
                map.put(listString.get((2*j)+(22*i)),listString.get((22*i)+1+(j*2)));
            }
            
            
            listCatalog.add(map);
        }
        return listCatalog;
    }
}
