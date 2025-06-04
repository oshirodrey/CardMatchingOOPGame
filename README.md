# Sakura Card Matching Game

- As the name playfully suggests, this game is a pastel-themed, anime-inspired memory card game centered around matching card pairs.
- This is my final project for the OOP course and also an attempt of applying clean architecture to game development in Java Swing.
- Tags for this game: memory, puzzle, leaderboard, aesthetic.

> The style and polish were heavily inspired by the anime/manga series [Cardcaptor Sakura](https://ccsakura.fandom.com/wiki/Cardcaptor_Sakura), it is a beautifully heartwarming and visually magical series that captures the innocence of youth and the strength of kindness. If you haven’t seen it yet, give it a try!

# How to Play

- Simple:
    - Click on a card to flip it and reveal its symbol.
    - Flip another card to try to find its matching pair.
    - If they match, the cards stay revealed.
    - If they don’t, they flip back.
    - Your goal is to match all card pairs using the fewest moves and shortest time.
    - Your performance will be recorded on a leaderboard.
    - If your score ranks within the top 10, it will be saved automatically.

- Game rules:
    - Move count is the primary metric for leaderboard ranking.
    - If two players have the same move count, faster time wins.

- UI Interactions:
    - You can return to the main menu anytime using the “Back” button.
    - The game features sound effects and background music — these enhance feedback for actions like flips and clicks.
    - The “Start” button on the title screen is disabled until all card assets are fully loaded.(only 1 time when the game is started)

# Is there anything fun in the code?

> TL;DR:
> - Algorithm: Flip-check-match logic, leaderboard sorting.
> - Data Structure: 2D Grid for cards, List for score sorting.
> - Design pattern: Clean Architecture, Callback, Factory, Builder.

- The game follows the Clean Architecture approach:
    - **Application Layer** (UseCases): Contains game logic like flipping cards, checking matches, and saving scores.
    - **Domain Layer** (Entities & Services): Defines core game entities like `Card`, `GameBoard`, and `Score`. Score sorting is done using a custom comparator.
    - **Infrastructure Layer**: Stores top scores persistently using `FileScoreRepository`.
    - **Interface Adapters**: Connects UI events to logic, via controller and presenter classes.
    - **UI Layer**: Made with Java Swing, includes themed screens like title, rules, game, win, and leaderboard screens.

- Noteworthy components:
    - `StyleCard`: Custom `JButton` that visually and interactively represents each card.
    - `ImageCache`: Handles efficient preloading of all images (PNG, GIF).
    - `ScoreUtils`: Uses comparator logic to sort by moves and time.
    - `GameBoard`: Coordinates the current state of the game including flip logic and score tracking.
    - `GameController`: Uses delayed timers to wait before checking a match — giving players visual feedback before cards flip back.

- The audio system uses the `ISoundPlayer` interface with `SFXPlayer` and `BGMPlayer` implementations, capable of playing looping music and responsive sound effects.

# Play & Build Instructions

> Prerequisites:
> - JDK 17+
> - Java-compatible IDE (IntelliJ, Eclipse, etc.)

1. Clone the repository.
2. Make sure resources in `/src/resources` (Cards, Game, Sound, Fonts) are in place.
3. Run the `Main` class inside `CardGame.Main`.

> Tip: For best experience, don't move the title screen down

---

✨ Have fun flipping! 🍡🌸
