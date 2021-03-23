package com.example.library;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Utils {
    private static final String ALL_BOOKS_KEY = "all_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favouriteBooks;

    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);
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
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "1Q84", "Haruku Murakami", 1350, "https://www.goldsborobooks.com/uploads/books/1q84-book-3/_bookCoverThumb/1Q84-3.jpg",
                "A work of maddening brilliance", "Long Description"));
        books.add(new Book(2,"To Kill a Mockingbird","Harper Lee",281,"https://images-na.ssl-images-amazon.com/images/I/81gepf1eMqL.jpg",
                "Told through the eyes of a child, Harper Lee's magnum opus may seem to take a simplistic point of view, but Scout's world is rich and complex.",
                "Long Description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();
    }

    public static Utils getInstance(Context context){
        if(null != instance){
            return instance;
        }else {
            instance = new Utils(context);
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

    public boolean removeFromAlreadyRead(Book book){
        return alreadyReadBooks.remove(book);
    }

    public boolean removeFromWantToRead(Book book){
        return wantToReadBooks.remove(book);
    }

    public boolean removeFromCurrentlyReading(Book book){
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeFromFavoriteBooks(Book book){
        return favouriteBooks.remove(book);
    }
}

