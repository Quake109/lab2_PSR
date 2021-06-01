package models;

import java.io.Serializable;

public class Book implements Serializable{

    private String title;
    private String autor;
    private String bookNumber;

    public Book(String title, String autor, String bookNumber) {
        this.title = title;
        this.autor = autor;
        this.bookNumber = bookNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTile(String title) {
        this.title = title;
    }

    public void setAutor(String autor) {this.autor = autor;}

    public String getAutor() {return autor;}

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    @Override
    public String toString(){
        return "Book " + title + " Autor " + autor + " book number " + bookNumber;
    }
}
