import java.util.Scanner;
import com.SistemTol.Bus;
import com.SistemTol.GerbangTol;
import com.SistemTol.KartuElektronik;
import com.SistemTol.Kendaraan;
import com.SistemTol.Mobil;
import com.SistemTol.Transaksi;
import com.SistemTol.Truk;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("       SISTEM TOL JAGORAWI");
        System.out.println("======================================");

        System.out.println("\nData Kendaraan");
        System.out.println("1. Mobil");
        System.out.println("2. Bus");
        System.out.println("3. Truk");
        System.out.print("Pilih jenis kendaraan : ");
        int pilihanKendaraan = input.nextInt();
        input.nextLine();

        // Objek kendaraan disediakan langsung oleh program.
        Mobil mobil = new Mobil("B 3892 AJS");
        Bus bus = new Bus("B 8219 AIJ");
        Truk truk = new Truk("B 4567 TOL");

        Kendaraan kendaraan;
        switch (pilihanKendaraan) {
            case 1: kendaraan = mobil; break;
            case 2: kendaraan = bus; break;
            case 3: kendaraan = truk; break;
            default:
                System.out.println("Jenis kendaraan tidak valid.");
                input.close();
                return;
        }

        System.out.println("\nDaftar Gerbang Tol Jagorawi");
        System.out.println("i001 - Jakarta");
        System.out.println("i002 - Sentul");
        System.out.println("i003 - Bogor");
        System.out.println("i004 - Ciawi");

        System.out.print("\nMasukkan kode gerbang awal   : ");
        String kodeAwal = input.nextLine();

        System.out.print("Masukkan kode gerbang tujuan : ");
        String kodeTujuan = input.nextLine();

        GerbangTol gerbangAwal = new GerbangTol(kodeAwal);
        GerbangTol gerbangTujuan = new GerbangTol(kodeTujuan);

        if (!gerbangAwal.isValid() || !gerbangTujuan.isValid()) {
            System.out.println("Kode gerbang tidak valid.");
            input.close();
            return;
        }

        System.out.print("\nNomor Kartu Elektronik : ");
        String nomorKartu = input.nextLine();

        System.out.print("Saldo Kartu            : Rp");
        double saldo = input.nextDouble();

        KartuElektronik kartu = new KartuElektronik(nomorKartu, saldo);
        Transaksi transaksi = new Transaksi(kendaraan, gerbangAwal, gerbangTujuan, kartu);

        transaksi.proses();
        transaksi.cetak();
        transaksi.simpanKeFile();

        input.close();
    }
}
