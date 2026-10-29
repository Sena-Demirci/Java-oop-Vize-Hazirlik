// MODÜL 4: KALITIM, ÇOK BİÇİMLİLİK VE SOYUTLAMA (Modul 4.java)

// 1. Konu: Kalıtım ve 'super' anahtar kelimesi

// üst sınıf ( superclass veya parent diye adlanıdırılır)
class FerrariF1Araci {
    protected String model;  // örn: SF-24   // protected olma sebebi: extends olmayan classların erişimine kapalı
    protected int beygirGucu; // örn: 1050 HP  // extends olan yanı child class olanların kendisine erişime izin verir

    public FerrariF1Araci(String model , int beygirGucu) {
        this.model = model;
        this.beygirGucu = beygirGucu;
        System.out.println ("1. FerrariF1Araci çalıştırıldı: V6 Turbo-Hibrit motor ateşlendi.");
    }
    public void telemetriYazdir() {
        System.out.println("Araç modeli: " + model + " |Güç : " + beygirGucu + "HP");
    }

}
//Alt snıf (Subclass / child) -> 'extends' ile FerrariF1Araci sınıfından kalıtım alır
class FerrariYarisModu extends FerrariF1Araci {
    private String lastikHamuru; //örn: soft kırmızı c5
    private boolean drsAcikmi;

    public FerrariYarisModu(String model , int beygirGucu , String lastikHamuru , boolean drsAcikmi) {
        //'super(...)': Üst sınıfın (FerrariF1Araci) yapıcısını çağırır.
        // - 'super(...)' çağrısı yapıcı bloğunun MUTLAKA İLK SATIRINDA olmalıdır
        // - Aynı yapıcı içinde hem 'this(...)' hem 'super(...)' birlikte kullanılmaz

        super(model, beygirGucu);
        this.lastikHamuru = lastikHamuru;
        this.drsAcikmi = drsAcikmi;
        System.out.println("2.FerrariYarisModu (Child) aktif: Pit çıkış ışığı YEŞİL!");

        // üst sınıftaki telemetriYazdir metdodunu override ederek pite canlı yarış verisi aktarıyor
        public void telemetriYazdir() {
            super.telemetriYazdir();
            System.out.println("Takılı Lastik: " + lastikHamuru
                    + " | DRS durumu: " + (drsAcikmi ? "AÇIK(Sollama Modu)" : "KAPALI"))
                    + " | Pit Telsizi: 'Box, box, box!");,
        }

        //=========================================================================================
        //Özet notlar: 'protected' ve '@Override' neden kullanıldı?

        // ==='protected'====
        // ' private ' yapılsaydı alt sınıflar bu alanlara erişemezdi (derleme hatası)
        // 'public' yapılsaydı ilgisiz tüm sınıflar erişebilrirdi ve kapsülleme bozulurdu.
        // 'protected' : dış dünyaya kapalıdır, sadece extendslarına açıktır.

        //==='@Override'==
        //Çok biçimlilik ( Polymorphism): Pit duvarı garajdaki tüm araçları
        // tek bir döngüye alıp tek isimle ('arac.telemetriYazdir()') çağırır.
        //Standartlaşma: projedeki tüm alt sınıfların aynı isim standartlarına uymasını sağlar.
        // Gelceğe uyum: yarın takıma yeni bir araç eklendiğinde ana sisteme hiç
        //dokunmadan yeni araç otomatik olarak sisteme entegre olur

        // peki neden 'super.telemetriYazdir()' çağırrlır?
        //üst sınıftaki temel kodları tekrar yazmak yerine var olanı aynen kullanır
        //altına sadece kendi sınıfına özgü yeni veriler ekler (kod tekrarlarını yani bir işkenceyi önler)
        //======================================================================================================




        }

    }
    public class Modul_4 {
        public static void main(String[] args) {
            System.out.println("=== 1.KONU: Kalıtım ve 'super' Testi (F1 Telemetri)");

            //Alt sınıf nesnesi oluşturulduğudna bellek ve yapıcı çalışma sırası:
            // öncelikle FerrariF1Araci (parent) motoru çalışır
            //sonra ise FerrariYarisModu (child) pist moduna geçer
            FerrariYarisModu sf24 = new FerrariYarisModu("SF-24" , 1050 , "Soft(Kırmızı-C5)", true);
            System.out.println("\\n--- PİT DUVARI CANLI TELEMETRİ VERİSİ ---");
            sf24.telemetriYazdir();
        }
    }

