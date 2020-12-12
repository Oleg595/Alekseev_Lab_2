package Reader;

import java.util.TreeMap;

public class Reader_Semantic {
    private ReaderGrammar.MODE mode;
    private int buffer_size;
    public Reader_Semantic(TreeMap<String, String> map){
        for (String str : map.keySet()) {
            if(str.equals(ReaderGrammar.TokenReader.BUFFER_SIZE.toString())){
                buffer_size = Integer.parseInt(map.get(str));
            }
        }
    }

    public int Buffer_Size() {
        return buffer_size;
    }

    public ReaderGrammar.MODE Mode() {
        return mode;
    }

}
