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
public class customLife extends BaseAdapter {

    private Context c;
    int[] images;
    long life;


    public customLife(Context c, int[] images, long life){
        this.c=c;
        this.images=images;
this.life=  life;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.life, null);
        }

        ImageView img = (ImageView)view.findViewById(R.id.imageViewLife);
        if(life>i){
            img.setImageResource(images[i]);
        }


        return view;
    }
}
