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

    publc Ders(String dersKodu, String dersAdi) {
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
public class Modul_3 {
    public static void main(String[] args) {
        System.out.println("=== 2\. PARÇA: 'final' TESTİ ===");

        Ders d1 = new Ders("YAZ201", "Nesne Yönelimli Programlama");
        System.out.println(Ders.FAKULTE_ADI + " - " + d1.getDersKodu() + ": " + d1.getDersAdi());

        // d1.dersKodu = "YAZ102";
        //DERLEME HATASI! 'final' değişkene yeniden değer atanamaz.

        final Ders sabitDers = new Ders("MAT101", "Matematik I");
        //DERLEME HATASI! 'sabitDers' değişkeni 'final' olduğu için başka bir nesnenin adresini tutamaz:
        //sabitDers = new Ders("FZK101", "Fizik I");

        //'sabitDers' referansının adresi sabittir ama gösterdiği nesnenin 'final' olmayan alanları değiştirilebilir:
        sabitDers.setDersAdi("İleri Matematik I");
        System.out.println("Güncellenmiş Ders Adı: " + sabitDers.getDersAdi());
    }

}

