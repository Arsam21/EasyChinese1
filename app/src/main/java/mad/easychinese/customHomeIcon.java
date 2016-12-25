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
public class customHomeIcon extends BaseAdapter {

    private Context c;
    int[] images;
    String[] text1;



    public customHomeIcon(Context c, int[] images, String[] text1){
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
            view = inflater.inflate(R.layout.home_icon, null);
        }

        ImageView img = (ImageView)view.findViewById(R.id.strokeIcon);
        TextView textView1 = (TextView)view.findViewById(R.id.strokeText1);

        img.setImageResource(images[i]);
      //  textView1.setText(text1[i]);
        if(i==0) textView1.setText(R.string.lesson);
        else if(i==1) textView1.setText(R.string.stroke);
        else if(i==2) textView1.setText(R.string.quiz);
        else if(i==3) textView1.setText(R.string.reminders);
        return view;
    }
}
