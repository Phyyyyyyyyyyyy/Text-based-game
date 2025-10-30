import java.util.Scanner;

public class Player1 {
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
           try{

        System.out.println(" __  __                      _    ____ _           _     _ ");
        System.out.println("|  \\/  | __ _ _ ____   _____| |  / ___| | __ _ ___| |__ | |");
        System.out.println("| |\\/| |/ _` | '__\\ \\ / / _ \\ | | |   | |/ _` / __| '_ \\| |");
        System.out.println("| |  | | (_| | |   \\ V /  __/ | | |___| | (_| \\__ \\ | | |_|");
        System.out.println("|_|  |_|\\__,_|_|    \\_/ \\___|_|  \\____|_|\\__,_|___/_| |_(_)");
        System.out.println();
        System.out.println();
              
            System.out.println("\t=========================================");
            System.out.println("\t====    MARVEL CLASH! TURN BASED     ====");
            System.out.println("\t====    Player 1 Choose Hero         ====");
            System.out.println("\t=========================================");
            System.out.println("\t|   Choose your hero:                   |");
            System.out.println("\t|     1. Iron Man                       |");
            System.out.println("\t|     2. Captain America                |");
            System.out.println("\t|     3. Thor                           |");
            System.out.println("\t|     4. Spider-Man                     |");
            System.out.println("\t|     5. Hulk                           |");
            System.out.println("\t|     6. Black Widow                    |");
            System.out.println("\t|     7. Ant-Man                        |");
            System.out.println("\t|     8. The Falcon                     |");
            System.out.println("\t|     9. Back                           |");
            System.out.print("\t > ");
             
                choice = sc.nextInt();
                clearScreen();

                switch (choice) {
                    case 1:
                        player = new Character("Iron Man", 110, 110, 20,
                                "Repulsor Blast - Deals 30 damage",
                                "Unibeam - Deals 20 damage",
                                "Rocket Barrage - Deals 40 damage",
                                30, 20, 40, 30, 20, 40, 100);
                        showStoryWithSkip("Iron Man", new String[]{
                                 "\nIron Man: Genius billionaire Tony Stark built his armored suit after a near-death experience.",
                            "He uses advanced technology to protect the world as Iron Man.",
                            "Despite his arrogance, his heart pushes him to fight for others.\n"
                        }, 40, player);
                        break;
                    case 2:
                        player = new Character("Captain America", 120, 120, 12,
                                "Shield Bash! - Deals 12 damage",
                                "Shield Throw! - Deals 25 damage",
                                "Inspire - Deals 30 damage",
                                12, 25, 30, 12, 25, 30, 100);
                        showStoryWithSkip("Captain America", new String[]{
                                "\nSteve Rogers - the super soldier from WWII.",
                                "Armed with his vibranium shield, he defends freedom and justice.\n"
                        }, 40, player);
                        break;
                    case 3:
                        player = new Character("Thor", 130, 130, 18,
                                "Lightning Blast! - Deals 30 damage",
                                "Mjolnir throw! - Deals 20 damage",
                                "God of Thunder - Deals 40 damage",
                                30, 20, 40, 30, 20, 40, 100);
                        showStoryWithSkip("Thor", new String[]{
                                "\nThor: The God of Thunder wields Mjolnir to protect the Nine Realms.",
                                "He commands storms and possesses incredible strength.\n"
                        }, 40, player);
                        break;
                    case 4:
                        player = new Character("Spider-Man", 120, 120, 14,
                                "Web Shot! - Deals 15 damage",
                                "Web Swing Strike! - Deals 25 damage",
                                "Spider Combo! - Deals 30 damage",
                                15, 25, 30, 15, 25, 30, 100);
                        showStoryWithSkip("Spider-Man", new String[]{
                                "\nPeter Parker: Bitten by a radioactive spider, he gained amazing powers.",
                                "Lives by 'with great power comes great responsibility.'\n"
                        }, 40, player);
                        break;
                    case 5:
                        player = new Character("Hulk", 150, 150, 20,
                                "Hulk Smash! - Deals 30 damage",
                                "Thunderclap - Deals 25 damage",
                                "Hulk Rage - Deals 25 damage",
                                30, 25, 20, 30, 25, 0, 100);
                        showStoryWithSkip("Hulk", new String[]{
                                "\nDr. Bruce Banner transforms into the Hulk when angered.",
                                "His unstoppable strength makes him both feared and admired.\n"
                        }, 40, player);
                        break;
                    case 6:
                        player = new Character("Black Widow", 100, 100, 10,
                                "Widow's Bite! - Deals 15 damage",
                                "Widow's Kick! - Deals 20 damage",
                                "Espionage - Deals 30 damage",
                                15, 20, 30, 15, 20, 30, 100);
                        showStoryWithSkip("Black Widow", new String[]{
                                "\nNatasha Romanoff: Trained as a deadly assassin.",
                                "Now an Avenger, agile and cunning.\n"
                        }, 40, player);
                        break;
                    case 7:
                        player = new Character("Ant-Man", 100, 100, 20,
                                "Pym Particle Punch! - Deals 20 damage",
                                "Disc Launcher - Deals 20 damage",
                                "Giant-Man - Deals 30 damage",
                                20, 20, 30, 20, 20, 30, 100);
                        showStoryWithSkip("Ant-Man", new String[]{
                                "\nScott Lang uses Hank Pyms shrinking technology.",
                                "A thief turned hero protecting those in need.\n"
                        }, 40, player);
                        break;
                    case 8:
                        player = new Character("The Falcon", 150, 150, 10,
                                "Wing Blast - deals 15 damage",
                                "Redwing Strike! - deals 20 damage",
                                "Tactical Barrage - deals 30 damage",
                                15, 20, 30, 15, 20, 30, 100);
                        showStoryWithSkip("The Falcon", new String[]{
                                "\nSam Wilson uses advanced wing technology to soar the skies.",
                                "A loyal soldier and hero with unmatched speed.\n"
                        }, 40, player);
                        break;
                    case 9:
                         MainMenu.start(sc);
                        return null;
                    case 69:
                        player = new Character("Jan Clark", 150, 150, 20,
                                "Lisora aning OOP uy! - deals 20 damage",
                                "Eternal Drip! - deals 30 damage",
                                "Lisora aning DSA uy! - deals 40 damage",
                                20, 30, 40, 20, 30, 40, 100);
                        showStoryWithSkip("Jan Clark", new String[]{
                                "\nJan Clark: Known for unstoppable drip and endless energy in class.",
                                "Turns tough coding battles into a stage for style.\n"
                        }, 40, player);
                        break;
                    case 70:
                        player = new Character("John Micoh", 150, 150, 20,
                                "CIT lang ya! - deals 20 damage",
                                "Lahus ni ug Cambuntan ya? - deals 20 damage",
                                "Kapoyag tuon oy! - deals 40 damage",
                                20, 20, 40, 20, 20, 40, 100);
                        showStoryWithSkip("John Micoh", new String[]{
                                "\nJohn Micoh: Laid-back warrior balancing jokes and determination.\n"
                        }, 40, player);
                        break;
                    case 8700:
                        player = new Character("Ethan Manto", 150, 150, 20,
                                "Hollaback Girl! - deals 20 damage",
                                "Soulja Boy Superman! - deals 30 damage",
                                "Bye Bye Bye! - deals 35 damage",
                                20, 30, 35, 20, 30, 35, 100);
                        showStoryWithSkip("Ethan Manto", new String[]{
                                "\nEthan Manto: A warrior fueled by rhythm and style.",
                                "Turns every battle into a stage with iconic moves.\n"
                        }, 40, player);
                        break;
                    case 7355608:
                        player = new Character("Thanos", 500, 500, 200,
                                "Power Stone Blast - Deals 200 damage",
                                "Power Stone Punch! - Deals 100 damage", 
                                "Snap - Eliminates enemy in an instant",
                                30, 30, 50, 200, 100, 999, 100);
                        showStoryWithSkip("Thanos", new String[]{
                                "\nThanos: The Mad Titan seeking universal balance.",
                                "Armed with Infinity Stones, bends reality with a flick of his hand.\n"
                        }, 40, player);
                        break;
                    default:
                        System.out.println("Invalid choice! Please select a valid hero number.\n");
                        break;
          
                }
        } catch (Exception e) {
                clearScreen();
                System.out.println("Unexpected exception caught: " +e);
                sc.next();
        } 
}
           return player;
}


    public static void showStoryWithSkip(String heroName, String[] storyLines, int delay, Character hero) {
        try{
        sc.nextLine(); 

        String input;
        boolean validInput=false;

         while(!validInput){
                 System.out.print("Press ENTER to view " + heroName + "'s story, or type '0' to skip: ");
                 input = sc.nextLine().trim();

         if (input.isEmpty()) {
                for(String line:storyLines){
                    MarvelGame.typeWriter(line, delay);
                }
                validInput=true;
         }
         else if(input.equalsIgnoreCase("0")){

                System.out.println("\nYou chose to skip " + heroName + "'s story.\n");
                validInput=true;
         } else {
                System.out.println("Invalid input. Please try again.");
         }
        }
         System.out.println("--- " + heroName + "s Stats ---");
             hero.displayIntro();


        } catch (Exception e) {
            System.out.println("Unexpected exception caught: " +e);
            sc.next();

              System.out.println("--- " + heroName + "s Stats ---");
             hero.displayIntro();
        }

    }
        
}
