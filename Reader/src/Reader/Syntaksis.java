package Reader;


import ru.spbstu.pipeline.RC;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.logging.Logger;

public class Syntaksis {
    private TreeMap<String, String> map;
    public Syntaksis(BufferedReader reader, Logger log, String delimiter){
        map = new TreeMap();
        try{
            String str = reader.readLine();
            String data = new String();
            while(str != null){
                if(str.contains(delimiter)) {
                    data = str.substring(str.lastIndexOf(delimiter) + 2);
                    str = str.substring(0, str.lastIndexOf(delimiter));
                    map.put(str, data);
                }
                str = reader.readLine();
            }
        }
        catch(IOException e){
            log.warning(RC.CODE_FAILED_TO_READ.toString());
        }
    }

    public TreeMap<String, String> Result(){
        return map;
    }
}
