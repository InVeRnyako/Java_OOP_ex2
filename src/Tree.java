import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Tree implements Serializable, TreeData, Iterable<Person> {
    private HashSet<Person> personsData;
    private HashSet<Parent> parentsData;
    

    public static Person findPersonByName(HashSet<Person> inputTree, String nameInput) {
        for (Person person : inputTree) {
            if (person.getFullName().contains(nameInput))
                return person;
        }
        return null;
    }

    public void addPerson(Person newPerson) {
        if (newPerson == null)
            return;
        personsData.add(newPerson);
    }

    public void removePerson(Integer indexToRemove) {
        personsData.removeIf((t) -> t.getId().equals(indexToRemove));
    }

    public void addParent(Integer kidId, Integer parentId) {
        if (kidId != parentId)
            parentsData.add(new Parent(kidId, parentId));
    }

    public HashSet<Integer> findAllKidsId(Integer parentId) {
        HashSet<Integer> kidsIdSet = new HashSet<>();
        for (Parent parentKidPair : parentsData) {
            if (parentId.equals(parentKidPair.getIdParent())) {
                kidsIdSet.add(parentKidPair.getIdKid());
            }
        }
        return kidsIdSet;
    }

    public HashSet<Integer> findAllParentsId(Integer kidId) {
        HashSet<Integer> parentsIdSet = new HashSet<>();
        for (Parent parentKidPair : parentsData) {
            if (kidId.equals(parentKidPair.getIdKid()))
                parentsIdSet.add(kidId);
        }
        return parentsIdSet;
    }

    public HashSet<Person> idToPersonSet(HashSet<Integer> inputIdSet) {
        HashSet<Person> outSet = new HashSet<>();
        for (Person person : personsData) {
            if (inputIdSet.contains(person.getId()))
                outSet.add(person);
        }
        return outSet;
    }

    public Tree(HashSet<Person> inputPersons, HashSet<Parent> inputParents) {
        this.personsData = inputPersons;
        this.parentsData = inputParents;
    }

    public Tree() {
        this(new HashSet<>(), new HashSet<>());
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        String prefix = "";
        for (Person person : personsData) {
            output.append(prefix);
            output.append(person);
            prefix = ", ";
        }
        return output.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tree)) {
            return false;
        }
        Tree tree = (Tree) o;
        return Objects.equals(personsData, tree.personsData) && Objects.equals(parentsData, tree.parentsData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personsData, parentsData);
    }

    public HashSet<Person> getPersonsData() {
        return personsData;
    }

    public void setPersonsData(HashSet<Person> personsData) {
        this.personsData = personsData;
    }

    public HashSet<Parent> getParentsData() {
        return parentsData;
    }

    public void setParentsData(HashSet<Parent> parentsData) {
        this.parentsData = parentsData;
    }

    public String showAll() {
        StringBuilder outputString = new StringBuilder();
        String prefix = "";
        for (Person person : personsData) {
            outputString.append(prefix);
            outputString.append(person);
            prefix = "\n";
        }
        return outputString.toString();
    }

    @Override
    public void saveData() {
        SaveFile sf = new SaveFile();
        sf.saveData(this);
    }

    @Override
    public void readData() {
        SaveFile sf = new SaveFile();
        Object dataFromFile = sf.readData();
        if (dataFromFile instanceof Tree) {
            Tree readTree = new Tree();
            readTree = (Tree) (dataFromFile);
            this.parentsData = readTree.parentsData;
            this.personsData = readTree.personsData;
        }
        return;
    }

    @Override
    public Iterator<Person> iterator() {
        return new PersonIterator(personsData);
    }

    public List<Person> sortByName(){
        List<Person> outList = new ArrayList<Person>(personsData);
        outList.sort(new PersonComparatorByName());
        return outList;
    }

    public List<Person> sortByBirthDate(){
        List<Person> outList = new ArrayList<Person>(personsData);
        outList.sort(new PersonComparatorByBirthDate());
        return outList;
    }
}
