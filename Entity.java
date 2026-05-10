import java.util.*;

public abstract class Entity {
    protected String nama, role;
    protected int hp, maxHp, attack, defense, level, floor = 1;
    protected List<String> skillList = new ArrayList<>();
    protected Pet petAktif;
    protected Random rand = new Random();

    // Palette Warna ANSI untuk Terminal
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public Entity(String nama, int hp, int attack, int defense, String role) {
        this.nama = nama;
        this.maxHp = hp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.role = role;
        
        // Skill dasar yang dimiliki semua class sejak awal
        this.skillList.add("Serang Biasa");
    }

    // --- KONTRAK SKILL ---
    // Method ini yang akan diisi oleh Mage.java, Combat.java, dll.
    public abstract void pakaiSkill(int pilihan, Entity lawan);

    // --- LOGIKA PERTANDINGAN ---
    public void serang(Entity lawan) {
        // Damage acak antara 50% - 100% dari total attack
        int dmg = rand.nextInt(attack / 2) + (attack / 2);
        System.out.println(YELLOW + "⚔️  " + nama + " meluncurkan serangan fisik!" + RESET);
        lawan.terimaDamage(dmg);
    }

    public void terimaDamage(int dmg) {
        // Rumus: Damage dikurangi defense, minimal kena 5 damage agar tidak abadi
        int finalDmg = Math.max(5, dmg - this.defense);
        this.hp -= finalDmg;
        if (this.hp < 0) this.hp = 0;
        System.out.println(RED + "💥 " + nama + " menerima " + finalDmg + " damage! (HP: " + hp + ")" + RESET);
    }

    // --- SISTEM PEMULIHAN ---
    public void heal(int amt) {
        this.hp = Math.min(maxHp, hp + amt);
        System.out.println(GREEN + "💖 " + nama + " memulihkan " + amt + " HP!" + RESET);
    }

    public void fullHeal() {
        this.hp = maxHp;
    }

    public void revive() {
        this.hp = this.maxHp;
        System.out.println(CYAN + "😇 " + nama + " bangkit dari kematian! Darah penuh kembali." + RESET);
    }

    // --- UPGRADE STATS (DARI GACHA REWARD) ---
    public void addAttack(int bonus) { this.attack += bonus; }
    public void addDefense(int bonus) { this.defense += bonus; }
    public void addMaxHp(int bonus) { 
        this.maxHp += bonus; 
        this.hp = maxHp; // Otomatis sembuh saat nambah Max HP
    }

    // --- GETTERS & SETTERS ---
    public String getNama() { return nama; }
    public String getRole() { return role; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }   // Penting untuk GameEngine
    public int getDefense() { return defense; } // Penting untuk GameEngine
    public int getFloor() { return floor; }
    public void naikLantai() { this.floor++; }
    public List<String> getSkillList() { return skillList; }
    public boolean isAlive() { return hp > 0; }
    
    // Sistem Pet
    public void pasangPet(Pet p) { this.petAktif = p; }
    public Pet getPet() { return petAktif; }
}