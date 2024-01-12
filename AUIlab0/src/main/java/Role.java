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
public class Role implements Comparable<Role>, Serializable {
    private String name;
    private int workHoursPerDay;
    private List<Person> Persons;

    public Role(String name, int workHoursPerDay, List<Person> Persons){
        this.name = name;
        this.workHoursPerDay = workHoursPerDay;
        this.Persons = Persons;
    }

    public Role(String name, int workHoursPerDay){
        this.name = name;
        this.workHoursPerDay = workHoursPerDay;
        this.Persons = new ArrayList<Person>();
    }

    public void addPerson(Person chara){
        Persons.add(chara);
    }

    public void removePerson(Person chara) throws NoSuchElementException{
        if (Persons.contains(chara)){
            Persons.remove(chara);
        }
        else{
            throw new NoSuchElementException("Person "+ chara + " was not found.");
        }
    }

    @Override
    public String toString(){
        return "Role name: " + name + " Work Hours Per Day: " + workHoursPerDay;
        //return "Role name: " + name + " Base armor: " + workHoursPerDay + "Persons belonging to this prof: " + Persons;
    }

    @Override
    public int compareTo(Role o) {
        if (name.equals(o.getName()) && workHoursPerDay == o.getWorkHoursPerDay() && Persons.equals(o.getPersons())){
            return 0;
        }
        else if (name.equals(o.getName()) && workHoursPerDay == o.getWorkHoursPerDay()){
            int iterator = 0;
            for (Person chara : Persons){
                if (o.getPersons().size() <= iterator){
                    return 1;
                }
                if(chara.equals(o.getPersons().get(iterator))){
                    iterator++;
                }
                else{
                    return chara.compareTo(o.getPersons().get(iterator));
                }
            }
            //this should not return 0, if statement above prevents it
            return 0;
        }
        else if (name.equals(o.getName())){
            return Integer.compare(workHoursPerDay, o.getWorkHoursPerDay());
        }
        else{
            return name.compareTo(o.getName());
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;
        Role prof = (Role) o;
        return name.equals(prof.getName()) && workHoursPerDay == prof.getWorkHoursPerDay() && Persons.equals(prof.getPersons());
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,workHoursPerDay,Persons);
    }
}
