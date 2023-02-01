package com.company;

public class TestParser {
    public String[] keys;
    public String[] delimiters;
    public SwitchStatus status;

    public enum SwitchStatus {
        NoError, Error, ShowUsage
    }


    public TestParser(String[] keys, String[] delimiters) {
        this.keys = keys;
        this.delimiters = delimiters;
    }

    public TestParser(String[] keys) {
        this(keys, new String[]{"/", "-"});
    }

    //Вывод подсказки с форматом командной строки
    public void onUsage(String errorKey) {
        status = SwitchStatus.NoError;
        if (errorKey != null) {
            System.out.println("Введенное значение ошибочно" + errorKey);
            System.out.println("формат командрой строки: имя Программы" +
                    "[-r<input-fileName>] [-w<output-fileName>]");
            System.out.println("-?  показать Help файл");
            System.out.println("-r  задать имя входного файла");
            System.out.println("-w  выполнить вывод в указанный файл");
            System.out.println("-exit Выход из программы");
        }
    }

    //Отрабатывает команду для каждого найденного ключа
    public SwitchStatus onSwitch(String key, String keyValue) {
        System.out.print(key.toUpperCase() + "" + keyValue.toLowerCase()+"\t");
        return SwitchStatus.NoError;
    }

    //Логика по разбору класса
    public SwitchStatus parse(String[] args) {
        status = SwitchStatus.NoError;
//        System.out.println(Arrays.toString(args));
        for (String elem : args) {
            if (isDelimiter(elem) && isKey(elem)) {
                onSwitch(elem.substring(0, 1).trim(), elem.substring(1).trim());
            } else {
                onUsage(elem);
               return status = SwitchStatus.Error;
            }
        }

          return status;
    }

    public boolean isDelimiter(String args) {
        for (String delimiter : delimiters) {
            if (delimiter.regionMatches(0, args, 0, 1)) {
                return true;
            }
        }
        status = SwitchStatus.Error;
        return false;
    }

    public boolean isKey(String key) {
        for (String keyElem : keys) {
            if ((keyElem).regionMatches(0, key, 1, 1)) {
                return true;
            }
        }
        status = SwitchStatus.Error;
        return false;
    }
}

//    public void  run()
//    {
//       scan=new Scanner(System.in);
//       while (scan.hasNext())
//        {
//          String elem = scan.nextLine();
//          if(elem.equals("-exit"))
//            {
//               System.exit(0);
//            }
//          else {
//           parse(elem.trim().split(" "));
//          }
//        }
//    }
//    }

