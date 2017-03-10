package com.gokhanozg;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by gokhanozg on 3/10/17.
 */
public class Main {

    private static Map<String, Person> nameToPeopleMap = new HashMap<String, Person>();

    public static void main(String[] args) {
        try {
            Locale locale = new Locale("tr");
            URL url = Main.class.getClass().getResource("/tumMilletvekilleri");
            File milletvekilleriFile = new File(url.getPath());
            List<String> csvValues = FileUtils.readLines(milletvekilleriFile, Charset.forName("UTF-8"));
            Set<String> noDuplicateSet = new HashSet<String>();
            for (String csvValue : csvValues) {
                noDuplicateSet.add(csvValue);
            }
            List<Person> people = new ArrayList<Person>();
            for (String csvValue : csvValues) {
                String[] vals = csvValue.split(",");
                if (vals.length < 3)
                    System.out.println("Problem in milletvekilleri");
                Person person = new Person();
                person.setName(vals[0]);
                person.setIl(vals[2]);
                List<String> parties = new ArrayList<String>();
                parties.add(vals[1]);
                person.setParties(parties);
                people.add(person);
                nameToPeopleMap.put(person.getName().toLowerCase(locale), person);
            }
            Set<String> companies = new HashSet<String>();
            url = Main.class.getClass().getResource("/companies.csv");
            File companyNamesFile = new File(url.getPath());
            List<String> compNames = FileUtils.readLines(companyNamesFile, Charset.forName("UTF-8"));
            for (String compName : compNames) {
                companies.add(compName);
            }
            url = Main.class.getClass().getResource("/companyFirstt.csv");
            File companyToPersons = new File(url.getPath());
            List<String> lines = FileUtils.readLines(companyToPersons, Charset.forName("UTF-8"));
            for (String line : lines) {
                if (line == null || line.length() == 0)
                    continue;
                if (!line.contains(","))
                    continue;
                String[] vals = line.split(",");
                String compName = vals[0];
                if (companies.contains(compName) && vals.length > 1) {
                    for (int i = 1; i < vals.length; i++) {
                        String name = vals[i];
                        if (name == null || name.length() == 0)
                            continue;
                        String nl = name.toLowerCase(locale);
                        if (nameToPeopleMap.containsKey(nl)) {
                            Person p = nameToPeopleMap.get(nl);
                            if (p.getCompanies() == null)
                                p.setCompanies(new ArrayList<String>());
                            if (!p.getCompanies().contains(compName))
                                p.getCompanies().add(compName);
                        } else {
                            Person p = createPersonForCompany(name, compName);
                            people.add(p);
                            nameToPeopleMap.put(p.getName().toLowerCase(locale), p);
                        }
                    }
                }
            }
            for (Person person : people) {
                if (person.getParties() != null && person.getCompanies() != null && person.getCompanies().size() > 0 && person.getParties().size() > 0) {
                    System.out.println("Connected Individual:" + person.toString());
                }
            }
            int maxPart = 0;
            int maxComp = 0;
            for (Person person : people) {
                int cmp = 0;
                int cmc = 0;
                List<String> parties = person.getParties();
                List<String> compnyList = person.getCompanies();
                if (parties != null) {
                    cmp += parties.size();
                }
                if (compnyList != null) {
                    cmc += compnyList.size();
                }
                if (cmp > maxPart)
                    maxPart = cmp;
                if (cmc == 11)
                    System.out.println("Max company record goes to:" + person.toString());
                if (cmc > maxComp)
                    maxComp = cmc;
            }
            System.out.println("max party:" + maxPart + ", max company:" + maxComp);
            StringBuilder sb = new StringBuilder();
            sb.append("Name,Party,City,Company1,Company2,Company3,Company4,Company5,Company6,Company7,Company8,Company9,Company10,Company11" + System.lineSeparator());
            for (Person person : people) {
                sb.append(person.getName() + ",");
                if (person.getParties() == null)
                    sb.append(",");
                else
                    sb.append(person.getParties().get(0) + ",");
                sb.append(person.getIl() + ",");
                for (int i = 0; i < 11; i++) {
                    if (person.getCompanies() == null || person.getCompanies().size() == 0) {
                        if (i != 10)
                            sb.append(",");
                    } else {
                        if (person.getCompanies().size() > i) {
                            sb.append(person.getCompanies().get(i) + ",");
                        } else {
                            if (i != 10)
                                sb.append(",");
                        }
                    }
                }
                sb.append(System.lineSeparator());
            }
            File out = new File("refinedData.csv");
            if (out.exists())
                out.delete();
            FileUtils.write(out, sb.toString(), Charset.forName("UTF-8"));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static Person createPersonForCompany(String name, String compName) {
        Person p = new Person();
        p.setName(name);
        p.setCompanies(new ArrayList<String>());
        p.getCompanies().add(compName);
        return p;
    }
}
