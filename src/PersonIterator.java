import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class PersonIterator implements Iterator<Person> {
    private int index;
    private List<Person> personList;
    
    public PersonIterator(List<Person> personList){
        this.personList = personList;
    }

    public PersonIterator(HashSet<Person> personSet){
        this.personList = new ArrayList<Person>(personSet);
    }

    @Override
    public boolean hasNext() {
        return index < personList.size();
    }

    @Override
    public Person next() {
        return personList.get(index++);
    }
}
