输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能
为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某
栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2
就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
      Stack<Integer> stack=new Stack();
        int j=0;
        for(int i=0;i<pushA.length;i++){
            stack.push(pushA[i]);
            while(!stack.empty()&&j<popA.length&&stack.peek()==popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.empty();
    }
}


根据逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack();
        for(int i=0;i<tokens.length;i++){
            String s=tokens[i];
            if(s.equals("+")){
                int a=stack.pop();
                if(!stack.empty()){
                    int b=stack.pop();
                    stack.push(b+a);
                }else{
                    int b=func(tokens[++i]);
                    stack.push(a+b);
                }
                
            }else if(s.equals("-")){
                int a=stack.pop();
                if(!stack.empty()){
                    int b=stack.pop();
                    stack.push(b-a);
                }else{
                    int b=func(tokens[++i]);
                    stack.push(a-b);
                }
            }else if(s.equals("*")){
                int a=stack.pop();
                if(!stack.empty()){
                    int b=stack.pop();
                    stack.push(b*a);
                }else{
                    int b=func(tokens[++i]);
                    stack.push(a*b);
                }
            }else if(s.equals("/")){
                int a=stack.pop();
                if(!stack.empty()){
                    int b=stack.pop();
                    stack.push(b/a);
                }else{
                    int b=func(tokens[++i]);
                    stack.push(a/b);
                }
            }else{
                stack.push(func(s));
            }
        }
        return stack.peek();
    }

    private int func(String s){
        int sum=0;
        if(s.charAt(0)=='-'){
            for(int i=1;i<s.length();i++){
                char c=s.charAt(i);
                sum=sum*10+c-'0';
            }
            return -sum;
        }
        for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                sum=sum*10+c-'0';
        }
        return sum;
    }
}


编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。

今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。

例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/online-stock-span
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class StockSpanner {
    class Node{
        private int data;
        private int count;
        public Node(int data,int count){
            this.data=data;
            this.count=count;
        }
    }
    private Stack<Node> stack;
    public StockSpanner() {
        stack=new Stack();
    }
    
    public int next(int price) {
        Node node =new Node(price,0);
        if(stack.empty()){
            node.count=1;
            stack.push(node);
            return 1;
        }
        int ret=1;
        while(!stack.empty()&&stack.peek().data<=price){
            ret+=stack.peek().count;
            stack.pop();
        }
        node.count=ret;
        stack.push(node);
        return stack.peek().count;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */