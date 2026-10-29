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

    // 2.Konu: Çok biçimlilik (Polymorphism) , Upcasting ve Downcasting)

    //ikinci bir alt sınıf tanımlıyoruz
    class FerrariSiralamaModu extends FerrariF1Araci {
        private boolean ersKullanimiAktif; // ers:  ekstra elektrik gücüdür
        public FerrariSiralamaModu(String model , int beygirGucu , boolean ersKullanimiAktif) {
            super(model , beygirgucu);
            this.ersKullanimiAktif = ersKullanimiAktif;
        }
        // override
        public void telemetriYazdir() {
            super.telemetriYazdir();
            System.out.println("Sıralama Modu: 1.5 Sıralama Turu | ERS deroyment:"
                    + (ersKullanimiAktif ? "MAX GÜÇ(%100)" : "ŞARJ MODU") );
        }
        // mor sektör
        public void morSektorDerecesiAt() {
            System.out.println("Leclerc pole pozisyonu için geliyor!");
        }

        // 3.KONU:SOYUT SINIFLAR (ABSTRACT CLASS) VE ARAYÜZLER (INTERFACE)


        // 1. Interfac (Arayüz-1): DRS Yeteneği Sözleşmesi
        interface DRSyonetimi {
            //Arayüzdeki metotlar varsayılan olarak 'public abstract' kabul edilir, gövdesizdir
            void drsAc();
            void drsKapat();

        }

        // 2 .Interface (arayüz-2) : pit stratejisi sözleşmesi
        interface PitStratejisi {
            void pitStopYap(String lastikTuru);
        }

        //3. ABSTRACT CLASS (soyut sınıf):
        // direkt new SoyutF1Araci() diyerek nesne üretilimez
        // extends eden sınıflara temel şablon oluşturur

        abstract SoyutF1Araci(String pilotAdi) {
            this.pilotAdi = pilotAdi;
        }
        // gövdeli (somut) metot: Tüm alt sınıflar aynen kullanabilir
        public void motorAtesle() {
            System.out.println(pilotAdi + "için V6 Turbo-Hibrit motor çalıştırıldı.");
        }
        // gövdesiz (abstract) metot : Alt sınıflar bunu kesinlikle ezmek @Override etmek zorunda
        public abstract void yarisModunuAyarla();
    }

    // 4. SOMUT SINIF (concrete class):
    // extends ile tek bir abstract sınıfı kalıtır
    // implements ile birden fazla arayüzü aynı anda uygulayabilir
    class SF24YarisAraci extends SoyutF1Araci implements DRSYonetimi, PitStratejisi {
        // Soyut sınıftan gelen 'abstract' metodu doldurmak ZORUNDAYIZ:
        public void yarisModunuAyarla() {
        System.out.println(" SF-24 'Maksimum Saldırı' moduna alındı!"); }

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

            System.out.println("\\n=== 2.KONU: ÇOK BİÇİMLİLİK VE UPCASTING TESTİ ===");

            // 1. UPCASTING (Yukarı Doğru Dönüşüm):
            // Üst sınıf referansı (FerrariF1Araci), alt sınıf nesnesini tutuyor.
            FerrariF1Araci garajAraci1 = new FerrariYarisModu("SF-24", 1050, "Medium (Sarı)", false);
            FerrariF1Araci garajAraci2 = new FerrariSiralamaModu("SF-24", 1080, true);

            // 2. POLİMORFİK DİZİ (Polymorphic Array):
            // Garajdaki farklı modlardaki araçları tek bir üst sınıf dizisinde toplanır
            FerrariF1Araci[] pitGarji = { garajAraci1 , garajAraci2};

            System.out.println("\n--- PİT DUVARI TOPLU TELEMETRİ OKUMASI ---");

            for (FerrariF1Araci arac: pitGaraji) {
                // DİNAMİK BAĞLAMA (Dynamic Binding):
                // Referans tipi 'FerrariF1Araci' olsa da, Java arka planda nesnenin GERÇEK TÜRÜNÜ
                // anlar ve ilgili sınıfın ezilmiş (@Override) 'telemetriYazdir' metodunu çalıştırır

                arac.telemtriYazdir();
                System.out.println("---------------------------------");
            }

            // 3. DOWNCASTING (Aşağı Doğru Dönüşüm) VE 'instanceof' KONTROLÜ:
            // 'garajAraci2' üzerinden alt sınıfa özel 'morSektorDerecesiAt()' metodunu doğrudan çağıramayız
            // Önceden 'instanceof' ile tip kontrolü yapıp tür dönüşümü (Casting) yapmamız gerekir:

            if(garajAraci2 instanceof FerrariSiralamaModu) {
                // 'FerrariF1Araci' türündeki referansı 'FerrariSiralamaModu' türüne dönüştürüyoruz (Downcasting)
                FerrariSiralamaModu siralamaAraci = (FerrariSiralamaModu) garajAraci2;

                siralamaAraci.morSektorDerecesiAt();
            }
        }
    }

