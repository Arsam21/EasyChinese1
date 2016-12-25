package mad.easychinese;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.widget.VideoView;

import mad.easychinese.database.lessonDataSource;
import mad.easychinese.database.lessonRecord;
import mad.easychinese.database.strokeDataSource;
import mad.easychinese.database.strokeRecord;

public class Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    int[] image = {R.drawable.bear,R.drawable.bird,R.drawable.cat,R.drawable.cow,R.drawable.deer,
            R.drawable.dog,R.drawable.donkey,R.drawable.duck,R.drawable.elephant,R.drawable.fish,
            R.drawable.goose,R.drawable.horse,R.drawable.jaguar,R.drawable.lion,R.drawable.monkey,
            R.drawable.mouse,R.drawable.pig,R.drawable.rabbit,R.drawable.sheep,R.drawable.tiger,

            R.drawable.apricot,R.drawable.banana,R.drawable.coconut,R.drawable.date,R.drawable.mango,
            R.drawable.melon,R.drawable.orange,R.drawable.peach,R.drawable.pear,R.drawable.persimmon,
            R.drawable.plum,R.drawable.tangerine,

            R.drawable.book,R.drawable.chair,R.drawable.desk,R.drawable.knife,R.drawable.light,
            R.drawable.paper,R.drawable.pen,R.drawable.pin,R.drawable.ring,R.drawable.ruler,

