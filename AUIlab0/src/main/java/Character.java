import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.String;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Character implements Comparable<Character>, Serializable {
    private String name;
    private int level;
    private Profession profession;

    public Character(String name, int level, Profession profession){
        this.level = level;
        this.name = name;
        this.profession = profession;
    }
    @Override
    public String toString(){
        return "Character: " + name + ", lvl: " + level + ", profession: " + profession.getName()+profession.getBaseArmor();
    }

    @Override
    public int compareTo(Character o) {
        if(o.getName().equals(name) && o.level == level && o.getProfession().getName().equals(profession.getName())){
            return 0;
        }
        else if (name.equals(o.getName()) && o.level == level){
            return profession.getName().compareTo(o.getProfession().getName());
        }
        else if (name.equals(o.getName())){
            return Integer.compare(level,o.level);
        }
        else{
            return name.compareTo(o.getName());
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;
        Character chara = (Character) o;
        return level == chara.getLevel() && name.equals(chara.getName()) &&
                profession.getName().equals(chara.getProfession().getName()) &&
                profession.getBaseArmor() == chara.getProfession().getBaseArmor();
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, level, profession.getName(), profession.getBaseArmor());
    }
}