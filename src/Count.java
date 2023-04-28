public class Count {
    //カウント数5
    private static final int COUNT = 5;
    // カウントダウン間隔(ミリ秒)
    private static final int COUNT_DOWN_TIME = 1000;

    public static void countdown()
    {
        try
        {
            //5秒カウント
            for(int i = COUNT; i>0; i--)
            {
                System.out.println(i + ".......");
                Thread.sleep(COUNT_DOWN_TIME);
            }
            
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }      
    }
}
