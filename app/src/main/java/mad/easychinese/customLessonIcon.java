package mad.easychinese;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MSI on 2016-07-30.
 */
public class customLessonIcon extends BaseAdapter {

    private Context c;
    int[] images;
    String[] text1;



    public customLessonIcon(Context c, int[] images, String[] text1){
        this.c=c;
        this.images=images;
        this.text1=text1;

    }

    @Override
    public int getCount() {
        return text1.length;
    }

    @Override
    public Object getItem(int i) {
        return text1[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lesson_icon, null);
        }

        ImageView img = (ImageView)view.findViewById(R.id.imageView4Lesson);
        TextView textView1 = (TextView)view.findViewById(R.id.textViewLesson);

        img.setImageResource(images[i]);
        textView1.setText(text1[i]);

        return view;
    }
}
