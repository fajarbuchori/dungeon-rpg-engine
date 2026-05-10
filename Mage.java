public class Mage extends Entity {
    public Mage(String n) { 
        super(n, 200, 100, 15, "Mage"); 
    }

    @Override
    public void pakaiSkill(int p, Entity lawan) {
        if (p > getSkillList().size()) {
            System.out.println("❌ Skill belum dipelajari!");
            return;
        }

        String namaSkill = getSkillList().get(p - 1);
        System.out.print("\u001B[35m🔮 " + getNama() + " merapal [" + namaSkill + "]! ");

        switch (namaSkill) {
            case "Fireball" -> {
                System.out.println("Bola api raksasa menghanguskan musuh!");
                lawan.terimaDamage(attack + 70);
            }
            case "Blizzard" -> {
                System.out.println("Badai es membekukan segalanya!");
                lawan.terimaDamage(attack * 2);
            }
            case "Lightning Bolt" -> {
                System.out.println("Petir menyambar dari langit!");
                lawan.terimaDamage(attack * 3);
            }
            case "METEOR FALL" -> {
                System.out.println("HUJAN METEOR MEMPORAK-PORANDAKAN AREA!");
                lawan.terimaDamage(attack * 5);
            }
            default -> serang(lawan);
        }
        System.out.print("\u001B[0m");
    }
}

