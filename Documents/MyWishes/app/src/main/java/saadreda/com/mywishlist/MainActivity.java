package saadreda.com.mywishlist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHandler;
import model.MyWish;

public class MainActivity extends AppCompatActivity {

    private EditText title , content;
    private Button saveButton;
    private DatabaseHandler dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        dba = new DatabaseHandler(MainActivity.this);

        title = (EditText) findViewById(R.id.EditTextTitle);
        content = (EditText) findViewById(R.id.editTextWish);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!title.getText().toString().equals(""))
                saveToDB();
                else
                    Toast.makeText(MainActivity.this, "رجاءً أدخل عنوان للملاحظة!", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), DisplayWishesActivity.class);
                startActivity(i);
                
            }
        });
    }

    private void saveToDB() {


        MyWish wish = new MyWish();
        wish.setTitle(title.getText().toString().trim());
        wish.setContent(content.getText().toString().trim());

        dba.addWhish(wish);
        dba.close();

        //clear
        title.setText("");
        content.setText("");

        Intent i = new Intent(this, DisplayWishesActivity.class);
        startActivity(i);

    }
}
