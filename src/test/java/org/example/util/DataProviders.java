package org.example.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class DataProviders {
    @DataProvider
    public static Iterator<Object[]> loginPositive() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginPositive.data")));
        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }


        @DataProvider
        public static Iterator<Object[]> loginPositiveMessage() throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    org.example.util.DataProviders.class
                            .getResourceAsStream("/loginPositiveMessage.data")));
            List<Object[]> userData = new ArrayList<>();
            String line = in.readLine();
            while (line != null) {
                userData.add(line.split(";"));
                line = in.readLine();
            }
            in.close();
            return userData.iterator();
        }

    @DataProvider
    public static Iterator<Object[]> createNewList() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/createNewList.data")));
        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginNegativeTestMessages() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginNegativeTestMessages.data")));
        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"lerkucij@gmail.com","trello0909"});
        data.add(new Object[]{"marinaqatest2019@gmail.com", "marinaqa"});
        return data.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> dataProviderSecondMessage() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"lerkucij@gmail.com","trello0909","Boards"});
        data.add(new Object[]{"marinaqatest2019@gmail.com", "marinaqa", "Boards"});
        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();
        for (int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.generateRandomName(), this.generateRandomPassword()});
        }
        return data.iterator();
    }

    private Object generateRandomName() {
        return "demo" + (new Random()).nextInt() + "@gmail.com";
    }

    private Object generateRandomPassword() {
        return "pass" + (new Random()).nextInt();
    }

    @DataProvider
    public Iterator<Object[]> loginNegativeTestRandom() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.generateRandomString(12), this.generateRandomString(8)});
        }
        return data.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginNegativeTestRandom1() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.generateRandomEmail(5), this.generateRandomString(8)});
        }
        return data.iterator();
    }

    public static String generateRandomEmail(int strLen){
        String randomStrings = "";
        Random random = new Random();
        char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for(int i = 0; i < strLen; i++) {
            word[i] = (char)('a' + random.nextInt(26));
            randomStrings += word[i];
        }
        String randomEmail = randomStrings + "@gmail.com";
        return randomEmail;
    }
    public static String generateRandomString(int strLen){
        Random random = new Random();
        String value = "";
        char randomChar;
        for(int i=5; i<strLen; i++){
            randomChar = (char)(48+random.nextInt(74));
            value += randomChar;
        }
        return value;
    }

    @DataProvider
    public Iterator<Object[]> loginNegativeRandomData() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.genRandomString(6,10), this.generateRandomString2(3,8)});
        }
        return data.iterator();
    }

    public String genRandomString(int min,int max) {
        String str = "";
        int length;
        int i = 0;
        int number;
        if (min > max) return "";
        Random gen = new Random();
        length = min + gen.nextInt(max - min +1);
        do {
            number = '0' + gen.nextInt('z' - '0' + 1);
            if ((number < 58 || number > 96 || (number > 64 && number < 91)))
            {
                str += (char) number;
                i++;
            }
        }
        while (i < length);
        return str;
    }

    private String generateRandomString2(int minSymbolsCount, int maxSymbolsCount) {
        String symbol = "0123456789zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
        char[] arr = symbol.toCharArray();
        Random gen = new Random();
        int countOfSimbols = minSymbolsCount + gen.nextInt(maxSymbolsCount - minSymbolsCount);
        String pass = "";
        for (int i = 0; i < countOfSimbols; i++) {
            int num = gen.nextInt(62);
            pass += arr[num];
        }
        return pass;
    }

}


