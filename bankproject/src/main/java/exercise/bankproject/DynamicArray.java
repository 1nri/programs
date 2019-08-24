/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.bankproject;

/**
 *
 * @author henrijuvonen
 */
public class DynamicArray {
    private int N; /* The current size of the array. */

        private Object[] A; /* The array for holding the objects of the stack. */



        /* n: the number of items in the stack.
         * We do not keep a separate variable 'top' for the index of the last stack
         * item because top = n-1.
         */

        private int n;



        /* Constructor: initializes N to 1, n to 0 and allocates a size-N array A. */
        public DynamicArray()
        {
                N = 1;

                n = 0;
                A = new Object[N];
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





        /************** EXERCISES ARE BELOW **************/



        /* Insert Object x on top of the stack (= to index n).
         * If n = N, move the stack into a new array that is twice
         * as large (e.g. double N, create a new size N array, move
         * the stack items from old A, and replace A by the new array).
         */

        public void push( Object x )
        {
        /* push(object x): lisää objektin x pinon viimeisen alkion perään. Jos taulukossa oli
         * jo n = N alkiota, siirtää pinon uuteen, kaksi kertaa isompaan taulukkoon
         * (samalla N ← 2N).
         */

                if(n == N)
                {
                        N = 2*N;
                        Object[] h = new Object[N];
                    System.arraycopy(A, 0, h, 0, n);

                        A = new Object[N];
                    System.arraycopy(h, 0, A, 0, n);

                }
                A[n] = x;
                n = n+1;
        }

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

                        A = new Object[N];
                    System.arraycopy(h, 0, A, 0, n);
                }
            return o;
        }

        /* Remove Object x from top of the stack (= from index n-1) and return it.
         * Returns null is the stack is empty.
         * If after the removal n = N/4 and N >= 2, move the stack into a new array
         * that is half as large (e.g. divide N by two, create a new size N array, move
         * the stack items from old A, and replace A by the new array).
         */
        public Object pop ()
        {

        /* pop(): poistaa ja palauttaa pinon viimeisen alkion. Jos taulukossa on poiston
         * jälkeen = ⌊N/4⌋ alkiota ja N ≥ 2, siirtää pinon uuteen kaksi kertaa pienempään
         * taulukkoon (samalla N ← N/2).
         */

                Object o = null;

                if(!isEmpty())
                {
                        o = A[n-1];
                        A[n-1] = null;
                        n = n-1;
                }

                if(n == N/4 && N >= 2){
                        N = N/2;
                        Object[] h = new Object[n];

                    System.arraycopy(A, 0, h, 0, n);

                        A = new Object[N];
                    System.arraycopy(h, 0, A, 0, n);
                }

                return o;
        }
}
