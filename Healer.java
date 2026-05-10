public class Healer extends Entity {
    public Healer(String n) { 
        super(n, 350, 55, 35, "Healer"); 
    }

    @Override
    public void pakaiSkill(int p, Entity lawan) {
        if (p > getSkillList().size()) {
            System.out.println("❌ Skill belum dipelajari!");
            return;
        }

        String namaSkill = getSkillList().get(p - 1);
        System.out.print("\u001B[36m✨ " + getNama() + " merapal [" + namaSkill + "]! ");

        switch (namaSkill) {
            case "Holy Light" -> {
                System.out.println("Cahaya suci membakar musuh!");
                lawan.terimaDamage(attack + 40);
                heal(30);
            }
            case "Purify" -> {
                System.out.println("Membersihkan kegelapan!");
                lawan.terimaDamage(attack * 2);
                heal(60);
            }
            case "Divine Shield" -> {
                System.out.println("Perisai dewa melindungi raga!");
                defense += 20;
                System.out.println("Defense meningkat permanen!");
            }
            case "JUDGEMENT RAY" -> {
                System.out.println("SINAR PENGHAKIMAN TURUN DARI LANGIT!");
                lawan.terimaDamage(attack * 4);
                fullHeal();
            }
            default -> serang(lawan);
        }
        System.out.print("\u001B[0m");
    }
}