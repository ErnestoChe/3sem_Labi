package com.company.Laba3;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        //getXMLOne();
        ArrayList<Athlete> list = AthletesList();

//        output3a(list);
//        output3b(list);
//        output3c(list);
        Athlete at1 = ath();
        System.out.println(at1.toString());
        if(at1.getComps().size() != 0){
            for (Competition c : at1.getComps()) {
                System.out.println(c.getYear() + " " + c.getPlace() + " " + c.getAward() + " " + c.getResult());
            }
        }
        list.add(at1);

        newXML(list);
    }
    //первое задание
    public static void getXMLOne() throws ParserConfigurationException, IOException, SAXException
    {
        String path = "C:\\Users\\pc\\IdeaProjects\\3sem\\src\\com\\company\\Laba3\\zadanie1.xml";
        NodeList list = nodeList(path);

        for (int i = 0; i < list.getLength(); i++) {
            Node tmp = list.item(i);
            if (tmp.hasAttributes()) {
                System.out.println(
                        "Автор произведения: " +
                                tmp.getAttributes().
                                        getNamedItem("name").
                                        getTextContent());
                NodeList tmp_list = tmp.getChildNodes();
                for (int j = 0; j < tmp_list.getLength(); j++) {
                    Node tmp2 = tmp_list.item(j);
                    if (tmp2.hasAttributes())
                        System.out.println(
                                "Название книги" +
                                        tmp2.getAttributes().
                                                getNamedItem("name").
                                                getTextContent());

                    if (tmp2.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) tmp2;
                        System.out.println(
                                "количество страниц: " +
                                        element.getElementsByTagName("pages").
                                                item(0).
                                                getTextContent());
                    }
                }

                System.out.println("------------------------------");
            }
        }
    }

    //второе задание
    public static ArrayList<Athlete> AthletesList() throws IOException, SAXException, ParserConfigurationException
    {
        System.out.println("SCHITIVAYU KOTLETOV...");
        ArrayList<Athlete> athletes = new ArrayList<>();
        String path = "C:\\Users\\pc\\IdeaProjects\\3sem\\src\\com\\company\\Laba3\\zadanie2.xml";

        NodeList athList = nodeList(path);

        for (int i = 0; i < athList.getLength() ; i++) {
            Node tmp_athlete = athList.item(i);
            if(tmp_athlete.getNodeName().equals("sportsman")){
                Athlete newAthlete = new Athlete();
                newAthlete.setName(tmp_athlete.getAttributes().getNamedItem("name").getNodeValue());
                newAthlete.setDate_of_birth(tmp_athlete.getAttributes().getNamedItem("birthday").getNodeValue());
                newAthlete.setSex(tmp_athlete.getAttributes().getNamedItem("s").getNodeValue());

                List<Competition> competitions = new ArrayList<>();
                NodeList compList = tmp_athlete.getChildNodes();

                for (int j = 0; j < compList.getLength(); j++) {
                    Node comp = compList.item(j);
                    if(comp.getNodeName().equals("event")){
                        Competition new_comp = new Competition();
                        new_comp.setPlace(comp.getAttributes().getNamedItem("place").getNodeValue());
                        new_comp.setYear(Integer.parseInt(comp.getAttributes().getNamedItem("year").getNodeValue()));
                        new_comp.setResult(Integer.parseInt(comp.getChildNodes().item(1).getChildNodes().item(0).getNodeValue()));
                        new_comp.setAward(comp.getChildNodes().item(3).getChildNodes().item(0).getNodeValue());
                        competitions.add(new_comp);
                    }
                }
                newAthlete.setComps(competitions);
                athletes.add(newAthlete);
            }
        }
        System.out.println("KOTLETI SCHITANI...");
        return athletes;
    }

    //парсим из хмл, DRY все дела
    public static NodeList nodeList(String path) throws ParserConfigurationException, IOException, SAXException
    {
        File xmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        Node root = doc.getDocumentElement();
        NodeList list = root.getChildNodes();

        return list;
    }

    //задание 3а
    public static void output3a(ArrayList<Athlete> athletes)
    {
        System.out.println("ВЫВОЖУ ВСЕХ МУЖЧИН...");
        for (Athlete ath:athletes) {
            if(ath.getSex().equals("м")){
                System.out.println(ath.toString());
            }
        }
    }

    //задание 3б
    public static void output3b(ArrayList<Athlete> athletes)
    {
        System.out.println("Вывожу женщин старше 1985 и количество медалей");
        for (Athlete ath: athletes) {
            if(ath.getSex().equals("ж") && Integer.parseInt(ath.getDate_of_birth().split("-")[0]) < 1985){
                System.out.println(ath.toString());
                System.out.println(ath.getMedals());
                System.out.println();
            }
        }
    }

    //задание 3в
    public static void output3c(ArrayList<Athlete> athletes)
    {
        for (Athlete ath: athletes) {
            for (Competition comp:ath.getComps()) {
                if(comp.getYear() == 2002 && comp.getPlace().equals("москва")){
                    System.out.println(ath.toString());
                }
            }
        }
    }

    public static Athlete ath()
    {
        Athlete athlete = new Athlete();
        Scanner scn = new Scanner(System.in);
        System.out.println("enter name");
        athlete.setName(scn.nextLine());
        System.out.println("enter birth date in yyyy-mm-dd format");
        //TODO check format
        athlete.setDate_of_birth(scn.nextLine());
        System.out.println("enter gender m/f");
        athlete.setSex(scn.nextLine());
        System.out.println("This athlete partpicated in any compettions? (Y/N)");
        List<Competition> list_comp = new ArrayList<>();
        String line = scn.nextLine();
        while(line.equals("Y")){
            Competition new_comp = new Competition();
            System.out.println("enter year of competition");
            new_comp.setYear(Integer.parseInt(scn.nextLine()));
            System.out.println("enter place of comp");
            new_comp.setPlace(scn.nextLine());
            System.out.println("enter award on comp (gold/silver/bronze)");
            new_comp.setAward(scn.nextLine());
            String aw = new_comp.getAward();
            switch (aw){
                case "gold":
                    new_comp.setResult(200);
                    break;
                case "silver":
                    new_comp.setResult(180);
                case "bronze":
                    new_comp.setResult(160);
            }
            list_comp.add(new_comp);
            System.out.println("Is there any more compititions? (Y/N)");
            line = scn.nextLine();
        }
        athlete.setComps(list_comp);
        return athlete;
    }

    public static void newXML(ArrayList<Athlete> ath_list) throws TransformerException, ParserConfigurationException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element team = document.createElement("team");
        document.appendChild(team);
        for (Athlete a : ath_list) {
            Element athlete = document.createElement("sportsmen");
            Attr name = document.createAttribute("name");
            name.setTextContent(a.getName());
            Attr numberOfEvent = document.createAttribute("numberOfEvent");
            numberOfEvent.setTextContent(String.valueOf(a.getComps().size()));
            List<Competition> eventList = a.getComps();
            int sumResoultInt = 0;
            for (Competition event : eventList) {
                sumResoultInt += event.getResult();
            }
            Attr sumResoult = document.createAttribute("sumResoult");
            sumResoult.setTextContent(String.valueOf(sumResoultInt));
            athlete.setAttributeNode(name);
            athlete.setAttributeNode(numberOfEvent);
            athlete.setAttributeNode(sumResoult);
            team.appendChild(athlete);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("newFile.xml"));
        transformer.transform(source, result);
    }
}
