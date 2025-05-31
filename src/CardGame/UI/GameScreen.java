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
    private BGMPlayer bgmPlayer;
    private SFXPlayer sfxPlayer;

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

        presenter = new GamePresenter(gameBoard, this, sfxPlayer);
      flipCardUseCase = new FlipCardUseCase(gameBoard);
      checkForMatchUseCase = new CheckForMatchUseCase(gameBoard);
      controller = new GameController(flipCardUseCase, checkForMatchUseCase, presenter);

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
        this.getParentFrame().setSize(new Dimension(900, 800));
        this.setLayout(new BorderLayout());
        this.setBackground(customPink);
        this.displayCardDeck = displayCards();

        JPanel cardDeckPanel = new JPanel();
        cardDeckPanel.setBackground(customPink);
        cardDeckPanel.setLayout(new GridLayout(rows,cols));
        for (StyleCard card : displayCardDeck) {
            cardDeckPanel.add(card);
        }

        //STORE THE RUN'S INFORMATION
        JPanel runInfoPanel = new JPanel();
        runInfoPanel.setBackground(customPink);
        runInfoPanel.setLayout(new BoxLayout(runInfoPanel,BoxLayout.X_AXIS));


       moveCountLabel = new JLabel("Move: 0");
       timePassedLabel = new JLabel("     Time Passed: 0 second" );


        // Timer that ticks every 100 milliseconds
        this.gameTimer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                presenter.presentStatus();
            }});
        gameTimer.start();

        //this button needs to stop the timer(and sound), so I didn't use buttonFactory here
        StyleButton backButton = new StyleButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            gameTimer.stop();
            bgmPlayer.stop();
            this.getParentFrame().replaceCurrentScreenWith(new TitleScreen(this.getParentFrame())); });

        runInfoPanel.add(moveCountLabel);
        runInfoPanel.add(timePassedLabel);


        this.add(backButton, BorderLayout.NORTH);
        this.add(cardDeckPanel, BorderLayout.CENTER);
        this.add(runInfoPanel, BorderLayout.SOUTH);
        this.setVisible(true);

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
