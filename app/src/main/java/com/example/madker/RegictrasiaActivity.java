package com.example.madker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegictrasiaActivity extends AppCompatActivity {
    EditText  loginEditText;
    EditText  EmailEditText;
    EditText  PasswordEditText;
    EditText  RepeatPasswordEditText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regictrasia);
        loginEditText = findViewById(R.id.editTextLogin);
        EmailEditText = findViewById(R.id.editTextEmail);
        PasswordEditText = findViewById(R.id.editTextPassword);
        RepeatPasswordEditText = findViewById(R.id.editTextRepeatPassword);
        button = findViewById(R.id.button);
        button.setOnClickListener(v->{
            if(checkFields()){
                writeToFile(String.valueOf(loginEditText.getText()), this, "login");
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    protected boolean checkFields(){
        if(loginEditText.length() == 0 && EmailEditText.length() == 0) {
            loginEditText.setError("Введите логин");
            EmailEditText.setError("Введите email");
            return false;
        }else if(loginEditText.length() == 0){
            loginEditText.setError("Введите логин");
            return  false;
        }
        else if(EmailEditText.length() == 0){
            EmailEditText.setError("Введите email");
            return false;
        }
        if(PasswordEditText.length() == 0){
            PasswordEditText.setError("Введите пароль");
             return false;
        }
         if(RepeatPasswordEditText.length() == 0){
            PasswordEditText.setError("Повторите пароль");
             return false;
        }
          if(PasswordEditText.length() < 0){
            PasswordEditText.setError("Введиnt пароль длинее 8 символов");
             return false;
        }
          if(!String.valueOf(RepeatPasswordEditText.getText()).equals(String.valueOf(PasswordEditText.getText()))){
            PasswordEditText.setError("Пароли не совпадают");
            RepeatPasswordEditText.setError("Пароли не совпадают");
             return false;
        }
        return true;
    }
    private void writeToFile(String data, Context context, String fileName){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName + "txt", MODE_PRIVATE));
            outputStreamWriter.write(data);
             outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed" + e);
        }

    }
}