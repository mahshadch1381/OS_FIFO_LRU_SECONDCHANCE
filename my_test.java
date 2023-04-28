public class my_test {
    public int [] a ={10,20,30,20,10,13,30,15,20,50};
    public int na=3;
    public int [] b ={10,20,30,15,40,30,40,15,60,90,12,15,23};
    public int nb=4;
    public int [] c ={10,20,20,15,30,40};
    public int nc=2;
   public void test1(){
       client cl1=new client();
       for (int i = 0; i < a.length; i++) {
          cl1.fifo(new costumer(a[i]),na);
           cl1.lru(new costumer(a[i]),na);
           cl1.Second_chance(new costumer(a[i]),na);
           System.out.println("-------------------------------------------------------");
       }

   }
    public void test2(){
        client cl2=new client();
        for (int i = 0; i < b.length; i++) {
            cl2.fifo(new costumer(b[i]),nb);
           cl2.lru(new costumer(b[i]),nb);
            cl2.Second_chance(new costumer(b[i]),nb);
            System.out.println("-------------------------------------------------------");
        }
    }
    public void test3(){
        client cl3=new client();
        for (int i = 0; i < c.length; i++) {
            cl3.fifo(new costumer(c[i]),nc);
            cl3.lru(new costumer(c[i]),nc);
            cl3.Second_chance(new costumer(c[i]),nc);
            System.out.println("-------------------------------------------------------");
        }
    }
}
