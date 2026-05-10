public class Archer extends Entity {
    public Archer(String n) { 
        super(n, 280, 75, 25, "Archer"); 
    }

    @Override
    public void pakaiSkill(int p, Entity lawan) {
        if (p > getSkillList().size()) {
            System.out.println("❌ Skill belum dipelajari!");
            return;
        }

        String namaSkill = getSkillList().get(p - 1);
        System.out.print("\u001B[32m🏹 " + getNama() + " melepaskan [" + namaSkill + "]! ");

        switch (namaSkill) {
            case "Double Shot" -> {
                System.out.println("Dua panah meluncur sekaligus!");
                lawan.terimaDamage(attack * 2);
            }
            case "Rain of Arrows" -> {
                System.out.println("Hujan panah jatuh dari langit!");
                lawan.terimaDamage(attack + 110);
            }
            case "Explosive Bolt" -> {
                System.out.println("Panah meledak saat mengenai target!");
                lawan.terimaDamage(attack * 3);
            }
            case "OMEGA PIERCING" -> {
                System.out.println("PANAH CAHAYA MENEMBUS SEGALA PERTAHANAN!");
                lawan.terimaDamage(attack * 5);
            }
            default -> serang(lawan);
        }
        System.out.print("\u001B[0m");
    }
}