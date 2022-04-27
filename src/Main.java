import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Note> notes = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("Save.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            notes = (ArrayList<Note>) objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("No Save File");;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        outer:
        while (true) {
            printCommands();
            try {
                int command = Integer.parseInt(input.nextLine());
                switch (command) {
                    case 1:
                        int state = 0;
                        System.out.print("Name: ");
                        String name = input.nextLine();
                        System.out.print("Body: ");
                        String body = input.nextLine();
                        for (Note note : notes) {
                            if (Objects.equals(note.getName(), name)) {
                                System.out.println("This note already exists");
                                state = 1;
                            }
                        }
                        if (state == 0) {
                            notes.add(new Note(name, body));
                        }
                        break;
                    case 2:
                        System.out.print("Name: ");
                        name = input.nextLine();
                        notes.removeIf(note -> Objects.equals(note.getName(), name));
                        break ;
                    case 3:
                        int i = 1 ;
                        for(Note note : notes){
                            System.out.println(i + "- " + note.getName());
                            i++;
                        }
                        break ;
                    case 4:
                        i = 1 ;
                        for(Note note : notes){
                            System.out.println(i + "- " + note.getName());
                            i++;
                        }
                        if(i == 1){
                            break ;
                        }
                        System.out.print("Choose to export: ");
                        int number = Integer.parseInt(input.nextLine());
                        i = 1;
                        for (Note note : notes) {
                            if (i == number) {
                                try {
                                    FileOutputStream fileOutputStream = new FileOutputStream("example/" + note.getName() + ".text");
                                    fileOutputStream.write(note.toString().getBytes());
                                    fileOutputStream.close();
                                } catch (Exception e) {
                                    System.out.println(e.getStackTrace());
                                }
                            }
                            i++;
                        }
                        break ;
                    case 5:
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream("Save.bin");
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                            objectOutputStream.writeObject(notes);
                            fileOutputStream.close();
                            objectOutputStream.close();
                        } catch (Exception e) {
                            System.out.println(e.getStackTrace());
                        }
                        break outer;
                }
            } catch (Exception e) {
                System.out.println("Wrong Input!");
            }
        }
    }

    public static void printCommands() {
        System.out.println("""
                [1] Add
                [2] Remove
                [3] Notes
                [4] Export
                [5] Exit""");
    }
}