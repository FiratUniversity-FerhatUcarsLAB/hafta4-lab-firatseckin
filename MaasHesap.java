/*
 * Ad Soyad: [Fırat Seçkin]
 * Ogrenci No: [250541042]
 * Tarih: 25.10.2025
 * Aciklama: Gorev 3 - Maaş Hesaplama Sistemi
 *
 * Bu program, bir çalışanın maaş bordrosunu hesaplar.
 * Kullanıcıdan ad/soyad, brüt maaş, haftalık saat ve mesai saati alınır.
 * Toplam gelir, mesai ücreti eklenerek hesaplanır.
 * Toplam gelirden SGK, Gelir Vergisi ve Damga Vergisi kesintileri yapılır.
 * Net maaş ve ek istatistikler (kesinti oranı, saatlik/günlük kazanç)
 * profesyonel bir bordro formatında ekrana yazdırılır.
 * Tüm vergi/kesinti oranları 'final' sabit olarak tanımlanmıştır.
 */

// Scanner'a ek olarak, bordroya tarih basmak icin 
// LocalDate ve DateTimeFormatter siniflarini da import ediyoruz.
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MaasHesap {

    public static void main(String[] args) {
        
        // --- Kesinti ve katsayı oranlarını SABIT (final) olarak tanimlayalim ---
        // Oranlari 0.14 (%14) gibi ondalik olarak tanimlamak hesaplamada kolaylik saglar.
        final double MESAI_KATSAYISI = 1.5;
        final double AYLIK_CALISMA_SAATI = 160.0; // Mesai hesabi icin temel
        final double SGK_KESINTI_ORANI = 0.14;    // %14
        final double GELIR_VERGISI_ORANI = 0.15;  // %15
        final double DAMGA_VERGISI_ORANI = 0.00759; // %0.759
        final double GUN_SAYISI = 22.0;           // Gunluk kazanc hesabi icin

        // Scanner objesini olustur
        Scanner input = new Scanner(System.in);

        // --- Kullanicidan bilgileri al ---
        System.out.println("=== MAAS HESAPLAMA SISTEMI ===");
        System.out.println();

        // DIKKAT: Ad ve soyad arasinda bosluk olabilecegi icin (örn: Ali Veli)
        // input.next() yerine input.nextLine() kullaniyoruz. next() sadece ilk kelimeyi alir.
        System.out.print("Çalışan adı soyadı: ");
        String adSoyad = input.nextLine();

        System.out.print("Brüt maaş (TL): ");
        double brutMaas = input.nextDouble();

        // Bu degisken ornekte istenmis ama ana maas hesaplamasinda kullanilmiyor.
        System.out.print("Haftalık çalışma saati: ");
        int haftalikCalismaSaati = input.nextInt(); 

        System.out.print("Mesai saati sayısı: ");
        int mesaiSaati = input.nextInt();

        
        // --- 1. GELIRLER HESAPLAMA ---
        // Once 1 saatlik brut ucreti bulmaliyiz (Aylik 160 saat uzerinden)
        double saatlikBrutUcret = brutMaas / AYLIK_CALISMA_SAATI;
        
        // Mesai ucretini hesapla (Saatlik * 1.5 * Mesai Saati)
        double mesaiUcreti = saatlikBrutUcret * mesaiSaati * MESAI_KATSAYISI;
        
        // Toplam Gelir (Brüt + Mesai). Tum kesintiler bu toplam uzerinden yapilir.
        double toplamGelir = brutMaas + mesaiUcreti;

        
        // --- 2. KESINTILER HESAPLAMA ---
        // Kesintiler her zaman TOPLAM GELIR uzerinden hesaplanir.
        double sgkKesintisi = toplamGelir * SGK_KESINTI_ORANI;
        double gelirVergisi = toplamGelir * GELIR_VERGISI_ORANI;
        double damgaVergisi = toplamGelir * DAMGA_VERGISI_ORANI;

        // Tum kesintileri topla
        double toplamKesinti = sgkKesintisi + gelirVergisi + damgaVergisi;

        
        // --- 3. NET MAAS HESAPLAMA ---
        double netMaas = toplamGelir - toplamKesinti;

        
        // --- 4. ISTATISTIKLER HESAPLAMA ---
        // Kesinti oranini yuzde olarak hesapla (örn: 29.8%)
        double kesintiOrani = (toplamKesinti / toplamGelir) * 100.0;
        
        // Toplam calisilan saat (Normal + Mesai)
        double toplamCalisilanSaat = AYLIK_CALISMA_SAATI + mesaiSaati;
        
        // Saatlik net kazanci bul (Net Maasi toplam calisilan saate bol)
        double saatlikNetKazanc = netMaas / toplamCalisilanSaat;
        
        // Gunluk net kazanci bul (Net Maasi 22 is gunune bol)
        double gunlukNetKazanc = netMaas / GUN_SAYISI;

        
        // --- 5. BORDRO CIKTISI ---
        // Guncel sistem tarihini al (LocalDate.now())
        LocalDate bugun = LocalDate.now();
        // Tarihi "dd.MM.yyyy" (örn: 25.10.2025) formatina getir
        DateTimeFormatter formatlayici = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String tarih = bugun.format(formatlayici);

        // Bordroyu printf ile formatlayarak yazdir
        System.out.println("\n====================================");
        System.out.println("         MAAS BORDROSI");
        System.out.println("====================================");
        System.out.printf("Calisan: %s\n", adSoyad);
        System.out.printf("Tarih: %s\n\n", tarih); // \n\n -> iki satir bosluk birak

        System.out.println("GELIRLER:");
        // %-25s -> 25 karakter sola yasli metin
        // %10.2f -> 10 karakterlik alana saga yasli, 2 ondalikli sayi
        System.out.printf("  %-25s : %10.2f TL\n", "Brut Maas", brutMaas);
        System.out.printf("  %-25s : %10.2f TL\n", "Mesai Ucreti (" + mesaiSaati + " saat)", mesaiUcreti);
        System.out.println("  ------------------------   -----------");
        System.out.printf("  %-25s : %10.2f TL\n\n", "TOPLAM GELIR", toplamGelir); // Gelirler sonrasi bosluk

        System.out.println("KESINTILER:");
        // %'nin kendisini yazdirmak icin %% kullaniriz.
        // %.1f%% -> 1 ondalikli sayi ve yaninda '%' isareti (örn: 14.0%)
        // %-7s -> Hizalamayi bozmamak icin araya eklenmis 7 karakterlik bosluk
        System.out.printf("  SGK Kesintisi (%.1f%%) %-7s : %10.2f TL\n", (SGK_KESINTI_ORANI * 100), "", sgkKesintisi);
        System.out.printf("  Gelir Vergisi (%.1f%%) %-7s : %10.2f TL\n", (GELIR_VERGISI_ORANI * 100), "", gelirVergisi);
        // Damga vergisini 3 ondalik gosterelim (%.3f%%)
        System.out.printf("  Damga Vergisi (%.3f%%) %-4s : %10.2f TL\n", (DAMGA_VERGISI_ORANI * 100), "", damgaVergisi);
        System.out.println("  ------------------------   -----------");
        System.out.printf("  %-25s : %10.2f TL\n\n", "TOPLAM KESINTI", toplamKesinti); // Kesintiler sonrasi bosluk

        // Net Maas (Ayirici bosluk icin %21s kullandik)
        System.out.printf("NET MAAS %21s : %10.2f TL\n\n", "", netMaas); // Net maas sonrasi bosluk

        System.out.println("ISTATISTIKLER:");
        // Istatistikleri %.1f (yuzde) ve %.2f (para) ile formatliyoruz
        System.out.printf("  %-25s : %9.1f%%\n", "Kesinti Orani", kesintiOrani);
        System.out.printf("  %-25s : %10.2f TL/saat\n", "Saatlik Net Kazanc", saatlikNetKazanc);
        System.out.printf("  %-25s : %10.2f TL/gun\n", "Gunluk Net Kazanc", gunlukNetKazanc);
        System.out.println("====================================");

        // Scanner'i kapatin
        input.close();
    }
}
