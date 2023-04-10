import java.io.Serializable;
import java.time.Year;

public class Person implements Serializable, Comparable<Person>{
    private Integer id;
    private String fullName;
    private Year birthYear;
    private Year deathYear;
    private String nullFiller = "<нет_данных>";

    public Person(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Person(Integer id, String fullName, Year birthYear, Year deathYear) {
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return person.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return (fullName == null ? nullFiller : fullName) + " ("
                + (birthYear == null ? nullFiller : birthYear.toString()) + " - "
                + (deathYear == null ? nullFiller : deathYear.toString()) + ") ";
    }

    public Year getBirthYear() {
        return birthYear;
    }

    public Year getDeathYear() {
        return deathYear;
    }

    @Override
    public int compareTo(Person o) {
        return this.getId().compareTo(o.getId());
    }
}
