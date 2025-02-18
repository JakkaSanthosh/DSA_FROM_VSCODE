import java.util.*;
class masg{
    public static void main(String[] args) {
      Scanner s=new Scanner(System.in);
      int t=s.nextInt();
      while(t-->0){
          int n=s.nextInt();
          String s1=s.next();
          int zc=0;
          int oc=0;
          for(int i=0;i<n;i++){
              char c=s1.charAt(i);
              if(c=='0') zc++;
              else oc++;
          }
          if(zc==n) System.out.println("0");
          if(oc==n) System.out.println("1");
          int k=0;
          for(int i=0;i<n-1;i++){
              char x=s1.charAt(i);
              char y=s1.charAt(i+1);
              if(x=='1'&&y=='0'){
                  k+=2;
              }
              
          }
          System.out.println(k);
      }
    }
}