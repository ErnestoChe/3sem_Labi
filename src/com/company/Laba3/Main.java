package com.company.Laba3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        //getXMLOne();
        ArrayList<Athlete> list = AthletesList();

        for (Athlete a: list) {

        }
        //output3a(list);
        //output3b(list);
        output3c(list);
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


}
