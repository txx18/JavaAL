package book.code;

import book.code.weiss.util.Collection;
import book.code.weiss.util.Set;
import book.code.weiss.util.TreeSet;
import book.code.weiss.util.Collections;

public class TreeSetDemo
{
    public static void printCollection( Collection<String> c )
    {
        for( String str : c )
            System.out.println( str );
    }
    
    public static void main( String [ ] args )
    {
        Set<String> s = new TreeSet<String>( Collections.reverseOrder( ) );
        s.add( "joe" );
        s.add( "bob" );
        s.add( "hal" );
        printCollection( s );    // Figure 6.8
    }
}