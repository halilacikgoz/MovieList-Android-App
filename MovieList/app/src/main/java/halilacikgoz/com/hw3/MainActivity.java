package halilacikgoz.com.hw3;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef1;
    TextView tw;
    EditText editText1;
    DatabaseReference oku = database.getInstance().getReference().child("Movies");
    ListView listemiz;


    ArrayList<Movies> moviesArrayList = new ArrayList<Movies>();
    ArrayList<String> movietitle = new ArrayList<String>();

    // String[] movies={
    //       "film1", "film2", "film3", "film4", "film5", "film6", "film7", "film8", "film9"
    //};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        database= FirebaseDatabase.getInstance();





        listemiz=(ListView) findViewById(R.id.listView1);


        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, movietitle);


        listemiz.setAdapter(veriAdaptoru);




    }





    public void database(View v){
        Intent intent =null;

       /* if(v.getId()==R.id.button) {
            //writeNewUser("gg","cc@gmail.com");

            String s = editText1.getText().toString();

            DatabaseReference myRef = database.getReference("Mesaj");


            DatabaseReference myRefNew =myRef.push();
            myRefNew.setValue(s);

        }*/

        if (v.getId()==R.id.button2) {

            ValueEventListener dinle= new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                        String title=postSnapshot.child("title").getValue().toString();
                        Log.v("halillog",title);
                        String year=postSnapshot.child("year").getValue().toString();
                        String director=postSnapshot.child("director").getValue().toString();
                        String budget=postSnapshot.child("budget").getValue().toString();
                        String country=postSnapshot.child("country").getValue().toString();

                        moviesArrayList.add(new Movies(title,year,director,budget,country));
                        movietitle.add(title);

                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            oku.addValueEventListener(dinle);
        }



        if(v.getId()==R.id.button7) {

            intent = new Intent(this, AddMovie.class);
            startActivity(intent);

        }


    }
}
