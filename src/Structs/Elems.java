package Structs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Elems implements Serializable{
    HashMap<Integer, Elem> elems;

    public Elems(){
        this.elems = new HashMap<Integer, Elem>();
    }

    public void addElem(Elem elem){
        this.elems.put(elem.id, elem);
    }

    public void removeElem(int elem_id){
        this.elems.remove(elem_id);
    }

    public Elem getElem(int elem_id){
        return this.elems.get(elem_id);
    }

    public void editElem(int elem_id, String file_name){
        Elem elem = this.elems.get(elem_id);
        elem.Edit(file_name);
    }

    public String toString(){
        String result = "";
        for (Elem elem : this.elems.values()){
            result += elem.toString() + "\n";
        }
        return result;
    }

    public static void save(Elems object, String file){
        try{
            FileOutputStream fileOutput = new FileOutputStream(file);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(object);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e){
            System.err.println("Couldn't save elements!");
        }    }

    public static Elems load(String file){
        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            Elems object = (Elems) objectInput.readObject();
            objectInput.close();
            fileInput.close();
            return object;
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist, created new object");
            return new Elems();
        } catch (IOException e) {
            System.err.println("Error while reading data!");
        } catch (ClassNotFoundException e) {
            System.err.println("Object not found!");
        }

        return null;
    }
}
