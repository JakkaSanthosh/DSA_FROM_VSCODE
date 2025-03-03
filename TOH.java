import java.util.*;

public class TOH{

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Main. */
    Scanner s=new Scanner(System.in);
    int t=s.nextInt();
        char src='A';
        char aux='B';
        char des='C';
    while(t-->0){
        int n=s.nextInt();
        System.out.println(((1<<n)-1));
        toh(n,src,des,aux);
    }
    }
   static void toh(int n,char src,char des,char aux){
       if(n==0) return;
        toh(n-1,src,aux,des);
        System.out.println("Move "+n+" from "+src+" to "+des);
        toh(n-1,aux,des,src);
    }
}