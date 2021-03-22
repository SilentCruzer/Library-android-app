package com.example.library;

import java.util.ArrayList;

public class Utils {
    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favouriteBooks;

    private Utils() {
        if(null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }

        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }

        if(null==currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }

        if(null == favouriteBooks){
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO: add initial data
        allBooks.add(new Book(1, "1Q84", "Haruku Murakami", 1350, "https://www.goldsborobooks.com/uploads/books/1q84-book-3/_bookCoverThumb/1Q84-3.jpg",
                "A work of maddening brilliance", "Long Description"));
        allBooks.add(new Book(2,"To Kill a Mockingbird","Harper Lee",281,"https://images-na.ssl-images-amazon.com/images/I/81gepf1eMqL.jpg",
                "Told through the eyes of a child, Harper Lee's magnum opus may seem to take a simplistic point of view, but Scout's world is rich and complex.",
                "Long Description"));
    }

    public static Utils getInstance(){
        if(null != instance){
            return instance;
        }else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public Book getBookById(int id){
        for(Book b: allBooks){
            if(b.getId() ==id){
                return b;
            }

        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book){
        return wantToReadBooks.add(book);
    }
    public boolean addToFavorites(Book book){
        return favouriteBooks.add(book);
    }
    public boolean addToCurrentlyReading(Book book){
        return currentlyReadingBooks.add(book);
    }
}