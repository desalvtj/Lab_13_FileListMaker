
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileListMaker {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> arrList = new ArrayList<>();
        String ans = "";
        boolean run = true;
        boolean needsToBeSaved = false;
        String fileName = "";
        do {
            ans = printMenu(in, arrList);
            switch (ans) {
                case "A":
                    addToList(in, arrList);
                    needsToBeSaved = true;
                    break;
                case "C":
                    clearList(arrList);
                    needsToBeSaved = true;
                    break;
                case "D":
                    deleteFromList(in, arrList);
                    needsToBeSaved = true;
                    break;
                case "O":
                    fileName = openListFile(in, arrList, needsToBeSaved);
                    break;
                case "S":
                    saveCurrentFile(arrList, fileName);
                    needsToBeSaved = false;
                    break;
                case "V":
                    displayList(arrList);
                    break;
                case "Q":
                    if (SafeInput.getYNConfirm(in, "Are you sure")) {
                        if (needsToBeSaved) {
                            saveCurrentFile(arrList, fileName);
                        }
                        run = false;
                    } else {
                        System.out.println("Returning to menu...");
                    }
                    break;
            }
        } while (run);
    }

    private static String printMenu(Scanner in, ArrayList arrList) {
        if (arrList.isEmpty()) {
            System.out.println("Your list is currently empty.");
        } else {
            System.out.println("Current list:");
            for (int i = 0; i < arrList.size(); i++) {
                System.out.printf("    %d. %s\n", i + 1 , arrList.get(i));
            }
        }
        return SafeInput.getRegExString(in, "Select a menu option:\n    A: Add\n    C: Clear\n    D: Delete\n    O: Open\n    S: Save\n    V: View\n    Q: Quit\n", "[AaCcDdOoSsVvQq]").toUpperCase();
    }

    public static void addToList(Scanner in, ArrayList arrList) {
        String itemToAdd = SafeInput.getNonZeroLenString(in, "What would you like to add to the array list");
        arrList.add(itemToAdd);
    }

    public static void clearList(ArrayList arrList) {
        arrList.clear();
    }

    public static void deleteFromList(Scanner in, ArrayList arrList) {
        int itemToDelete = SafeInput.getRangedInt(in, "What item do you want to delete", 1, arrList.size());
        arrList.remove(itemToDelete - 1);
        System.out.println(itemToDelete + " was successfully removed!");
    }

    public static void displayList(ArrayList arrList) {
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(arrList.get(i));
        }
    }

    private static String openListFile(Scanner in, ArrayList arrList, boolean needsToBeSaved) {
        if (needsToBeSaved) {
            String prompt = "Opening a new list will result in losing your current one. Are you sure";
            boolean burnListYN = SafeInput.getYNConfirm(in, prompt);
            if (!burnListYN) {
                return "";
            }
        }
        clearList(arrList);
        Scanner inFile;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);
        String line;

        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                inFile = new Scanner(target);
                System.out.println("Opening File: " + target.getFileName());
                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    arrList.add(line);
                }
                inFile.close();
            } else { // user did not select a file
                System.out.println("You must select a file! Returning to menu...");
            }
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
        return target.toFile().toString();
    }

    public static void saveCurrentFile(ArrayList arrList, String fileName) {
        PrintWriter outFile;
        Path target = new File(System.getProperty("user.dir")).toPath();
        if (fileName.equals("")) {
            target = target.resolve("src\\list.txt");
        } else {
            target = target.resolve(fileName);
        }

        try {
            outFile = new PrintWriter(target.toString());
            for (int i = 0; i < arrList.size(); i++) {
                outFile.println(arrList.get(i));
            }
            outFile.close();
            System.out.printf("File \"%s\" saved!\n", target.getFileName());
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
    }
}