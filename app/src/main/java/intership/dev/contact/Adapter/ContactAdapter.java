package intership.dev.contact.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    /**
     *
     * @param context
     * @param layoutID
     * @param mContacts
     */
    public ContactAdapter(Activity context, int layoutID, ArrayList<Contact> mContacts) {
        super(context, layoutID, mContacts);
        this.context = context;
        this.layoutID = layoutID;
        this.mContacts = mContacts;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_list_contact, parent, false);
            holder = new ContactHolder();

            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDeleteOff);
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
                final Dialog dialog = new Dialog(getContext(),R.style.Theme_Dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_delete_contact);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView tvQuestion = (TextView) dialog.findViewById(R.id.tvQuestion);
                tvQuestion.setText(Html.fromHtml("<center>Are you sure you to delete " + "<b>" + mContacts.get(position).getmNameContacts() + "</b>" + " ?</center>"));
                dialog.show();

                Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContacts.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }       });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
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
