package Coding;

import java.util.TreeMap;

public class Coding_Semantic {
    private CodingGrammar.MODE mode;
    private String data;
    public Coding_Semantic(TreeMap<String, String> map){
        for (String str : map.keySet()) {
            if(str.equals(CodingGrammar.TokenCoding.MODE.toString())){
                if(map.get(str).equals(CodingGrammar.MODE.DECODING.toString())){
                    mode = CodingGrammar.MODE.DECODING;
                }
                if(map.get(str).equals(CodingGrammar.MODE.ENCODING.toString())){
                    mode = CodingGrammar.MODE.ENCODING;
                }
            }
            if(str.equals(CodingGrammar.TokenCoding.FILE.toString())){
                data = map.get(str);
            }
        }
    }

    public CodingGrammar.MODE Mode(){
        return mode;
    }

    public String FileName(){
        return data;
    }
}
