import java.util.Scanner;

public class MarvelGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MainMenu menu= new MainMenu(sc);
        menu.start(sc);
        sc.close();
    }
}
