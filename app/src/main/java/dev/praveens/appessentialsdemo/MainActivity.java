/*
 * Copyright (c) 2019. Praveen Kumar S
 */

package dev.praveens.appessentialsdemo;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dev.praveens.appessentials.EssDialogs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View v) {
        if (v.getId() == R.id.info_dialog_btn) {
            EssDialogs.infoDialog(this, "Info Dialog", "This is an information. No bells No whistles");
        } else if (v.getId() == R.id.yesno_dialog_btn) {

            EssDialogs.yesNoDialog(this, "Yes or No?", "Do you understand binary?", "1", "0", new EssDialogs.YesNoCallback() {
                @Override
                public void onResult(boolean yes) {
                    Toast.makeText(MainActivity.this, yes ? "Congratulations" : "Oops!", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (v.getId() == R.id.singleChoice_dialog_btn) {

            EssDialogs.singleChoiceDialog(this,  "Your favourite ice cream?", new String[]{"Chocolate", "Vanilla", "Butterscotch", "Pistachio"}, new EssDialogs.SingleItemCallback() {
                @Override
                public void onItemSelected(int pos, String entry) {
                    Toast.makeText(MainActivity.this, entry + " is yummy too!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCanceled() {
                    Toast.makeText(MainActivity.this, "You got no taste", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (v.getId() == R.id.multiChoice_dialog_btn) {

            EssDialogs.multipleChoiceDialog(this, "Multiple choices", new String[]{"Java", "Java", "Java", "Kotlin"}, new boolean[]{true, true, true, false}, new EssDialogs.MultipleItemCallback() {
                @Override
                public void onSelected(boolean[] checked) {
                    Toast.makeText(MainActivity.this, "Kotlin " + (checked[3] ? "got checked" : "Nope"), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCanceled() {
                    Toast.makeText(MainActivity.this, "Too many choices are overwhelming sometimes", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (v.getId() == R.id.text_dialog_btn) {

            EssDialogs.inputTextDialog(this, "Text but number", "Enter number between 1 and 100", false, InputType.TYPE_CLASS_NUMBER, new EssDialogs.TextEnteredCallback() {
                @Override
                public void onTextEntered(String text) {
                    Toast.makeText(MainActivity.this, text + "!!!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCanceled() {

                }

                @Override
                public boolean validate(String text) {
                    int n = Integer.parseInt(text);
                    if (n >= 1 && n <= 100)
                        return true;
                    Toast.makeText(MainActivity.this, "Guess you know numbers. 1 to 100", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

        }
    }
}
