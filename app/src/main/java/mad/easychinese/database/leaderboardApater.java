package mad.easychinese.database;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mad.easychinese.R;

/**
 * Created by MSI on 2016-08-20.
 */
public class leaderboardApater extends ArrayAdapter<leaderboardRecord> {
    public leaderboardApater(Activity context, int resource, List<leaderboardRecord>
            list) {
        super(context, resource, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        leaderboardRecord leaderboardRecord = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.leaderboard_item,
                            parent,
                            false);
        }
        ImageView img1 = (ImageView)convertView.findViewById(R.id.imageViewNumber);
        TextView name1 = (TextView)convertView.findViewById(R.id.textViewName);
        TextView score1 = (TextView)convertView.findViewById(R.id.textViewScore);

        if(position==0){
            img1.setImageResource(R.drawable.one);
        }else if(position==1){
            img1.setImageResource(R.drawable.two);
        }else if(position==2){
            img1.setImageResource(R.drawable.three);
        }else if(position==3){
            img1.setImageResource(R.drawable.four);
        }else if(position==4){
            img1.setImageResource(R.drawable.five);
        }

        name1.setText(leaderboardRecord.getname());
        score1.setText(leaderboardRecord.getscore()+"");
        return convertView;
    }
}
