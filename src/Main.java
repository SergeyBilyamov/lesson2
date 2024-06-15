import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание членов семьи
        Person john = new Person("John Doe", Gender.MALE, LocalDate.of(1980, 1, 15));
        Person jane = new Person("Jane Doe", Gender.FEMALE, LocalDate.of(1982, 5, 22));
        Person child1 = new Person("Alice Doe", Gender.FEMALE, LocalDate.of(2005, 7, 30));
        Person child2 = new Person("Bob Doe", Gender.MALE, LocalDate.of(2008, 11, 5));

        // Добавление даты смерти
        john.setDeathDate(LocalDate.of(2020, 4, 10)); // Джон умер в 2020 году

        // Добавление детей к родителям
        john.addChild(child1);
        john.addChild(child2);
        jane.addChild(child1);
        jane.addChild(child2);

        // Создание генеалогического древа и добавление членов семьи
        FamilyTree familyTree = new FamilyTree();
        familyTree.addMember(john);
        familyTree.addMember(jane);
        familyTree.addMember(child1);
        familyTree.addMember(child2);

        // Сохранение генеалогического древа в файл
        FamilyTreeFileHandler fileHandler = new FamilyTreeFileHandlerImpl();
        String filename = "family_tree.ser";
        try {
            fileHandler.saveToFile(familyTree, filename);
            System.out.println("Family tree saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Загрузка генеалогического древа из файла
        try {
            FamilyTree loadedFamilyTree = fileHandler.loadFromFile(filename);
            System.out.println("Family tree loaded from file.");

            // Проведение исследования: получение всех детей выбранного человека
            List<Person> childrenOfJohn = loadedFamilyTree.getAllChildren("John Doe");
            if (childrenOfJohn != null) {
                System.out.println("Children of John Doe:");
                for (Person child : childrenOfJohn) {
                    System.out.println("- " + child.getName() + " (" + child.getGender() + ", " + child.getBirthDate() + ")");
                }
            } else {
                System.out.println("John Doe has no children or is not in the family tree.");
            }

            // Вывод возраста Джона
            Person foundJohn = loadedFamilyTree.findPersonByName("John Doe");
            if (foundJohn != null) {
                System.out.println("Age of John Doe: " + foundJohn.getAge());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
