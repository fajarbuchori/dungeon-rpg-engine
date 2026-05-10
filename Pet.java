public class Pet {
    private String spesies;
    private int level = 1;
    private int powerBase = 20;

    public Pet(String spesies) {
        this.spesies = spesies;
    }

    public void tambahExp(int jumlah) {
        this.level++; // Langsung naik level setiap ganti lantai sesuai permintaan
        this.powerBase += 15;
        System.out.println("\u001B[35m🐾 PET EVOLUTION! " + spesies + " naik ke Lv." + level + "!\u001B[0m");
    }

    public void gunakanSkill(Entity owner, Entity enemy) {
        System.out.print("\u001B[35m🐾 [" + spesies + " Lv." + level + "] \u001B[0m");
        int effect = powerBase + (level * 5);
        
        switch (spesies) {
            case "Naga" -> { System.out.println("Nafas Api! -" + effect + " HP Musuh."); enemy.terimaDamage(effect); }
            case "Fenrir" -> { System.out.println("Gigitan Es! -" + effect + " HP Musuh."); enemy.terimaDamage(effect); }
            case "Pegasus" -> { System.out.println("Cahaya Suci! +" + effect + " HP Kamu."); owner.heal(effect); }
            case "Sphinx" -> { System.out.println("Teka-teki! Musuh bingung -" + effect + " HP."); enemy.terimaDamage(effect); }
            case "Hydra" -> { System.out.println("Semburan Racun! -" + (effect+10) + " HP Musuh."); enemy.terimaDamage(effect+10); }
        }
    }
    public String getSpesies() { return spesies; }
    public int getLevel() { return level; }
}