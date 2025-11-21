public interface MethodsInterface {
    void clearScreen();
    void showStoryWithSkip(String heroName, String[] storyLines, int delay, Object hero);
    Character select();
    void typeWriter(String text, int delay);
    void start(java.util.Scanner sc);
    void playerVsPlayerMenu(java.util.Scanner sc);
    void playerVsAiMenu(java.util.Scanner sc);
   
}
