/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uts_banking;

import java.util.ArrayList;
import java.util.Scanner;
import static uts_banking.bilanganChecker.getValidNumber;

/**
 *
 * @author Asus
 */
public class UTS_Banking {

    private ArrayList<bankAccount> accounts = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Registrasi Akun Baru");
            System.out.println("2. Mengirim Uang");
            System.out.println("3. Menyimpan Uang");
            System.out.println("4. Mengecek Informasi Rekening Pribadi");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: \n\n");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            switch (choice) {
                case 1:
                    registerNewAccount(scanner);
                    break;
                case 2:
                    sendMoney(scanner);
                    break;
                case 3:
                    saveMoney(scanner);
                    break;
                case 4:
                    checkAccountInfo(scanner);
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
    //==================================
    // Method untuk registrasi akun baru
    //==================================
    private void registerNewAccount(Scanner scanner) {
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan alamat: ");
        String alamat = scanner.nextLine();
        
    //Memeriksa jika nomor telepon bertipe bilangan bulat
        String nomorTelepon;     
        do{
            System.out.print("Masukkan nomor telepon: ");
            nomorTelepon = scanner.nextLine();
            if (!bilanganChecker.isNumeric(nomorTelepon)) {
                System.out.println("Nomor akun pengirim harus berupa angka. Silakan coba lagi.");
            }
        }while (!bilanganChecker.isNumeric(nomorTelepon));
        
        System.out.print("Masukkan saldo awal: ");
        int saldo = scanner.nextInt();

        bankAccount newAccount = new bankAccount(nama, alamat, nomorTelepon, saldo);
        accounts.add(newAccount);
        System.out.println("Registrasi berhasil. Nomor akun Anda adalah: " + newAccount.getNomorAkun()+"\n\n");
    }
    //==================================
    // Method untuk mengirim uang antar akun
    //==================================
    private void sendMoney(Scanner scanner) {
        // Memeriksa inputan user bilangan bulat atau bukan
        String nomorAkunPengirim;     
        do{
            System.out.print("Masukkan nomor akun pengirim: ");
            nomorAkunPengirim = scanner.nextLine();
            if (!bilanganChecker.isNumeric(nomorAkunPengirim)) {
                System.out.println("Nomor akun pengirim harus berupa angka. Silakan coba lagi.");
            }
        }while (!bilanganChecker.isNumeric(nomorAkunPengirim));
        
        // Memeriksa inputan user bilangan bulat atau bukan
        String nomorAkunPenerima;     
        do{
            System.out.print("Masukkan nomor akun penerima: ");
            nomorAkunPenerima = scanner.nextLine();
            if (!bilanganChecker.isNumeric(nomorAkunPenerima)) {
                System.out.println("Nomor akun penerima harus berupa angka. Silakan coba lagi.");
            }
        }while (!bilanganChecker.isNumeric(nomorAkunPenerima));
        
        System.out.print("Masukkan jumlah uang yang akan ditransfer: ");
        int jumlahTransfer = scanner.nextInt();

        bankAccount pengirim = findAccountByNumber(nomorAkunPengirim);
        bankAccount penerima = findAccountByNumber(nomorAkunPenerima);

        if (pengirim != null && penerima != null) {
            pengirim.transfer(jumlahTransfer);
            penerima.topUp(jumlahTransfer);
            System.out.println("Transfer berhasil. \n\n");
        } else {
            System.out.println("Nomor akun tidak ditemukan.\n\n");
        }
    }

    //==================================
    // Method untuk menyimpan uang ke dalam akun
    //==================================
    private void saveMoney(Scanner scanner) {
        
        //menyimpan uang dengan mengecek nomor akun user
        String nomorAkun;     
        do{
            System.out.print("Masukkan nomor akun anda: ");
            nomorAkun = scanner.nextLine();
            if (!bilanganChecker.isNumeric(nomorAkun)) {
                System.out.println("Nomor akun anda harus berupa angka. Silakan coba lagi.");
            }
        }while (!bilanganChecker.isNumeric(nomorAkun));
        
        
        System.out.print("Masukkan jumlah uang yang akan disimpan: ");
        int jumlahTopUp = scanner.nextInt();

        bankAccount account = findAccountByNumber(nomorAkun);
        if (account != null) {
            account.topUp(jumlahTopUp);
            System.out.println("Uang berhasil disimpan.\n\n");
        } else {
            System.out.println("Nomor akun tidak ditemukan.\n\n");
        }
    }
    
    //==================================
    // Method untuk mengecek informasi rekening pribadi
    //==================================
    private void checkAccountInfo(Scanner scanner) {
        
        //mengecek informasi akun berupa angka dari nomor akun user
        String nomorAkun;     
        do{
            System.out.print("Masukkan nomor akun anda: ");
            nomorAkun = scanner.nextLine();
            if (!bilanganChecker.isNumeric(nomorAkun)) {
                System.out.println("Nomor akun anda harus berupa angka. Silakan coba lagi.");
            }
        }while (!bilanganChecker.isNumeric(nomorAkun));

        bankAccount account = findAccountByNumber(nomorAkun);
        if (account != null) {
            account.showDescription();
        } else {
            System.out.println("Nomor akun tidak ditemukan.\n\n");
        }
    }
    
    //==================================
    // Method untuk mencari akun berdasarkan nomor akun
    //==================================
    private bankAccount findAccountByNumber(String nomorAkun) {
        for (bankAccount account : accounts) {
            if (account.getNomorAkun().equals(nomorAkun)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        
        UTS_Banking uts_banking = new UTS_Banking();
        uts_banking.showMenu();
    }
}
