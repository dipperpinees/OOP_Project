package Project;

import java.util.Scanner;

public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline() {
        this.dictionaryManagement = new DictionaryManagement();
    }

    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("NO\t\t" + "|ENGLISH\t\t" + "|VIETNAMESE");
        for (int i = 0;i < dictionaryManagement.getDictionnary().getWordsList().size(); i++) {
            System.out.println(i + "\t\t" + dictionaryManagement.getDictionnary().showWordAt(i));
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
        dictionaryManagement.dictionaryLookup();
    }

    public void play() {
        Scanner scan = new Scanner(System.in);
        int num;
        while (true) {
            System.out.println("Lua chon :");
            System.out.println("1 : Them tu vao danh sach tu dien.");
            System.out.println("2 : Tim kiem tu trong tu dien.");
            System.out.println("3 : In danh sach tu dien.");
            System.out.println("4 : Xoa tu trong tu dien.");
            System.out.println("5 : Thoat");
            num = scan.nextInt();
            if (num == 1) {
                dictionaryManagement.insertFromCommandline();
            } else if (num == 2) {
                dictionaryManagement.dictionaryLookup();
            } else if (num == 3) {
                showAllWords();
            } else if (num == 4) {
                dictionaryManagement.removeWord();
            }
            else if (num == 5) {
                break;
            }
        }
    }

}
