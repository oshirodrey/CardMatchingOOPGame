package CardGame.UI;

import CardGame.Application.UseCases.CheckForMatchUseCase;
import CardGame.Application.UseCases.FlipCardUseCase;
import CardGame.Domain.Entities.Card;
import CardGame.Domain.Entities.GameBoard;
import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.CardFactory;
import CardGame.InterfaceAdapters.Controller.GameController;
import CardGame.InterfaceAdapters.IUIController;
import CardGame.InterfaceAdapters.Presenter.GamePresenter;
import CardGame.UI.CustomizedComponents.CardClickListener;
import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.CustomizedComponents.StyleCard;
import CardGame.UI.Helpers.ButtonFactory;
import CardGame.UI.Helpers.ImageCache;
import CardGame.UI.Sound.BGMPlayer;
import CardGame.UI.Sound.SFXPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameScreen extends Screen implements CardClickListener, IUIController {
    // =================== 🧠 Game Core ===================
    private GameBoard gameBoard;
    private final int rows = 4;
    private final int cols = 5;
    private List<String> cardNameList = new ArrayList<>(Arrays.asList(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
    ));
    private List<Card> shuffledCardDeck;

    // =================== 🎴 UI Components ===================
    private List<StyleCard> displayCardDeck;
    private JLabel moveCountLabel;
    private JLabel timePassedLabel;


    // =================== ⏱ Game Flow Utilities ===================
    private Timer gameTimer;

    // ======================= 🧩Use Cases =======================
    private FlipCardUseCase flipCardUseCase;
    private CheckForMatchUseCase checkForMatchUseCase;

    // ======================= 🕹️ Adapters ========================
    private GameController controller;
    private GamePresenter presenter;

    public GameScreen() {
      shuffledCardDeck = CardFactory.createShuffledCardPairs(cardNameList,rows,cols);
      gameBoard = new GameBoard(rows,cols, shuffledCardDeck);


        try {
            bgmPlayer= new BGMPlayer();
            sfxPlayer= new SFXPlayer();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        presenter = new GamePresenter(gameBoard, this);
      flipCardUseCase = new FlipCardUseCase(gameBoard);
      checkForMatchUseCase = new CheckForMatchUseCase(gameBoard);
      controller = new GameController(flipCardUseCase, checkForMatchUseCase, presenter,sfxPlayer);

    }

    public ArrayList<StyleCard> displayCards() {
        ArrayList<StyleCard> cards = new ArrayList<>();
      for (Card card : shuffledCardDeck) {
          StyleCard styleCard = new StyleCard(card,this);
          cards.add(styleCard);
      }
      return cards;
    }




    @Override
    public void init() {
        this.getParentFrame().setSize(new Dimension(1000, 800));

        // === GIF Background ===
        ImageIcon background = ImageCache.loadGIFImage("sakuraBG2");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        // === Transparent Content Panel with BorderLayout ===
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBounds(0, 0, 1000, 765); // Match frame size

        // === CENTER Panel For Cards ===
        this.displayCardDeck = displayCards();
        JPanel cardDeckPanel = new JPanel(new GridBagLayout());
        cardDeckPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);  // Small gap between cards
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index >= displayCardDeck.size()) break;
                StyleCard card = displayCardDeck.get(index++);

                gbc.gridx = c;
                gbc.gridy = r;
                cardDeckPanel.add(card, gbc);
            }
        }
        // === Run Info Panel (Bottom) ===
        JPanel runInfoPanel = new JPanel();
        runInfoPanel.setOpaque(false);
        runInfoPanel.setLayout(new BoxLayout(runInfoPanel, BoxLayout.X_AXIS));
        moveCountLabel = new JLabel("Move: 0");
        timePassedLabel = new JLabel("     Time Passed: 0 second");
        runInfoPanel.add(moveCountLabel);
        runInfoPanel.add(timePassedLabel);

        // === Back Button (Top) ===
        StyleButton backButton = ButtonFactory.createBackButton(this).onClick(e -> {
            gameTimer.stop();
            bgmPlayer.stop();
            this.getParentFrame().replaceCurrentScreenWith(new TitleScreen(this.getParentFrame()));
        }).fontSize(25f).build();

        // === Game Timer Setup ===
        this.gameTimer = new Timer(100, e -> presenter.presentStatus());
        gameTimer.start();

        // === Add All Panels to Transparent contentPanel ===
        contentPanel.add(backButton, BorderLayout.NORTH);
        contentPanel.add(cardDeckPanel, BorderLayout.CENTER);
        contentPanel.add(runInfoPanel, BorderLayout.SOUTH);

        // === Layered Pane for Background and Content ===
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 800));
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(contentPanel, JLayeredPane.PALETTE_LAYER);

        // === Add to Main Panel ===
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
        this.setVisible(true);

        // === Play Music ===
        bgmPlayer.playRandom("CCS_BGM");
    }

    @Override
    public void onCardClicked(StyleCard clickedCard) {
       controller.onCardClicked(clickedCard.getCardEntity().getRow(), clickedCard.getCardEntity().getCol());
    }
    @Override
    public void updateCardIcons() {
        for (StyleCard sc : this.displayCardDeck) {
            sc.updateCardIcons(); // flips back if not face up
        }
    }

    @Override
    public void updateMoveAndTime(int moveCount, double time) {
        moveCountLabel.setText("Move: " + moveCount);
        timePassedLabel.setText("     Time Passed: " + time + " seconds");
    }

    @Override
    public void showWinScreen(Score score) {
        SwingUtilities.invokeLater(() -> {
            gameTimer.stop();
            bgmPlayer.stop();
            getParentFrame().replaceCurrentScreenWith(
                    new WinScreen(getParentFrame(), score)
            );
        });
    }
}


