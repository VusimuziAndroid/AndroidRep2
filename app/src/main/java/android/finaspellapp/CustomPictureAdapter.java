package android.finaspellapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vusi Ngwenya on 6/14/2016.
 */

public class CustomPictureAdapter extends BaseAdapter {

    private Context context;
    private final String[] name;
    private final int[]img;

    public CustomPictureAdapter(Context c,String[] name,int[] img) {

        context=c;
        this.img=img;
        this.name=name;
    }



    @Override
    public int getCount() {
    return name.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
     return 0;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        View list;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null) {

            list = new View(context);
            list = inflater.inflate(R.layout.list_single,null);

            TextView tvName = (TextView) list.findViewById(R.id.tvName);
            ImageView image = (ImageView) list.findViewById(R.id.imgIcon1);

            tvName.setText(name[position]);
            image.setImageResource(img[position]);
        }
        else{
            list=(View) convertView;
        }

        return list;

}
/*class CustomPictureBaseAdapter extends BaseAdapter {

    ArrayList picList = new ArrayList();
    LayoutInflater inflater;
    Context context;

    public CustomPictureBaseAdapter(Context context,ArrayList picList){
        this.picList=picList;
        this.context=context;


    }

    @Override
    public int getCount() {


        return picList.size();
    }

    @Override
    public Object getItem(int position) {
        return picList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       MyViewHolder myviewHolder;

       if(convertView == null){
           convertView = inflater.inflate(R.layout.list_single,parent,false);
           myviewHolder = new MyViewHolder(convertView);
           convertView.setTag(myviewHolder);

       }
        else {
           myviewHolder = (MyViewHolder) convertView.getTag();

       }

        ListData currentListData = (ListData) getItem(position);

        myviewHolder.imgPicture.setImageResource(currentListData.getImgPicture());







return convertView;

    }

    private class MyViewHolder{
        ImageView imgPicture;

        public MyViewHolder(View item){
            imgPicture = (ImageView) item.findViewById(R.id.imgIcon1);
        }
    }
}*/




//The CustomList Adapter for inflating picture on the listview
/*public class CustomPictureAdapter extends ArrayAdapter<String> {

    Context context;
    String[] name;
    String[] surname;
    int[] picture;
    LayoutInflater layoutInflater;


    public CustomPictureAdapter(Activity context, String[] name, String[] surname, int[] picture) {
        super(context,R.layout.list_single,name);
        this.context =  context;
        this.name = name;
        this.surname = surname;
        this.picture = picture;
    }

    public class ViewHolder{
        TextView name;
        TextView surname;
        ImageView img;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent)
    {

        if(convertView ==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_single,null);

        }

        final ViewHolder holder = new ViewHolder();

        holder.img = (ImageView) convertView.findViewById(R.id.imgIcons);
        holder.name = (TextView) convertView.findViewById(R.id.tvName);
        holder.surname = (TextView) convertView.findViewById(R.id.tvSurname);

        holder.img.setImageResource(picture[position]);
        holder.name.setText(name[position]);
        holder.surname.setText(surname[position]);

        return convertView;

    }*/
}
