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

       super.setStatus(SwitchStatus.NoError);
      //  System.out.println(key);
        switch (key) {
            case "?":
                System.out.println(key+ " " + keyValue + "справка");
                setStatus(SwitchStatus.ShowUsage);
               break;
            case "r":
                System.out.println(key+ " " + keyValue+ " файл для чтения");
                if (keyValue!=null){
                    setStatus(SwitchStatus.NoError);
                    setInFile(keyValue);
                }
                else {
                    setStatus(SwitchStatus.Error);
                    onUsage(keyValue);
                }
                break;
            case "w":
                System.out.println(key+ " " + keyValue + " файл для записи");
                if (keyValue!=null){
                    setStatus(SwitchStatus.NoError);
                    setOutFile(keyValue);
                }
                else {
                    setStatus(SwitchStatus.Error);
                    onUsage(keyValue);
                }
                break;

        }
        return getStatus();
    }

    @Override
    public SwitchStatus parse(String[] args) {
       return super.parse(args);
    }
}
