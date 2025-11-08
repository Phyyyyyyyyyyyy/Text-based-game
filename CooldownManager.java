import java.util.HashMap;
import java.util.Map;

public class CooldownManager {
               
      final String RESET = "\u001B[0m";
      final String RED = "\u001B[31m";
      final String BG_BLACK   = "\u001B[40m";
      final String BG_RED     = "\u001B[41m";
      final String BG_GREEN   = "\u001B[42m";
      final String BG_YELLOW  = "\u001B[43m";
      final String BG_BLUE    = "\u001B[44m";
      final String BG_PURPLE  = "\u001B[45m";
      final String BG_CYAN    = "\u001B[46m";
      final String BG_WHITE   = "\u001B[47m";
    final String BLACK   = "\u001B[30m";
    final String GREEN   = "\u001B[32m";
    final String YELLOW  = "\u001B[33m";
    final String BLUE    = "\u001B[34m";
    final String PURPLE  = "\u001B[35m";
    final String CYAN    = "\u001B[36m";
    final String WHITE   = "\u001B[37m";
    final String BRIGHT_GREEN = "\u001B[92m";
    final String BRIGHT_YELLOW = "\u001B[93m";
    final String BRIGHT_RED = "\u001B[91m";
    final String BRIGHT_BLUE = "\u001B[94m";   
    private Map<Integer, Integer> cooldowns = new HashMap<>();
    private Map<Integer, Integer> cooldownDuration = new HashMap<>();

    public CooldownManager() {
        cooldowns.put(1, 0);
        cooldowns.put(2, 0);
        cooldowns.put(3, 0);

        cooldownDuration.put(1, 2); // Skill 1 = 2 turns cooldown
        cooldownDuration.put(2, 2); // Skill 2 = 2 turns cooldown
        cooldownDuration.put(3, 3); // Skill 3 = 3 turns cooldown
    }

    public boolean canUseSkill(int skillNumber) {
        return cooldowns.get(skillNumber) == 0;
    }

    public void applyCooldown(int skillNumber) {
        cooldowns.put(skillNumber, cooldownDuration.get(skillNumber));
    }

    public void reduceCooldowns() {
        for (int skill : cooldowns.keySet()) {
            if (cooldowns.get(skill) > 0) {
                cooldowns.put(skill, cooldowns.get(skill) - 1);
            }
        }
    }

    public int getCooldown(int skillNumber) {
        return cooldowns.get(skillNumber);
    }

    public String getFormattedCooldown(int skillNumber) {
        return cooldowns.get(skillNumber) == 0 ? BRIGHT_GREEN + "READY" + RESET : cooldowns.get(skillNumber) + BRIGHT_RED + " turn(s)" + RESET;
    }
}

                   
      //final String RESET = "\u001B[0m";
      //final String RED = "\u001B[31m";
     // final String BG_BLACK   = "\u001B[40m";
     // final String BG_RED     = "\u001B[41m";
      //final String BG_GREEN   = "\u001B[42m";
      //final String BG_YELLOW  = "\u001B[43m";
     // final String BG_BLUE    = "\u001B[44m";
     // final String BG_PURPLE  = "\u001B[45m";
    //  final String BG_CYAN    = "\u001B[46m";
     // final String BG_WHITE   = "\u001B[47m";
