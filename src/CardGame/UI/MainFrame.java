package CardGame.UI;

import CardGame.UI.CustomizedComponents.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {


        private Screen currentScreen;

        private boolean centered;

        private Toolkit toolkit = Toolkit.getDefaultToolkit();
        private Image image = toolkit.getImage(getClass().getResource("/UI/cursor.png"));
        private Cursor customCursor = toolkit.createCustomCursor(image, new Point(0, 0), "Custom Cursor");

        public MainFrame(Dimension dimension) {
            this.setSize(dimension);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setResizable(false);
            this.centered = false;
            this.setCursor(customCursor);

        }

        public void replaceCurrentScreenWith(Screen anotherScreen) {
            Screen currentOngoingScreen = getCurrentScreen();
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
        return this.currentScreen;}
        public void setCurrentScreen(Screen currentScreen) {
            this.currentScreen = currentScreen;
        }
        public void exit() {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

    }


