# Sakura Card Matching Game

- As the name playfully suggests, this game is a pastel-themed, anime-inspired memory card game centered around matching card pairs.
- This is my final project for the OOP course and also an attempt of applying clean architecture to game development in Java Swing.
- Tags for this game: memory, puzzle, leaderboard, aesthetic.
# Project Inspiration and Architecture
> - The style and polish were heavily inspired by the anime/manga series [Cardcaptor Sakura](https://ccsakura.fandom.com/wiki/Cardcaptor_Sakura), it is a beautifully heartwarming and visually magical series that captures the innocence of youth and the strength of kindness. If you haven’t seen it yet, give it a try!
> - For the coding and organizational style of this project, I took inspiration from the [maze-runner](https://github.com/swyrin/maze-runner) repository by the respected developer [swyrin](https://github.com/swyrin). Swyrin has been incredibly helpful as a mentor, and he encouraged me to adopt [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) for this project. His guidance served as a strong reference point throughout the development process.

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
> - Data Structure: 2D Grid for cards, List for score sorting, HashMap for resource caching.
> - Design pattern: Clean Architecture, Callback, Factory, Builder.

- The game follows the [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) approach:
    - **Domain Layer** (Layer 0) (Entities & Services): Defines core game entities like `Card`, `GameBoard`, and `Score`. Score sorting is done using a custom comparator.
    - **Application Layer** (Layer 1) (UseCases): Contains game logic like flipping cards, checking matches, and saving scores.
    - **Interface Adapters** (Layer 2): Connects UI events to logic, via controller and presenter classes.
    - **Infrastructure Layer** (Layer 3): Stores top scores persistently using `FileScoreRepository`.
    - **UI Layer** (Layer 3): Made with Java Swing, includes themed screens like title, rules, game, win, and leaderboard screens.

- Noteworthy components:
    - `StyleCard`: Custom `JButton` that visually and interactively represents each card.  
      - Implements *Callback pattern* via `CardClickListener` interface — it doesn't handle clicks itself, but notifies a listener.
    - `GameScreen`: Implements `CardClickListener` — receives card click events and delegates to `GameController`.
      - Also part of the *Callback pattern* (responds to events without direct coupling).    
    - `ImageCache`: Handles efficient preloading of all images (PNG, GIF).
    - `ScoreUtils`: Uses comparator logic to sort by moves and time.
    - `GameBoard`: Coordinates the current state of the game including flip logic and score tracking.
    - `GameController`: Uses delayed timers to wait before checking a match — giving players visual feedback before cards flip back.

- UI buttons are built using the *Builder pattern*:
  - `ButtonBuilder` provides a fluent API for customization.
  - Used by `ButtonFactory` to create `Start`, `Back`, `Exit`, etc.

- Card creation is handled by the *Factory pattern*:
  - `CardFactory.createShuffledCardPairs()` shuffles and positions cards for the game grid.
  - `ButtonFactory` also serves as a factory for predefined UI buttons.

- The audio system uses the `ISoundPlayer` interface (*Abstraction*):
  - `SFXPlayer` and `BGMPlayer` implement this for sound effects and looping music.
# Play & Build Instructions

> Prerequisites:
> - JDK 23+
> - Make sure you didn't change anything in the game folder

- There are uhm...3 ways to run the game:
1. Through Main.java(won't be there in the release)
2. Through the .exe file (Recommended)
3. Through .jar file (I used openJDK 23) (God this is so painful to figure out the way TvT)

> Tip: For best experience, don't move the title screen down! (The move counter and timer will be left out of the screen)

---

✨ Have fun flipping! 🍡🌸
