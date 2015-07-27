package intership.dev.contact.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import intership.dev.contact.fragment.EditFragment;
import intership.dev.contact.fragment.ListContactFragment;
import intership.dev.contact.model.Contact;
import intership.dev.contact.R;

/**
 * Created by nhokquay9x26 on 7/21/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
    private Activity context;
    private int layoutID;
    private ArrayList<Contact> mContacts;

    /**
     * This constructor used to initialize the value
     * from ContactActivity passed
     *
     * @param context   The from Activity Main
     * @param layoutID  The layout custom creat by me
     * @param mContacts The list of Contact transferred from Main
     */

    public ContactAdapter(Activity context, int layoutID, ArrayList<Contact> mContacts) {
        super(context, layoutID, mContacts);
        this.context = context;
        this.layoutID = layoutID;
        this.mContacts = mContacts;
    }

    /**
     * interface set event for button edit
     */
    public interface onEditClick {
        void onClick(View v, int pos);
    }

    public onEditClick mEditClick;

    /**
     * Set event for mEditClick
     *
     * @param editClick: event from click
     */

    public void setOnEditClick(onEditClick editClick) {
        this.mEditClick = editClick;
    }

    /**
     * function to custom layout, you must override the function
     * from ContactActivity passed
     *
     * @param position    The position of the element in the list contact
     * @param convertView using it to handel Item
     * @param parent      The list of Contact transferred from Main
     * @return View :     return the convertView
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ContactHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_list_contact, parent, false);
            holder = new ContactHolder();

            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDeleteOff);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.imgDelete.setImageResource(R.drawable.ic_delete);
            convertView.setTag(holder);
        } else {
            holder = (ContactHolder) convertView.getTag();
        }

        holder.imgAvatar.setImageResource(mContacts.get(position).getmImgContacts());
        holder.tvName.setText(mContacts.get(position).getmNameContacts());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgDelete.setImageResource(R.drawable.ic_delete_on);
                final Dialog dialog = new Dialog(getContext(), R.style.Theme_Dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_delete_contact);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView tvQuestion = (TextView) dialog.findViewById(R.id.tvQuestion);
                tvQuestion.setText(Html.fromHtml("<center>Are you sure you to delete " + "<b>" + mContacts.get(position).getmNameContacts() + "</b>" + " ?</center>"));
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContacts.remove(position);
                        notifyDataSetChanged();
                        holder.imgDelete.setImageResource(R.drawable.ic_delete);
                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.imgDelete.setImageResource(R.drawable.ic_delete);
                        dialog.dismiss();
                    }
                });
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditClick != null) {
                    mEditClick.onClick(v, position);
                }
            }
        });
        return convertView;
    }

    /**
     * Holder keep last convertview
     */
    private static class ContactHolder {
        ImageView imgAvatar, imgEdit, imgDelete;
        TextView tvName;
    }
}

