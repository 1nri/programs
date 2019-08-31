package tests.bankproject;

import exercise.bankproject.DynamicArray;

/* A routine for testing your dynamic array -based stack implementation.
 * Put into the same directory as "DynArrStack.java" that contains your
 * implementations, and execute first 'javac DynArrStackTest.java' and
 * then 'java DynArrStackTest'. Compare the ouput values with the
 * "should be:" -values printed within parenthesis.
 */

public class DynArrStackTest
{
        public static void main( String[] args )
        {
                DynamicArray S = new DynamicArray();
                System.out.println( "Initially the array has size " + S.arraySize() );
                System.out.println( "Let's push the following items on the stack" );
                for( int i = 0; i < 10; ++i )
                {
                        S.push( new Integer (i) );
                        System.out.print( " " + i );
                }
                System.out.println();
                System.out.println( "After inserting 10 items, the array has size " +
                                                                                                S.arraySize()   + " (should be: 16)" );
                System.out.println( "Let's slide 5 items from the stack" );
                System.out.print( "items:" );
                for( int i = 0; i < 5; ++i )
                {
                        System.out.print( " " + S.slide() );
                }
                System.out.println();
                System.out.println( "Now the array has size " + S.arraySize() + " (should be: 16)" );
                System.out.println( "Let's slide 1 more item from the stack" );
                System.out.println( "item: " + S.slide() );
                System.out.println( "Now the array has size " + S.arraySize() + " (should be: 8)" );
                System.out.println( "Let's pop the remaining items, one at a time" );
                System.out.println( "item: " + S.pop() );
                System.out.println( "Now the array has size " + S.arraySize() + " (should be: 8)" );
                System.out.println( "item: " + S.pop() );
                System.out.println( "Now the array has size " + S.arraySize() + " (should be: 4)" );
                System.out.println( "item: " + S.pop() );
                System.out.println( "Now the array has size " + S.arraySize() + " (should be: 2)" );
                System.out.println( "item: " + S.pop() );
                System.out.println( "Now the array has size " + S.arraySize() + " (should be: 1)" );
                System.out.println( "Answer given by isEmpty(): " + S.isEmpty() + " (should be: true)" );
                System.out.println( "Attempting pop returns: " + S.pop() + " (should be: null)" );
                System.out.println( "Final test: push 1000 items on the stack, not printed on screen" );
                for( int i = 0; i < 1000; ++i )
                {
                        S.push( new Integer (i) );
                }
                System.out.println( "After pushing 1000 items, the array has size " + S.arraySize() + " (should be: 1024)" );
        }
}