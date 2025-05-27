package CardGame.UI;

import CardGame.Application.UseCases.LoadScoresUseCase;
import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.IScoreRepository;
import CardGame.Infrastructure.Persistence.FileScoreRepository;
import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.Helpers.ButtonFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class LeaderBoardScreen extends Screen {

    private IScoreRepository scoreRepository;
    private LoadScoresUseCase loadScoresUseCase;

    public LeaderBoardScreen() {
        scoreRepository = new FileScoreRepository();
        loadScoresUseCase = new LoadScoresUseCase(scoreRepository);
    }

    @Override
    public void init() {
        this.setLayout(new BorderLayout());
        this.setBackground(customPink);


        //leader board panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(customPink);


        JLabel title = new JLabel("🏆 Leaderboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane table = createScoreTable();

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(table);

        //button container
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        buttonContainer.setBackground(customPink);

        StyleButton playButton = ButtonFactory.createStartGameButton("Time to make history", this);
        StyleButton rulesButton = ButtonFactory.createRuleButton("Rules", this);
        StyleButton backButton = ButtonFactory.createBackButton("Back to Main Menu", this);

        buttonContainer.add(playButton);
        buttonContainer.add(rulesButton);
        buttonContainer.add(backButton);


        this.add(buttonContainer, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);



    }
    private JScrollPane createScoreTable() {
        String[] columnNames = {"#", "Moves", "Time (s)"};

        List<Score> scores = loadScoresUseCase.execute();
        Object[][] rowData = new Object[scores.size()][3];
        for (int i = 0; i < scores.size(); i++) {
            Score s = scores.get(i);
            rowData[i][0] = i + 1;
            rowData[i][1] = s.getMoveCount();
            rowData[i][2] = String.format("%.1f", s.getElapsedTime());
        }

        JTable table = new JTable(rowData, columnNames);
        table.setEnabled(false);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setBackground(new Color(230, 230, 250)); // Light lavender
        header.setForeground(Color.BLACK);

        table.setFillsViewportHeight(true);

        //remove grid lines for a cleaner look
        table.setShowGrid(false);

        //make cells center-aligned
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return scrollPane;
    }
}
