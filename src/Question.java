import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Question {
    private InputStreamReader isr = new InputStreamReader(System.in);
    private BufferedReader br = new BufferedReader(isr);

    //問題
    private static final String[] EASY_Q = {"long", "short", "rain", "sunny", "cloud", "snow"};
    private static final String[] NOMAL_Q = {"action", "anyway", "devote", "growth", "honest", "unsure"};
    private static final String[] HARD_Q = {"airplane", "educator", "fugitive", "guardian", "lonesome", "militant"};
    
    //問題数
    private static final int CNT_EASY = 5;
    private static final int CNT_NOMAL = 10;
    private static final int CNT_HARD = 20;

    //選択した難易度
    private String level;
    //難易度に応じた問題数
    private int q;
    //難易度に応じた問題
    private String[] questions; 

    //問題セット コンストラクタ
    Question(String level)
    {
        this.level = level;        

        //問題数分岐
        switch(Const.Difficulty.getEnumValue(this.level)){
            //難易度1:Easyの時
            case EASY:
                q = CNT_EASY;
                questions = EASY_Q;
                break;
            //難易度3:Hardの時
            case HARD:
                q = CNT_HARD;
                //配列のコピー
                questions = new String[EASY_Q.length + NOMAL_Q.length + HARD_Q.length];
                System.arraycopy(EASY_Q, 0, questions, 0, EASY_Q.length);
                System.arraycopy(NOMAL_Q, 0, questions, EASY_Q.length, NOMAL_Q.length);
                System.arraycopy(HARD_Q, 0, questions, EASY_Q.length + NOMAL_Q.length, HARD_Q.length);
                break;
            //難易度2:Nomal, デフォルトの時
            default:
                q = CNT_NOMAL;
                //配列のコピー
                questions = new String[EASY_Q.length + NOMAL_Q.length];
                System.arraycopy(EASY_Q, 0, questions, 0, EASY_Q.length);
                System.arraycopy(NOMAL_Q, 0, questions, EASY_Q.length, NOMAL_Q.length);
                break;
        }               
    }

    //ゲーム
    void question()
    {        
        //1つ前の問題との被りを阻止する
        String beforeQuestion = "";
        try
        {
            //問題出題
            while(q > 0)
            {
                Random random = new Random();
                String question = questions[random.nextInt(questions.length)];
                
                //1つ前の問題と違えば問題出題
                if(!question.equals(beforeQuestion))
                {
                    String answer = "";
                    //問題間違いの場合同じ問題を繰り返す
                    do{
                        System.out.print(question + ":");
                        answer = br.readLine();
                        if(!answer.equals(question))
                        {
                            System.out.println("Miss...");
                        }
                    }while(!answer.equals(question));
                    
                    //正解の場合次の問題へ
                    System.out.println("OK!");
                    q -= 1;                                 
                }           
                beforeQuestion = question;
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
