/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author CRAVELO
 */

@ManagedBean
public class SearchView {
    

private String text1;   
    private String text2;
 
    public String getText1() {
        return text1;
    }
 
    public void setText1(String text1) {
        this.text1 = text1;
    }
 
    public String getText2() {
        return text2;
    }
 
    public void setText2(String text2) {
        this.text2 = text2;
    }





}
