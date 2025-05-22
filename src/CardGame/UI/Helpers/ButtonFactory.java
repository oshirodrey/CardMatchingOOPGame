package CardGame.UI.Helpers;

import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.GameRuleScreen;
import CardGame.UI.GameScreen;
import CardGame.UI.LeaderBoardScreen;
import CardGame.UI.TitleScreen;

import java.awt.*;

public class ButtonFactory {
    public static StyleButton createBackButton(String text, Screen screen) {
        StyleButton button = new StyleButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> screen.getParentFrame().replaceCurrentScreenWith(new TitleScreen(screen.getParentFrame())));
        return button;
    }
    public static StyleButton createStartGameButton(String text, Screen screen) {
        StyleButton button = new StyleButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> screen.getParentFrame().replaceCurrentScreenWith(new GameScreen()));
        return button;
    }
    public static StyleButton createExitButton(String text, Screen screen) {
        StyleButton button = new StyleButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> System.exit(0));
        return button;
    }
    public static StyleButton createRuleButton(String text, Screen screen) {
        StyleButton button = new StyleButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> screen.getParentFrame().replaceCurrentScreenWith(new GameRuleScreen(screen.getParentFrame())));
        return button;
    }
    public static StyleButton createLeaderBoardButton(String text, Screen screen) {
        StyleButton button = new StyleButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e-> screen.getParentFrame().replaceCurrentScreenWith(new LeaderBoardScreen()));
        return button;
    }

}
