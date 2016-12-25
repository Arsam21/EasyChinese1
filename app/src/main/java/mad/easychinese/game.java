package mad.easychinese;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class game extends Activity {

    ArrayList<Integer> questionCollection = new ArrayList<Integer>();

    TextView textView, textViewTotalMark, textViewQuestion,textViewQuestionLeft;
    long timer = 0;
    GridView gridViewLife;
    int life = 5;
    int[] image = {R.drawable.life,R.drawable.life,R.drawable.life,R.drawable.life,R.drawable.life};
    Button answer1,answer2,answer3,answer4,answer5;
    ImageView imageView;
    int randomNum;
    int[] QuestionImage = { R.drawable.animal_dog,
            R.drawable.cat,
            R.drawable.bird,
            R.drawable.pig,
            R.drawable.goat,
            R.drawable.bear,
            R.drawable.deer,
            R.drawable.cow,
            R.drawable.duck,
            R.drawable.donkey,
            R.drawable.elephant,
            R.drawable.tiger,
            R.drawable.rabbit,
            R.drawable.fish,
            R.drawable.goose,
            R.drawable.jaguar,
            R.drawable.lion,
            R.drawable.monkey,

            R.drawable.apricot,
            R.drawable.banana,
            R.drawable.coconut,
            R.drawable.date,
            R.drawable.mango,
            R.drawable.melon,
            R.drawable.orange,
            R.drawable.peach,
            R.drawable.pear,
            R.drawable.persimmon,
            R.drawable.plum,
            R.drawable.tangerine,

            R.drawable.book,
            R.drawable.chair,
            R.drawable.desk,
            R.drawable.knife,
            R.drawable.light,
            R.drawable.paper,
            R.drawable.pen,
            R.drawable.pin,
            R.drawable.ring,
            R.drawable.ruler,

            R.drawable.bamboo,
            R.drawable.feng,
            R.drawable.grass,
            R.drawable.juhua,
            R.drawable.lanhua,
            R.drawable.leaf,
            R.drawable.lily,
            R.drawable.mei,
            R.drawable.sunflower,
            R.drawable.tree};

    String[][] Question = {{"这是什么动物？","狗","猫","狗","猪","鸟","羊"},
            {"这是什么动物？","猫","猫","狗","猪","鸟","羊"},
            { "这是什么动物？","鸟","猫","狗","猪","鸟","羊"},
            { "这是什么动物？","猪","猫","狗","猪","鸟","羊"},
            { "这是什么动物？","羊","猫","狗","猪","鸟","羊"},
            { "这是什么动物？","熊","猫","鱼","猪","豹","熊"},
            { "这是什么动物？","鹿","猫","狗","鹿","鸟","羊"},
            { "这是什么动物？","牛","牛","狗","猪","鸟","猪"},
            { "这是什么动物？","鸭","猫","鸭","驴","象","羊"},
            { "这是什么动物？","驴","驴","狗","鸭","鸟","虎"},
            { "这是什么动物？","象","驴","狗","猪","鸟","象"},
            { "这是什么动物？","虎","猫","虎","猪","鸟","鱼"},
            { "这是什么动物？","兔","猫","狗","兔","鱼","羊"},
            { "这是什么动物？","鱼","猫","鱼","猪","鸟","兔"},
            { "这是什么动物？","鹅","豹","鹅","狮","鸟","羊"},
            { "这是什么动物？","豹","猫","鹅","猪","豹","羊"},
            { "这是什么动物？","狮","猴","狮","兔","鸟","猴"},
            { "这是什么动物？","猴","猴","狗","猪","鸟","狮"},

            { "这是什么水果？","杏","杏","枣","桃","柿","李"},
            { "这是什么水果？","蕉","芒","桃","蕉","杏","柑"},
            { "这是什么水果？","椰","枣","椰","瓜","柿","梨"},
            { "这是什么水果？","枣","芒","柑","李","枣","桃"},
            { "这是什么水果？","芒","杏","蕉","枣","瓜","芒"},
            { "这是什么水果？","瓜","橙","瓜","桃","柿","枣"},
            { "这是什么水果？","橙","橙","桃","瓜","杏","椰"},
            { "这是什么水果？","桃","桃","李","梨","芒","蕉"},
            { "这是什么水果？","梨","杏","橙","猪","梨","李"},
            { "这是什么水果？","柿","柿","蕉","芒","枣","蕉"},
            { "这是什么水果？","李","瓜","李","芒","椰","柑"},
            { "这是什么水果？","柑","芒","橙","柑","椰","芒"},

            {"这是什么物品？","书","书","椅","桌","灯","纸"},
            {"这是什么物品？","椅","桌","椅","书","刀","尺"},
            {"这是什么物品？","桌","灯","刀","桌","钉","铃"},
            {"这是什么物品？","刀","书","灯","刀","纸","铃"},
            {"这是什么物品？","灯","纸","书","桌","笔","灯"},
            {"这是什么物品？","纸","纸","笔","灯","桌","椅"},
            {"这是什么物品？","笔","纸","笔","柑","桌","芒"},
            {"这是什么物品？","钉","钉","铃","笔","桌","椅"},
            {"这是什么物品？","铃","桌","铃","笔","铃","钉"},
            {"这是什么物品？","尺","尺","笔","铃","桌","灯"},

            {"这是什么植物？","竹","竹","枫","草","菊","兰"},
            {"这是什么植物？","枫","草","枫","竹","兰","莲"},
            {"这是什么植物？","草","枫","竹","草","叶","梅"},
            {"这是什么植物？","菊","草","枫","兰","菊","梅"},
            {"这是什么植物？","兰","竹","枫","草","菊","兰"},
            {"这是什么植物？","叶","叶","莲","梅","葵","树"},
            {"这是什么植物？","莲","叶","莲","梅","菊","草"},
            {"这是什么植物？","梅","竹","叶","兰","梅","树"},
            {"这是什么植物？","葵","葵","莲","梅","菊","枫"},
            {"这是什么植物？","树","叶","菊","草","竹","树"}
    };
    int totalQuestion = 15;

    long totalMark = 0;
    int questionCount =1 , currectQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        textView = (TextView)findViewById(R.id.textView3);


        gridViewLife = (GridView)findViewById(R.id.gridViewLife);
        customLife adapter = new customLife(this,image, life);
        gridViewLife.setAdapter(adapter);



        textViewTotalMark = (TextView)findViewById(R.id.textViewTotalMark);
        textViewTotalMark.setText(totalMark+"");
        timer();

        answer1 = (Button)findViewById(R.id.answer1);
        answer2 = (Button)findViewById(R.id.answer2);
        answer3 = (Button)findViewById(R.id.answer3);
        answer4 = (Button)findViewById(R.id.answer4);
        answer5 = (Button)findViewById(R.id.answer5);
        textViewQuestionLeft = (TextView)findViewById(R.id.textViewQuestionLeft);
        textViewQuestion = (TextView)findViewById(R.id.textviewQuestion);
        textViewQuestion.setMovementMethod(new ScrollingMovementMethod());
        textViewQuestionLeft.setText(questionCount+"/"+totalQuestion);
        imageView=(ImageView)findViewById(R.id.imagePIC);

        ArrayQuestion();
        setQuestion();

    }

    public void setQuestion(){
        currectQuestion = questionCollection.get(questionCount-1);
        imageView.setImageResource(QuestionImage[currectQuestion]);
        textViewQuestion.setText(Question[currectQuestion][0]);

        answer1.setText(Question[currectQuestion][2]);
        answer2.setText(Question[currectQuestion][3]);
        answer3.setText(Question[currectQuestion][4]);
        answer4.setText(Question[currectQuestion][5]);
        answer5.setText(Question[currectQuestion][6]);
    }
    public void ArrayQuestion(){
        for(int i = 0; i < Question.length; i++) questionCollection.add(i);
        Collections.shuffle(questionCollection);
    }

    public void checkAnswer(View v){
        Button b = (Button) v;
        String buttonText = b.getText().toString();

        if (Question[currectQuestion][1].equals(buttonText)) {
            if(checkLastQuestion()){
                totalMark = totalMark + timer;
                textViewTotalMark.setText(totalMark+"");
                endThis();
            }else correct();
        } else wrong();
    }
    public void endThis(){
        Intent intent = new Intent(game.this, result.class);
        intent.putExtra("score", totalMark+"");
        startActivity(intent);
        finish();
    }

    public void wrong(){
        life--;
        if(life<0){
            endThis();
        }
        customLife adapter = new customLife(this,image, life);
        gridViewLife.setAdapter(adapter);
    }
    public void correct(){
        totalMark = totalMark + timer;
        textViewTotalMark.setText(totalMark+"");
        nextQuestion();
    }
    public void nextQuestion(){
        questionCount++;
        textViewQuestionLeft.setText(questionCount+"/"+totalQuestion);
        timer();
        setQuestion();
    }
    public boolean checkLastQuestion(){
        if(questionCount==totalQuestion){
            return true;
        }else return false;
    }

    public void timer(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = millisUntilFinished / 1000;
         //       textView.setText("seconds remaining: " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                //   Toast.makeText(getApplicationContext(), "next question", Toast.LENGTH_LONG).show();
                //    nextQuestion();
            }
        }.start();
    }
}
