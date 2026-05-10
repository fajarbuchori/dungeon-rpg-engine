public class Assassin extends Entity {
    public Assassin(String n) { 
        super(n, 250, 85, 20, "Assassin"); 
    }

    @Override
    public void pakaiSkill(int p, Entity lawan) {
        if (p > getSkillList().size()) {
            System.out.println("❌ Skill belum dipelajari!");
            return;
        }

        String namaSkill = getSkillList().get(p - 1);
        System.out.print("\u001B[31m🌑 " + getNama() + " menggunakan [" + namaSkill + "]! ");

        switch (namaSkill) {
            case "Quick Stab" -> {
                System.out.println("Tusukan cepat ke titik vital!");
                lawan.terimaDamage(attack + 50);
            }
            case "Poison Dagger" -> {
                System.out.println("Belati beracun melukai musuh!");
                lawan.terimaDamage(attack + 100);
            }
            case "Shadow Dance" -> {
                System.out.println("Menari di dalam bayangan dan menyerang!");
                lawan.terimaDamage(attack * 3);
            }
            case "THOUSAND CUTS" -> {
                System.out.println("SERIBU SAYATAN DALAM SEKEJAP MATA!");
                lawan.terimaDamage(attack * 5);
            }
            default -> serang(lawan);
        }
        System.out.print("\u001B[0m");
    }
}