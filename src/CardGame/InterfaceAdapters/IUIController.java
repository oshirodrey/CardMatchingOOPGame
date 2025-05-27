package CardGame.InterfaceAdapters;

import CardGame.Domain.Entities.Score;

public interface IUIController {
    void updateCardIcons();
    void updateMoveAndTime(int moveCount, double time);
    void showWinScreen(Score score);
}
