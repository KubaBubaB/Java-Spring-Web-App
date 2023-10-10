import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

public class Main {
    static Role waiterProf;
    static Role bartenderProf;
    static Role cookProf;
    static Vector<Role> Roles;
    private static void buildRoles(){
        waiterProf = Role.builder().name("Waiter").workHoursPerDay(8).build();
        bartenderProf = Role.builder().name("Bartender").workHoursPerDay(12).build();
        cookProf = Role.builder().name("Cook").workHoursPerDay(16).build();
        Roles = new Vector<>();
        Roles.add(waiterProf);
        Roles.add(bartenderProf);
        Roles.add(cookProf);
    }

    private static void buildAndAssignPersons(){
        Person char1 = Person.builder().name("Seraphin").salary(1000).Role(waiterProf).build();
        Person char2 = Person.builder().name("Ignatius").salary(1200).Role(waiterProf).build();
        Person char3 = Person.builder().name("Elowen").salary(1300).Role(waiterProf).build();
        Person char4 = Person.builder().name("Magnus").salary(2400).Role(bartenderProf).build();
        Person char5 = Person.builder().name("Isolde").salary(2100).Role(bartenderProf).build();
        Person char6 = Person.builder().name("Rook").salary(2000).Role(bartenderProf).build();
        Person char7 = Person.builder().name("Selene").salary(3500).Role(cookProf).build();
        Person char8 = Person.builder().name("Astrid").salary(3200).Role(cookProf).build();
        Person char9 = Person.builder().name("Jareth").salary(3300).Role(cookProf).build();
        waiterProf.setPersons(new ArrayList<Person>(Arrays.asList(char1,char2,char3)));
        bartenderProf.setPersons(new ArrayList<Person>(Arrays.asList(char4,char5,char6)));
        cookProf.setPersons(new ArrayList<Person>(Arrays.asList(char7,char8,char9)));
    }

    private static void printRoles(){
        System.out.println("Printing Roles:");
        Roles.forEach(Role -> {Role.getPersons().forEach(Person ->
                System.out.println(Person.toString()));});
    }

    private static TreeSet<Person> createSet() {
        Stream<Person> PersonStream = Roles.stream()
                .flatMap(Role -> Role.getPersons().stream());

        TreeSet<Person> PersonSet = PersonStream.collect(Collectors.toCollection(TreeSet::new));

        return PersonSet;
    }

    private static void printSet(TreeSet<Person> tSet){
        Stream<Person> PersonStream = tSet.stream();

        System.out.println(PersonStream.collect(Collectors.toList()));
    }

    private static void filterSet(TreeSet<Person> tSet){
        Stream<Person> PersonStream = tSet.stream().filter(Person -> Person.getRole().
                equals(waiterProf)).sorted();
        printSet(PersonStream.collect(Collectors.toCollection(TreeSet::new)));
    }

    private static void transformIntoDtoAndPrint(TreeSet<Person> tSet){
        List<PersonDTO> arDTO = tSet.stream().map(Person -> {
            final PersonDTO charDTo = new PersonDTO(Person.getName(), Person.getSalary(), Person.getRole().getName());
            return charDTo;
        }).sorted().toList();

        Stream<PersonDTO> PersonStream = arDTO.stream();
        PersonStream.forEach(System.out::println);
    }

    private static void serializeDeserializeAndPrint(Vector<Role> vecProf) {
        String path = "src/main/resources/serialization.txt";
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(vecProf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //deserielize
        Vector<Role> vecProf2 = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            vecProf2 = (Vector<Role>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            if(ois != null){
                try{
                    ois.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fis!=null){
                try{
                    fis.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

        if(vecProf2!=null){
            vecProf2.forEach(Role -> {
                System.out.println(Role);
                Role.getPersons().forEach(Person ->
                    System.out.println(Person.toString()));});
        }
    }

    private static void threadPoolTask(){
        int poolSize = 4;
        ForkJoinPool customThreadPool = new ForkJoinPool(poolSize);
        //is this the same as Roles.parallelStream(). ... ???????????
        Roles.stream().parallel().forEach(Role -> {
            Role.getPersons().forEach(Person -> {
                customThreadPool.execute(() -> {
                    try {
                        System.out.println("Working on " + Person.getName() + " "+ Person.getRole());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });

        });
        customThreadPool.shutdown();
        while (!customThreadPool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        buildRoles();
        buildAndAssignPersons();
        System.out.println("Task 2");
        printRoles();
        System.out.println("Task 3");
        TreeSet<Person> charSet = createSet();
        printSet(charSet);
        System.out.println("Task 4");
        filterSet(charSet);
        System.out.println("Task 5");
        transformIntoDtoAndPrint(createSet());
        System.out.println("Task 6");
        serializeDeserializeAndPrint(Roles);
        System.out.println("Task 7");
        threadPoolTask();
    }
}