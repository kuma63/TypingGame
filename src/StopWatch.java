public class StopWatch {
    //計測開始時間
    private long start_point;

    //計測終了時間
    private long end_point;

    //計測開始する
    public void start()
    {
        this.start_point = System.currentTimeMillis();
    }
    
    //計測終了する
    public void stop()
    {
        this.end_point = System.currentTimeMillis();
    }
    
    //計測時間取得
    public long getTime()
    {
        return end_point - start_point;
    }

}
