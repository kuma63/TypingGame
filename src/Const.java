public class Const {
    
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
