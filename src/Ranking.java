import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
    private static final long TIME_NONE = 9999999999999999L;
    private static final int rank_num = 10;
    
    ArrayList<Long> ranking_list = new ArrayList<>();

    void ranking_top10(long game_time)
    {
        ranking_list.add(game_time);
        Collections.sort(ranking_list);
        int j = 1;
        for(int idx = 0; idx <= rank_num - 1; idx++)
        {
            if(idx < ranking_list.size())
            {
                System.out.println(j + ": " + ranking_list.get(idx) + "[ms]");
            }
            else
            {
                System.out.println(j + ": " + TIME_NONE + "[ms]");
            }

            j++;
        }
    }  
}
