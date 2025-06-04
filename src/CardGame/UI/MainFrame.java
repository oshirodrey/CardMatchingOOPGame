package CardGame.UI;

import CardGame.UI.CustomizedComponents.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {


    private final ImageIcon gameIcon = new ImageIcon(getClass().getResource("/Game/gameIcon.png"));
    private final boolean centered;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Image image = toolkit.getImage(getClass().getResource("/UI/cursor.png"));
    private final Cursor customCursor = toolkit.createCustomCursor(image, new Point(0, 0), "Custom Cursor");
    private Screen currentScreen;

    public MainFrame(Dimension dimension) {
        this.setSize(dimension);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width/6, 0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.centered = false;
        this.setCursor(customCursor);
        this.setIconImage(gameIcon.getImage());

    }

    public void replaceCurrentScreenWith(Screen anotherScreen) {
        this.getCurrentScreen().onExit(); // stop music etc.
        this.getContentPane().invalidate();
        this.getContentPane().removeAll();

        this.getContentPane().add(anotherScreen);
        this.getContentPane().revalidate();
        anotherScreen.setVisible(true);

        anotherScreen.requestFocus();

        this.setCurrentScreen(anotherScreen);
        anotherScreen.setParentFrame(this);
        anotherScreen.init();
    }

    public Screen getCurrentScreen() {
        return this.currentScreen;
    }

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public void exit() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}


