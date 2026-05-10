public class Combat extends Entity {
    public Combat(String n) { 
        super(n, 400, 60, 40, "Combat"); 
        // Jangan tambah skillList.add di sini agar tidak langsung muncul semua
    }

    @Override
    public void pakaiSkill(int p, Entity lawan) {
        // Ambil nama skill berdasarkan urutan di list
        String namaSkill = getSkillList().get(p-1); 
        System.out.print("\u001B[33m⚔️  " + getNama() + " menggunakan [" + namaSkill + "]! ");

        switch(namaSkill) {
            case "Heavy Bash" -> { System.out.println("Menghantam musuh!"); lawan.terimaDamage(attack + 70); }
            case "Whirlwind" -> { System.out.println("Berputar menebas!"); lawan.terimaDamage(attack * 2); }
            case "Shield Charge" -> { System.out.println("Menyeruduk!"); lawan.terimaDamage(attack + 120); }
            case "DRAGON SLAYER" -> { System.out.println("TEBASAN DEWA!"); lawan.terimaDamage(attack * 5); }
            default -> { System.out.println("Menyerang!"); serang(lawan); }
        }
        System.out.print("\u001B[0m");
    }
}