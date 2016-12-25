package mad.easychinese;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import mad.easychinese.database.strokeDataSource;
import mad.easychinese.database.strokeRecord;
import mad.easychinese.database.strokeRecordAdapter;

public class stroke extends Activity implements AdapterView.OnItemClickListener , TextToSpeech.OnInitListener {

    GridView gridView;
    strokeRecord strokeRecord = new strokeRecord();
    strokeDataSource strokeDataSource;
    TextToSpeech tts;
    final int CHECK_CODE = 1;
    String textSound;
    int[] images = {R.drawable.strokes1,R.drawable.strokes2,R.drawable.strokes3,R.drawable.strokes4,R.drawable.strokes5
            ,R.drawable.strokes6,R.drawable.strokes7,R.drawable.strokes8,R.drawable.strokes9,R.drawable.strokes10
            ,R.drawable.strokes11,R.drawable.strokes12,R.drawable.strokes13,R.drawable.strokes14,R.drawable.strokes15
            ,R.drawable.strokes16,R.drawable.strokes17,R.drawable.strokes18,R.drawable.strokes19,R.drawable.strokes20
            ,R.drawable.strokes21,R.drawable.strokes22,R.drawable.strokes23,R.drawable.strokes24,R.drawable.strokes25
            ,R.drawable.strokes26,R.drawable.strokes27,R.drawable.strokes28,R.drawable.strokes29,R.drawable.strokes30};
    String[] text1 = {"横","竖","撇","点", "捺","提","横折","横撇","横钩","横折钩"
            ,"横折提","横折弯","弯钩","横折斜钩","横折弯钩","横撇弯钩","横折折撇","横折折折钩","撇提","竖提"
            ,"竖折","竖钩","竖弯","竖弯钩","竖折撇","卧钩","竖折弯钩","撇点","撇折","斜钩"};
    String[] text2 = {"héng","shù","piě","diǎn","nà","tí","héng zhé","héng piě","héng gōu","héng zhé gōu"
            ,"héng zhé tí","héng zhé wān","wān gōu","héng zhé xié gōu","héng zhé wān gōu","héng piě wān gōu","héng zhé zhé piě","héng zhé zhé zhé gōu","piě tí","shù tí"
            ,"shù zhé","shù gōu","shù wān","shù wān gōu","shù zhé piě","wò gōu","shù zhé wān gōu","piě diǎn","piě zhé","xié gōu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroke);
        checkTTS();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        strokeDataSource = new strokeDataSource(this);

        strokeDataSource.open();
        strokeDataSource.delete();
        // strokeDataSource.dropTable();


       for(int i =0;i<text1.length;i++){
            strokeRecord.setImage(images[i]);
            strokeRecord.setText(text1[i]);
            strokeRecord.setPinyin(text2[i]);

            strokeDataSource.insertStroke(strokeRecord);
        }

        gridView = (GridView)findViewById(R.id.gridViewStroke);




    }
    @Override
    protected void onResume() {
        updateList();
        super.onResume();
    }
    private void updateList() {
//Retrieve records from SQLite
        strokeDataSource = new strokeDataSource(this);

        strokeDataSource.open();
        final List<strokeRecord> values = strokeDataSource.getAllStroke();
        strokeRecordAdapter adapter = new strokeRecordAdapter(this,
                R.layout.stroke_item, values);
//Link adapter to ListView
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

    }
    protected void onPause(){
        strokeDataSource.close();
        super.onPause();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            textSound = text1[i];



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            tts.speak(textSound, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            HashMap<String, String> hash = new HashMap<String,String>();
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
            tts.speak(textSound, TextToSpeech.QUEUE_FLUSH, hash);
        }


         //   view.setBackgroundColor(Color.GREEN);



         //   adapterView.getChildAt(previousSelected).setBackgroundColor(Color.TRANSPARENT);





    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
            tts = new TextToSpeech(this, this);
        }else{
            Intent install = new Intent();
            install.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(install);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
    }



    private void checkTTS(){
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, CHECK_CODE);

    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            tts.setLanguage(Locale.CHINA);
        }else{
            Log.e("TTS", "Initialization Failed!");
        }
    }

}
