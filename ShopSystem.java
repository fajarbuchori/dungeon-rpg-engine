import java.util.HashMap;

public class ShopSystem {
    private HashMap<String, Integer> daftarBarang = new HashMap<>();

    public ShopSystem() {
        daftarBarang.put("Mega Potion", 50);
        daftarBarang.put("Attack Booster", 75);
        daftarBarang.put("Revive Stone", 150);
    }

    public void tampilkanToko() {
        System.out.println("\n🛒 --- TOKO PETUALANG ---");
        daftarBarang.forEach((item, harga) -> {
            System.out.println("- " + item + " : " + harga + " Gold");
        });
    }
}