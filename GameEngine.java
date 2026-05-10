import java.util.*;

public class GameEngine {
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();
    private Entity player;

    private String[] monsterPool = {"Shadow Slime", "Skeleton Warrior", "Dark Elf", "Dungeon Bat", "Stone Golem"};
    private String[] bossPool = {"ANCIENT DRAGON", "DEMON LORD", "ABYSSAL KNIGHT", "VOID REAPER"};

    public void start() {
        System.out.println("\u001B[33m" +
            "================================================\n" +
            "   🏰 THE TOWER OF ETERNITY: 100 FLOORS 🏰      \n" +
            "================================================\u001B[0m");
        
        System.out.print("📝 Masukkan Nama Hero: ");
        String nama = sc.nextLine();
        
        pilihClass(nama);
        pilihPet();

        System.out.println("\n\u001B[32m✅ Gerbang Dungeon Terbuka! Selamat Berjuang...\u001B[0m");
        mainMenu();
    }

    private void pilihClass(String nama) {
        System.out.println("\n\u001B[36m--- PILIH CLASS HERO ---");
        System.out.println("1. Combat | 2. Mage | 3. Assassin | 4. Healer | 5. Archer\u001B[0m");
        System.out.print("Pilihan: ");
        int c = sc.nextInt();
        switch(c) {
            case 2 -> player = new Mage(nama);
            case 3 -> player = new Assassin(nama);
            case 4 -> player = new Healer(nama);
            case 5 -> player = new Archer(nama);
            default -> player = new Combat(nama);
        }
    }

    private void pilihPet() {
        System.out.println("\n\u001B[35m--- PILIH GUARDIAN PET ---");
        String[] pets = {"Naga", "Fenrir", "Pegasus", "Sphinx", "Hydra"};
        for(int i=0; i<5; i++) System.out.println((i+1) + ". " + pets[i]);
        System.out.print("Pilihan: ");
        player.pasangPet(new Pet(pets[sc.nextInt()-1]));
    }

