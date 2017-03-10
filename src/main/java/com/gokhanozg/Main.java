package com.gokhanozg;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gokhanozg on 3/10/17.
 */
public class Main {


    public static void main(String[] args) {
        try {
            URL url = Main.class.getClass().getResource("/tumMilletvekilleri");
            File milletvekilleriFile = new File(url.getPath());
            List<String> csvValues = FileUtils.readLines(milletvekilleriFile, Charset.forName("UTF-8"));
            Set<String> noDuplicateSet = new HashSet<String>();
            for (String csvValue : csvValues) {
                noDuplicateSet.add(csvValue);
            }
            List<Milletvekili> milletvekilleri = new ArrayList<Milletvekili>();
            for (String csvValue : csvValues) {
                String[] vals = csvValue.split(",");
                if (vals.length < 3)
                    System.out.println("Problem in milletvekilleri");
                Milletvekili m = new Milletvekili();
                m.setName(vals[0]);
                m.setIl(vals[2]);
                m.setParti(vals[1]);
                milletvekilleri.add(m);
            }
            System.out.println(milletvekilleri.size());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
