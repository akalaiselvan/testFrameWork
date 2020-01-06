package Core;

public class UpDownCast {
    void cast(){
        System.out.println("Cast happens from parent");
    }
    public String toString(){
        System.out.println("updown class");
        return "upDown class";
    }
}
class Trans extends UpDownCast{
    void cast(){
        System.out.println("Cast happens from trans");
    }
}
class Sales extends Trans{
    void cast(){
        System.out.println("Cast happens from sales");
    }
    void addItems(){
        System.out.println("Add items");
    }
}

class Pg{
    public static void main(String[] args) {
        UpDownCast trans1=new Sales();
        trans1.toString();
        trans1.cast(); // upcasting


        Trans trans=new Sales();
        if (trans instanceof Sales){
            Sales sales=(Sales) trans;
            sales.cast();
            sales.addItems();
        }  //downcasting


    }
}
