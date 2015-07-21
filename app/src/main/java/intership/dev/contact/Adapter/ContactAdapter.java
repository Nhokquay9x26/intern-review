package intership.dev.contact.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import intership.dev.contact.Model.Contact;
import intership.dev.contact.R;

/**
 * Created by nhokquay9x26 on 7/21/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int layoutID;
    ArrayList<Contact> mContacts;
    ContactHolder holder;

    public ContactAdapter(Activity context, int layoutID, ArrayList<Contact> mContacts) {
        super(context, layoutID, mContacts);
        this.context = context;
        this.layoutID = layoutID;
        this.mContacts = mContacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_list_contact, parent, false);
            holder = new ContactHolder();

            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        } else {
            holder = (ContactHolder) convertView.getTag();
        }

        holder.imgAvatar.setImageResource(mContacts.get(position).getmImgContacts());
        holder.tvName.setText(mContacts.get(position).getmNameContacts());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return convertView;
    }

    private static class ContactHolder {
        ImageView imgAvatar, imgEdit, imgDelete;
        TextView tvName;
    }
}
