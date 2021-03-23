package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtBookName, txtAuth, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavourite;
    private ImageView bookImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();

 /*       String longDesc = "A young woman named Aomame follows a taxi driver’s enigmatic suggestion and begins to notice puzzling discrepancies in the world around her. She has entered, she realizes, a parallel existence, which she calls 1Q84 —“Q is for ‘question mark.’ A world that bears a question.” Meanwhile, an aspiring writer named Tengo takes on a suspect ghostwriting project. He becomes so wrapped up with the work and its unusual author that, soon, his previously placid life begins to come unraveled.\n" +
                "\n" +
                "As Aomame’s and Tengo’s narratives converge over the course of this single year, we learn of the profound and tangled connections that bind them ever closer: a beautiful, dyslexic teenage girl with a unique vision; a mysterious religious cult that instigated a shoot-out with the metropolitan police; a reclusive, wealthy dowager who runs a shelter for abused women; a hideously ugly private investigator; a mild-mannered yet ruthlessly efficient bodyguard; and a peculiarly insistent television-fee collector.\n" +
                "\n" +
                "A love story, a mystery, a fantasy, a novel of self-discovery, a dystopia to rival George Orwell’s — 1Q84 is Haruki Murakami’s most ambitious undertaking yet: an instant best seller in his native Japan, and a tremendous feat of imagination from one of our most revered contemporary writers.";

        //TODO: Get data from recycler view
        Book book = new Book(1, "1Q84", "Haruku Murakami", 1350, "https://www.goldsborobooks.com/uploads/books/1q84-book-3/_bookCoverThumb/1Q84-3.jpg",
                "A work of maddening brilliance", longDesc);*/
        Intent intent = getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if (bookId !=-1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }
            }
        }

    }
    private void handleCurrentlyReadingBooks(final Book book){
        ArrayList<Book> currentlyReading = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existinCurrentlyReading = false;
        for(Book b: currentlyReading){
            if(b.getId() == book.getId()){
                existinCurrentlyReading = true;
            }
        }
        if(existinCurrentlyReading){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavouriteBooks(final Book book){
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavouriteBooks();

        boolean existInFavoriteBooks = false;
        for(Book b: favoriteBooks){
            if(b.getId() == book.getId()){
                existInFavoriteBooks = true;
            }
        }
        if(existInFavoriteBooks){
            btnAddToFavourite.setEnabled(false);
        }else{
            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavorites(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FavoriteBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private  void handleWantToReadBooks(final Book book){
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existsInWantToReadBooks = false;
        for(Book b: wantToReadBooks){
            if(b.getId() == book.getId()){
                existsInWantToReadBooks = true;
            }
        }
        if(existsInWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /*
    * Enable and disable btn
    * Add the book to already read book ArrayList
    * */
    private void handleAlreadyRead(Book book){
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;
        for(Book b: alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }
        if(existInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuth.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);
    }
    private void initViews(){
        txtAuth = findViewById(R.id.txtAuth);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavourite = findViewById(R.id.btnAddToFavorite);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);

        bookImage = findViewById(R.id.bookImage);
    }
}