            R.drawable.bamboo,R.drawable.feng,R.drawable.grass,R.drawable.juhua,R.drawable.lanhua,
            R.drawable.leaf,R.drawable.lily,R.drawable.mei,R.drawable.sunflower,R.drawable.tree
    };
    String[] des = {"拼音：xióng\n部首：灬\n部首笔画：4画\n部外笔画：10画\n画总笔画：14画\n\n基本字义\n1、哺乳动物，体大，尾短，四肢短而粗，脚掌大，能直立行走，也能攀树，种类很多，有“棕熊”、“白熊”、“黑熊”等：狗熊（即“黑熊”）。熊掌。熊白（熊背上的脂肪，白色，珍贵食品）。熊胆（熊的胆，可入药）。熊包（喻无能的人，废物）。\n" +
            "2、方言，指斥责：挨了一顿熊。",
            "拼音：niǎo\n部首：鸟\n部首笔画：5画\n部外笔画：0画\n画总笔画：5画\n\n基本字义\n脊椎动物的一纲，温血卵生，全身有羽毛，后肢能行走，前肢变为翅，一般能飞：鸟类。候鸟。益鸟。鸟语花香。",
            "拼音：māo\n部首：犭\n部首笔画：3画\n部外笔画：8画\n画总笔画：11画\n\n基本字义\n1、哺乳动物，面呈圆形，脚有利爪，行动敏捷，会捉老鼠。\n" +
                    "2、方言，躲藏。",
            "拼音：niú \n部首：牛\n部首笔画：4画\n部外笔画：0画\n画总笔画：4画\n\n基本字义\n1、哺乳动物，趾端有蹄，头上长一对角，是反刍类动物，力量很大，能耕田拉车，肉和奶可食，角、皮、骨可作器物：牛刀小试（喻有很大的本领，先在小事情上施展一下）。牛黄。牛角。\n" +
                    "2、星名，二十八宿之：牛斗（指牛宿和斗宿二星）。\n" +
                    "3、喻固执或骄傲：牛气。",
            "拼音：lù \n部首：鹿\n部首笔画：11画\n部外笔画：0画\n画总笔画：11画\n\n基本字义\n1、哺乳动物，四肢细长，尾短。雄鹿头上有树枝状的角。毛多为棕褐色，有的有花斑或条纹。听觉和嗅觉都很灵敏。种类很多，常见的有梅花鹿、水鹿、白唇鹿、马鹿等。\n" +
                    "2、方形的粮仓：“市无赤米，而囷鹿空虚。”\n" +
                    "3、粗，粗劣：鹿裘。鹿布。鹿床。",
            "拼音：gou\n部首：犭\n部首笔画：3画\n部外笔画：5画\n画总笔画：8画\n\n基本字义\n哺乳动物，种类很多，听觉嗅觉都很敏锐，善于看守门户，有的可以训练成军犬、警犬",
            "拼音：lǘ  \n部首：马\n部首笔画：3画\n部外笔画：4画\n画总笔画：7画\n\n基本字义\n哺乳动物，像马，比马小，能驮东西、拉车、耕田、供人骑乘：驴骡。驴皮胶（亦称“阿胶”）。驴皮影。驴唇不对马嘴。",
            "拼音：yā \n部首：鸟\n部首笔画：5画\n部外笔画：5画\n画总笔画：10画\n\n基本字义\n鸟类的一科，嘴扁腿短，趾间有蹼，善游泳，不能高飞：鸭子。鸭蛋。鸭绒。烤鸭。填鸭。",
            "拼音：xiàng \n部首：豕\n部首笔画：7画\n部外笔画：4画\n画总笔画：11画\n\n基本字义\n1、哺乳动物，是目前地球陆地上最大的哺乳类动物，多产在印度、非洲等热带地区，门牙极长，可用于雕刻成器皿或艺术品：象牙。象牙宝塔（喻脱离群众和生活的文学家、艺术家的小天地）。\n" +
                    "2、形状，样子：形象。景象。气象。现象。想象。象征。万象更新。象声。象形。",
            "拼音：yú \n部首：鱼\n部首笔画：8画\n部外笔画：0画\n画总笔画：8画\n\n基本字义\n脊椎动物的一类，生活在水中，一般有鳞和鳍，用鳃呼吸，冷血：鱼虾。鱼虫。鱼网。鱼跃。鱼贯（像鱼游一样先后相续）。鱼雁（书信，信息）。鱼米乡。鱼尾纹。鱼目混珠。鱼质龙文（喻虚有其表）。",
            "拼音：é \n部首：鸟\n部首笔画：5画\n部外笔画：7画\n画总笔画：12画\n\n基本字义\n家禽，比鸭子大，颈长，喙扁阔，尾短，体白色或灰色，额部有肉质突起，雄的突起较大，颈长，脚大有蹼，善游水：白鹅。鹅毛。鹅卵石。鹅毛大雪。",
            "拼音：mǎ \n部首：马\n部首笔画：3画\n部外笔画：0画\n画总笔画：3画\n\n基本字义\n1、哺乳动物，颈上有鬃，尾生长毛，四肢强健，善跑，供人骑或拉东西：马匹。骏马。马到成功。马首是瞻（喻跟随别人行动）。\n" +
                    "2、大：马蜂。马勺。",
            "拼音：bào \n部首：豸\n部首笔画：7画\n部外笔画：3画\n画总笔画：10画\n\n基本字义\n哺乳动物，能上树，常捕食鹿、羊、猿猴等，毛皮可制衣、褥：豹头环眼（形容人面目威严凶狠）。未窥全豹。",
            "拼音：shī \n部首：犭\n部首笔画：3画\n部外笔画：6画\n画总笔画：9画\n\n基本字义\n哺乳动物，雄的脖子上有长鬣，多产于非洲及印度西北部（通常称“狮子”；古亦作“师子”）：狮子舞。狮子搏兔（喻对小事情也拿出全部力量，不轻视）。",
            "拼音：hóu \n部首：犭\n部首笔画：3画\n部外笔画：9画\n画总笔画：12画\n\n基本字义\n1、哺乳动物，种类很多，行动灵活，好群居：猴子。猿猴。猴戏。猴头。猴拳。猴枣（中医指猕猴内脏的结石）。\n" +
                    "2、喻机灵的人：猴儿精。",
            "拼音：shǔ \n部首：鼠\n部首笔画：13画\n部外笔画：0画\n画总笔画：13画\n\n基本字义\n1、哺乳动物的一科，门齿终生持续生长，常借啮物以磨短，繁殖迅速，种类甚多，有的能传播鼠疫等病原，并为害农林草原，盗食粮食，破坏贮藏物、建筑物等（俗称“耗子”）：老鼠。鼠胆。鼠目寸光。投鼠忌器。鼠辈。\n" +
                    "2、隐忧：鼠思。",
            "拼音：zhū \n部首：犭\n部首笔画：3画\n部外笔画：8画\n画总笔画：11画\n\n基本字义\n1、哺乳动物，肉可食，鬃可制刷，皮可制革，粪是很好的肥料：猪倌。猪场。猪圈（juàn）。猪肉。生猪。野猪。种（zhóng）猪。\n" +
                    "2、古同“潴”，水积存之处。",
            "拼音：tù \n部首：刀\n部首笔画：2画\n部外笔画：6画\n画总笔画：8画\n\n基本字义\n哺乳动物，耳长，尾短，上唇中间裂开，后肢较长，跑得快：兔子。兔脱（迅速地逃走）。兔毫笔。兔起鹘落（“鹘”，打猎用的猛禽。兔子才起来而鹘已经扑下去，喻动作敏捷。亦喻作书画或写文章下笔迅捷）。",
            "拼音：yáng \n部首：羊\n部首笔画：6画\n部外笔画：0画\n画总笔画：6画\n\n基本字义\n哺乳动物，反刍类，一般头上有一对角，品种很多：绵羊。黄羊。羚羊。羊羔。羊毫。羊肠线。羊肠小道。",
            "拼音：hǔ\n部首：虍\n部首笔画：6画\n部外笔画：2画\n画总笔画：8画\n\n基本字义\n哺乳动物，毛黄褐色，有黑色条纹，性凶猛，力大。骨和血及内脏均可入药（通称“老虎”）：虎口（a.喻危险境地；b.手上拇指和食指相交的地方）。虎穴（喻危险境地）。虎符（古代调兵的凭证，用铜铸成虎形，分两半）。虎狼（喻凶残的人）。虎头蛇尾。虎踞龙盘。龙腾虎跃。\n" +
                    "2、勇猛、威武：虎将。虎势。虎劲。虎威。虎虎。虎气。",


            "拼音：xìng \n部首：木\n部首笔画：4画\n部外笔画：3画\n画总笔画：7画\n\n基本字义\n落叶乔木，叶卵形，花白色或淡红色，果实称“杏儿”、“杏子”，酸甜，可食：杏仁儿。杏黄。",
            "拼音：jiāo \n部首：艹\n部首笔画：3画\n部外笔画：12画\n画总笔画：15画\n\n基本字义\n1、指某些有像芭蕉那样的大叶子的植物：香蕉。美人蕉。\n" +
                    "2、生麻，未沤治的麻。\n" +
                    "3、〔芭蕉〕见“芭”。\n" +
                    "4、古同“焦”，枯焦。",
            "拼音：yē \n部首：木\n部首笔画：4画\n部外笔画：8画\n画总笔画：12画\n\n基本字义\n常绿乔木，产于热带，树干很高，核果椭圆形，果肉白色多汁，可食，亦可榨油。果壳可做各种器皿，叶可盖屋、编席、制扇等；\n\n这种植物的果实，简称“椰”，如“椰油”，“椰杯”，“椰雕”，“椰蓉”，“椰胡”（用半个椰壳制成的胡琴）。",
            "拼音：zǎo\n部首：木\n部首笔画：4画\n部外笔画：4画\n画总笔画：8画\n\n基本字义\n落叶灌木或乔木，枝有刺，叶卵形，开小黄花，核果称“枣子”或“枣儿”，椭圆形，熟时红色，可食：枣红。枣泥。囫囵吞枣（喻读书等不加分析辨别地笼统接受）。",
            "拼音：máng \n部首：艹\n部首笔画：3画\n部外笔画：3画\n画总笔画：6画\n\n基本字义\n1、某些禾本科植物种子壳上的细刺：针尖对麦芒。初露锋芒。\n" +
                    "2、像芒的东西：光芒。\n" +
                    "3、多年生草本植物，叶细长有尖，叶除可作绿篱和布置庭园外，又可作造纸原料和编织草鞋，嫩叶可做牛的饲料。",
            "拼音：guā \n部首：灬\n部首笔画：5画\n部外笔画：0画\n画总笔画：5画\n\n基本字义\n蔓生植物，属葫芦科，果实可食：西瓜。冬瓜。瓜蒂。瓜分（像切瓜一样分割，如“瓜瓜天下”）。瓜葛。瓜代。瓜李（指嫌疑的境地）。瓜子儿。",
            "拼音：chéng \n部首：木\n部首笔画：4画\n部外笔画：12画\n画总笔画：16画\n\n基本字义\n1、常绿乔木或灌木，果实称“橙子”，多汁，品种很多，可食。皮可入药：橙皮。橙汁。甜橙。\n" +
                    "2、红和黄合成的颜色：橙黄。",
            "拼音：táo\n部首：木\n部首笔画：4画\n部外笔画：6画\n画总笔画：10画\n\n基本字义\n1、落叶小乔木，品种很多，果实略呈球形，表面有短绒毛，味甜，有大核，核仁可入药：桃儿。桃李（喻所教的学生）。桃李不言，下自成蹊（喻实至名归，尚事实，不尚虚名）。世外桃源（指不受外界影响的地方或幻想中的美好世界）。\n" +
                    "2、形状像桃子的：棉桃儿。\n" +
                    "3、指核桃：桃仁。桃酥。",
            "拼音：lí \n部首：木\n部首笔画：4画\n部外笔画：7画\n画总笔画：11画\n\n基本字义\n落叶乔木或灌木，果实是普通水果，品种很多：梨膏。广梨。鸭梨。",
            "拼音：shì \n部首：木\n部首笔画：4画\n部外笔画：5画\n画总笔画：9画\n\n基本字义\n落叶乔木，果实为扁圆形或圆椎形浆果，黄或橙红色，可食：柿子。柿饼。柿霜。",
            "拼音：lǐ \n部首：木\n部首笔画：4画\n部外笔画：3画\n画总笔画：7画\n\n基本字义\n落叶小乔木，果实称“李子”，熟时呈黄色或紫红色，可食：李代桃僵（原用“桃”“李”共患难来喻兄弟相爱相助，后喻互相顶替或代他人受过）。投桃报李。李下不正冠（喻要避免不必要的嫌疑）。桃李不言，下自成蹊（喻为人只要忠诚、正直一定会感动别人）。",
            "拼音：gān\n部首：木\n部首笔画：4画\n部外笔画：5画\n画总笔画：9画\n\n基本字义\n常绿灌木，果实圆形，似橘而大，赤黄色，味甜或酸甜，种类很多。树皮、叶、花、种子均可入药：柑橘（“柑”、“橘”、“柚”、“橙”等类果树或其果实）。广柑。",

            "拼音：shū\n部首：乙\n部首笔画：1画\n部外笔画：3画\n画总笔画：4画\n\n基本字义\n成本的著作：书籍。书刊。",
            "拼音：yǐ yī\n部首：木\n部首笔画：4画\n部外笔画：8画\n画总笔画：12画\n\n基本字义\n有靠背的坐具：椅子。坐椅。",
            "拼音：zhuō\n部首：木\n部首笔画：4画\n部外笔画：6画\n画总笔画：10画\n\n基本字义\n几（jī）案，可用于饮食、读书、写字：桌案。书桌。",
            "拼音：dāo\n部首：刀\n部首笔画：2画\n部外笔画：0画\n画总笔画：2画\n\n基本字义\n用来切、割、斩、削、砍、刺、铡的工具：刀子。刀片。",
            "拼音：dēng\n部首：火\n部首笔画：4画\n部外笔画：2画\n画总笔画：6画\n\n基本字义\n照明的器具：电灯。桌灯。",
            "拼音：zhǐ\n部首：纟\n部首笔画：3画\n部外笔画：4画\n画总笔画：7画\n\n基本字义\n用植物纤维制成的薄片，作为写画、印刷书报、包装等用：纸张。",
            "拼音：bǐ\n部首：毛\n部首笔画：4画\n部外笔画：6画\n画总笔画：10画\n\n基本字义\n写字、画图的工具：钢笔。铅笔。",
            "拼音：dīng\n部首：钅\n部首笔画：5画\n部外笔画：2画\n画总笔画：7画\n\n基本字义\n竹木、金属制成的呈条形的、可以打入他物的东西：钉子。铁钉。",
            "拼音：líng\n部首：钅\n部首笔画：5画\n部外笔画：5画\n画总笔画：10画\n\n基本字义\n用金属做成的响器，形式不一：电铃。按铃。",
            "拼音：chǐ chě\n部首：尸\n部首笔画：3画\n部外笔画：1画\n画总笔画：4画\n\n基本字义\n量长度的器具：竹尺。木尺。",

            "拼音：zhú\n部首：竹\n部首笔画：6画\n部外笔画：0画\n画总笔画：6画\n\n基本字义\n常绿多年生植物，春日生笋，茎有很多节，中间是空的，质地坚硬，种类很多。可制器物，又可做建筑材料：竹子。竹叶。",
            "拼音：fēng\n部首：木\n部首笔画：4画\n部外笔画：4画\n画总笔画：8画\n\n基本字义\n落叶乔木，春季开花，叶互生，通常三裂，边缘有锯齿，秋季变成红色，树脂可入药，亦称“枫香树”。",
            "拼音：cǎo\n部首：艹\n部首笔画：3画\n部外笔画：6画\n画总笔画：9画\n\n基本字义\n对高等植物中除了树木、庄稼、蔬菜以外的茎干柔软的植物的统称；广义指茎干比较柔软的植物，包括庄稼和蔬菜：青草。野草。",
            "拼音：jú\n部首：艹\n部首笔画：3画\n部外笔画：8画\n画总笔画：11画\n\n基本字义\n多年生草本植物，秋天开花，是观赏植物，有的花可以入药，亦可作饮料：菊花。赏菊。",
            "拼音：lán\n部首：丷\n部首笔画：2画\n部外笔画：3画\n画总笔画：5画\n\n基本字义\n指“兰草”和“兰花”：兰艾（“兰花”和“艾草”，喻君子和小人）。",
            "拼音：yè xié\n部首：口\n部首笔画：3画\n部外笔画：2画\n画总笔画：5画\n\n基本字义\n植物的营养器官之一：树叶。叶子。",
            "拼音：lián\n部首：艹\n部首笔画：3画\n部外笔画：7画\n画总笔画：10画\n\n基本字义\n多年生草本植物，生浅水中。叶子大而圆。花大，有粉红、白色两种。种子称“莲子”，包在倒圆锥形的花托内，合称“莲蓬”。\n地下茎肥大而长，有节，称“藕”。种子和地下茎均可食（亦称“荷”、“芙蓉”）：莲藕。",
            "拼音：méi\n部首：木\n部首笔画：4画\n部外笔画：7画\n画总笔画：11画\n\n基本字义\n落叶乔木，品种很多，性耐寒，初春开花，有白、红等颜色，分五瓣，香味很浓，果实球形，味酸：梅花。梅子。",
            "拼音：kuí\n部首：艹\n部首笔画：3画\n部外笔画：9画\n画总笔画：12画\n\n基本字义\n〔向日葵〕一年生草本植物，茎很高，开大黄花，花常朝向太阳，子可食，亦可榨油。简称“葵”，如“葵花”，“葵心”。",
            "拼音：shù\n部首：木\n部首笔画：4画\n部外笔画：5画\n画总笔画：9画\n\n基本字义\n木本植物的通称：树木。树林。",




    };
    String[] type = {"animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal","animal",
    "fruit","fruit","fruit","fruit","fruit","fruit","fruit","fruit","fruit","fruit","fruit","fruit",
            "school","school","school","school","school","school","school","school","school","school",
            "plant","plant","plant","plant","plant","plant","plant","plant","plant","plant"};
    lessonDataSource lessonDataSource;
    lessonRecord lessonRecord = new lessonRecord();

    VideoView videoViewRunning;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        videoViewRunning = (VideoView)findViewById(R.id.videoViewRunning);
        videoViewRunning.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        Uri uri2 = Uri.parse("android.resource://mad.easychinese/"+R.raw.running);
        videoViewRunning.setVideoURI(uri2);
        videoViewRunning.requestFocus();
        videoViewRunning.start();

        lessonDataSource = new lessonDataSource(this);

        lessonDataSource.open();

        lessonDataSource.delete();

        for(int i =0;i<image.length;i++){
            lessonRecord.setImage(image[i]);
            lessonRecord.setDes(des[i]);
            lessonRecord.settype(type[i]);
            lessonDataSource.insertLesson(lessonRecord);
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(Splash.this,home.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}