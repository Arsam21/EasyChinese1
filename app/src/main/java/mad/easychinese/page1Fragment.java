package mad.easychinese;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class page1Fragment extends Fragment {

    GridView gridView;
    int[] images = {R.drawable.animal,R.drawable.fruit,R.drawable.school};
    String[] text;

    public page1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_page1, container, false);
        text = getResources().getStringArray(R.array.page1);

        gridView = (GridView)rootView.findViewById(R.id.gridViewLesson);
        customLessonIcon adapter = new customLessonIcon(rootView.getContext() ,images,text);
        gridView.setAdapter(adapter);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0){
                    startActivity(new Intent(getActivity(), animalContent.class));
                }
                else if(i==1){
                    startActivity(new Intent(getActivity(), fruitContent.class));
                }else if(i==2){
                    startActivity(new Intent(getActivity(), schoolContent.class));
                }

            }
        });
        return rootView;
    }

}
