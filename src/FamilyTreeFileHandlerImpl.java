import java.io.*;
import java.util.List;

public class FamilyTreeFileHandlerImpl implements FamilyTreeFileHandler {

    @Override
    public void saveToFile(FamilyTree familyTree, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTree);
        }
    }

    @Override
    public FamilyTree loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (FamilyTree) ois.readObject();
        }
    }
}
