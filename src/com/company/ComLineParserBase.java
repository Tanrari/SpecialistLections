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
//        System.out.println("Key =" + key);
//        System.out.println("keyValue = " + keyValue);
        return status;
    }

    protected SwitchStatus parse(String[] args) {
        SwitchStatus ss = SwitchStatus.NoError;
        int argNum;
        for ( argNum = 0; (ss == SwitchStatus.NoError) && (argNum < args.length); argNum++) {
            boolean isDelimiter = false;
            for (int n = 0; !isDelimiter && (n < delimeters.length); n++) {
                isDelimiter = delimeters[n].regionMatches(0, args[argNum], 0, 1);
            }
            if (isDelimiter) {
                boolean isKey=false;
                for (int i = 0; !isKey && (i < keys.length); i++) {
                    isKey = keys[i].regionMatches(0, args[argNum], 1, 1);
                }
                if (isKey){
                    ss=SwitchStatus.NoError;
                   // System.out.println(args[argNum ]+  "////");
                    onSwitch(args[argNum].substring(1,2),args[argNum].substring(2));
                }
                else {
                    ss=SwitchStatus.Error;
                    break;
                }
            }
            else {
                ss = SwitchStatus.Error;
                break;
            }
        }

        if (ss == SwitchStatus.ShowUsage)    onUsage(null);
        if (ss == SwitchStatus.Error) {
            onUsage((argNum == args.length) ? null : args[argNum]);
            ss=SwitchStatus.NoError;
        }
        return ss;
    }      
}
