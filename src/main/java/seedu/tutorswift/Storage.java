package seedu.tutorswift;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles loading and saving student data to a local text file.
 */
public class Storage {
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private static final String DEFAULT_FILE_PATH = "./data/tutorswift.txt";
    private final String filePath;

    /**
     * Initializes Storage with the default file path.
     */
    public Storage() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Initializes Storage with a custom file path.
     * Useful for testing with temporary files.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
        prepareDirectory(filePath);
    }

    /**
     * Creates the parent directory for the given file path if it doesn't exist.
     */
    private void prepareDirectory(String filePath) {
        Path path = Paths.get(filePath);
        Path parentDir = path.getParent();
        if (parentDir != null) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Could not create directory: " + parentDir, e);
            }
        }
    }

    /**
     * Saves all students in the list to the hard disk.
     *
     * @param students The StudentList to save.
     * @throws TutorSwiftException If writing fails.
     */
    public void save(StudentList students) throws TutorSwiftException {
        assert students != null : "Cannot save a null StudentList";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Student s : students.getAllActive()) {
                writer.write(s.toSaveFormat() + System.lineSeparator());
            }
            for (Student s : students.getAllArchived()) {
                writer.write(s.toSaveFormat() + System.lineSeparator());
            }
            logger.log(Level.INFO, "Saved " + (students.getActiveSize() + students.getArchivedSize()) + " students.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error during save", e);
            throw new TutorSwiftException("Error: Could not save data to " + filePath);
        }
    }

    /**
     * Loads student data from the file. Handles corruption and file-not-found.
     *
     * @return A populated StudentList.
     */
    public StudentList load() {
        StudentList list = new StudentList();
        File file = new File(filePath);

        if (!file.exists()) {
            logger.log(Level.INFO, "No storage file found. Starting with empty list.");
            return list;
        }

        try (Scanner scanner = new Scanner(file)) {
            int lineCount = 0;
            while (scanner.hasNextLine()) {
                lineCount++;
                String line = scanner.nextLine();
                try {
                    Student s = parseLineToStudent(line);
                    list.addStudent(s);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Skipping corrupted line " + lineCount + ": " + line);
                }
            }
            logger.log(Level.INFO, "Loaded data from disk successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Critical error reading file", e);
        }
        return list;
    }

    /**
     * Decodes a line of text into a Student object.
     * Format: Name | Level | Subject | isArchived
     */
    private Student parseLineToStudent(String line) throws TutorSwiftException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 4) {
            throw new TutorSwiftException("Invalid format");
        }

        String name = parts[0].trim();
        String level = parts[1].trim();
        String subject = parts[2].trim();
        boolean isArchived = Boolean.parseBoolean(parts[3].trim());

        Student s = new Student(name, level, subject);
        s.setArchived(isArchived);
        if (parts.length >= 5 && !parts[4].equals("EMPTY")) {
            String[] gradeEntries = parts[4].split(",");
            for (String entry : gradeEntries) {
                String[] gradeParts = entry.split(":");
                if (gradeParts.length == 2) {
                    s.addGrade(gradeParts[0], Integer.parseInt(gradeParts[1]));
                }
            }
        }
        return s;
    }
}
