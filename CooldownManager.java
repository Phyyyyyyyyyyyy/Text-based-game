import java.util.HashMap;
import java.util.Map;

public class CooldownManager {
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

    public void resetCooldowns() {
        for (int skill : cooldowns.keySet()) {
            cooldowns.put(skill, 0);
        }
    }

    public int getCooldown(int skillNumber) {
        return cooldowns.get(skillNumber);
    }

    public String getFormattedCooldown(int skillNumber) {
        return cooldowns.get(skillNumber) == 0 ? "READY" : cooldowns.get(skillNumber) + " turn(s)";
    }
}
