// MethodsInterface.java
import java.util.Scanner;

public interface MethodsInterface {
    // Core UI/UX utilities
    void clearScreen();
    void typeWriter(String text, int delay);
    void showStoryWithSkip(String heroName, String[] storyLines, int delay, Object hero);

    // Game mode entry points
    void start(Scanner sc);
    void playerVsPlayerMenu(Scanner sc);
    void playerVsAiMenu(Scanner sc);
    void arcadeModeMenu(Scanner sc);
    void startArcadeRun();

    // Game state accessors
    boolean isArcadeModeBeat();
    int getMatchWins();
    int getTotalArcadeWins();

    // Default sound implementation (no need to override unless customizing)
    default void playSound(String filename) {
        try {
            java.io.File file = new java.io.File(filename);
            if (!file.exists()) {
                System.out.println("\t\t\t\tSound file not found: " + filename);
                return;
            }

            javax.sound.sampled.AudioInputStream audioStream =
                javax.sound.sampled.AudioSystem.getAudioInputStream(file);
            javax.sound.sampled.Clip clip =
                javax.sound.sampled.AudioSystem.getClip();
            clip.open(audioStream);

            clip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();

        } catch (Exception e) {
            System.out.println("\t\t\t\tCould not play sound: " + e.getMessage());
        }
    }
}
