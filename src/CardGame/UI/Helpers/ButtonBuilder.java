package CardGame.UI.Helpers;

import CardGame.UI.CustomizedComponents.StyleButton;

import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonBuilder {
    private final StyleButton button;

    public ButtonBuilder(String text) {
        this.button = new StyleButton(text);
        this.button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public ButtonBuilder onClick(ActionListener listener) {
        button.addActionListener(listener);
        return this;
    }
    public ButtonBuilder text(String text) {
        button.setText(text);
        return this;
    }

    public ButtonBuilder initAndHoverColor(Color initColor, Color hoverColor) {
        button.setInitAndHoverColor(initColor, hoverColor);
        return this;
    }

    public StyleButton build() {
        return button;
    }
}

