import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public class SelectScreen {

    static Scanner sc = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Character select() {
        Character player = null;
        int choice;
        clearScreen();
        while (player == null) {
            try {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.err.println("\n\n\n\n\n\n\n");
                System.out.println("\t\t\t\t\t\t\t=========================================");
                System.out.println("\t\t\t\t\t\t\t====             PVSAI               ====");
                System.out.println("\t\t\t\t\t\t\t=========================================");
                System.out.println("\t\t\t\t\t\t\t|   Choose your hero:                   |");
                System.out.println("\t\t\t\t\t\t\t|     1. Iron Man                       |");
                System.out.println("\t\t\t\t\t\t\t|     2. Captain America                |");
                System.out.println("\t\t\t\t\t\t\t|     3. Thor                           |");
                System.out.println("\t\t\t\t\t\t\t|     4. Spider-Man                     |");
                System.out.println("\t\t\t\t\t\t\t|     5. Hulk                           |");
                System.out.println("\t\t\t\t\t\t\t|     6. Black Widow                    |");
                System.out.println("\t\t\t\t\t\t\t|     7. Ant-Man                        |");
                System.out.println("\t\t\t\t\t\t\t|     8. The Falcon                     |");
                System.out.println("\t\t\t\t\t\t\t|     9. Back                           |");
                System.out.print("\t\t\t\t\t\t\t > ");

                choice = sc.nextInt();
                clearScreen();

                switch (choice) {
                    case 1:
                        playSound("InputSound.wav");
                        player = new Character("Iron Man", 110, 110, 15,
                                "Repulsor Blast - Deals 13 damage",
                                "Unibeam Strike - Deals 15 damage",
                                "Rocket Barrage - Deals 22 damage",
                                13, 15, 22, 13, 15, 22, 100);
                        showStoryWithSkip("Iron Man", new String[]{
                            "\n\t\t\t\t\t\t\tIron Man: Genius billionaire Tony Stark built his armored suit after a near-death experience.",
                            "\t\t\t\t\t\t\tHe uses advanced technology to protect the world as Iron Man.",
                            "\t\t\t\t\t\t\tDespite his arrogance, his heart pushes him to fight for others.\n"
                        }, 40, player);
                        break;
                    case 2:
                        playSound("InputSound.wav");
                        player = new Character("Captain America", 110, 110, 15,
                                "Shield Throw - Deals 12 damage",
                                "Shield Bash - Deals 14 damage",
                                "Shield Combo - Deals 24 damage",
                                12, 14, 24, 12, 14, 24, 100);
                        showStoryWithSkip("Captain America", new String[]{
                            "\n\t\t\t\t\t\t\tSteve Rogers - the super soldier from WWII.",
                            "\t\t\t\t\t\t\tArmed with his vibranium shield, he defends freedom and justice.\n"
                        }, 40, player);
                        break;
                    case 3:
                        playSound("InputSound.wav");
                        player = new Character("Thor", 110, 110, 15,
                                "Lightning Strike - Deals 11 damage",
                                "Mjolnir Impact - Deals 13 damage",
                                "Thunder Crash - Deals 26 damage",
                                11, 13, 26, 11, 13, 26, 100);
                        showStoryWithSkip("Thor", new String[]{
                            "\n\t\t\t\t\t\t\tThor: The God of Thunder wields Mjolnir to protect the Nine Realms.",
                            "\t\t\t\t\t\t\tHe commands storms and possesses incredible strength.\n"
                        }, 40, player);
                        break;
                    case 4:
                        playSound("InputSound.wav");
                        player = new Character("Spider-Man", 110, 110, 15,
                                "Web Strike - Deals 12 damage",
                                "Venom Blast - Deals 18 damage",
                                "Spider Fury - Deals 20 damage",
                                12, 18, 20, 12, 18, 20, 100);
                        showStoryWithSkip("Spider-Man", new String[]{
                            "\n\t\t\t\t\t\t\tPeter Parker: Bitten by a radioactive spider, he gained amazing powers.",
                            "\t\t\t\t\t\t\tLives by 'with great power comes great responsibility.'\n"
                        }, 40, player);
                        break;
                    case 5:
                        playSound("InputSound.wav");
                        player = new Character("Hulk", 110, 110, 15,
                                "Hulk Smash - Deals 14 damage",
                                "Thunder Clap - Deals 15 damage",
                                "Gamma Crush - Deals 21 damage",
                                14, 15, 21, 14, 15, 21, 100);
                        showStoryWithSkip("Hulk", new String[]{
                            "\n\t\t\t\t\t\t\tDr. Bruce Banner transforms into the Hulk when angered.",
                            "\t\t\t\t\t\t\tHis unstoppable strength makes him both feared and admired.\n"
                        }, 40, player);
                        break;
                    case 6:
                        playSound("InputSound.wav");
                        player = new Character("Black Widow", 100, 100, 15,
                                "Widow's Bite - Deals 16 damage",
                                "Combat Kick - Deals 18 damage",
                                "Assassin Strike - Deals 26 damage",
                                16, 18, 26, 16, 18, 26, 100);
                        showStoryWithSkip("Black Widow", new String[]{
                            "\n\t\t\t\t\t\t\tNatasha Romanoff: Trained as a deadly assassin.",
                            "\t\t\t\t\t\t\tNow an Avenger, agile and cunning.\n"
                        }, 40, player);
                        break;
                    case 7:
                        playSound("InputSound.wav");
                        player = new Character("Ant-Man", 100, 100, 15,
                                "Pym Particle Blast - Deals 18 damage",
                                "Quantum Strike - Deals 20 damage",
                                "Giant Slam - Deals 22 damage",
                                18, 20, 22, 18, 20, 22, 100);
                        showStoryWithSkip("Ant-Man", new String[]{
                            "\n\t\t\t\t\t\t\tScott Lang uses Hank Pyms shrinking technology.",
                            "\t\t\t\t\t\t\tA thief turned hero protecting those in need.\n"
                        }, 40, player);
                        break;
                    case 8:
                        playSound("InputSound.wav");
                        player = new Character("The Falcon", 100, 100, 15,
                                "Aerial Strike - Deals 14 damage",
                                "Redwing Assault - Deals 22 damage",
                                "Tactical Barrage - Deals 24 damage",
                                14, 22, 24, 14, 22, 24, 100);
                        showStoryWithSkip("The Falcon", new String[]{
                            "\n\t\t\t\t\t\t\tSam Wilson uses advanced wing technology to soar the skies.",
                            "\t\t\t\t\t\t\tA loyal soldier and hero with unmatched speed.\n"
                        }, 40, player);
                        break;
                    case 9:
                        playSound("InputSound.wav");
                         MainMenu menu= new MainMenu(sc);
                        menu.start(sc);
                        return null;
                    case 69:
                        playSound("InputSound.wav");
                        player = new Character("Jan Clark", 120, 120, 15,
                                "Code Crash - Deals 10 damage",
                                "Debug Strike - Deals 12 damage",
                                "System Overload - Deals 18 damage",
                                10, 12, 18, 10, 12, 18, 100);
                        showStoryWithSkip("Jan Clark", new String[]{
                            "\n\t\t\t\t\t\t\tJan Clark: Known for unstoppable drip and endless energy in class.",
                            "\t\t\t\t\t\t\tTurns tough coding battles into a stage for style.\n"
                        }, 40, player);
                        break;
                    case 70:
                        playSound("InputSound.wav");
                        player = new Character("John Micoh", 120, 120, 15,
                                "CIT Crash - Deals 8 damage",
                                "Bug Blast - Deals 12 damage",
                                "Code Fury - Deals 20 damage",
                                8, 12, 20, 8, 12, 20, 100);
                        showStoryWithSkip("John Micoh", new String[]{
                            "\n\t\t\t\t\t\t\tJohn Micoh: Laid-back warrior balancing jokes and determination.\n"
                        }, 40, player);
                        break;
                    case 72:
                        playSound("InputSound.wav");
                        player = new Character("Reuben Navarrete", 120, 120, 15,
                                "HE Grenade! - Deals 10 damage",
                                "Binary Tree Confusion! - Deals 10 damage",
                                "Tik-Tok of Doom(scroll) - Deals 20 damage",
                                10, 10, 20, 10, 10, 20, 100);
                        showStoryWithSkip("Reuben Navarrete", new String[]{
                            "\n\t\t\t\t\t\t\tReuben Navarrete: A modern warrior who codes by day and scrolls by night.",
                            "\t\t\t\t\t\t\tHe analyzes enemies like binary trees - searching for their weakest nodes with precision.",
                            "\t\t\t\t\t\t\tHis TikTok-scrolling fingers move at lightning speed, predicting patterns before they form.",
                            "\t\t\t\t\t\t\tWhen he's not optimizing algorithms, he's optimizing combat strategies with viral efficiency.\n"
                        }, 40, player);
                        break;
                    case 71:
                        playSound("InputSound.wav");
                        player = new Character("Ethan Manto", 120, 120, 15,
                                "Rhythm Strike - Deals 12 damage",
                                "Dance Blast - Deals 13 damage",
                                "Beat Drop - Deals 15 damage",
                                12, 13, 15, 12, 13, 15, 100);
                        showStoryWithSkip("Ethan Manto", new String[]{
                            "\n\t\t\t\t\t\t\tEthan Manto: A warrior fueled by rhythm and style.",
                            "\t\t\t\t\t\t\tTurns every battle into a stage with iconic moves.\n"
                        }, 40, player);
                        break;
                    case 7355608:
                        playSound("InputSound.wav");
                        player = new Character("Thanos", 500, 500, 200,
                                "Power Stone Blast - Deals 200 damage",
                                "Infinity Snap - Deals 999 damage",
                                "Titan Punch - Deals 100 damage",
                                30, 50, 30, 200, 999, 100, 100);
                        showStoryWithSkip("Thanos", new String[]{
                            "\n\t\t\t\t\t\t\tThanos: The Mad Titan seeking universal balance.",
                            "\t\t\t\t\t\t\tArmed with Infinity Stones, bends reality with a flick of his hand.\n"
                        }, 40, player);
                        break;
                    default:
                        System.out.println("\t\t\t\t\t\t\tInvalid choice! Please select a valid hero number.\n");
                        break;
                }
            } catch (Exception e) {
                clearScreen();
                System.out.println("\t\t\t\t\t\t\tUnexpected exception caught: " + e);
                sc.next();
            }
        }
        return player;
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

    public static void showStoryWithSkip(String heroName, String[] storyLines, int delay, Character hero) {
        try {
            sc.nextLine();

            String input = "";
            boolean validInput = false;

            while (!validInput) {
                System.out.println("\n\n\n\n\n\n\n");
                System.out.print("\t\t\t\t\t\t\tPress ENTER to view " + heroName + "'s story, or type '0' to skip: ");
                input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    for (String line : storyLines) {
                        typeWriter(line, delay);
                    }
                    validInput = true;
                } else if (input.equalsIgnoreCase("0")) {
                    System.out.println("\n\t\t\t\t\t\t\tYou chose to skip " + heroName + "'s story.\n");
                    validInput = true;
                } else {
                    System.out.println("\t\t\t\t\t\t\tInvalid input. Please try again.");
                }
            }

        } catch (Exception e) {
            System.out.println("\t\t\t\t\t\t\tUnexpected exception caught: " + e);
            sc.next();

            System.out.println("\t\t\t\t\t\t\t--- " + heroName + "'s Stats ---");
            hero.displayIntro();
        }
    }

     public static void playSound(String filename) {
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
