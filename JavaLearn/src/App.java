import java.util.Scanner;
public class App {
    static Scanner s = new Scanner(System.in);
    static String nama="";
    static int jamBerangkat=0;
    static boolean pembelihanBerhasil=false;
    static String[] DataMenu={"Tiket Jayabaya","Tiket Kertajaya","Tiket Jayakarta"};
    static String[] rute1={"Jakarta-Lamongan","Jakarta-Yogyakarta","Jakarta-Surabaya","Bandung-Yogyakarta","Yogyakarta-Surabaya"};
    static String[] rute2={"Jakarta-Jember","Jakarta-Depok","Jakarta-Klaten","Klaten-Solo","Klaten-Surabaya"};
    static String[] rute3={"Surabaya-Banten","Surabaya-Lamongan","Mojokerto-Bandung","Purwakarta-Solo","Puwakarta-Surabaya"};
    static int[] jamKeberangkatan={2,8,10,12,14};
    static int[] jamSampai={9,5,22,13,18};
    static String[] caraPembayaran={"BRI","Mandiri","BNI"};
    //method menemukan rute
    static int findRute(String[] rute, String target) {
        for (int index = 0; index < rute.length; index++) {
            if (rute[index].equals(target)) {
                return index;
            }
        }
        return -1;
    }
    //sorting jam kereta
    static int[] sortJamKereta(int[] jam){
        int n=jam.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (jam[j] > jam[j + 1]) {
                    int temp = jam[j];
                    jam[j] = jam[j + 1];
                    jam[j + 1] = temp;
                }
            }
        }
        return jam;
    }
    //method menu
    static String menu(){
        System.out.println("Daftar Tiket");
        for(int i=0;i<DataMenu.length;i++){
            System.out.println((i+1)+". "+DataMenu[i]);
        }
        System.out.print("Pilih Tiket: ");
        int InputUser= s.nextInt();
        if(InputUser-1> DataMenu.length){
            return "Error";
        }
        return DataMenu[InputUser-1];
    }
    static void displayTiapRute(int displayPilihanRute){
        if(displayPilihanRute==1){
            for(int i=0;i<rute1.length;i++){
                System.out.println((i+1)+". "+rute1[i]);
            }
        }else if(displayPilihanRute==2){
            for(int i=0;i<rute2.length;i++){
                System.out.println((i+1)+". "+rute2[i]);
            }
        }else if(displayPilihanRute==3){
            for(int i=0;i<rute3.length;i++){
                 System.out.println((i+1)+". "+rute3[i]);
            }
        }
        System.out.println("");
    }
    //method display rute
    static String displayRute(String tiketKereta){
        String rutePilihan= "";
        int noRute=0;
        System.out.println(" ");
        switch (tiketKereta) {
            case "Tiket Jayabaya" :
                System.out.println("Daftar Rute : "+tiketKereta);
                System.out.println("");
                displayTiapRute(1);
                System.out.print("Pilih Rute Anda: ");
                noRute=s.nextInt();
                rutePilihan= rute1[noRute-1];
                break;
            case "Tiket Kertajaya":
                System.out.println("Daftar Rute : "+tiketKereta);
                System.out.println("");
                displayTiapRute(2);
                System.out.print("Pilih Rute Anda: ");
                noRute=s.nextInt();
                rutePilihan= rute2[noRute-1];
                break;
            case "Tiket Jayakarta": 
                System.out.println("Daftar Rute : "+tiketKereta);
                displayTiapRute(3);
                System.out.print("Pilih Rute Anda: ");
                noRute=s.nextInt();
                rutePilihan= rute3[noRute-1];
                break;  
            default:
            System.out.println("Something is wrong");
                break;
        }
        return rutePilihan;
    }
    //method display harga per rute
    static void displayHargaTiket(int[] hargaTiketPerRute, int[] arrJamberangkat, int[] arrJamSampai,String rute, int adaRute){
        System.out.println("");
        System.out.println("Rute Pilihan : "+rute);
        System.out.println("");

         for(int i=0;i<hargaTiketPerRute.length;i++){
            System.out.println((i+1)+". Rute : "+rute+", Pada Jam : "+arrJamberangkat[i]+"-"+arrJamSampai[i]+" WIB , harga : "+(hargaTiketPerRute[i]*adaRute));
        }
    }
    static void displayCarabayar(){
        for(int i=0; i<caraPembayaran.length;i++){
            System.out.println((i+1)+". "+caraPembayaran[i]);
        }
    }
    static int PilihJamTiapRute(int[] hargaTiketPerRute, int[] arrJamberangkat, int[] arrJamSampai, String rute, int adaRute1){
            int PilihanUser=0;
            displayHargaTiket(hargaTiketPerRute, arrJamberangkat, arrJamSampai, rute, adaRute1==0?5:adaRute1);
            System.out.println(" ");
            System.out.print("Pilih jam keberangkatan anda : ");
            PilihanUser=s.nextInt();
            return PilihanUser;
    }
    static String hargaTiket(String rute){
        String pesanan="";
        int adaRute1=findRute(rute1, rute);
        int adaRute2=findRute(rute2, rute);
        int adaRute3=findRute(rute3, rute);
        
        if(adaRute1!=-1){
            int pilihJam=0;
            int bayar=0;
            int aksibayar=0;
            int caraBayar=0;
            int[] hargaTiketPerRute={260000,350000,150000,85000,100000};
            int[] arrJamberangkat=sortJamKereta(jamKeberangkatan);
            int[] arrJamSampai=sortJamKereta(jamSampai);

            pilihJam=PilihJamTiapRute(hargaTiketPerRute, arrJamberangkat, arrJamSampai, rute,  (adaRute1==0?5:adaRute1));

            System.out.println("1. Bayar");
            System.out.println("2. Back");
            System.out.print("Pilih Aksi Selanjutnya : "); 
            aksibayar=s.nextInt();

            if(aksibayar==1){
                displayCarabayar();
                System.out.print("Pilih Metode Pembayaran : ");
                caraBayar=s.nextInt();
                System.out.println("Total Bayar "+(hargaTiketPerRute[pilihJam-1]*(adaRute1==0?5:adaRute1)));
                System.out.print("Masukkan Uang Anda : ");
                bayar=s.nextInt();
                if((hargaTiketPerRute[pilihJam-1]*(adaRute1==0?5:adaRute1))>bayar){
                    pesanan="Maaf pembayaran tidak berhasil, uang anda kurang "+((hargaTiketPerRute[pilihJam-1]*(adaRute1==0?5:adaRute1))-bayar);
                }else if(bayar==(hargaTiketPerRute[pilihJam-1]*(adaRute1==0?5:adaRute1))){
                    pembelihanBerhasil=true;
                    jamBerangkat=arrJamberangkat[pilihJam-1];
                    pesanan="Terimakasih pesanan atas nama "+nama+", dengan keberangkatan kereta pukul "+arrJamberangkat[pilihJam-1]+" WIB";
                }else{
                    pembelihanBerhasil=true;
                    jamBerangkat=arrJamberangkat[pilihJam-1];
                    pesanan="Terimakasih pesanan atas nama "+nama+", dengan keberangkatan kereta pukul "+arrJamberangkat[pilihJam-1]+" WIB \n Kembalian anda : "+(bayar-(hargaTiketPerRute[pilihJam-1]*(adaRute1==0?5:adaRute1)));
                }
            }else if(aksibayar==2){
                pilihJam=PilihJamTiapRute(hargaTiketPerRute, arrJamberangkat, arrJamSampai, rute,  adaRute1==0?5:adaRute1);
                System.out.println("1. Bayar");
                System.out.println("2. Back");
            }
        }else if(adaRute2!=-1){
            int pilihJam=0;
            int bayar=0;
            int aksibayar=0;
            int caraBayar=0;
            int[] hargaTiketPerRute={200000,300000,150000,830000,700000};
            int[] arrJamberangkat=sortJamKereta(jamKeberangkatan);
            int[] arrJamSampai=sortJamKereta(jamSampai);

            pilihJam=PilihJamTiapRute(hargaTiketPerRute, arrJamberangkat, arrJamSampai, rute, adaRute2==0?5:adaRute2);

            System.out.println("1. Bayar");
            System.out.println("2. Back");
            System.out.print("Pilih Aksi Selanjutnya : "); 
            aksibayar=s.nextInt();

            if(aksibayar==1){
                displayCarabayar();
                System.out.print("Pilih Metode Pembayaran : ");
                caraBayar=s.nextInt();
                System.out.println("Total Bayar "+(hargaTiketPerRute[pilihJam-1]*(adaRute2==0?5:adaRute2)));
                System.out.print("Masukkan Uang Anda : ");
                bayar=s.nextInt();
                if((hargaTiketPerRute[pilihJam-1]*(adaRute2==0?5:adaRute2))>bayar){
                    pesanan="Maaf pembayaran tidak berhasil, uang anda kurang "+((hargaTiketPerRute[pilihJam-1]*(adaRute2==0?5:adaRute2))-bayar);
                }else if(bayar==(hargaTiketPerRute[pilihJam-1]*(adaRute2==0?5:adaRute2))){
                    pembelihanBerhasil=true;
                    jamBerangkat=arrJamberangkat[pilihJam-1];
                    pesanan="Terimakasih pesanan atas nama "+nama+", dengan keberangkatan kereta pukul "+arrJamberangkat[pilihJam-1]+" WIB";
                }else{
                    pembelihanBerhasil=true;
                    jamBerangkat=arrJamberangkat[pilihJam-1];
                    pesanan="Terimakasih pesanan atas nama "+nama+", dengan keberangkatan kereta pukul "+arrJamberangkat[pilihJam-1]+" WIB \n Kembalian anda : "+(bayar-(hargaTiketPerRute[pilihJam-1]*(adaRute2==0?5:adaRute2)));
                }
            }else if(aksibayar==2){
                pilihJam=PilihJamTiapRute(hargaTiketPerRute, arrJamberangkat, arrJamSampai, rute, (adaRute2==0?5:adaRute2));
                System.out.println("1. Bayar");
                System.out.println("2. Back");
            }
        }else if(adaRute3!=-1){
            int pilihJam=0;
            int bayar=0;
            int aksibayar=0;
            int caraBayar=0;
            int[] hargaTiketPerRute={900000,400000,350000,500000,600000};
            int[] arrJamberangkat=sortJamKereta(jamKeberangkatan);
            int[] arrJamSampai=sortJamKereta(jamSampai);

            pilihJam=PilihJamTiapRute(hargaTiketPerRute, arrJamberangkat, arrJamSampai, rute, (adaRute3==0?5:adaRute3));

            System.out.println("1. Bayar");
            System.out.println("2. Back");
            System.out.print("Pilih Aksi Selanjutnya : "); 
            aksibayar=s.nextInt();

            if(aksibayar==1){
                displayCarabayar();
                System.out.print("Pilih Metode Pembayaran : ");
                caraBayar=s.nextInt();
                System.out.println("Total Bayar "+(hargaTiketPerRute[pilihJam-1]*(adaRute3==0?5:adaRute3)));
                System.out.print("Masukkan Uang Anda : ");
                bayar=s.nextInt();
                if((hargaTiketPerRute[pilihJam-1]*(adaRute3==0?5:adaRute3))>bayar){
                    pesanan="Maaf pembayaran tidak berhasil, uang anda kurang "+((hargaTiketPerRute[pilihJam-1]*(adaRute3==0?5:adaRute3))-bayar);
                }else if(bayar==(hargaTiketPerRute[pilihJam-1]*(adaRute3==0?5:adaRute3))){
                    pembelihanBerhasil=true;
                    jamBerangkat=arrJamberangkat[pilihJam-1];
                    pesanan="Terimakasih pesanan atas nama "+nama+", dengan keberangkatan kereta pukul "+arrJamberangkat[pilihJam-1]+" WIB";
                }else{
                    pembelihanBerhasil=true;
                    jamBerangkat=arrJamberangkat[pilihJam-1];
                    pesanan="Terimakasih pesanan atas nama "+nama+", dengan keberangkatan kereta pukul "+arrJamberangkat[pilihJam-1]+" WIB \n Kembalian anda : "+(bayar-(hargaTiketPerRute[pilihJam-1]*(adaRute3==0?5:adaRute3)));
                }
            }else if(aksibayar==2){
                pilihJam=PilihJamTiapRute(hargaTiketPerRute, arrJamberangkat, arrJamSampai, rute, (adaRute3==0?5:adaRute3));
                System.out.println("1. Bayar");
                System.out.println("2. Back");
            }
        }
        return pesanan;
    }


    public static void main(String[] args) {
        boolean membeliTiket=true;
        String[] daftarPembelihanTiket = new String[10];
        int quantity=0;
        int aksiUser=0;
        String[] menu={"Input UserName","Ubah userName","Membeli Tiket","Keluar"};
        while (membeliTiket) {
            pembelihanBerhasil=false;
            System.out.println("\t!===============!");
            System.out.println("Selamat Datang Di IsmaTiket");
            System.out.println("\t   !========!");
            for(int i=0; i<menu.length;i++){
                System.out.println((i+1)+". "+menu[i]);
            }
            System.out.print("Pilih Menu Anda : ");
            aksiUser=s.nextInt();
            switch (aksiUser) {
                case 1:
                    System.out.println("Masukkan Nama Anda : ");
                    s.nextLine();
                    nama=s.nextLine();
                break;
                case 2:
                    System.out.println("Ubah Nama Anda  Nama Anda : ");
                    s.nextLine();
                    nama=s.nextLine();
                break;
                case 3:
                    System.out.println("");
                    String menuTiket=menu();
                    String pilihanRute=displayRute(menuTiket);
                    String aksi=hargaTiket(pilihanRute);
                    System.out.println(aksi);
                    daftarPembelihanTiket[quantity]="Nama Penumpang : , "+nama+(quantity+1)+"Tipe Kereta : "+menuTiket+", pilihan rute : "+pilihanRute+" jam Keberangkatan : "+jamBerangkat+" WIB";
                    quantity++;
                break;
                case 4:
                    System.out.println("Keluar");
                    System.out.println("Terima Kasih Atas Pembelihan Anda");
                    System.out.println("=================================");
                    System.out.println("");
                   for(int i=0; i<daftarPembelihanTiket.length;i++){
                        if(daftarPembelihanTiket[i]!=null){
                            System.out.println(daftarPembelihanTiket[i]); 
                        }   
                    }
                    membeliTiket=false;
                break;
                default:
                System.out.println("somethings Wrong!!!");
                break;
            }

        }

    }

}


