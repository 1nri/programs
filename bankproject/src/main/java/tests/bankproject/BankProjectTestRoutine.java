/*
 * Any license applies.
 */

package tests.bankproject;

/**
 *
 * @author henrijuvonen
 * 
 * created on 21.12.2019
 * this class is intended to test all functionality in one go just because
 * 
 */
public class BankProjectTestRoutine {
    public static void main(String args[]) {
        
        System.out.println("testing begins");
        System.out.println("testing dynamic arrays");
        DynArrStackTest.main(null);
        System.out.println("testing xml parsing");
        ParserTest.main(null);
        System.out.println("testing single transactions");
        SingleTransactionTest.main(null);
        System.out.println("testing bank statements");
        BankStatementTest.main(null);
    }
}
