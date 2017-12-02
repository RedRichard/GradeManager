package clases;

import android.app.Activity;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;

import com.example.aaron.calificaciones.R;

import java.util.ArrayList;

/**
 * Created by gomri on 12/2/2017.
 */

public class AdapterMateria extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Materia> items;

    public AdapterMateria (Activity activity, ArrayList<Materia> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Materia> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.display_materia, null);
        }

        Materia dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.name);
        title.setText(dir.getName());

        TextView description = (TextView) v.findViewById(R.id.prom);
        description.setText(Float.toString(dir.getProm()));

        return v;
    }
}
