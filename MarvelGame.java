import java.util.Scanner;
public class MarvelGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MainMenu.start(sc);
        sc.close();
    }










    public static void typeWriter(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}
