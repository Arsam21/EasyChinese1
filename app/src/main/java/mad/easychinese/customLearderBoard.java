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
public class customLearderBoard extends BaseAdapter {

    private Context c;
    int[] images;
    String[] name;
    int[] score;



    public customLearderBoard(Context c, int[] images, String[] name, int[] score){
        this.c=c;
        this.images=images;
        this.name=name;
        this.score=score;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return name[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.leaderboard_item, null);
        }

        ImageView img1 = (ImageView)view.findViewById(R.id.imageViewNumber);
        TextView name1 = (TextView)view.findViewById(R.id.textViewName);
        TextView score1 = (TextView)view.findViewById(R.id.textViewScore);

        img1.setImageResource(images[i]);
        name1.setText(name[i]);
        score1.setText(score[i]+"");

        return view;
    }
}
