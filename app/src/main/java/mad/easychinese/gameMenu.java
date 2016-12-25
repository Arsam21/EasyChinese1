package mad.easychinese;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import mad.easychinese.database.leaderboardApater;
import mad.easychinese.database.leaderboardRecord;
import mad.easychinese.database.learderboardDataSource;

public class gameMenu extends Activity {

    Button buttonStartGame;
    ListView listViewLearderBoard;
    int image[] ={R.drawable.strokes1,R.drawable.strokes1,R.drawable.strokes1,R.drawable.strokes1,R.drawable.strokes1} ;
    String[] name = {"HANZHE","b","c","d","e"};
    int[] score = {600,2,3,4,5};
    TextView textViewInstruction;
    learderboardDataSource learderboardDataSource;
    leaderboardRecord leaderboardRecord = new leaderboardRecord();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        buttonStartGame = (Button)findViewById(R.id.buttonStartGame);
        listViewLearderBoard = (ListView)findViewById(R.id.listViewLearderBoard);

        textViewInstruction = (TextView)findViewById(R.id.textViewInstruction);
        textViewInstruction.setMovementMethod(new ScrollingMovementMethod());


        refresh();
    }
    public void onResume(){
        super.onResume();
        refresh();
    }
    public void start(View v){
       startActivity(new Intent(gameMenu.this, game.class));


    }
    public void refresh(){
        learderboardDataSource = new learderboardDataSource(this);

        learderboardDataSource.open();
        //learderboardDataSource.delete();
        final List<leaderboardRecord> values = learderboardDataSource.getBoard();
        leaderboardApater adapter = new leaderboardApater(this,
                R.layout.leaderboard_item, values);
//Link adapter to ListView
        listViewLearderBoard.setAdapter(adapter);
    }

}
