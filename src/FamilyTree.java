import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree implements Serializable {
    private List<Person> members;

    public FamilyTree() {
        this.members = new ArrayList<>();
    }

    public void addMember(Person person) {
        if (person != null) {
            this.members.add(person);
        } else {
            throw new IllegalArgumentException("Member cannot be null");
        }
    }

    public Person findPersonByName(String name) {
        for (Person person : members) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public List<Person> getAllChildren(String personName) {
        Person person = findPersonByName(personName);
        if (person != null) {
            return person.getChildren();
        } else {
            return null;
        }
    }
}
