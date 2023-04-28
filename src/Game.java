import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    //難易度別ランキング
    Ranking[] rankings = {new Ranking(), new Ranking(), new Ranking()};

    public void start()
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        while(true){
            //難易度選択
            String level = select(br);
            Ranking ranking = getLevelRanking(Integer.parseInt(level));
            
            //カウントダウン
            Count.countdown();

            //開始の合図
            System.out.println("start!");

            //計測開始
            StopWatch watch = new StopWatch();
            watch.start();
            
            //問題出題
            Question question = new Question(level);
            question.question();

            //計測終了
            watch.stop();

            //計測終了合図＋計測時間表示
            System.out.printf("Finished. time=%d[ms]\n", watch.getTime());

            //ランキング1-10位の表示                                 
            ranking.ranking_top10(watch.getTime());

            //リトライするか
            if(!isRetry(br)) break;
        }
    }

    //難易度選択(入力した難易度を返す)
    private String select(BufferedReader br)
    {
        String text = "";
        try
        {
            System.out.println("難易度を選択してください(1-3)");
            System.out.println("1: Easy");
            System.out.println("2: Nomal");
            System.out.println("3: Hard");
            System.out.print(">");
            text = br.readLine();            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return text;
    }

    //難易度別ランキング
    private Ranking getLevelRanking(int level)
    {
        return this.rankings[level - 1];
    }

    //リトライするか(true:する false:しない)
    private boolean isRetry(BufferedReader br)
    {
        System.out.println("リトライしますか？ (1:する 2:しない)");
        System.out.print(">");
        try
        {
            if (br.readLine().equals(Const.IsRetry.YES.getValue()))
                return true;                       
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        //リトライしない
        return false;
    }
}
