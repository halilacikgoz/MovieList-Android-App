package halilacikgoz.com.hw3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddMovie extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference dbRef;
    EditText title,year,director,budget,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        title= (EditText) findViewById(R.id.editText6);
        year= (EditText) findViewById(R.id.editText7);
        director= (EditText) findViewById(R.id.editText8);
        budget= (EditText) findViewById(R.id.editText9);
        country= (EditText) findViewById(R.id.editText10);




        database= FirebaseDatabase.getInstance();
    }
    private void addNewMovie(String title, String year, String director, String budget, String country) {

        Movies movies = new Movies(title, year, director, budget, country);
        dbRef = database.getReference("Movies");
        DatabaseReference idRef =dbRef.push();
        idRef.child("title").setValue(title);
        idRef.child("year").setValue(year);
        idRef.child("director").setValue(director);
        idRef.child("budget").setValue(budget);
        idRef.child("country").setValue(country);



    }



    public void add(View v) {


        if(v.getId()==R.id.button6){
            String title1= title.getText().toString();
            String year1 =year.getText().toString();
            String director1=director.getText().toString();
            String budget1=budget.getText().toString();
            String country1=country.getText().toString();

            addNewMovie(title1,year1,director1,budget1,country1);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
