package com.example.juliantolentino.laundromatic_2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.List;
import com.hmkcode.android.model.Book;
import com.hmkcode.android.sqlite.MySQLiteHelper;
import android.os.Bundle;
import android.app.Activity;

import com.example.juliantolentino.laundromatic_2.Clothes.Clothes;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<Clothes> clothesList;
    MySQLiteHelper closet = new MySQLiteHelper(this);
    MySQLiteHelper hamper = new MySQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clothesList = new ArrayList<Clothes>();
    }

    public void buttonOnCLick(View v){
        Button button = (Button) v;
        ((Button) v).setText("clicked");
        Clothes jacket = new Clothes();
        // Do stuff to this jacket

        clothesList.add(jacket);

    }

    public void buttonOnCLick2(View v){
        Button button = (Button) v;
        ((Button) v).setText("clicked");
        Clothes shirt = new Clothes();
        // Do stuff to this shirt

        clothesList.add(shirt);

    }
    public void buttonOnCLick3(View v){
        Button button = (Button) v;
        ((Button) v).setText("clicked");
        Clothes pants = new Clothes();
        // Do stuff to this jacket

        clothesList.add(pants);

    }
    public void buttonOnCLick4(View v){
        Button button = (Button) v;
        ((Button) v).setText("clicked");
        Clothes personals = new Clothes();
        // Do stuff to this jacket

        clothesList.add(personals);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
