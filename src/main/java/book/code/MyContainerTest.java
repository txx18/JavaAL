package book.code;

import book.code.weiss.ds.MyContainer;
import book.code.weiss.ds.Iterator;


// No generics
class MyContainerTest
{
    public static void main( String [ ] args )
    {
        MyContainer v = new MyContainer( );
        v.add( "hello" );
        v.add( "world" );
        
        Iterator itr = v.iterator( );
        while( itr.hasNext( ) )
            System.out.println( itr.next( ) );
    }
}
