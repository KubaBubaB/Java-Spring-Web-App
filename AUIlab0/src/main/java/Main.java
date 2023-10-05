import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static Profession mageProf;
    static Profession warriorProf;
    static Profession thiefProf;
    static Vector<Profession> professions;
    private static void buildProfessions(){
        mageProf = Profession.builder().name("Mage").baseArmor(7).build();
        warriorProf = Profession.builder().name("Warrior").baseArmor(15).build();
        thiefProf = Profession.builder().name("Thief").baseArmor(10).build();
        professions = new Vector<>();
        professions.add(mageProf);
        professions.add(warriorProf);
        professions.add(thiefProf);
    }

    private static void buildAndAssignCharacters(){
        Character char1 = Character.builder().name("Seraphin").level(1).profession(mageProf).build();
        Character char2 = Character.builder().name("Ignatius").level(2).profession(mageProf).build();
        Character char3 = Character.builder().name("Elowen").level(3).profession(mageProf).build();
        Character char4 = Character.builder().name("Magnus").level(4).profession(warriorProf).build();
        Character char5 = Character.builder().name("Isolde").level(1).profession(warriorProf).build();
        Character char6 = Character.builder().name("Rook").level(2).profession(warriorProf).build();
        Character char7 = Character.builder().name("Selene").level(5).profession(thiefProf).build();
        Character char8 = Character.builder().name("Astrid").level(2).profession(thiefProf).build();
        Character char9 = Character.builder().name("Jareth").level(3).profession(thiefProf).build();
        mageProf.setCharacters(new ArrayList<Character>(Arrays.asList(char1,char2,char3)));
        warriorProf.setCharacters(new ArrayList<Character>(Arrays.asList(char4,char5,char6)));
        thiefProf.setCharacters(new ArrayList<Character>(Arrays.asList(char7,char8,char9)));
    }

    private static void printProffesions(){
        System.out.println("Printing professions:");
        professions.forEach(profession -> {profession.getCharacters().forEach(character ->
                System.out.println(character.toString()));});
    }

    private static TreeSet<Character> createSet() {
        // Use a Stream to flatMap the character lists from each profession
        Stream<Character> characterStream = professions.stream()
                .flatMap(profession -> profession.getCharacters().stream());

        // Collect the characters into a TreeSet
        TreeSet<Character> characterSet = characterStream.collect(Collectors.toCollection(TreeSet::new));

        return characterSet;
    }

    private static void printSet(TreeSet<Character> tSet){
        Stream<Character> characterStream = tSet.stream();

        System.out.println(characterStream.collect(Collectors.toList()));
    }

    private static void filterSet(TreeSet<Character> tSet){
        Stream<Character> characterStream = tSet.stream().filter(character -> character.getProfession().
                equals(mageProf)).sorted();
        printSet(characterStream.collect(Collectors.toCollection(TreeSet::new)));
    }

    public static void main(String[] args){
        buildProfessions();
        buildAndAssignCharacters();
        printProffesions();
        TreeSet<Character> charSet = createSet();
        printSet(charSet);
        filterSet(charSet);
    }
}