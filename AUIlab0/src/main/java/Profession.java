import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Setter
@Getter
@Builder
public class Profession implements Comparable<Profession>, Serializable {
    private String name;
    private int baseArmor;
    private List<Character> characters;

    public Profession(String name, int baseArmor, List<Character> characters){
        this.name = name;
        this.baseArmor = baseArmor;
        this.characters = characters;
    }

    public Profession(String name, int baseArmor){
        this.name = name;
        this.baseArmor = baseArmor;
        this.characters = new ArrayList<Character>();
    }

    public void addCharacter(Character chara){
        characters.add(chara);
    }

    public void removeCharacter(Character chara) throws NoSuchElementException{
        if (characters.contains(chara)){
            characters.remove(chara);
        }
        else{
            throw new NoSuchElementException("Character "+ chara + " was not found.");
        }
    }

    @Override
    public String toString(){
        return "Profession name: " + name + " Base armor: " + baseArmor;
        //return "Profession name: " + name + " Base armor: " + baseArmor + "Characters belonging to this prof: " + characters;
    }

    @Override
    public int compareTo(Profession o) {
        if (name.equals(o.getName()) && baseArmor == o.getBaseArmor() && characters.equals(o.getCharacters())){
            return 0;
        }
        else if (name.equals(o.getName()) && baseArmor == o.getBaseArmor()){
            int iterator = 0;
            for (Character chara : characters){
                if (o.getCharacters().size() <= iterator){
                    return 1;
                }
                if(chara.equals(o.getCharacters().get(iterator))){
                    iterator++;
                }
                else{
                    return chara.compareTo(o.getCharacters().get(iterator));
                }
            }
            //this should not return 0, if statement above prevents it
            return 0;
        }
        else if (name.equals(o.getName())){
            return Integer.compare(baseArmor, o.getBaseArmor());
        }
        else{
            return name.compareTo(o.getName());
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;
        Profession prof = (Profession) o;
        return name.equals(prof.getName()) && baseArmor == prof.getBaseArmor() && characters.equals(prof.getCharacters());
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,baseArmor,characters);
    }
}
