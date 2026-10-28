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



