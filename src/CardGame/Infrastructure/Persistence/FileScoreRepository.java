package CardGame.Infrastructure.Persistence;

import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.IScoreRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileScoreRepository implements IScoreRepository {
    private final File file = new File("./src/CardGame/Infrastructure/Persistence/ScoreData.txt");
//    public FileScoreRepository(String filepath) {
//        this.file = new File(filepath);

    public static void main(String[] args) {
        File file = new File("/src/CardGame/Infrastructure/Persistence/ScoreData.txt");
        if (!file.exists()) {
            System.out.println("File not found at: " + file.getAbsolutePath());
        } else {
            System.out.println("File found: " + file.getAbsolutePath());
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
}
