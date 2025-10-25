/*
 * Ad Soyad: [Fırat Seçkin]
 * Ogrenci No: [250541042]
 * Tarih: 25.10.2025
 * Aciklama: Gorev 2 - Geometrik Hesaplayıcı
 *
 * Bu program, kullanıcıdan bir dairenin yarıçapını (double) alır.
 * Bu yarıçapı kullanarak dairenin alanını, çevresini, çapını ve
 * aynı yarıçaplı bir kürenin hacmini ve yüzey alanını hesaplar.
 * PI değeri bir sabit (final) olarak tanımlanmıştır.
 * Sonuçlar printf() ile 2 ondalık basamaklı olarak formatlanır.
 */

// Scanner sınıfını kullanabilmek için import ediyoruz.
import java.util.Scanner;

public class GeometrikHesap {

    public static void main(String[] args) {
        
        // Gerekli sabiti (constant) tanimlayalim
        // 'final' anahtar kelimesi, bu değişkenin değerinin
        // program boyunca bir daha asla değiştirilemeyeceğini belirtir.
        final double PI = 3.14159;

        // Scanner objesi olusturun (Kullanicidan veri almak icin)
        Scanner input = new Scanner(System.in);

        // Kullaniciya baslik goster ve veriyi iste
        System.out.println("=== GEOMETRIK HESAPLAYICI ===");
        
        // 'print' kullaniyoruz ki kullanici sorunun yanina yazabilsin
        System.out.print("Dairenin yaricapini girin (cm): ");

        // Kullanicidan 'double' (ondalikli sayi) tipinde yaricapi oku ve sakla
        double yaricap = input.nextDouble();

        // --- Gerekli hesaplamalari yap ---
        
        // Dairenin alanı: π * r²
        // Math.pow(taban, us) fonksiyonu üs almak için kullanılır.
        // Math.pow(yaricap, 2) -> yaricap'in 2. kuvveti (karesi)
        double daireAlan = PI * Math.pow(yaricap, 2);

        // Dairenin çevresi: 2 * π * r
        double daireCevre = 2 * PI * yaricap;

        // Dairenin çapı: 2 * r
        double daireCap = 2 * yaricap;

        // Kürenin hacmi: (4/3) * π * r³
        // DIKKAT: Eger (4/3) yazarsak, Java bunu tamsayi bolmesi (integer division)
        // olarak algilar ve sonucu 1 bulur. Bu buyuk bir hatadir.
        // (4.0 / 3.0) yazarak Java'ya ondalikli (double) bolme yapmasini soyluyoruz.
        double kureHacim = (4.0 / 3.0) * PI * Math.pow(yaricap, 3); // r³ -> yaricapin 3. kuvveti

        // Kürenin yüzey alanı: 4 * π * r²
        double kureYuzeyAlan = 4 * PI * Math.pow(yaricap, 2);

        // --- Sonuclari formatli olarak ekrana yazdir ---
        
        // '\n' karakteri yeni bir satira gecmeyi saglar (new line)
        System.out.println("\nSONUCLAR:");
        System.out.println("----------");

        // printf() ile formatli cikti:
        // %-20s -> 20 karakterlik alana Sola yaslanmis String (metin)
        //          '-' isareti sola yasla demektir.
        // %s     -> Normal String
        // %.2f   -> 2 ondalik basamakla sinirlandirilmis double/float (sayi)
        // cm²    -> Normal metin, formatlamanin disinda.
        // \n     -> Yeni satira gec
        
        // Bu formatlama sayesinde tum ':' isaretleri dikey olarak hizalanir.
        System.out.printf("%-20s : %.2f cm²\n", "Daire Alani", daireAlan);
        System.out.printf("%-20s : %.2f cm\n", "Daire Cevresi", daireCevre);
        System.out.printf("%-20s : %.2f cm\n", "Daire Capi", daireCap);
        System.out.printf("%-20s : %.2f cm³\n", "Kure Hacmi", kureHacim);
        System.out.printf("%-20s : %.2f cm²\n", "Kure Yuzey Alani", kureYuzeyAlan);

        // Actigimiz Scanner'i kapatmak iyi bir programlama aliskanligidir.
        input.close();
    }
}
