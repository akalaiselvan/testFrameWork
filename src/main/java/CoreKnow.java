import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CoreKnow {

    @Test
    public void set(){
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(1,2,5,4,2,3,5,2,8));
        Set<Integer>seta=new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,9,5,2,3,6));
        System.out.println(set);
        System.out.println(seta);
        Set<Integer>test=new HashSet<Integer>(set);
        test.addAll(seta);
        System.out.println("Union : "+test);
        Set<Integer>test1=new HashSet<Integer>(set);
        test1.retainAll(seta);
        System.out.println("Intersection : "+test1);
        Set<Integer>test2=new HashSet<Integer>(set);
        test2.removeAll(seta);
        System.out.println("Difference : "+test2);
    }

    @Test
    public void moveNum(){
        int[] num={1,2,3,4,5,6,7,8};
        int n=3;
        int m=0;
        int[] res=new int[num.length];
        int rightTurn=(num.length-1)-n;
        for (int i=0;i<=num.length-1;i++){
            if (rightTurn+i<num.length-1) {
                res[i] = num[rightTurn+i];
            }else {
                res[i]=num[m];
                m++;
            }
        }
        System.out.println(Arrays.toString(res));
    }


}
