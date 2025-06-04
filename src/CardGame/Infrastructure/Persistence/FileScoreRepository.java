package CardGame.Infrastructure.Persistence;

import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.IScoreRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads and saves score data to a text file located in the current working directory.
 */
public class FileScoreRepository implements IScoreRepository {
    private static final String fileName = "ScoreData.txt";
    private final File file;

    public FileScoreRepository() {
        File jarDir;
        try {
            // Get the directory of the running JAR
            jarDir = new File(FileScoreRepository.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI())
                    .getParentFile();
        } catch (Exception e) {
            throw new RuntimeException("Could not determine JAR directory", e);
        }

        this.file = new File(jarDir, fileName);
        // Create the file if it doesn't exist
        try {
            file.getParentFile().mkdirs(); // if needed
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize score file", e);
        }
    }

    @Override
    public List<Score> loadScores() {
        List<Score> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Score.fromString(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load scores", e);
        }
        return scores;
    }

    @Override
    public void saveAll(List<Score> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Score score : scores) {
                writer.write(score.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to overwrite scores", e);
        }
    }

    public static void main(String[] args) {
        File file = new File("/src/CardGame/Infrastructure/Persistence/ScoreData.txt");
        if (!file.exists()) {
            System.out.println("File not found at: " + file.getAbsolutePath());
        } else {
            System.out.println("File found: " + file.getAbsolutePath());
        }
    }
}
