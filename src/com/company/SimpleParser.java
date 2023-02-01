package com.company;

public class SimpleParser  extends ComLineParserBase{

    public SimpleParser(String[] keys, String[] delimeters) {
        super(keys, delimeters);
    }
    public SimpleParser(String[] keys) {
        super(keys);
    }

    @Override
    protected void onUsage(String errorKey){
        if (errorKey != null)
            System.out.println("Command-line switch error:" + errorKey);
        System.out.println("формат ком.строки: имяПрограммы [-r<input-fileName>] [-w<output-fileName>]");
	System.out.println("   -?  показать Help файл");
	System.out.println("   -r  задать имя входного файла");
	System.out.println("   -w  выполнить вывод в указанный файл");
    }

    @Override
    protected SwitchStatus onSwitch(String key, String keyValue) {
        SwitchStatus status = super.getStatus();
        switch (key) {
            case "?":
               status= SwitchStatus.ShowUsage;
               break;
            case "r":

        }
        return null;
    }

    @Override
    public boolean parse(String[] args) {
       return super.parse(args);
    }
}
