package mad.easychinese;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class home extends AppCompatActivity {

    ListView gridView;
    int[] images = {R.drawable.home_icon,R.drawable.home_icon,R.drawable.home_icon,R.drawable.home_icon};

    String[] text ={"1","1","1","1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);


        gridView = (ListView)findViewById(R.id.gridViewHome);
        customHomeIcon adapter = new customHomeIcon(this ,images,text);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(i==0){
            startActivity(new Intent(home.this, lesson.class));
        }
        else if(i==1){
            startActivity(new Intent(home.this, stroke.class));
        }
        else if(i==2){
            startActivity(new Intent(home.this, gameMenu.class));
        }
        else if(i == 3){
            startActivity(new Intent(home.this, reminder.class));

        }
            }
        });

    }
    public void info(View v){
        FragmentManager fm = getFragmentManager();
        MyDialogFragment dialogFragment = new MyDialogFragment ();
        dialogFragment.show(fm, "");
    }
    public void onBackPressed() {
        finishAffinity();
    }
}
