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
 * 
 * edited 17.9.2019
 * further enhanced, implemented push() to understand ItemOfDynamicArray classes
 * better
 */
public class DynamicArray 
{

    /**
     * N = The current size of the array. 
     * ItemOfDynamicArray = The array for holding the objects of the stack.
     */
    private int N; 
    private ItemOfDynamicArray[] A;
    
    /**
     * n: the number of items in the stack.
     * top = n-1.
     */
    private int n;
    
    /**
     * Constructor: initializes N to 1, n to 0 and allocates a size-N 
     * array A. 
     */
    public DynamicArray()
    {
        N = 1;
        n = 0;
        A = new ItemOfDynamicArray[N];
    }


    /** 
     * Returns true if stack is empty, and false otherwise. 
     * @return boolean
     */
    public boolean isEmpty()
    {
        return n == 0;
    }



    /**
     * Returns the current size of the array A. 
     * @return int
     */
    public int arraySize()
    {
        return N;
    }

    /**
     * push(object x) : Inserts Object x on top of the stack (= to index n).
     * If n = N, moves the stack into a new array that is twice
     * as large (e.g. double N, creates a new size N array, moves
     * the stack items from old A and replaces A by the new array).
     * @param x 
     */
    public void push( Object x )
    {
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

    /**
     * slide() : returns ItemOfDynamicArray from the bottom of the stack ie. slides one out
     * @return ItemOfDynamicArray
     */
    public ItemOfDynamicArray slide()
    {
        ItemOfDynamicArray o = null;

        if(!isEmpty())
        {
            o = A[0];
            
            for(int i = 0; i < n; i++)
            {
                A[i] = A[i+1];
            }
            
            n--;
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

    /**
     * pop(): Removes ItemOfDynamicArray x from top of the stack (= from index n-1) and returns it.
     * Returns null is the stack is empty.
     * If after the removal n = N/4 and N >= 2, moves the stack into a new array
     * that is half as large (e.g. divides N by two, creates a new size N array, moves
     * the stack items from old A, and replaces A by the new array).
     * 
     * @return ItemOfDynamicArray
     */
    public ItemOfDynamicArray pop ()
    {
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
    
    /**
     * peek(int) : provides functionality for checking what item is store on a 
     * particular index of this DynamicArray
     * 
     * @param n
     * @return ItemOfDynamicArray
     */
    public ItemOfDynamicArray peek(int n)
    {
    
        if(this.n>=n && n >= 0)
        {
            return A[n];
        }
        
        else return null;
    }
}
