import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CharacterDTO implements Comparable<CharacterDTO>{
    private String name;
    private int level;
    private String profession;

    @Override
    public int compareTo(CharacterDTO o) {
        if(o.getName().equals(name) && o.level == level && o.getProfession().equals(profession)){
            return 0;
        }
        else if (name.equals(o.getName()) && o.level == level){
            return profession.compareTo(o.getProfession());
        }
        else if (name.equals(o.getName())){
            return Integer.compare(level,o.level);
        }
        else{
            return name.compareTo(o.getName());
        }
    }
}
