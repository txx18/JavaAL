package book.code;

import book.code.weiss.util.ArrayList;
import book.code.weiss.util.ListIterator;

class TestArrayList
{
    public static void main( String [ ] args )
    {
        ArrayList<Integer> lst = new ArrayList<Integer>( );
        lst.add( 2 );
        lst.add( 4 );
        
        ListIterator<Integer> itr1 = lst.listIterator( 0 );
        System.out.print( "Forward: " );
        while( itr1.hasNext( ) )
            System.out.print( itr1.next( ) + " " );
        System.out.println( );    
            
        System.out.print( "Backward: " );
        while( itr1.hasPrevious( ) )
            System.out.print( itr1.previous( ) + " " );
        System.out.println( );    
        
        System.out.print( "Backward: " );
        ListIterator<Integer> itr2 = lst.listIterator( lst.size( ) );
        while( itr2.hasPrevious( ) )
            System.out.print( itr2.previous( ) + " " );    
        System.out.println( );    
      
	  	System.out.print( "Forward: ");
		for( Integer x : lst )
			System.out.print( x + " " );
		System.out.println( );	
			
        ArrayList<Integer> lst2 = new ArrayList<Integer>( lst );  
        while( itr1.hasNext( ) )
        {
            int x = itr1.next( );
            itr1.remove( );
            System.out.println( x );
        }
        
        ListIterator<Integer> itr3 = lst2.listIterator( lst2.size( ) );
        while( itr3.hasPrevious( ) )
        {
            int x = itr3.previous( );
            itr3.remove( );
            System.out.println( x );
        }
    }    
}