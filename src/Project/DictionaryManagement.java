package Project;

import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary;

    public DictionaryManagement() {
        this.dictionary = new Dictionary();
    }

    public Dictionary getDictionnary() {
        return dictionary;
    }

    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap so luong tu muon them : ");
        int num = Integer.parseInt(scan.next());
        String target;
        String expain;
        scan.nextLine();
        for (int i = 0; i < num; i++) {
            System.out.print("Nhap tu tieng anh : ");
            target = scan.nextLine();
            System.out.print("Nhap nghia : ");
            expain = scan.nextLine();
            Word word = new Word(target,expain);
            dictionary.getWordsList().add(word);
        }
    }

    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu tieng anh muon tim : ");
        String str  = sc.nextLine();
        boolean check = false;
        for (int i = 0; i < dictionary.getWordsList().size(); i++) {
            if (dictionary.getWordsList().get(i).getWordTarget().equals(str)) {
                System.out.println(dictionary.showWordAt(i));
                check = true;
            }
        }
        if (!check) {
            System.out.println("Khong tim thay!");
        }
    }

    public void removeWord() {
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            System.out.println("Lua chon tim kiem tu muon xoa : ");
            System.out.println("1 : Tieng Anh");
            System.out.println("2 : Tieng Viet");
            choose = sc.nextInt();
        } while (choose != 1 && choose != 2);
        sc.nextLine();
        boolean check = false;
        if (choose == 1) {
            System.out.println("Nhap tu tieng anh can tim xoa : ");
            String wordRemove = sc.nextLine();
            for (int i = 0; i < dictionary.getWordsList().size(); i++) {
                if (dictionary.getWordsList().get(i).getWordTarget().equals(wordRemove)) {
                    dictionary.getWordsList().remove(i);
                    check = true;
                }
            }
        } else if (choose == 2) {
            System.out.print("Nhap tu tieng viet can tim xoa : ");
            String wordRemove = sc.nextLine();
            for (int i = 0; i < dictionary.getWordsList().size(); i++) {
                if (dictionary.getWordsList().get(i).getWordExplain().equals(wordRemove)) {
                    dictionary.getWordsList().remove(i);
                    check = true;
                }
            }
        }
        if (!check) {
            System.out.println("Khong tim thay tu can xoa!");
        }
    }
}
