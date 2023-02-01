package com.company;

public class ComLineParser {
    public enum SwitchStatus {
        NoError,
        Error,
        ShowUsage
    }
    String[] keys;
    String[] delimeters;

    public ComLineParser(String[] keys, String[] delimeters) {
        this.keys = keys;
        this.delimeters = delimeters;
    }
    public ComLineParser(String[] keys) {
        this(keys, new String[]{"/", "-"});
    }

    public void onUsage(String errorKey) {
        if (errorKey != null) {
        System.out.println("Ошибочное значение = " + errorKey);
        }
        System.out.println("формат ком.строки: имяПрограммы [-r<input-fileName>] [-w<output-fileName>]");
        System.out.println("-?  показать Help файл");
        System.out.println("-r  задать имя входного файла");
        System.out.println("-w  выполнить вывод в указанный файл");
    }

    public SwitchStatus onSwitch(String key, String keyValue) {
        System.out.println("Key =" + key);
        System.out.println("keyValue = " + keyValue);
        return SwitchStatus.NoError;
    }

    public SwitchStatus parse(String[] args) {
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
                    onSwitch(args[argNum].substring(0,2),args[argNum].substring(2));
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

      //  if (ss == SwitchStatus.ShowUsage)    onUsage(null); для чего?
        if (ss == SwitchStatus.Error) {
              onUsage((argNum == args.length) ? null : args[argNum]);
              ss=SwitchStatus.NoError;
          }
        return ss;
    }
}

