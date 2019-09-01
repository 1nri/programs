/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.bankproject;

/**
 *
 * @author henrijuvonen
 * 
 */
public class ItemOfDynamicArray extends Object{
    private final Object item;
    private final int location;
    
    public ItemOfDynamicArray(Object o, int loc){
        item = o;
        location = loc;
    }
    
    @Override
    public String toString(){
        return "This is " + item + " from location :" + this.location 
                + System.lineSeparator();
    }
}
