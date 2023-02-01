package com.company;

abstract public class ComLineParserBase {
    private final String[] keys;           // ключи
    private final String[] delimeters;     // разделители  "/", "-"
    private String inFile;
    private String outFile;
    private SwitchStatus status;

    public SwitchStatus getStatus() {
        return status;
    }

    public void setStatus(SwitchStatus status) {
        this.status = status;
    }

    protected enum SwitchStatus { NoError, Error, ShowUsage };

    public ComLineParserBase(String[] keys) {
        this(keys, new String[] { "/", "-" });
    }
    public ComLineParserBase(String[] keys, String[] delimeters) {
        this.keys = keys;
        this.delimeters   = delimeters;
    }

    public String getInFile() {
        return inFile;
    }

    public void setInFile(String inFile) {
        this.inFile = inFile;
    }

    public String getOutFile() {
        return outFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    protected abstract void onUsage(String errorKey);


    protected SwitchStatus onSwitch(String key, String keyValue) {
        return status;
    }

    public boolean parse(String[] args) {
        SwitchStatus status = SwitchStatus.NoError;
        int argNum;
        for (argNum = 0; (status == SwitchStatus.NoError) && (argNum < args.length); argNum++) {
            // провера наличия правильного разделителя
            boolean isDelimeter = false;
            for (int n = 0; !isDelimeter && (n < delimeters.length); n++) {
                isDelimeter = args[argNum].regionMatches(0,delimeters[n], 0, 1);
            }
            if (isDelimeter) {
                // проверка наличия правильного ключа
                boolean isKey = false;
                int i;
                for (i = 0; !isKey && (i < keys.length); i++) {
                    isKey = args[argNum].toUpperCase().regionMatches(1, 
                            keys[i].toUpperCase(),0,keys[i].length());
                    if (isKey) break;
                }
                if (!isKey) {
                    status = SwitchStatus.Error;
                    break;
                } 
                else {
                    status= onSwitch(keys[i].toLowerCase(),
                         args[argNum].substring(1 + keys[i].length()));
                }
            }
            else {
                status= SwitchStatus.Error;
                break;
            }
        }
        // завершение разбора командной строки
        if (status == SwitchStatus.ShowUsage)    onUsage(null);
        if (status == SwitchStatus.Error)        onUsage((argNum == args.length) ? null : args[argNum]);
        
        return status == SwitchStatus.NoError;
    }      
}
