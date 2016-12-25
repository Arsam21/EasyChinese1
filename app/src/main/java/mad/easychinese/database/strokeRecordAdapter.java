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
public class strokeRecordAdapter extends ArrayAdapter<strokeRecord> {
    public strokeRecordAdapter(Activity context, int resource, List<strokeRecord>
            list) {
        super(context, resource, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        strokeRecord strokeRecord = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.stroke_item,
                            parent,
                            false);
        }
        TextView textViewName, textViewEmail;
        ImageView img = (ImageView)convertView.findViewById(R.id.imageView3);
        textViewName = (TextView)convertView.findViewById(R.id.textView);
        textViewEmail = (TextView)convertView.findViewById(R.id.textView2);

        img.setImageResource(strokeRecord.getImage());
        textViewName.setText(strokeRecord.getText());
        textViewEmail.setText(strokeRecord.getPinyin());
        return convertView;
    }
}
