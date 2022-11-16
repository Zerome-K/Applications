package thirukkural.controller;

import thirukkural.model.Kural;
import thirukkural.repository.Database;
import thirukkural.view.MainView;

import java.util.*;

public class MainController {
    private final MainView mainView;
    ArrayList<Kural> kurals = Database.getInstance().getKural();

    public MainController(MainView mainView) {
        this.mainView = mainView;
    }

    public void boundaryPrinter(int start, int end) {
        for (int index = start; index <= end; index++)
            searchByNumber(index);
    }

    public void searchByNumber(int num) {
        Kural kural = kurals.get(num - 1);
        mainView.printKural(kural);
    }

    public void searchByWord(String word) {
        for (Kural kural : kurals) {
            String line1 = kural.getLine1();
            String line2 = kural.getLine2();
            if (line1.contains(word) || line2.contains(word)) {
                mainView.printKural(kural);
            }
        }
    }

    public void getAllKural() {
        String message = "*-- ALL KURAL --*";
        mainView.message(message);
        for (Kural kural : kurals)
            mainView.printKural(kural);
    }

    public void kuralGame() {
        Random random = new Random();
        while (true) {
            int position = random.nextInt(1331);
            Kural kural = kurals.get(position);
            String sentence = kural.getLine1() + " " + kural.getLine2();
            ArrayList<String> array = new ArrayList<>(List.of(sentence.split("\\s")));
            String[] vacantArray = {"----1----", "----2----", "----3----", "----4----", "----5----", "----6----", "----7----"};
            for (int rounds = 0; rounds < 7; rounds++) {
                mainView.message(Arrays.toString(vacantArray));
                arrayShuffler(array);
                mainView.gamePrinter(array);
                System.out.println("\nSELECT PHRASE : ");
                int number = mainView.getNumericInput();
                if (!(number < 1 || number > 7 - rounds)) {
                    vacantArray[rounds] = array.get(number - 1);
                    array.remove(number - 1);
                } else rounds -= 1;
            }
            StringBuilder answer = new StringBuilder();
            for (String string : vacantArray)
                answer.append(string).append(" ");
            answer.deleteCharAt(answer.length() - 1);
            if (!Objects.equals(sentence, answer.toString())) {
                String message = "*-- FAILED --*\n" + "CORRECT ANSWER -> " + sentence ;
                mainView.message(message);
            } else mainView.message("*-- YOU WON --*");
            mainView.message("1. RETRY \n2. EXIT");
            int input = mainView.getNumericInput();
            if (input == 2) break;
        }
    }

    private void arrayShuffler(List<String> array) {
        for (int i = array.size() - 1; i >= 0; i--) {
            int position = new Random().nextInt(array.size());
            if (position != i) {
                String temp = array.get(i);
                array.set(i, array.get(position));
                array.set(position, temp);
            }
        }
    }
}


