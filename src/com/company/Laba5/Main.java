package com.company.Laba5;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        String path1 = "C:\\Users\\pc\\IdeaProjects\\3sem\\src\\com\\company\\Laba3\\zadanie1.xml";
        String path2 = "C:\\Users\\pc\\IdeaProjects\\3sem\\src\\com\\company\\Laba3\\zadanie2.xml";

        authors(path1);
    }

    public static ArrayList<Author> authors(String path) throws FileNotFoundException, XMLStreamException {

        boolean pages = false;
        ArrayList<Author> authors = new ArrayList<>();
        Author tmp_author = null;
        Book tmp_book = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(path));

        int event = reader.getEventType();
        while (true) {
            switch (event) {
                case XMLStreamConstants.START_ELEMENT: {
                    if (reader.getLocalName().equalsIgnoreCase("author")) {
                        tmp_author = new Author();
                        tmp_author.setName(reader.getAttributeValue(0));
                    } else if (reader.getLocalName().equalsIgnoreCase("book")) {
                        tmp_book = new Book();
                        tmp_book.setTitle(reader.getAttributeValue(0));
                    } else if (reader.getLocalName().equalsIgnoreCase("pages")) {
                        pages = true;
                    }
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    if (pages) {
                        tmp_book.setPages(Integer.parseInt(reader.getText()));
                        pages = false;
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    if (reader.getLocalName().equals("author")) {
                        authors.add(tmp_author);
                    }
                    if (reader.getLocalName().equalsIgnoreCase("book")) {
                        tmp_author.addBook(tmp_book);
                    }
                    break;
                }
            }
            if (!reader.hasNext())
                break;
            event = reader.next();
        }

        for (Author a : authors) {
            System.out.println(a.toString());
            for (Book b : a.getBooks()) {
                System.out.println(b.getTitle() + " " + b.getPages());
            }
        }
        return authors;
    }
}
