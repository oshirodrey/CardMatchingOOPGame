package CardGame.UI.Helpers;

import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.GameRuleScreen;
import CardGame.UI.GameScreen;
import CardGame.UI.LeaderBoardScreen;
import CardGame.UI.TitleScreen;

public class ButtonFactory {
    public static ButtonBuilder createBackButton(Screen screen) {
        return new ButtonBuilder(" BACK TO MAIN MENU ")
                .onClick(e -> screen.getParentFrame().replaceCurrentScreenWith(new TitleScreen(screen.getParentFrame())));
    }

    public static ButtonBuilder createStartGameButton(Screen screen) {
        return new ButtonBuilder(" START GAME ")
                .onClick(e -> screen.getParentFrame().replaceCurrentScreenWith(new GameScreen()));
    }

    public static ButtonBuilder createCustomStartGameButton(Screen screen,String text) {
        return new ButtonBuilder(text)
                .onClick(e -> screen.getParentFrame().replaceCurrentScreenWith(new GameScreen()));
    }

    public static ButtonBuilder createExitButton() {
        return new ButtonBuilder(" EXIT ")
                .onClick(e -> System.exit(0));
    }

    public static ButtonBuilder createRuleButton(Screen screen) {
        return new ButtonBuilder(" RULES ")
                .onClick(e -> screen.getParentFrame().replaceCurrentScreenWith(new GameRuleScreen(screen.getParentFrame())));
    }

    public static ButtonBuilder createLeaderBoardButton(Screen screen) {
        return new ButtonBuilder(" LEADERBOARD ")
                .onClick(e -> screen.getParentFrame().replaceCurrentScreenWith(new LeaderBoardScreen()));
    }
}

