package com.sprout;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String AUTHOR_KEY = "author";
    public static final String QUOTE_KEY = "quote";

    private DocumentReference ndocRef = FirebaseFirestore.getInstance().document("sampleData/inspiration");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access a Cloud Firestore instance from your Activity

        FirebaseFirestore db = FirebaseFirestore.getInstance();



    }

    public  void  saveQuote(View view){

        EditText quoteview = (EditText) findViewById(R.id.editText);
        EditText authorview = (EditText) findViewById(R.id.editText2);
        String quoteText = quoteview.getText().toString();
        String authorText = authorview.getText().toString();

        if (quoteText.isEmpty() || authorText.isEmpty()){ return;}
        Map<String, Object>dataToSave = new HashMap<String, Object>();
        dataToSave.put(QUOTE_KEY, quoteText);
        dataToSave.put(AUTHOR_KEY, authorText);

        ndocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void aVoid){
                Log.d("InspiringQuote", "Document has been saved");

            }

        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("sorry", "Document was not saved");
            }


        });
    }
}
