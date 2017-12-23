package demo;

/**
 * Created by mich0916 on 1/31/2017.
 */
public class MathApplicationStatic {

    MathApplicationStatic()
    {
        System.out.println("in constructor...");
    }
 public static int add(int a, int b)
 {
  return 0;
 }
    public static int sub(int a,int b)
    {
       if (a>b) return a-b; else return 0;
    }

    public static int multiply(int a, int b)
    {
        return a*b;
    }
    public static int divide(int a, int b)
    {
        if (b> 0) return a/b; else return  0;
    }
}
