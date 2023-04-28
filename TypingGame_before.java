import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.print(">");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String startGameText = "java TypingGame";
        String inputText = br.readLine();
        
        ArrayList<Long> ranking_list = new ArrayList<>(); 
        String retryText;

        //"java TypingGame"と入力したらゲームが始まる
        if(!(inputText.equals(startGameText))) return;

        do{
            //難易度選択
            System.out.println("難易度を選択してください(1-3)");
            System.out.println("1: Easy");
            System.out.println("2: Nomal");
            System.out.println("3: Hard");
            System.out.print(">");
            inputText = br.readLine();
            
            //カウントダウン
            Count count = new Count();
            count.countdown();
            
            //TypingGame開始～終了
            Game game = new Game();
            long game_time = game.typing(inputText);

            //ランキング1-10位の表示                      
            Ranking ranking = new Ranking();
            ranking.ranking_top10(game_time, ranking_list);

            //リトライ
            System.out.println("リトライしますか？ (1:する 2:しない)");
            System.out.print(">");
            retryText = br.readLine();
        } while(retryText.equals(Const.IsRetry.YES.getValue()));       
    }
   
    
}

class Const{
    //リトライするかしないか
    public enum IsRetry{
        YES("1"),
        NO("2");

        private String retry_num;

        private IsRetry(String retry_num)
        {
            this.retry_num = retry_num;
        }

        public String getValue(){
            return this.retry_num;
        }
    }

    //難易度定数
    public enum Difficulty{
        EASY("1"),
        NOMAL("2"),
        HARD("3"),
        DEFAULT("4");

        private String difficulty_num;

        private Difficulty(String difficulty_num)
        {
            this.difficulty_num = difficulty_num;
        }

        private String getValue()
        {
            return this.difficulty_num;
        }

        //入力された文字と一致するか(Enumの値を返す)
        public static Difficulty getEnumValue(String strnum)
        {
            for(Difficulty d : values())
            {
                if(d.getValue().equals(strnum)) return d;                
            }

            return DEFAULT;
        }
    }
}

class Count{
    void countdown()
    {
        try
        {
            //5秒カウント
            for(int i = 5; i>0; i--)
            {
                System.out.println(i + ".......");
                Thread.sleep(1000);
            }
            System.out.println("start!");
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }      
    }
}

class Game
{
    private InputStreamReader isr = new InputStreamReader(System.in);
    private BufferedReader br = new BufferedReader(isr);

    //問題
    private String[] questions = {"long", "short", "rain", "sunny", "cloud", "snow"};
    private Random random = new Random();

    //タイピングゲーム時間計測開始～終了まで
    long typing(String inputText)
    {
        //時間計測開始
        long start_point = System.currentTimeMillis();
        
        String strnum = inputText;        
        int[] numbers = new int[1];
        //問題数分岐
        switch(Const.Difficulty.getEnumValue(strnum)){
            //難易度1:Easyの時
            case EASY:
                numbers[0] = 5;
                break;
            //難易度2:Nomalの時
            case NOMAL:
                numbers[0] = 10;
                break;
            //難易度3:Hardの時
            case HARD:
                numbers[0] = 20;
                break;
            default:
                numbers[0] = 10;
                break;
        }       
        int i = numbers[0];
        
        //問題ここから
        String beforeQuestion = "";
        try
        {
            while(i > 0)
            {
                String question = questions[random.nextInt(questions.length)];
                
                if(!question.equals(beforeQuestion))
                {
                    String answer = "";
                    do{
                        System.out.print(question + ":");
                        answer = br.readLine();
                        if(!answer.equals(question))
                        {
                            System.out.println("Miss...");
                        }
                    }while(!answer.equals(question));
                        
                    System.out.println("OK!");
                    i -= 1;                                 
                }           
                beforeQuestion = question;
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        //計測終了
        long end_point = System.currentTimeMillis();
        long game_time = end_point - start_point;
        System.out.println("Finished." + " " + "time=" + game_time + "[ms]");
        return game_time;
    }
}

class Ranking{
    void ranking_top10(long game_time, ArrayList<Long> ranking_list)
    {
        ranking_list.add(game_time);
        Collections.sort(ranking_list);
        int j = 1;
        for(int idx = 0; idx<=9; idx++)
        {
            if(idx < ranking_list.size())
            {
                System.out.println(j + ": " + ranking_list.get(idx) + "[ms]");
            }
            else
            {
                System.out.println(j + ": 9999999999999999[ms]");
            }

            j++;
        }
    }  
}


