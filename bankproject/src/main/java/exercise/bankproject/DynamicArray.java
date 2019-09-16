/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.bankproject;

/**
 *
 * @author henrijuvonen
 * modified from a uni exercise
 * 
 * edited on 1.9.2019
 * done corrections and formatting, added data for understanding order of items
 * which should be useful for peeking out what's inside the array
 * 
 * edited on 16.9.2019
 * edited the comments, formatted the code. this works perfectly and looks 
 * perfect now, not so much like an exercise.
 */
public class DynamicArray {

    /* 
     * N = The current size of the array. 
     * ItemOfDynamicArray = The array for holding the objects of the stack.
     */
    private int N; 
    private ItemOfDynamicArray[] A;
    
    /* n: the number of items in the stack.
     * top = n-1.
     */
    private int n;
    
    /* Constructor: initializes N to 1, n to 0 and allocates a size-N 
     * array A. 
     */
    public DynamicArray()
    {
        N = 1;
        n = 0;
        A = new ItemOfDynamicArray[N];
    }


    /* Returns true if stack is empty, and false otherwise. */
    public boolean isEmpty()
    {
        return n == 0;
    }



    /* Returns the current size of the array A. */
    public int arraySize()
    {
        return N;
    }





    /**************
     * @param x 
     **************/



    

    public void push( Object x )
    {

    /* push(object x) : Inserts Object x on top of the stack (= to index n).
     * If n = N, moves the stack into a new array that is twice
     * as large (e.g. double N, creates a new size N array, moves
     * the stack items from old A and replaces A by the new array).
     */
        ItemOfDynamicArray item = new ItemOfDynamicArray(x, n);
        
        if(n == N)
        {
            N = 2*N;
            Object[] h = new Object[N];
            System.arraycopy(A, 0, h, 0, n);
            A = new ItemOfDynamicArray[N];
            System.arraycopy(h, 0, A, 0, n);
        }
        
        A[n] = item;
        n = n+1;
    }

    /* slide() : returns object from the bottom of the stack, slides one out
     * 
     */
    public Object slide()
    {
        Object o = null;

        if(!isEmpty()){
            o = A[0];
            
            for(int i = 0; i < n; i++){
                A[i] = A[i+1];
            }
            
            n--;
        }

        if(n == N/4 && N >= 2){
            N = N/2;
            Object[] h = new Object[n];
            System.arraycopy(A, 0, h, 0, n);
            A = new ItemOfDynamicArray[N];
            System.arraycopy(h, 0, A, 0, n);
        }
        
        return o;
    }

    public ItemOfDynamicArray pop ()
    {

    /* pop(): Removes Object x from top of the stack (= from index n-1) and returns it.
     * Returns null is the stack is empty.
     * If after the removal n = N/4 and N >= 2, moves the stack into a new array
     * that is half as large (e.g. divides N by two, creates a new size N array, moves
     * the stack items from old A, and replaces A by the new array).
     */

        ItemOfDynamicArray o = null;

        if(!isEmpty())
        {
            o = A[n-1];
            A[n-1] = null;
            n = n-1;
        }

        if(n == N/4 && N >= 2)
        {
            N = N/2;
            Object[] h = new Object[n];
            System.arraycopy(A, 0, h, 0, n);
            A = new ItemOfDynamicArray[N];
            System.arraycopy(h, 0, A, 0, n);
        }

        return o;
    }
    
    public ItemOfDynamicArray peek(int n)
    {
    
        if(this.n>=n && n >= 0){
            return A[n];
        }
        
        else return null;
    }
}
