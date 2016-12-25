package mad.easychinese;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mad.easychinese.database.leaderboardRecord;
import mad.easychinese.database.learderboardDataSource;

public class result extends Activity {

    EditText ttt;
    learderboardDataSource learderboardDataSource;
    mad.easychinese.database.leaderboardRecord leaderboardRecord = new leaderboardRecord();
    Bundle bundle;
    TextView textViewScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        bundle = getIntent().getExtras();
        bundle.getString("score");
        ttt = (EditText)findViewById(R.id.editText) ;
        textViewScore = (TextView)findViewById(R.id.textViewScore);
        textViewScore.setText(bundle.getString("score"));

       // Toast.makeText(this, bundle.getString("score"),Toast.LENGTH_LONG).show();
    }
    public void save(View v){

        learderboardDataSource = new learderboardDataSource(this);
        learderboardDataSource.open();
       // learderboardDataSource.delete();
        // strokeDataSource.dropTable();




        leaderboardRecord.setname(ttt.getText().toString());
        leaderboardRecord.setscore(Integer.parseInt(bundle.getString("score")));

        learderboardDataSource.insertLeaderBoard(leaderboardRecord);
        finish();
    }

}
