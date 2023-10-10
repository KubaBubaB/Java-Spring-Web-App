import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.String;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Person implements Comparable<Person>, Serializable {
    private String name;
    private int salary;
    private Role Role;

    public Person(String name, int salary, Role Role){
        this.salary = salary;
        this.name = name;
        this.Role = Role;
    }
    @Override
    public String toString(){
        return "Person: " + name + ", Salary: " + salary + ", Role: " + Role.getName()+Role.getWorkHoursPerDay();
    }

    @Override
    public int compareTo(Person o) {
        if(o.getName().equals(name) && o.salary == salary && o.getRole().getName().equals(Role.getName())){
            return 0;
        }
        else if (name.equals(o.getName()) && o.salary == salary){
            return Role.getName().compareTo(o.getRole().getName());
        }
        else if (name.equals(o.getName())){
            return Integer.compare(salary,o.salary);
        }
        else{
            return name.compareTo(o.getName());
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        else if (o == null || getClass() != o.getClass()) return false;
        Person chara = (Person) o;
        return salary == chara.getSalary() && name.equals(chara.getName()) &&
                Role.getName().equals(chara.getRole().getName()) &&
                Role.getWorkHoursPerDay() == chara.getRole().getWorkHoursPerDay();
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, salary, Role.getName(), Role.getWorkHoursPerDay());
    }
}