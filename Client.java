import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class client {
    public static List<Integer> fifolist=new ArrayList<>();
    public static List<Integer> fifo_result=new ArrayList<>();
    public static List<costumer> lru_list_id=new ArrayList<>();
    public static List<Integer> lru_result=new ArrayList<>();
    public static List<costumer> sec_list=new LinkedList<>();
    public static List<Integer> sec_result=new LinkedList<>();
    public static int l_counter=0;
    public static int numof_fifo_costumer=0;
    public static int numof_lru_costumer=0;
    public static int numof_second_costumer=0;
    public static int CountFifoFault=0;
    public static int CountLruFault=0;
    public static int CountSecondChanceFault=0;
    public static int index_fifo;
    public static int countoffifo;

    public static void fifo(costumer c,int n){
        if(fifolist.contains(c.id)){
            // nothing
        }
        else {
            numof_fifo_costumer++;
            if (numof_fifo_costumer <= n) {
                CountFifoFault++;
                fifolist.add(c.id);
                fifo_result.add(c.id);
            } else if (numof_fifo_costumer > n) {
                fifolist.remove(0);
                numof_fifo_costumer--;
                fifolist.add(c.id);
                CountFifoFault++;
                fifo_result.set(index_fifo,c.id);
                countoffifo++;
                index_fifo=(countoffifo)%n;

            }
        }
            System.out.print("fifo:");
            for (int i = 0; i < fifo_result.size(); i++) {
                if(i==fifo_result.size()-1){
                    System.out.println(fifo_result.get(i));
                    continue;
                }
                else
                System.out.print(fifo_result.get(i) + " ,");
            }
    }
    public static void lru(costumer new_one,int n){
        l_counter++;
        if(lru_list_id.contains(new_one)) {
            int j= lru_list_id.indexOf(new_one);
            costumer c=lru_list_id.get(j);
            c.counter=l_counter;
            lru_list_id.set(j,c);
        }
        else{
            numof_lru_costumer++;
            new_one.counter=l_counter;
            if (numof_lru_costumer <= n) {
                CountLruFault++;
                lru_list_id.add(new_one);
                lru_result.add(new_one.id);
            }
            else if (numof_lru_costumer > n) {
                CountLruFault++;
                int min=Integer.MAX_VALUE;
                int delete_id=0;
                int x=0;
                for (int i=0;i<lru_list_id.size();i++){
                    if(lru_list_id.get(i).counter<min){
                        costumer c=lru_list_id.get(i);
                        min=c.counter;
                        delete_id=c.id;
                        x=i;
                    }
                }
                lru_list_id.remove(x);
                numof_lru_costumer--;
                lru_list_id.add(new_one);
                for (int i = 0; i < lru_result.size(); i++) {
                    if(lru_result.get(i)==delete_id){
                        lru_result.set(i,new_one.id);
                        break;
                    }
                }
            }

        }
        System.out.print("LRU:");
        for (int i=0;i< lru_result.size();i++)
            System.out.print( lru_result.get(i)+" ,");
        System.out.println();
    }
    public static void Second_chance(costumer new_one,int n){
        if (sec_list.contains(new_one)){
            int j=sec_list.indexOf(new_one);
            costumer c= sec_list.get(j);
            c.refrence=1;
            sec_list.set(j,c);
        }
        else {
            numof_second_costumer++;
            if (numof_second_costumer<=n){
                CountSecondChanceFault++;
                sec_list.add(new_one);
                sec_result.add(new_one.id);
            }
            else if (numof_second_costumer>n){
                CountSecondChanceFault++;
                int delete_id=0;
                while (true){
                    if(sec_list.get(0).refrence==1){
                        costumer c=sec_list.get(0);
                        c.refrence=0;
                        sec_list.remove(0);
                        sec_list.add(c);
                        continue;
                    }
                    else {
                        delete_id=sec_list.get(0).id;
                        sec_list.remove(0);
                        break;
                    }
                }
                sec_list.add(new_one);
                for (int i = 0; i < sec_result.size(); i++) {
                    if(sec_result.get(i)==delete_id){
                        sec_result.set(i,new_one.id);
                        break;
                    }
                }
            }

        }
        System.out.print("SecondChance :");
        for (int i = 0; i < sec_result.size(); i++) {
            System.out.print(sec_result.get(i)+ " ,");
        }

        System.out.println();
    }
}


