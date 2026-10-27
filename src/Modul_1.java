// Primitif (ilkel) Türler ve Referans  Türleri dosyası

class Kitap {         // referans tür örneğinde kullanılacak nesne şablonu (Heap'ta yaşayacak)
    String ad;
    int sayfaSayisi;
}

public class Modul_1 {
    public static void main(String[] args) {

        //-------------------------------
        //1.PRİMİTİF (İLKEL) TÜRLER (değer doğrudan stackte saklanır.
        //-----------------------------------

        //Tam Sayı Türleri
        byte kucukSayi = 127; // 1 Byte (8 bit) --> aralik : -128 ile 127
        short ortaSayi = 32000; // 2 Byte (16 bit) --> araliği: -32.768 ile 32.767
        int tamSayi = 1234432433; // 4 Byte (32 bit) --> varsayılan tüm tam sayı türü
        long buyukSayi = 12434324342L; // 8 Byte( 64 bit) --> 'L' veya 'l' takısı alır , bu takı yazılmazsa "integer number too large" hatası verir.

        //Ondalk sayı türleri
        float ondalik1 = 3.14f;  // 4 Byte (32 bit) --> 'f' veya 'F'takısı zorunlduur. Eğer bu takı yazılmazsa neden o zaman double kullanmıyorsun der (tescillendi)
        double ondalik2 = 3.14234327493; // 8 Byte (64 bit) -> varsayılan hassas ondalık türü
        // bilgisaarlarda bolca ram var neden float kullanayım derdim ama meğerse ekran kartları mimarisi float verileri işlemek üzere optimize edilmiş


        // karakter ve mantıksal türler
        char karakter = 'A'; //2 Byte ( 16 bit) --> Tek tırnak (unicode simgeleri)
        boolean mantik = true; // 1 bit (yalnizca true veya false tutar.)

        System.out.println ("--- 1.Primitif Türler Tanımlandı---");

        // =======================================================
        // 3. REFERANS TÜRLERİ (Stack'te Adres, Heap'te Nesne)
        // =======================================================

        // 1. 'k1' referans değişkeni STACK'te oluşur.
        // 2. 'new Kitap()' ile nesnenin kendisi HEAP belleğinde açılır.
        Kitap k1 = new Kitap();
        k1.ad = "Java ile OOP";
        k1.sayfaSayisi = 350;

        // REFERANS KOPYALAMA (ADRES AKTARIMI):
        // k2 = k1 satırında Heap'te YENİ BİR KİTAP ÜRETİLMEZ!
        // Yalnızca k1'in elindeki bellek adresi k2'ye verilir. Artık ikisi de aynı nesneyi gösterir.
       Kitap k2 = k1;

        // k2 (ikinci referans) üzerinden yapılan değişiklik k1'i de doğrudan etkiler:
        k2.sayfaSayisi = 500;

        System.out.println("\n --- 2. Refarns Türü Testi ---");
        System.out.println("k1 kitap sayfa sayisi: " + k1.sayfaSayisi); // çıktı = 500 çünkü en sonda k2 sayfa sayisi =500 olmuş yani k1 de 500
        System.out.println("k2 kitap sayfa sayisi: " + k2.sayfaSayisi); // çıktı = 500

        //k2 = k1 Mantığı (Referans Kopyalama): `k2` nesnesi için `new` kelimesi kullanılmadığı için yeni bir alan tahsis edilmez.
        // Aynı nesneyi kontrol eden iki referansın (adresin) oluştuğunu `k2.sayfaSayisi = 400;` satırından sonra `k1`'i yazdıarak kanıtlanmış oldu.



        // =======================================================
        // 4\. TİP DÖNÜŞÜMLERİ (TYPE CASTING)
        // ===================================================

        // A) OTOMATİK (GENİŞLETME) DÖNÜŞÜM: int -> double
        int kucukSayi1 = 100;
        double buyukSayi1 = kucukSayi1; // Otomatik olarak 100.0 olur (veri kaybı yok)
        System.out.println("Otomatik Dönüşüm (int -> double): " + buyukSayi1);

        // B) ZORUNLU (DARALTMA) DÖNÜŞÜM: double -> int
        double hassasOndalik = 3.23432;
        int kesilmisTamSayi = (int) hassasOndalik; // (int) dökümü ile .23432 kısmı atılır, yuvarlama olmaz en yakın tam sayıya
        System.out.println("Zorunlu Dönüşüm (double -&gt; int): " + kesilmisTamSayi); // çıktı = 3

        // C) STRING -&gt; INT DÖNÜŞÜMÜ (Integer.parseInt)
        String gelenGirisi = "250";
        int donusturulenSayi = Integer.parseInt(gelenGirisi); // metin sayıya dönüştü
        System.out.println("Sayıasl işlem sonucu: " + (donusturulenSayi + 50)); //çıktı = 300




    }
}