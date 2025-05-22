package CardGame.Infrastructure.Persistence;

import CardGame.Domain.Entities.GameBoard;
import CardGame.Domain.Entities.Score;

import java.io.File;
import java.io.IOException;
import java.util.*;
import CardGame.Domain.Services.IScoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class FileScoreRepository implements IScoreRepository {
    private GameBoard gameBoard;
    public FileScoreRepository(GameBoard gb) {
        this.gameBoard = gb;
    }
    @Override
    public void saveScore(Score score) {


    }

    @Override
    public List<Score> loadScores() {
        return new ArrayList<>();
    }
    //how to actually save your score to DB(in this case: maybe a JSON file)
    //implement the IScoreRepository interface

    public void loadProductsFromDB(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Directly assign the list from Jackson's deserialization
            products = mapper.readValue(new File(filePath), new TypeReference<ArrayList<Product>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDB() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
