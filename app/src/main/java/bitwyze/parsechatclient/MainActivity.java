package bitwyze.parsechatclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ParseUser currentUser;
    private EditText chatText;
    private ArrayAdapter arrayAdapter;
    private ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayAdapter = new ArrayAdapter(this,messages);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chatText = (EditText)findViewById(R.id.chatText);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myAppId")
                .server("https://parsechatclient.herokuapp.com/parse/").build());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        doAnonLogin();
        queryChats();
    }

    private void doAnonLogin() {
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    System.out.println("Successful login");
                    currentUser = ParseUser.getCurrentUser();

                } else {
                    System.out.println("Failure on login" + e.toString());

                }
            }
        });
    }

    private void queryChats() {
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
      //  query.whereEqualTo("userName","Scott Richards");
        query.findInBackground(new FindCallback<Message>() {
            public void done(List<Message> messageList, ParseException e) {
                if (e == null) {
                    //System.out.println("success retrieved objects " + scoreList.size());
                    Log.d("score", "Retrieved " + messageList.size() + " scores");
                    arrayAdapter.clear();
                    arrayAdapter.addAll(messageList);
                    arrayAdapter.notifyDataSetChanged();
//                    for (int i=0;i<scoreList.size();i++) {
//                        Message message = new Message(scoreList.in);
//                        ParseObject parseObject = scoreList[i];
//                        message.setMessage(scoreList[i].);
//                    }
                } else {
                    System.out.println("failure");
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
    public void onSendClick(View v) {
        ParseObject parseObject = new ParseObject("Message");
        parseObject.put("userName", "Scott Richards");
        String chatMessage = chatText.getText().toString();
        parseObject.put("message",chatMessage);
        parseObject.saveInBackground();
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
