package CardGame.UI.CustomizedComponents;


public interface CardClickListener {
    void onCardClicked(StyleCard clickedCard);
    /* why using interface instead of passing the game screen into style card directly?
     yes, i can pass the game screen directly into the style card and it will
     still works the same, yet that is a bad practice since
     the sc can accidentally (like edit the rows and cols) reach the internals of
     the game screen(called a tight dependency). also this will help mocktest easier( let a different class
     implement this interface).
     */
}
