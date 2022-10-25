package capers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import static capers.Utils.*;


/** Represents a dog that can be serialized.
 * @author LMT
*/
public class Dog implements Serializable{ // TODO

    static Map<String, File> dogFiles = new TreeMap<>();

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = join(".capers","dogs"); // TODO (hint: look at the `join`
                                                                        //      function in Utils)
    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
        // TODO (hint: look at the Utils file)
        File fileName = join(DOG_FOLDER,name);
        Dog temp = readObject(fileName, Dog.class);
        return temp;
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() throws IOException {
        // TODO (hint: don't forget dog names are unique)
        File dogFile = join(DOG_FOLDER,this.name);
        dogFile.createNewFile();
        writeObject(dogFile,this);
        //dogFiles.put(this.name, dogFile);
    }

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }

}
