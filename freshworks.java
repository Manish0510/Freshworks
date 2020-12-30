import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore{
    static class Value{
        String name;
        int age;
        long ttl,createdTime;
        Value(String a,int b,long c,long d){
            name = a;
            age=b;
            ttl=c;
            createdTime=d;
        }
    }
   static Map<String, Value> map = new ConcurrentHashMap<>();
   static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
     
      while(true) {
        System.out.println("Do you want to perform any operation on data store: Enter Y or N");
        char op = sc.next().charAt(0);
        if(op!='y' || op!='Y')
          break;
        System.out.println("\n<--------------------Freshworks Key-Value DataStore-------------------->");
        System.out.println("Press 1 : Create Key-Value pair");
        System.out.println("Press 2 : Read Key-Value pair");
        System.out.println("Press 3 : Delete Key-Value pair");
        int option = sc.nextInt();
        String key = null, value = null;
        Long dateInMilliSecs = 0L;
        switch (option) {
            case 1:
              create();
            case 2:
              read();
            case 3:
              delete();
        }
      }
    }
    public static void create(){
              System.out.println("Enter Key");
              String key = sc.next();
              if(key.length()==0 )
                System.err.println("Key cannot be empty");
              else if(key.length()>32)
                System.err.println("Key length cannot exceed 32 chars");
              else if(map.containsKey(key))
                System.err.println("Key already exists");
              else{
                String name = sc.next();
                int age = sc.nextInt();
                long ttl = sc.nextInt();
                long curtime = System.currentTimeMillis();
                Value v = new Value(name,age,ttl,curtime);
                map.put(key,v);
              }
            }
            public static void read(){
              System.out.println("Enter key");
               String key = sc.next();
              if(!map.containsKey(key))
                System.err.println("Key doesn't exist.");
              else{
                Value v = map.get(key);
                long curtime = System.currentTimeMillis();
                long keytime = v.createdTime;
                long remainingTime = curtime-keytime;
                if(remainingTime>=v.ttl){
                  System.err.println("Key has expired");
                  map.remove(key);
                }
                else{
                  System.out.println("Name :"+map.get(v.name));
                  System.out.println("Age :"+map.get(v.age));
                }
              }
            }
            public static void delete(){
              System.out.println("Enter key");
               String key = sc.next();
              if(!map.containsKey(key))
                System.err.println("Key doesn't exist.");
              else{
                map.remove(key);
              }
            }    
}