    private void mainMenu() {
        while (player.isAlive() && player.getFloor() <= 100) {
            System.out.println("\n\u001B[34m════════════════════════════════════════════");
            System.out.println(" 📍 LANTAI: [" + player.getFloor() + "/100] | HP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println("════════════════════════════════════════════\u001B[0m");
            System.out.println("1. ⚔️  Jelajahi Lantai (3 Monster)");
            System.out.println("2. 📜 Lihat Status");
            System.out.println("0. 🏃 Menyerah");
            System.out.print("Aksi: ");
            int menu = sc.nextInt();

            if (menu == 1) exploreFloor();
            else if (menu == 2) tampilkanStatus();
            else if (menu == 0) break;
        }
    }

    private void exploreFloor() {
        for (int i = 1; i <= 3; i++) {
            String mName = monsterPool[rand.nextInt(monsterPool.length)];
            System.out.println("\n\u001B[31m⚠️  Monster " + i + "/3: " + mName + " Muncul!\u001B[0m");
            if (!battle(mName, false)) {
                handleGameOver();
                return;
            }
            dropReward();
        }

        if (player.getFloor() % 10 == 0) {
            String bName = bossPool[rand.nextInt(bossPool.length)];
            System.out.println("\n\u001B[31m👹 BOSS FLOOR! " + bName + " MENGHADANG!\u001B[0m");
            if (!battle(bName, true)) {
                handleGameOver();
                return;
            }
            dropReward();
        }

        System.out.println("\n\u001B[32m🎉 Lantai " + player.getFloor() + " Berhasil Dibersihkan!\u001B[0m");
        player.naikLantai();
        player.getPet().tambahExp(100); 
        offerSkill(); 
    }

    private boolean battle(String mName, boolean isBoss) {
        // SCALING BALANCE: HP Monster Lantai 1 = 120 (Sangat Adil)
        int targetHp = 80 + (player.getFloor() * 40);
        int targetAtk = 15 + (player.getFloor() * 5);
        
        if (isBoss) { 
            targetHp *= 4; 
            targetAtk *= 2; 
        }

        // Buat monster dan sesuaikan HP-nya dari base 300
        Entity monster = new Combat(mName); 
        monster.addMaxHp(targetHp - 300); // Menyesuaikan agar HP monster sesuai target
        monster.addAttack(targetAtk - 60); // Menyesuaikan agar ATK monster sesuai target

        while (player.isAlive() && monster.isAlive()) {
            if (player.getPet() != null) player.getPet().gunakanSkill(player, monster);

            System.out.println("\n" + player.getNama() + " (HP:" + player.getHp() + ") VS " + mName + " (HP:" + monster.getHp() + ")");
            List<String> sList = player.getSkillList();
            for(int i=0; i<sList.size(); i++) System.out.print((i+1) + "." + sList.get(i) + "  ");
            
            System.out.print("\nPilih Skill: ");
            int p = sc.nextInt();
            
            // Cek agar tidak pilih skill yang belum dipelajari
            if (p > sList.size()) {
                System.out.println("❌ Kamu belum mempelajari skill itu!");
                player.serang(monster);
            } else {
                player.pakaiSkill(p, monster);
            }

            if (monster.isAlive()) {
                monster.serang(player);
            }
        }

        if (player.isAlive()) {
            System.out.println("\u001B[32m✨ Menang! Darah pulih otomatis.\u001B[0m");
            player.fullHeal();
            return true;
        }
        return false;
    }

    private void dropReward() {
        String[] rarity = {"[BIASA]", "[RARE]", "[EPIC]", "[LEGENDARIS]", "[MYTHIC]"};
        int r = rand.nextInt(100);
        int tier = (r<50)?0 : (r<80)?1 : (r<95)?2 : (r<99)?3 : 4;
        int bonus = (tier + 1) * 20;

        System.out.println("\u001B[33m🎁 DROP: Kamu dapat item " + rarity[tier] + "!\u001B[0m");
        int type = rand.nextInt(3);
        switch(type) {
            case 0 -> { player.addAttack(bonus); System.out.println("⚔️  Atk meningkat +" + bonus); }
            case 1 -> { player.addDefense(bonus); System.out.println("🛡️  Def meningkat +" + bonus); }
            case 2 -> { player.addMaxHp(bonus); System.out.println("🧪 Max HP meningkat +" + bonus); }
        }
    }

    private void offerSkill() {
        if (player.getSkillList().size() >= 5) return;

        String skillBaru = "";
        int slot = player.getSkillList().size();
        
        if (player instanceof Combat) {
            String[] s = {"", "Heavy Bash", "Whirlwind", "Shield Charge", "DRAGON SLAYER"};
            skillBaru = s[slot];
        } else if (player instanceof Mage) {
            String[] s = {"", "Fireball", "Blizzard", "Lightning Bolt", "METEOR FALL"};
            skillBaru = s[slot];
        } else if (player instanceof Assassin) {
            String[] s = {"", "Quick Stab", "Poison Dagger", "Shadow Dance", "THOUSAND CUTS"};
            skillBaru = s[slot];
        } else if (player instanceof Archer) {
            String[] s = {"", "Double Shot", "Rain of Arrows", "Explosive Bolt", "OMEGA PIERCING"};
            skillBaru = s[slot];
        } else if (player instanceof Healer) {
            String[] s = {"", "Holy Light", "Purify", "Divine Shield", "JUDGEMENT RAY"};
            skillBaru = s[slot];
        }

        System.out.println("\n\u001B[36m✨ Rahasia Menara! Ingin mempelajari skill [" + skillBaru + "]?");
        System.out.println("1. Ya | 2. Tidak\u001B[0m");
        if (sc.nextInt() == 1) {
            player.getSkillList().add(skillBaru);
            System.out.println("✅ Skill [" + skillBaru + "] Berhasil Ditambahkan!");
        }
    }

    private void handleGameOver() {
        System.out.println("\n\u001B[31m💀 GAME OVER 💀");
        System.out.println("Pahlawan gugur di Lantai " + player.getFloor());
        System.out.println("1. Bangkit Kembali (Revive) | 2. Keluar\u001B[0m");
        if (sc.nextInt() == 1) player.revive();
        else System.exit(0);
    }

    private void tampilkanStatus() {
        System.out.println("\n\u001B[33m--- STATUS CURRENT HERO ---");
        System.out.println("Role: " + player.getRole());
        System.out.println("Atk: " + player.getAttack() + " | Def: " + player.getDefense());
        System.out.println("Pet: " + player.getPet().getSpesies() + " (Lv." + player.getPet().getLevel() + ")");
        System.out.println("Skill: " + player.getSkillList() + "\u001B[0m");
    }
}