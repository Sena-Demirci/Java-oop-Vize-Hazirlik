// Sınıflar Arası İlişkiler &amp; Bileşim

// =====================================
// MODUL 3: GELŞMİŞ SINIF MİMARİSİ VE YAPILAR
// ====================================

//1.KONU = 'this' Anahatar kelimesi ve yapıcı zincirleme (constructor chaining)
class Ogrenci {
    private String ad;
    private int numara;
    private String bolum;

    // Tam parametreli ana yapıcı (constructor)
    public Ogrenci(String ad , int numara , String bolum) {
        //'this.alan' : metot parametresi ile sınıf alanının isimleri çakıştığında
        //'this' o anki çalışan nesnenin alanını temsil eder.
        this.ad = ad;
        this.numara = numara;
        this.bolum = bolum;
    }

    // 2 parametreli yapıcı -> 3 yapılı yapııcıya yönlendrir
    public Ogrenci(String ad, int numara) {
        //'this(...)' ifadesi yapıcı bloğunun MUTLAKA İLK SATIRINDA olmalıdır
        // statik metotların içinde this kullanılmaz çünkü ortada nesne yoktur

        this(ad, numara, "belirtilmedi");

    }

    // tek parametreli yapıcı ->  2 parametreli yapıcıya yönlendirir
    public Ogrenci(String ad) {
        thid(ad , 0); // çağrı zinciri: Ogrenci(String) -> Ogrenci(String , int) -> Ogrenci(String , int , String)

    }
    public void bilgiYazdir() {
        System.out.println("Öğrenci: " + ad + " | No: " + numara + " | Bölüm: " + bolum);

    }
}

// 2.KONU: 'final' ANAHTAR KELİMESİ VE DEĞİŞMEZLİK (IMMUTABILITY)

class Ders {
    // 1.Sabit (constant) : 'public static final' ->  Her yerden erişilebilir, ortak ve değişitirilemez
    public static final String FAKULTE_ADI = "Mühendislik Fakültesi";

    // 2.'final' ALAN : Sadece tanımlandığı satırda veya yapıcı içinde değer atanabilir.
    // Değer atandıktan sonra bir daha asla değiştirilenez
    private final String dersKodu;
    private String dersAdi; // final deeil, değiştirilebirşl

    public Ders(String dersKodu, String dersAdi) {
        this.dersKodu = dersKodu; // ilk ve son atama yapılıyor
        this.dersAdi = dersAdi;
    }
    public String getDerskodu() { return dersKodu; }
    public String getDersAdi() {return dersAdi;}

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }
    // dersKodu için setter metodu yazılamaz çünkü
    //derleyici 'final' değişkene yeniden değer atanmasına izin vermez

}

// KONU 3: 'static' ALANLAR VE METOTLAR

class Siparis {
    // 1. INSTANCE (NESNEYE ÖZEL) ALAN:
    // Her sipariş nesnesinin kendi ürün adı vardır.
    private String urunAdi;

    // 2. STATIC (SINIFA AİT) ALAN:
    // Tüm nesnelerin ortaklaşa paylaştığı TEK BİR bellektedir.
    // Kaç tane Siparis nesnesi üretilirse üretilsin bu sayaç ortak artar.
    private static int toplamSiparisSayisi = 0;

    public Siparis(String urunAdi) {
        this.urunAdi = urunAdi;
        toplamSiparisSayisi++; // Her yeni siparişte ortak sayacı 1 artırıyoruz
    }

    // 3. STATIC METOT:
    // Nesne üretmeye gerek kalmadan doğrudan
    // 'Siparis.getToplamSiparisSayisi()' şeklinde çağrılır.
    public static int getToplamSiparisSayisi() {
        // - Statik metot içinden statik olmayan 'urunAdi' alanına DOĞRUDAN ERİŞİLEMEZ! (Derleme Hatası)
        // - Statik metot içinde 'this' veya 'super' KULLANILAMAZ! (Çünkü ortada nesne olmayabilir)
        return toplamSiparisSayisi;

    }
    public String getUrunAdi() {
        return urunAdi;
    }

}



public class Modul_3 {
    public static void main(String[] args) {
        System.out.println("=== 2\. PARÇA: 'final' TESTİ ===");

        Ders d1 = new Ders("YAZ201", "Nesne Yönelimli Programlama");
        System.out.println(Ders.FAKULTE_ADI + " - " + d1.getDersKodu() + ": " + d1.getDersAdi());

        // d1.dersKodu = "YAZ102";
        //DERLEME HATASI! 'final' değişkene yeniden değer atanamaz.

        final Ders sabitDers = new Ders("MAT101", "Matematik I"); // burada ilk atama yapılıyor , final olduğu için sabitDers artık değiştirilemez
        //meselaa "sabitDers = new Ders("FZK101", "Fizik I");" derleyici hata verir burada

        //'sabitDers' referansının adresi sabittir ama gösterdiği nesnenin 'final' olmayan alanları değiştirilebilir:
        sabitDers.setDersAdi("İleri Matematik I");
        System.out.println("Güncellenmiş Ders Adı: " + sabitDers.getDersAdi());



        System.out.println("=== 3\. PARÇA: 'static' TESTİ ===");
        // 1. Henüz HİÇ nesne oluşturulmamışken bile statik metoda sınıf adıyla erişebiliriz:
        System.out.println("Başlangıç Sipariş Sayısı: " + Siparis.getToplamSiparisSayisi()); // Çıktı: 0

        // 2. Nesneleri oluşturuyoruz:
        Siparis s1 = new Siparis("Pizza");
        Siparis s2 = new Siparis("Burger");
        Siparis s3 = new Siparis("Kahve");

        // 3. Her nesnenin kendi ürün adı farklıdır (Instance):
        System.out.println("1\. Sipariş: " + s1.getUrunAdi()); // Pizza
        System.out.println("2\. Sipariş: " + s2.getUrunAdi()); // Burger

        // 4. Ancak toplam sipariş sayısı hepsi için ORTAKTIR (Static):
        System.out.println("Toplam Sipariş (Sınıf Adıyla): " + Siparis.getToplamSiparisSayisi());
        // Çıktı: 3
    }

}

