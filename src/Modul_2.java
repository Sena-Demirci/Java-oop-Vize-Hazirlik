// Primitif vs. Referans Türler, Bellek Mimarisi (\`Stack/Heap\`), Kapsülleme ve Soyutlama

// ---Garbage Collector ve Referans Testi İçin Oyuncu Sınıf ----
class Oyuncu{
    int seviye = 1;
    String isim = "Bilinmeyen";

    // Parametresiz varsayılan yapıcı (default constructor):
    // parametreli ypaıcı tanımlandıpında javanın otomatik varsayılnan yapıcısı silindiği için
    // 'new Oyuncu ()' kullanımına tekrar izin vermek üzere tanımlandı.
    public Oyuncu() {}  //

    public Oyuncu(String isim) {
        this.isim = isim;
    }

}

//---Kapsülleme için sınıf----
class BankaHesabi {
    private double bakiye = 0; // kapsülleme: private alan

    // Getter / Setter ve iş metotları kendi sınıfının içine yazılır:
    public void paraYatir(double tutar) {
        if(tutar>0) {
            bakiye += tutar;
            System.out.println(tutar + " TL yatirildi.");
        }else {
            System.out.println("Geçersiz tutar!");
        }
    }
    public double getBakiye() {
        return bakiye;
    }
}

// --- Soyutlama İçin Sınıf ---
class Kisi {
    private String ad;
    private int dogumYili;

    public Kisi(String ad , int dogumYili) {
        this.ad = ad;
        this.dogumYili = dogumYili;
    }
    public int getYas() {
        return 2026 - dogumYili;
    }
    //Metot ismi tamamen küçük 'i' harfiyle tanit:
    public void tanit() {
        System.out.println(ad + " ," + getYas() + " yaşında.");
    }
}


public class Modul_2 {
    // === 2\. YARDIMCI METOTLAR ===
    public static void caniArttir(int can) {
        can = can + 50; // sadece bu metodun yerel stack çerçevesindeki 'can' değişir.
    }
    public static void seviyeAtlat(Oyuncu oyuncu) {
        oyuncu.seviye += 1; // Adres kopyası üzerinden Heap'teki nesnenin içi değişitrilir.
    }
    public static void yeniOyuncuBagla(Oyuncu oyuncu) {
        oyuncu = new Oyuncu(); // yerel 'oyuncu' değişkenine yeni bir heap adresi atanır (eski bağ kopar.)
        oyuncu.seviye = 500;
    }

    // 2\. MAIN METODU (Komutlar ve çalıştırılabilir kodlar BURADA olmalıdır)
    public static void main(String[] args) {
        System.out.println("=== 1.ATAMA DİNAMİKLERİ (STACK VS HEAP) ====");

        // --- A) PRİMİTİF TÜR ATAMASI
        // primitif değişkenler (int, double, boolean vbvb) değerlerini doğrudan stack'te tutar
        int can1 = 100;
        int can2 = can1; // Stack'teki 100 değeri okunur ve can2 kutusuna kopyalanır.

        can2 = 50; //can2'nin değeri değişir ama can1 bundan hiçbir şekilde etkilenmez
        System.out.println("can1: " + can1); //çıkyı = 100
        System.out.println("can2 : " + can2); // çıktı= 50

        // --- B) REFERANS TÜR ATAMASI ---
        //referans değişkenleri nesnenin kendisi değil, heaptaki adresini yani referansını tutar.

        // 1.durum: Aynı bellek adresini paylaşma
        Oyuncu o1 = new Oyuncu(); // Heap'teki yeni bir oyuncu nesnesi açılır, adresi o1'e verilir.
        o1.seviye = 50;

        Oyuncu o2 = o1; // o1'in tuttuğu heap adresi o2'ye kopyalanır, artık ikisi de aynı nesneye bakar.
        o2.seviye = 50; // o2 üzerinde yapılan değişiklik, o1'in gösterdiği nesneyi de değiştirir.

        System.out.println("o1.seviye: " + o1.seviye); //çıktı = 50;
        System.out.println("o2.seviye: " + o2.seviye); // çıktı = 50

        //2.durum: 2\. Durum: İçerikleri/Değerleri Aynı Ama Ayrı Nesneler (new ile açılan)
        Oyuncu p1 = new Oyuncu(); // 1\. nesne oluşturuldu , referansı meslea @15239 olsun
        p1.seviye = 100;

        Oyuncu p2 = new Oyuncu(); // 2\. nesne oluşturuldubun da referansı @15238 olsun
        p2.seviye = 100;
        // iki nesnenin referansları farklı ve içerdikleri değer aynı.
        System.out.println("p1 == p2 (Aynı adres mi? " + (p1 == p2)); // false çünkü referasnlar aynı değil.
        System.out.println("p1.equals(p2) (içerikleri eşit mi?)" + p1.equals(p2)); // true çıkar çünkü bu fonskiyon içerikleri karşılaştırır.


        System.out.println("\\n === METOTLARA PARAMETRE AKTARIMI (PASS-BY-VALUE) ===");
        int mainCan = 100;
        caniArttir(mainCan);
        // Java'da her zaman pass-by-value geçerlidir.
        // mainCan'ın bir kopyası metoda gittiği için orijinal değişken değişmez.

        System.out.println("mainCan (metot sonrasi): " + mainCan); // Çıktı: 100
        Oyuncu mainOyuncu = new Oyuncu();
        mainOyuncu.seviye = 5;
        seviyeAtlat(mainOyuncu);
        System.out.println("mainOyuncu.seviye (seviyeAtlat sonrası): " + mainOyuncu.seviye); // Çıktı: 6 (100 olmadı)

        // 3.STRING POOL VS NEW STRING
        String s1 = "Java"; // Java önce String poola bakar , eğer havuzda "Java" texti yoksa s1'e adres verir
        String s2 = "Java"; // java havuza tekrar bakar ve "havuzda zaten "java" texti var" der ve  s1'deki adresi verir.
        String s3 = new String("Java"); // Java string poola bakmaz, direkt heapta zorla yeni bir nesne açar.

        System.out.println ( "s1 == s2: " + (s1 == s2)); // true çünkü s1 ve s2 primitiftir ve == ifadesi direkt içerik karşılaştırır
        System.out.println("s1 == s3: " + (s1 == s3)); // false çünkü biri primitif ve kendi adresi var, diğeri de nesne ve onun da adresi var
        // adresleri yani heap referasnları farklı olduğundan direkt false olur
        System.out.println("s1.equals(s3): " + s1.equals(s3)); //true çünkü equals fonksiyonu direkt yapının türüne bakmaksızın içerik karşılaştırır.

        // === Soyutlama Testi ===
        System.out.println("=== SOYUTLAMA TESTİ ===");
        //nesne üretme ve metot çağırma komutları main içine yazılır:
        Kisi k = new Kisi ("Sena" , 2007);
        k.tanit(); // Çıktı: Sena, 19 yaşında.

        System.out.println("\n === Kapsülleme Testi===");
        BankaHesabi hesap = new BankaHesabi();
        hesap.paraYatir(1000); // metot çağırı main içinde
        System.out.println("Güncel bakiye: " + hesap.getBakiye());

        System.out.println("\\n=== 3. GARBAGE COLLECTOR TESTİ ===");
        Oyuncu o3 = new Oyuncu ("Bezzza");
        Oyuncu o4 = new Oyuncu ( "shilethal");
        o2 = null; // o2'in bağını kopardik -> 'Bezzza' nesnesi GC tarafından silinmeye uygun hale geldi
        System.out.println("o1 referansı null yapıldı.GC sahipsiz nesneyi toplayacak.");


    }
}