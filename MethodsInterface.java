
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public interface MethodsInterface {

    void clearScreen();

    void showStoryWithSkip(String heroName, String[] storyLines, int delay, Object hero);

    Character select();

    void typeWriter(String text, int delay);

    void start(java.util.Scanner sc);

    void playerVsPlayerMenu(java.util.Scanner sc);

    void playerVsAiMenu(java.util.Scanner sc);

    default void playSound(String filename){
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("\t\t\t\tSound file not found: " + filename);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

           
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();

        } catch (Exception e) {
            System.out.println("\t\t\t\tCould not play sound: " + e.getMessage());
        }

    }
}
