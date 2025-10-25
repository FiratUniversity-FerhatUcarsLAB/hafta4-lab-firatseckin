/*
 * Ad Soyad: [Fırat Seçkin]
 * Ogrenci No: [250541042]
 * Tarih: 25.10.2025
 * Aciklama: Gorev 1 - Ogrenci Bilgi Sistemi
 *
 * Bu program kullanicidan ogrenci bilgilerini alir ve
 * duzenli bir formatta ekrana yazdirir.
 * Diğer java dosyalarının başında da bu örnek formattaki gibi kısa bilgi giriniz
 */

import java.util.Scanner;

public class OgrenciBilgi {
    public static void main(String[] args) {
        // Scanner objesi olusturun
        Scanner input = new Scanner(System.in);
        
        // Degisken tanimlamalari
        // Değişkenleri aşağıda veriyi alırken tanımlayacağız.
        
        
        // Kullanicidan bilgileri alin
        System.out.println("=== OGRENCI BILGI SISTEMI ===");
        System.out.println();
        
        // Ad
        System.out.print(" adınızı girin: ");
        String ad = input.next();
        
        // Soyad
        System.out.print("soyadınızı girin: ");
        String soyad = input.next();
        
        // Ogrenci No
        System.out.print("Öğrenci numaranızı girin: ");
        int ogrenciNo = input.nextInt();
        
        // Yas
        System.out.print(" yaşınızı girin: ");
        int yas = input.nextInt();
        
        // GPA
        System.out.print("GPA (not ortalaması, 0.00-4.00 arası) girin: ");
        double gpa = input.nextDouble();
        
        
        // Bilgileri ekrana yazdirin
        System.out.println("\n=== OGRENCI BILGI SISTEMI ===");
        
        // COZUMUNUZU BURAYA YAZIN
        // printf() ile düzenli çıktı alıyoruz
        System.out.printf("Ad Soyad: %s %s\n", ad, soyad);
        System.out.printf("Ogrenci No: %1d\n", ogrenciNo);
        System.out.printf("Yas: %1d\n", yas);
        
        // GPA'yi 2 ondalık basamakla göstermek için %.2f kullandık
        System.out.printf("GPA: %.2f\n", gpa);

        // Örnek çıktıdaki "Durum" kısmını da ekleyelim
        if (gpa >= 2.0) {
            System.out.println("Durum: Basarili Ogrenci");
        } else {
            System.out.println("Durum: Basarisiz Ogrenci");
        }
        
        
        // Scanner'i kapatin (önemli pratik)
        input.close();
    }
}
