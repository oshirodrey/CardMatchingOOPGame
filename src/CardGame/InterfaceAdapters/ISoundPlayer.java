package CardGame.InterfaceAdapters;

public interface ISoundPlayer {
    void play(String soundID) ;
    void stop();
    void setMuted(boolean muted);
}
