package mad.easychinese;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.HashMap;
import java.util.Locale;

import mad.easychinese.database.lessonDataSource;
import mad.easychinese.database.lessonRecord;


public class animalContent extends Activity implements  TextToSpeech.OnInitListener {

    TextView textViewName, textViewDescription;
    int position = 0;
    String []animal;
    Button buttonNext, buttonPrevious, buttonImage, buttonDescription;
    ImageView imgViewImage;
    TextToSpeech tts;
    final int CHECK_CODE = 1;
    VideoView imgViewPinyin;
    String textSound;
    String[] id= {"熊","鸟","猫","牛","鹿","狗","驴","鸭","象","鱼"
    ,"鹅","马","豹","狮","猴","鼠","猪","兔","羊","虎"};
    String[] video = {"android.resource://mad.easychinese/"+R.raw.bear_pinyin,
            "android.resource://mad.easychinese/"+R.raw.bird_pinyin,
            "android.resource://mad.easychinese/"+R.raw.cat_pinyin,
            "android.resource://mad.easychinese/"+R.raw.cow_pinyin,
            "android.resource://mad.easychinese/"+R.raw.deer_pinyin,
            "android.resource://mad.easychinese/"+R.raw.dog_pinyin,
            "android.resource://mad.easychinese/"+R.raw.donkey_pinyin,
            "android.resource://mad.easychinese/"+R.raw.duck_pinyin,
            "android.resource://mad.easychinese/"+R.raw.elephant_pinyin,
            "android.resource://mad.easychinese/"+R.raw.fish_pinyin,
            "android.resource://mad.easychinese/"+R.raw.goose_pinyin,
            "android.resource://mad.easychinese/"+R.raw.hourse_pinyin,
            "android.resource://mad.easychinese/"+R.raw.jaguar_pinyin,
            "android.resource://mad.easychinese/"+R.raw.lion_pinyin,
            "android.resource://mad.easychinese/"+R.raw.monkey_pinyin,
            "android.resource://mad.easychinese/"+R.raw.mouse_pinyin,
            "android.resource://mad.easychinese/"+R.raw.pig_pinyin,
            "android.resource://mad.easychinese/"+R.raw.rabbit_pinyin,
            "android.resource://mad.easychinese/"+R.raw.sheep_pinyin,
            "android.resource://mad.easychinese/"+R.raw.tiger_pinyin};

    lessonRecord lessonRecord = new lessonRecord();
    lessonDataSource lessonDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_content);
        checkTTS();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        animal = getResources().getStringArray(R.array.animal);

        textViewName = (TextView)findViewById(R.id.textViewName);

        buttonNext = (Button)findViewById(R.id.imageButtonNext);
        buttonPrevious = (Button)findViewById(R.id.imageButtonPrevious);
        buttonImage = (Button)findViewById(R.id.buttonImage);
        buttonDescription = (Button)findViewById(R.id.buttonDescription);
        textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewDescription.setMovementMethod(new ScrollingMovementMethod());
        imgViewImage = (ImageView)findViewById(R.id.imageViewImage);
        imgViewPinyin = (VideoView)findViewById(R.id.imageViewPinyin);

        imgViewPinyin.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        imgViewImage.setVisibility(View.VISIBLE);
        textViewDescription.setVisibility(View.GONE);
        refresh();
    }

    public void playvideo(String uripath2){
        Uri uri2 = Uri.parse(uripath2);
        imgViewPinyin.setVideoURI(uri2);
        imgViewPinyin.requestFocus();
        imgViewPinyin.start();
    }
    public void next(View v){
        if(position>=0&&position<id.length-1){
            position++;
            refresh();
        }

    }
    public void previous(View v){
        if(position>0&&position<id.length){
            position--;
            refresh();
        }

    }
    public void refresh(){

        textViewDescription.scrollTo(0, 0);
            if(position==0){
                buttonPrevious.setVisibility(View.GONE);
            }else buttonPrevious.setVisibility(View.VISIBLE);

            if(position==animal.length-1){
                buttonNext.setVisibility(View.GONE);
            }else buttonNext.setVisibility(View.VISIBLE);

            playvideo(video[position]);

            textViewName.setText(animal[position]);

            lessonDataSource = new lessonDataSource(this);
            lessonDataSource.open();

            imgViewImage.setImageResource(lessonDataSource.getlessonAnimal().get(position).getImage());
            textViewDescription.setText(lessonDataSource.getlessonAnimal().get(position).getDes());




    }
    public void imageClicked(View v){
        imgViewImage.setVisibility(View.VISIBLE);
        textViewDescription.setVisibility(View.GONE);
    }
    public void desClicked(View v){
        imgViewImage.setVisibility(View.GONE);
        textViewDescription.setVisibility(View.VISIBLE);
    }
    public void playSound(View v){
        play();
    }
    public void play(){
        textSound = id[position];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            tts.speak(textSound, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            HashMap<String, String> hash = new HashMap<String,String>();
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
            tts.speak(textSound, TextToSpeech.QUEUE_FLUSH, hash);
        }
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
