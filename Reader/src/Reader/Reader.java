package Reader;

import ru.spbstu.pipeline.*;

import java.io.*;
import java.util.logging.Logger;

public class Reader implements IReader {
    private FileInputStream reader;
    private String config;
    private Logger logger;
    private ReaderGrammar grammar;
    private IExecutable consumer;
    private IExecutable producer;
    private int buffer_size;
    private ReaderGrammar.MODE mode;

    public Reader(Logger log){
        logger = log;
        grammar = new ReaderGrammar();
    }

    public RC setInputStream(FileInputStream fis){
        reader = fis;
        return RC.CODE_SUCCESS;
    }
    public RC setConsumer(IExecutable c){
        consumer = c;
        return RC.CODE_SUCCESS;
    }
    public RC setProducer(IExecutable p){
        producer = p;
        return RC.CODE_SUCCESS;
    }
    public RC setConfig(String configFileName){
        config = configFileName;
        ReaderGrammar grammar = new ReaderGrammar();
        try {
            Syntaksis synt = new Syntaksis(new BufferedReader(new FileReader(config)), logger, grammar.delimiter());
            Reader_Semantic seman = new Reader_Semantic(synt.Result());
            mode = seman.Mode();
            buffer_size = seman.Buffer_Size();
        }
        catch(FileNotFoundException e){
            logger.warning(RC.CODE_INVALID_ARGUMENT.toString());
            return RC.CODE_INVALID_ARGUMENT;
        }
        return RC.CODE_SUCCESS;
    }

    public RC execute(byte [] data){
        byte[] arr = new byte[buffer_size];
        try {
            int end = buffer_size;
            while(end == buffer_size){
                end = reader.read(arr);
                if(end == -1){
                    break;
                }
                byte[] word = new byte[end];
                for(int i = 0; i < end; i++){
                    word[i] = arr[i];
                }
                consumer.execute(word);
                arr = new byte[buffer_size];
            }
        }
        catch(IOException e){
            logger.warning(RC.CODE_FAILED_TO_READ.toString());
            return RC.CODE_FAILED_TO_READ;
        }
        return RC.CODE_SUCCESS;
    }
}
