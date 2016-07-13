package saadreda.com.mywishlist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;

public class WishDetailActivity extends AppCompatActivity {

    private TextView title , date , content;
    private Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_detail);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        title = (TextView) findViewById(R.id.titleTextView);
        date = (TextView) findViewById(R.id.dateTextView);
        content = (TextView) findViewById(R.id.DetailTextView);



       Bundle extras = getIntent().getExtras();

        if( extras != null) {
            title.setText(extras.getString("title"));
            date.setText("أنشأت  :"+extras.getString("date"));
            content.setText(" \" "+ extras.getString("content") + " \" ");

            final int id = extras.getInt("id");

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatabaseHandler dba = new DatabaseHandler(getApplicationContext());
                    dba.deleteWish(id);

                    Toast.makeText(WishDetailActivity.this, "الملاحظة حذفت!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext() , DisplayWishesActivity.class));
                    finish();

                }
            });
        }
    }
}
