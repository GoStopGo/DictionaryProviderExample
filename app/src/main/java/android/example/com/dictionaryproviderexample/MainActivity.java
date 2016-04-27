/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.example.com.dictionaryproviderexample;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.provider.UserDictionary.Words;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * This is the central activity for the Provider Dictionary Example App. The purpose of this app is
 * to show an example of accessing the {@link Words} list via its' Content Provider.
 */
public class MainActivity extends ActionBarActivity {

    private static final String[] COLUMNS_TO_BE_FOUND = new String[]{
        Words.WORD,
        Words.FREQUENCY
    };

    private static final int[] LAYOUT_ITEM_TO_FILL = new int[]{
            android.R.id.text1,
            android.R.id.text2
    };

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the TextView which will be populated with the Dictionary ContentProvider data.
        TextView dictTextView = (TextView) findViewById(R.id.dictionary_text_view);
        ListView dictListView = (ListView) findViewById(R.id.dictionary_list_view);

        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();

        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,COLUMNS_TO_BE_FOUND,LAYOUT_ITEM_TO_FILL,0);
        dictListView.setAdapter(adapter);

        /*try {
            dictTextView.setText("The UserDictionary contains " + cursor.getCount() + " words\n");
            dictTextView.append("COLUMNS: " + Words._ID + " - " + Words.FREQUENCY + " - " + Words.WORD);

            //Get the index of the columns using UserDictionary.Words constants,
            //which contain the headers of the columns.
            int idColumn = cursor.getColumnIndex(UserDictionary.Words._ID);
            int frequencyColumn = cursor.getColumnIndex(Words.FREQUENCY);
            int wordColumn = cursor.getColumnIndex(Words.WORD);

            //Iterates through all returned rows in the cursor
            while (cursor.moveToNext()){
                //Use that index to extract the String value of the word
                //at the current row the cursor is on
                int id = cursor.getInt(idColumn);
                int frequency = cursor.getInt(frequencyColumn);
                String word = cursor.getString(wordColumn);

                dictTextView.append(("\n" + id + " - " + frequency + " - " + word));
            }
        }finally {
            //Always close your cursor
            cursor.close();
        }*/

        Log.e("ERROR", Words.CONTENT_URI.toString());
    }
}
