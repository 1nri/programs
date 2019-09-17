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
 * edited 16.9.2019
 * reformatting
 * 
 * edited 17.9.2019
 * added new method toString() with boolean parameter in order to provide testing
 * improvements
 */
public class ItemOfDynamicArray extends Object
{
    private final Object item;
    private final int location;
    
    public ItemOfDynamicArray(Object o, int loc)
    {
        item = o;
        location = loc;
    }
    
    
    @Override
    public String toString()
    {
        return item + "";
    }
    
    public String toString(boolean t)
    {
        return "This is " + item + " from location :" + this.location 
                + System.lineSeparator();
    }
}
