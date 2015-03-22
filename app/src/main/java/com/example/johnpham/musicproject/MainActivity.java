package com.example.johnpham.musicproject;

import android.content.Context;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Locale;
import java.util.Scanner;

public class MainActivity extends ActionBarActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech talk;
    private Button button;
    private EditText text=null;
    private String need="";
    private String show="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        text=(EditText)findViewById(R.id.editText);
        File file = new File(Environment.getExternalStoragePublicDirectory("DOWNLOAD")+"/readFile");
        try {
            FileReader reader=new FileReader(file);

            BufferedReader br=new BufferedReader(reader);

            while((need=br.readLine())!=null)
            {
                show=show+need+"\n";
               Log.d("printing .....",need);
            }
            br.close();

        }
        catch(Exception e)
        {
           Log.d("file cannot be open\t", e+ "\t"+file+"\n");
        }
        text.setText(show);
        talk=new TextToSpeech(this,this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTextToSpeech();
            }
        });
        convertTextToSpeech();

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
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = talk.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error", "This Language is not supported");
            } else {
                convertTextToSpeech();
            }
        } else {
            Log.e("error", "Initilization Failed!");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        talk.shutdown();
    }
    private void convertTextToSpeech() {
        String tex = text.getText().toString();
        if (null == text || "".equals(text)) {
            tex = "Please give some input.";
        }
        talk.speak(tex, TextToSpeech.QUEUE_FLUSH, null);
    }
}
