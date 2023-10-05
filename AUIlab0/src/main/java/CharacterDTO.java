import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CharacterDTO {
    private String name;
    private int level;
    private String profession;
    public CharacterDTO(String name, int level, String profession){
        this.level = level;
        this.name = name;
        this.profession = profession;
    }
}
