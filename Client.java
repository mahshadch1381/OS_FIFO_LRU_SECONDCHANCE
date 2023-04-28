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


}


