package exercise;

import java.util.Scanner;
import java.util.Stack;

public class Main{
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("nhap vao mySimp");
        String s = sc.nextLine();
        Solve(s);
    }
    public static int check(char c)
    {
        if(c == '(' || c == ')') return 0;
        if(c == '*' || c == '/') return 2;
        if(c == '+' || c == '-') return 1;
        return 3;
    }
    public static String conver(String s)
    {
        Stack<Character> stk = new Stack<>();
        String res = "";
        for(int i = 0; i <= s.length()-1; i++)
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
            {
                if(i < s.length() - 1) {
                    if (!(s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')) res = res + s.charAt(i) + " ";
                    else res = res + s.charAt(i);
                }
                else {
                    res = res + s.charAt(i) + " ";
                }
            }
            else if(s.charAt(i) == '(') stk.push(s.charAt(i));
            else if(s.charAt(i) == ')')
            {
                while(stk.peek() != '(' && stk.size() > 0)
                {
                    res = res + stk.peek() + " ";
                    stk.pop();
                }
                stk.pop();
            }
            else if(s.charAt(i) == '+' || s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '-')
            {
                while(stk.size() > 0 && (check(stk.peek()) >= check(s.charAt(i))))
                {
                    res = res + stk.peek() + " ";
                    stk.pop();
                }
                stk.push(s.charAt(i));
            }
        while(stk.size() > 0)
        {
            if(stk.peek() != '(') res = res + stk.peek();
            stk.pop();
        }
        return res;
    }
    public static void Solve(String s)
    {
        s = conver(s);
        ///System.out.println(conver(s));
        int x = 0;
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i <= s.length()-1; i++)
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
            {
                x = x * 10 + s.charAt(i) - '0';
            }
            else if(s.charAt(i) == ' ' && s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9')
            {
                st.push(x);
                x = 0;
            }
            else if(s.charAt(i) != ' ' && !(s.charAt(i) >= '0' && s.charAt(i) <= '9') && st.size() > 1)
            {
                    int b = st.peek();
                    st.pop();
                    int a = st.peek();
                    st.pop();
                    int tmp = 0;
                    if (s.charAt(i) == '+') tmp = a + b;
                    if (s.charAt(i) == '-') tmp = a - b;
                    if (s.charAt(i) == '*') tmp = a * b;
                    if (s.charAt(i) == '/') tmp = a / b;
                    st.push(tmp);
            }
        if(st.size() == 0) System.out.println(x);
        else {
            System.out.println(st.peek());
        }
    }
}